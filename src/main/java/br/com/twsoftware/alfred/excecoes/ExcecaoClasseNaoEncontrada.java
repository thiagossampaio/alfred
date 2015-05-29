package br.com.twsoftware.alfred.excecoes;

public class ExcecaoClasseNaoEncontrada extends ExcecaoObrigatoriaTJPB {
  /**
	 * 
	 */
	private static final long serialVersionUID = 67980645672119898L;

public ExcecaoClasseNaoEncontrada() {
    super();
  }

  public ExcecaoClasseNaoEncontrada(String mensagem) {
    super(mensagem);
  }

  public ExcecaoClasseNaoEncontrada(String mensagem, Throwable e) {
    super(mensagem, e);
  }

  public ExcecaoClasseNaoEncontrada(Throwable e) {
    super(e);
  }
}
