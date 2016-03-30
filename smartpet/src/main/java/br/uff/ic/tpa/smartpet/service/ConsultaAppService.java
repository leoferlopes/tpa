/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.service;

import br.uff.ic.tpa.smartpet.dao.ConsultaDAO;
import br.uff.ic.tpa.smartpet.excecao.ObjetoNaoEncontradoException;
import br.uff.ic.tpa.smartpet.model.Consulta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leonardo
 */
public class ConsultaAppService {

    private ConsultaDAO consultaDAO = null;

    @Autowired
    public void setConsultaDAO(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    public Integer inclui(Consulta consulta) {
        return consultaDAO.inclui(consulta).getCodigoConsulta();
    }

    @Transactional
    public void altera(Consulta consulta) throws ObjetoNaoEncontradoException {
        try {
            consultaDAO.getPorIdComLock(consulta.getCodigoConsulta());
            consultaDAO.altera(consulta);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Consulta não encontrado");
        }
    }

    @Transactional
    public void exclui(Consulta consulta) throws ObjetoNaoEncontradoException {
        try {
            Consulta c = consultaDAO.getPorIdComLock(consulta.getCodigoConsulta());
            consultaDAO.exclui(c);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Consulta não encontrado");
        }
    }

    @Transactional
    public Consulta recupera(Integer codigoConsulta) throws ObjetoNaoEncontradoException {
        try {
            return consultaDAO.getPorId(codigoConsulta);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Consulta não encontrado");
        }

    }

    public List<Consulta> recuperaConsultas() {
        return consultaDAO.recuperaListaDeConsultas();
    }

    public List<Consulta> recuperaPaginaDeConsultas(Integer startPosition, Integer length) {
        return consultaDAO.recuperaPaginaDeConsultas(startPosition, length);
    }
}
