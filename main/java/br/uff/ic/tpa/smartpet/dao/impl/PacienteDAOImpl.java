/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao.impl;

import br.uff.ic.tpa.smartpet.dao.PacienteDAO;
import br.uff.ic.tpa.smartpet.model.Paciente;

/**
 *
 * @author leonardo
 */
public abstract class PacienteDAOImpl extends JPADaoGenerico<Paciente, Integer> implements PacienteDAO {

    public PacienteDAOImpl() {
        super(Paciente.class);
    }

}
