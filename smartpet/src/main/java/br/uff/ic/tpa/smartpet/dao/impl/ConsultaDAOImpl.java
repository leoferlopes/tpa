/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao.impl;

import br.uff.ic.tpa.smartpet.dao.ConsultaDAO;
import br.uff.ic.tpa.smartpet.model.Consulta;

/**
 *
 * @author leonardo
 */
public abstract class ConsultaDAOImpl extends JPADaoGenerico<Consulta, Integer> implements ConsultaDAO {

    public ConsultaDAOImpl() {
        super(Consulta.class);
    }

}
