package com.github.thiagonego.alfred.excecoes;

public class ExcecaoRegistroDuplicado extends ExcecaoPersistencia {
  /**
	 * 
	 */
	private static final long serialVersionUID = -7311718718218983007L;

public ExcecaoRegistroDuplicado() {
    super();
  }

  /**
   * @param Mensagem de erro
   */
  public ExcecaoRegistroDuplicado(String mensagemDeErro) {
    super(mensagemDeErro);
  }

  /**
   * @param Objeto da exce��o
   */
  public ExcecaoRegistroDuplicado(Throwable excecao) {
    super(excecao);
  }

  /**
   * @param Mensagem de erro
   * @param Objeto da exce��o
   */
  public ExcecaoRegistroDuplicado(String mensagemDeErro, Throwable excecao) {
    super(mensagemDeErro, excecao);
  }
}
