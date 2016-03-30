package br.uff.ic.tpa.smartpet.excecao;

public class ObjetoNaoEncontradoException extends ApplicationException {

    private final static long serialVersionUID = 1;

    public ObjetoNaoEncontradoException() {
    }

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
