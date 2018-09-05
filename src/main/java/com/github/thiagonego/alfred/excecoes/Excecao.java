/*
 * Created on Jan 27, 2005
 *
 */
package com.github.thiagonego.alfred.excecoes;

/**
 * @author luizcgr
 *
 */
public abstract class Excecao extends RuntimeException {
	
  /**
	 * 
	 */
	private static final long serialVersionUID = -5460759032649691619L;

public Excecao() {
    super();
  }

  public Excecao(String message) {
    super(message);
  }

  public Excecao(Throwable cause) {
    super(cause);
  }

  public Excecao(String message, Throwable cause) {
    super(message, cause);
  }
}