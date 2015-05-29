package br.com.twsoftware.alfred.excecoes;

public abstract class ExcecaoObrigatoriaTJPB extends Exception {
  /**
	 * 
	 */
	private static final long serialVersionUID = 6711872032452685774L;

public ExcecaoObrigatoriaTJPB() {
    super();
  }

  public ExcecaoObrigatoriaTJPB(String mensagem) {
    super(mensagem);
  }

  public ExcecaoObrigatoriaTJPB(String mensagem, Throwable e) {
    super(mensagem, e);
  }

  public ExcecaoObrigatoriaTJPB(Throwable e) {
    super(e);
  }
}