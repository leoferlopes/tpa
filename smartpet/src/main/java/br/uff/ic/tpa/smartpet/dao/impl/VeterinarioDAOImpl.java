/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao.impl;

import br.uff.ic.tpa.smartpet.dao.VeterinarioDAO;
import br.uff.ic.tpa.smartpet.model.Veterinario;

/**
 *
 * @author leonardo
 */
public abstract class VeterinarioDAOImpl extends JPADaoGenerico<Veterinario, Integer> implements VeterinarioDAO {

    public VeterinarioDAOImpl() {
        super(Veterinario.class);
    }

}
