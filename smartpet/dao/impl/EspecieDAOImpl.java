/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao.impl;

import br.uff.ic.tpa.smartpet.dao.EspecieDAO;
import br.uff.ic.tpa.smartpet.model.Especie;

/**
 *
 * @author leonardo
 */
public abstract class EspecieDAOImpl extends JPADaoGenerico<Especie, Integer> implements EspecieDAO {

    public EspecieDAOImpl() {
        super(Especie.class);
    }

}
