package br.uff.ic.tpa.smartpet.excecao;

import br.uff.ic.tpa.smartpet.annotation.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class DataInvalidaException extends ApplicationException {

    private final static long serialVersionUID = 1;

    public DataInvalidaException(String msg) {
        super(msg);
    }
}
