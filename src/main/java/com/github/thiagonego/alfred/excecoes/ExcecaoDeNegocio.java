/*
 * Created on 15/04/2005
 */
package br.com.twsoftware.alfred.excecoes;

public class ExcecaoDeNegocio extends RuntimeException {


	private static final long serialVersionUID = 3074712864208326409L;

	public ExcecaoDeNegocio() {
    super();
  }

  public ExcecaoDeNegocio(String mensagem) {
    super(mensagem);
  }

  public ExcecaoDeNegocio(String mensagem, Throwable e) {
    super(mensagem, e);
  }

  public ExcecaoDeNegocio(Throwable e) {
    super(e);
  }
}
