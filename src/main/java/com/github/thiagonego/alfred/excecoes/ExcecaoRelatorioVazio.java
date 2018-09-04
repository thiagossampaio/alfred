/*
 * Created on 19/05/2005
 */
package com.github.thiagonego.alfred.excecoes;

public class ExcecaoRelatorioVazio extends ExcecaoRelatorio {
  private static final long serialVersionUID = 3760845644914242359L;

  public ExcecaoRelatorioVazio() {
    super();
  }

  public ExcecaoRelatorioVazio(String message) {
    super(message);
  }

  public ExcecaoRelatorioVazio(Throwable cause) {
    super(cause);
  }

  public ExcecaoRelatorioVazio(String message, Throwable cause) {
    super(message, cause);
  }
}
