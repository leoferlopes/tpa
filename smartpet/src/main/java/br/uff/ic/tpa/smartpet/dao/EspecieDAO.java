/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao;

import br.uff.ic.tpa.smartpet.annotation.RecuperaLista;
import br.uff.ic.tpa.smartpet.model.Especie;
import java.util.List;

/**
 *
 * @author leonardo
 */
public interface EspecieDAO extends DaoGenerico<Especie, Integer> {

    @RecuperaLista
    List<Especie> recuperaListaDeEspecies();

}
