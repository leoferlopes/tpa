/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao.controle;

/**
 *
 * @author leonardo
 */
import br.uff.ic.tpa.smartpet.annotation.ContaLista;
import br.uff.ic.tpa.smartpet.annotation.RecuperaConjunto;
import br.uff.ic.tpa.smartpet.annotation.RecuperaLista;
import br.uff.ic.tpa.smartpet.annotation.RecuperaObjeto;
import br.uff.ic.tpa.smartpet.annotation.RecuperaPagina;
import br.uff.ic.tpa.smartpet.annotation.RecuperaUltimoOuPrimeiro;
import br.uff.ic.tpa.smartpet.dao.impl.JPADaoGenerico;
import br.uff.ic.tpa.smartpet.excecao.InfraestruturaException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class InterceptadorDeDAO implements MethodInterceptor {

    /* Parametros:
     * 
     * objeto - "this", o objeto "enhanced", isto é, o proxy.
     * 
     * metodo - o  método   interceptado,  isto  é,  um   método  da 
     *          interface ProdutoDAO, LanceDAO, etc. 
     * 
     * args - um  array  de args; tipos  primitivos são empacotados.
     *        Contém   os   argumentos  que  o  método  interceptado 
     *        recebeu.
     * 
     * metodoProxy - utilizado para executar um método super. Veja o
     *               comentário abaixo.
     * 
     * MethodProxy  -  Classes  geradas pela  classe Enhancer passam 
     * este objeto para o objeto MethodInterceptor registrado quando
     * um método  interceptado é  executado.  Ele pode ser utilizado
     * para  invocar o  método  original,  ou  chamar o mesmo método
     * sobre um objeto diferente do mesmo tipo.
     * 
     */
    public Object intercept(Object objeto,
            Method metodo,
            Object[] args,
            MethodProxy metodoDoProxy)
            throws Throwable {
        // O símbolo ? representa um tipo desconhecido.
        JPADaoGenerico<?, ?> daoGenerico = (JPADaoGenerico<?, ?>) objeto;

        if (metodo.isAnnotationPresent(RecuperaLista.class)) {
            // O método buscaLista() retorna um List
            return daoGenerico.buscaLista(metodo, args);
        } else if (metodo.isAnnotationPresent(RecuperaPagina.class)) {
            // O método buscaPagina() retorna um List
            return daoGenerico.buscaPagina(metodo, args);
        } else if (metodo.isAnnotationPresent(ContaLista.class)) {
            // O método buscaListaCount() retorna um Long
            return daoGenerico.buscaListaCount(metodo, args);
        } else if (metodo.isAnnotationPresent(RecuperaConjunto.class)) {
            // O método buscaConjunto() retorna um Set
            return daoGenerico.buscaConjunto(metodo, args);
        } else if (metodo.isAnnotationPresent(RecuperaUltimoOuPrimeiro.class)) {
            // O método buscaUltimoOuPrimeiro() retorna um Objeto (Entidade)
            return daoGenerico.buscaUltimoOuPrimeiro(metodo, args);
        } else if (metodo.isAnnotationPresent(RecuperaObjeto.class)) {
            // O método busca() retorna um Objeto (Entidade)
            return daoGenerico.busca(metodo, args);
        } else {
            throw new InfraestruturaException("Um método não final deixou de ser anotado");
            // return metodoDoProxy.invokeSuper(objeto, args);
        }
    }
}
