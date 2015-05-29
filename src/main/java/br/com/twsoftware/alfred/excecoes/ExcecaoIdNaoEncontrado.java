/*
 * Created by Luiz Carlos G. Reis
 * Created on 29/04/2004
 */
package br.com.twsoftware.alfred.excecoes;

/**
 * @author Luiz Carlos G. Reis
 */
public class ExcecaoIdNaoEncontrado extends ExcecaoPersistencia {
  private static final long serialVersionUID = 3545798779954868786L;

  /**
   * 
   */
  public ExcecaoIdNaoEncontrado() {
    super();
  }

  /**
   * @param mensagemDeErro
   */
  public ExcecaoIdNaoEncontrado(String mensagemDeErro) {
    super(mensagemDeErro);
  }

  /**
   * @param excecao
   */
  public ExcecaoIdNaoEncontrado(Throwable excecao) {
    super(excecao);
  }

  /**
   * @param mensagemDeErro
   * @param excecao
   */
  public ExcecaoIdNaoEncontrado(String mensagemDeErro, Throwable excecao) {
    super(mensagemDeErro, excecao);
  }
}