/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.service;

import br.uff.ic.tpa.smartpet.dao.DonoDAO;
import br.uff.ic.tpa.smartpet.excecao.ObjetoNaoEncontradoException;
import br.uff.ic.tpa.smartpet.model.Dono;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leonardo
 */
public class DonoAppService {

    private DonoDAO donoDAO = null;

    @Autowired
    public void setDonoDAO(DonoDAO donoDAO) {
        this.donoDAO = donoDAO;
    }

    public Integer inclui(Dono dono) {
        return donoDAO.inclui(dono).getIdDono();
    }

    @Transactional
    public void altera(Dono dono) throws ObjetoNaoEncontradoException {
        try {
            donoDAO.getPorIdComLock(dono.getIdDono());
            donoDAO.altera(dono);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Dono não encontrado");
        }
    }

    @Transactional
    public void exclui(Dono dono) throws ObjetoNaoEncontradoException {
        try {
            Dono d = donoDAO.getPorIdComLock(dono.getIdDono());
            donoDAO.exclui(d);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Dono não encontrado");
        }
    }

    @Transactional
    public Dono recupera(Integer idDono) throws ObjetoNaoEncontradoException {
        try {
            return donoDAO.getPorId(idDono);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Dono não encontrado");
        }

    }

    public List<Dono> recuperaDonos() {
        return donoDAO.recuperaListaDeDonos();
    }

    public List<Dono> recuperaPaginaDeDonos(Integer startPosition, Integer length) {
        return donoDAO.recuperaPaginaDeDonos(startPosition, length);
    }
}
