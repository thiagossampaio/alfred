package com.github.thiagonego.alfred.excecoes;

public class ExcecaoURLNaoEncontrada extends Excecao {
	private static final long serialVersionUID = 1L;

	public ExcecaoURLNaoEncontrada(Exception e) {
		super(mensagemUrlNaoEncontrada(), e);
	}

	public ExcecaoURLNaoEncontrada() {
		super(mensagemUrlNaoEncontrada());
	}

	public ExcecaoURLNaoEncontrada(String mensagem, Exception e) {
		super(mensagem, e);
	}

	private static String mensagemUrlNaoEncontrada() {
		return "A url informada n�o foi encontrada.";
	}
}
