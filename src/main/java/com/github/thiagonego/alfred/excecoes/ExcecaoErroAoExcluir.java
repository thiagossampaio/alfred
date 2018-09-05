/*
 * Created on 28/02/2005
 */
package com.github.thiagonego.alfred.excecoes;

/**
 * @author Luiz Carlos G. Reis
 */
public class ExcecaoErroAoExcluir extends Excecao {
  private static final long serialVersionUID = 3257286950367932469L;

  public ExcecaoErroAoExcluir() {
    super();
  }

  public ExcecaoErroAoExcluir(String message) {
    super(message);
  }

  public ExcecaoErroAoExcluir(Throwable cause) {
    super(cause);
  }

  public ExcecaoErroAoExcluir(String message, Throwable cause) {
    super(message, cause);
  }
}