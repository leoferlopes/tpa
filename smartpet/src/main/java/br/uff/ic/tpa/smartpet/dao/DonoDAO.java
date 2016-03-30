/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao;

import br.uff.ic.tpa.smartpet.annotation.RecuperaLista;
import br.uff.ic.tpa.smartpet.annotation.RecuperaPagina;
import br.uff.ic.tpa.smartpet.model.Dono;
import java.util.List;

/**
 *
 * @author leonardo
 */
public interface DonoDAO extends DaoGenerico<Dono, Integer> {

    @RecuperaLista
    List<Dono> recuperaListaDeDonos();

    @RecuperaPagina
    List<Dono> recuperaPaginaDeDonos(Integer startPosition, Integer length);


}
