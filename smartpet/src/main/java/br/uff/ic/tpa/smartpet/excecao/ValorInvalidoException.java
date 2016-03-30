package br.uff.ic.tpa.smartpet.excecao;

import br.uff.ic.tpa.smartpet.annotation.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class ValorInvalidoException extends ApplicationException {

    private final static long serialVersionUID = 1;

    public ValorInvalidoException(String msg) {
        super(msg);
    }
}
