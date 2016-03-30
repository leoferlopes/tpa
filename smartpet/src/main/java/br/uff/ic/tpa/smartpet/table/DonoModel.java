/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.table;

/**
 *
 * @author Murilo
 */
import br.uff.ic.tpa.smartpet.excecao.ObjetoNaoEncontradoException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;
import br.uff.ic.tpa.smartpet.model.Dono;
import br.uff.ic.tpa.smartpet.service.DonoAppService;
import java.util.Date;

public class DonoModel extends AbstractTableModel {

    public static final int COLUNA_ID = 0;
    public static final int COLUNA_NOME = 1;
    public static final int COLUNA_TELEFONE = 2;
    public static final int COLUNA_EMAIL = 3;
    public static final int COLUNA_NASCIMENTO = 4;
    public static final int COLUNA_ENDERECO = 5;

    private DonoAppService service = new DonoAppService();
    private Map<Integer, Dono> cache;
    private int rowIndexAnterior = 0;
    private Integer qtd;

    public DonoModel() {
        this.qtd = null;
        this.cache = new HashMap<Integer, Dono>(170);
    }

    public String getColumnName(int c) {
        if (c == COLUNA_ID) {
            return "ID";
        }
        if (c == COLUNA_NASCIMENTO) {
            return "Nascimento";
        }
        if (c == COLUNA_NOME) {
            return "Nome";
        }
        if (c == COLUNA_ENDERECO) {
            return "Endereço";
        }
        if (c == COLUNA_TELEFONE) {
            return "Telefone";
        }
        if (c == COLUNA_EMAIL) {
            return "Email";
        }
        return null;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        if (qtd == null) {
            List<Dono> lista = service.recuperaDonos();
            System.out.println("TESTE");
            qtd = lista.size();
        };

        return qtd;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (!cache.containsKey(rowIndex)) {
            if (cache.size() > 80) {
                cache.clear();
                if (rowIndex >= rowIndexAnterior) {
                    // O cache é maior do que 80 e estamos navegando para baixo

                    // Se, por exemplo, rowindex = 120 queremos recuperar 2 páginas:
                    // de 101 a 120 (página atual)
                    // de 121 a 140 (próxima página - para baixo)
                    // A tabela não pode ter mais de 20 linhas
                    List<Dono> resultados = service
                            .recuperaPaginaDeDonos(rowIndex - 19, 40);

                    for (int j = 0; j < resultados.size(); j++) {
                        Dono dono = resultados.get(j);
                        cache.put(rowIndex - 19 + j, dono);
                    }
                } else {
                    int inicio = rowIndex - 20;
                    if (inicio < 0) {
                        inicio = 0;
                    }

                    // O cache é maior do que 80 e estamos navegando para cima
                    // Se, por exemplo, rowindex = 121 então queremos recuperar 2 páginas:
                    // de 101 a 120 (página anterior - para cima)
                    // de 121 a 140 (página atual)
                    List<Dono> resultados = service
                            .recuperaPaginaDeDonos(inicio, 40);

                    for (int j = 0; j < resultados.size(); j++) {
                        Dono cliente = resultados.get(j);
                        cache.put(inicio + j, cliente);
                    }
                }

            } else if (rowIndex >= rowIndexAnterior) {
                // O cache não é maior do que 80 e estamos navegando para baixo

                // Se, por exemplo, rowindex = 121 vamos recuperar 2 páginas (além das entradas que estão no cache):
                // de 121 a 160 (a linha atual (121) e mais 39 linhas (quase duas páginas - supondo cada página com 20 linhas))
                List<Dono> resultados = service
                        .recuperaPaginaDeDonos(rowIndex, 40);

                for (int j = 0; j < resultados.size(); j++) {
                    Dono dono = resultados.get(j);
                    cache.put(rowIndex + j, dono);
                }
            } else {
                int inicio = rowIndex - 39;
                if (inicio < 0) {
                    inicio = 0;
                }

                // O cache não é maior do que 80 e estamos navegando para cima
                // Se, por exemplo, rowindex = 139 vamos recuperar 2 páginas (além das entradas que estão no cache):
                // de 100 a 139 (a linha atual (139) e mais 39 linhas anteriores (quase duas páginas - supondo cada página com 20 linhas))
                List<Dono> resultados = service
                        .recuperaPaginaDeDonos(inicio, 40);

                for (int j = 0; j < resultados.size(); j++) {
                    Dono cliente = resultados.get(j);
                    cache.put(inicio + j, cliente);
                }
            }
        }

        rowIndexAnterior = rowIndex;

        Dono dono = cache.get(rowIndex);        
        if (columnIndex == COLUNA_ID) {
            return dono.getIdDono();
        } else if (columnIndex == COLUNA_TELEFONE) {
            return dono.getTelefone();
        } else if (columnIndex == COLUNA_NOME) {
            return dono.getNome();
        } else if (columnIndex == COLUNA_EMAIL) {
            return dono.getEmail();
        } else if (columnIndex == COLUNA_NASCIMENTO) {
            return dono.getDataNascimento();
        } else if (columnIndex == COLUNA_ENDERECO) {
            return dono.getEndereco();
        } else {
            return null;
        }
    }
    
    public void setValueAt(Object obj, int r, int c) 
	{
		Dono umDono = cache.get(r);
		if(c == COLUNA_TELEFONE) umDono.setTelefone((String) obj);
		if(c == COLUNA_NOME) umDono.setNome((String) obj);
		if(c == COLUNA_EMAIL) umDono.setEmail((String) obj);
                if(c == COLUNA_NASCIMENTO) umDono.setDataNascimento((Date)obj);
                if(c == COLUNA_ENDERECO) umDono.setEndereco((String) obj);

		try 
		{	
                        service.altera(umDono);
		} 
		catch (ObjetoNaoEncontradoException e) 
		{	e.printStackTrace();
		}
	}
}
