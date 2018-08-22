/*
 * Created on 23/06/2005
 */
package br.com.twsoftware.alfred.excecoes;

public class ExcecaoRelatorio extends Excecao {
  private static final long serialVersionUID = 3257852099227433272L;

  public ExcecaoRelatorio() {
    super();
  }

  public ExcecaoRelatorio(String message) {
    super(message);
  }

  public ExcecaoRelatorio(Throwable cause) {
    super(cause);
  }

  public ExcecaoRelatorio(String message, Throwable cause) {
    super(message, cause);
  }
}
