/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.excecao;

/**
 *
 * @author leonardo
 */
public class ApplicationException extends Exception {

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }
}
