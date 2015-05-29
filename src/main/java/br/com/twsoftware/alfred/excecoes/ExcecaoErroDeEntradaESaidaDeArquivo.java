package br.com.twsoftware.alfred.excecoes;

public class ExcecaoErroDeEntradaESaidaDeArquivo extends ExcecaoObrigatoriaTJPB {
  /**
	 * 
	 */
	private static final long serialVersionUID = -6646937757609106775L;

public ExcecaoErroDeEntradaESaidaDeArquivo() {
    super();
  }

  public ExcecaoErroDeEntradaESaidaDeArquivo(String mensagem) {
    super(mensagem);
  }

  public ExcecaoErroDeEntradaESaidaDeArquivo(String mensagem, Throwable e) {
    super(mensagem, e);
  }

  public ExcecaoErroDeEntradaESaidaDeArquivo(Throwable e) {
    super(e);
  }
}
