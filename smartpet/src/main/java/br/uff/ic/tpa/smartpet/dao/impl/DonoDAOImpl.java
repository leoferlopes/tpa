/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao.impl;

import br.uff.ic.tpa.smartpet.dao.DonoDAO;
import br.uff.ic.tpa.smartpet.model.Dono;

/**
 *
 * @author leonardo
 */
public abstract class DonoDAOImpl extends JPADaoGenerico<Dono, Integer> implements DonoDAO{

    public DonoDAOImpl() {
        super(Dono.class);
    }
        
}
