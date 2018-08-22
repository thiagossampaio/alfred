package br.com.twsoftware.alfred.excecoes;

public class ExcecaoArquivoNaoEncontrado extends Excecao {
	private static final long serialVersionUID = 6181959181054275637L;

	public ExcecaoArquivoNaoEncontrado() {
		super();
	}

	public ExcecaoArquivoNaoEncontrado(String mensagem) {
		super(mensagem);
	}

	public ExcecaoArquivoNaoEncontrado(String mensagem, Throwable e) {
		super(mensagem, e);
	}

	public ExcecaoArquivoNaoEncontrado(Throwable e) {
		super(e);
	}
}