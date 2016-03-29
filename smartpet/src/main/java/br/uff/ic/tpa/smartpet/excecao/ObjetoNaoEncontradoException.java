package br.uff.ic.tpa.smartpet.excecao;

public class ObjetoNaoEncontradoException extends Exception {

    private final static long serialVersionUID = 1;

    public ObjetoNaoEncontradoException() {
    }

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
}
