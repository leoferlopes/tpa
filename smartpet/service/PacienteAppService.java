/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.service;

import br.uff.ic.tpa.smartpet.dao.PacienteDAO;
import br.uff.ic.tpa.smartpet.excecao.ObjetoNaoEncontradoException;
import br.uff.ic.tpa.smartpet.model.Paciente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author leonardo
 */
public class PacienteAppService {

    private PacienteDAO pacienteDAO = null;

    @Autowired
    public void setPacienteDAO(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    public Integer inclui(Paciente paciente) {
        return pacienteDAO.inclui(paciente).getIdPaciente();
    }

    @Transactional
    public void altera(Paciente paciente) throws ObjetoNaoEncontradoException {
        try {
            pacienteDAO.getPorIdComLock(paciente.getIdPaciente());
            pacienteDAO.altera(paciente);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Paciente não encontrado");
        }
    }

    @Transactional
    public void exclui(Paciente paciente) throws ObjetoNaoEncontradoException {
        try {
            Paciente p = pacienteDAO.getPorIdComLock(paciente.getIdPaciente());
            pacienteDAO.exclui(p);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Paciente não encontrado");
        }
    }

    @Transactional
    public Paciente recupera(Integer idPaciente) throws ObjetoNaoEncontradoException {
        try {
            return pacienteDAO.getPorId(idPaciente);
        } catch (ObjetoNaoEncontradoException e) {
            throw new ObjetoNaoEncontradoException("Paciente não encontrado");
        }

    }

    public List<Paciente> recuperaPacientes() {
        return pacienteDAO.recuperaListaDePacientes();
    }

}
