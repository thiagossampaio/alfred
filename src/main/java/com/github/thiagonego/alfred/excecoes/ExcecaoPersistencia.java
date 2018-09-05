/*
 * Created by Luiz Carlos G. Reis
 * Created on 27/04/2004
 */
package com.github.thiagonego.alfred.excecoes;

/**
 * @author Luiz Carlos G. Reis
 */
public class ExcecaoPersistencia extends Excecao {
  private static final long serialVersionUID = 3257009838994043448L;

  public ExcecaoPersistencia() {
    super();
  }

  /**
   * @param Mensagem de erro
   */
  public ExcecaoPersistencia(String mensagemDeErro) {
    super(mensagemDeErro);
  }

  /**
   * @param Objeto da exce��o
   */
  public ExcecaoPersistencia(Throwable excecao) {
    super(excecao);
  }

  /**
   * @param Mensagem de erro
   * @param Objeto da exce��o
   */
  public ExcecaoPersistencia(String mensagemDeErro, Throwable excecao) {
    super(mensagemDeErro, excecao);
  }
}
