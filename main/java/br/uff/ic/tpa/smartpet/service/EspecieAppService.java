/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.service;

import br.uff.ic.tpa.smartpet.dao.EspecieDAO;
import br.uff.ic.tpa.smartpet.excecao.ObjetoNaoEncontradoException;
import br.uff.ic.tpa.smartpet.model.Especie;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leonardo
 */
public class EspecieAppService {

    private EspecieDAO especieDAO = null;

    @Autowired
    public void setEspecieDAO(EspecieDAO especieDAO) {
        this.especieDAO = especieDAO;
    }

    public Integer inclui(Especie especie) {
        return especieDAO.inclui(especie).getIdEspecie();
    }

    @Transactional
    public void altera(Especie especie) throws ObjetoNaoEncontradoException {
        try {
            especieDAO.getPorIdComLock(especie.getIdEspecie());
            especieDAO.altera(especie);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Espécie não encontrada");
        }
    }

    @Transactional
    public void exclui(Especie especie) throws ObjetoNaoEncontradoException {
        try {
            Especie e = especieDAO.getPorIdComLock(especie.getIdEspecie());
            especieDAO.exclui(e);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Espécie não encontrada");
        }
    }

    @Transactional
    public Especie recupera(Integer idEspecie) throws ObjetoNaoEncontradoException {
        try {
            return especieDAO.getPorId(idEspecie);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Espécie não encontrada");
        }

    }
    
    public List<Especie> recuperaEspecies(){
        return especieDAO.recuperaListaDeEspecies();
    }
}
