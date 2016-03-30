/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao;

/**
 *
 * @author leonardo
 */
import org.springframework.transaction.annotation.Transactional;

import br.uff.ic.tpa.smartpet.excecao.ObjetoNaoEncontradoException;
import java.util.List;

/**
 * A interface GenericDao básica com os métodos CRUD. Os métodos de busca são
 * adicionados por herança de interface.
 */
public interface DaoGenerico<T, PK> {

    @Transactional
    T inclui(T obj);

    @Transactional
    void altera(T obj);

    @Transactional
    void exclui(T obj);

    T getPorId(PK id) throws ObjetoNaoEncontradoException;

    T getPorIdComLock(PK id) throws ObjetoNaoEncontradoException;

    //List<T> getPagina(Number inicio, Number tamanho);
}
