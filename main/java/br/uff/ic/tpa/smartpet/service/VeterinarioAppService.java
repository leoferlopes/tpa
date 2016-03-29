/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.service;

import br.uff.ic.tpa.smartpet.dao.VeterinarioDAO;
import br.uff.ic.tpa.smartpet.excecao.ObjetoNaoEncontradoException;
import br.uff.ic.tpa.smartpet.model.Veterinario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leonardo
 */
public class VeterinarioAppService {
    
    private VeterinarioDAO veterinarioDAO = null;

    @Autowired
    public void setVeterinarioDAO(VeterinarioDAO veterinarioDAO) {
        this.veterinarioDAO = veterinarioDAO;
    }

    public Integer inclui(Veterinario veterinario) {
        return veterinarioDAO.inclui(veterinario).getIdVeterinario();
    }

    @Transactional
    public void altera(Veterinario veterinario) throws ObjetoNaoEncontradoException {
        try {
            veterinarioDAO.getPorIdComLock(veterinario.getIdVeterinario());
            veterinarioDAO.altera(veterinario);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Veterinario não encontrado");
        }
    }

    @Transactional
    public void exclui(Veterinario veterinario) throws ObjetoNaoEncontradoException {
        try {
            Veterinario v = veterinarioDAO.getPorIdComLock(veterinario.getIdVeterinario());
            veterinarioDAO.exclui(v);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Veterinario não encontrado");
        }
    }

    @Transactional
    public Veterinario recupera(Integer idVeterinario) throws ObjetoNaoEncontradoException {
        try {
            return veterinarioDAO.getPorId(idVeterinario);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Veterinario não encontrado");
        }

    }

    public List<Veterinario> recuperaVeterinarios() {
        return veterinarioDAO.recuperaListaDeVeterinarios();
    }
}
