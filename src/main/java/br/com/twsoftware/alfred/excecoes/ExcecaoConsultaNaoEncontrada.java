/*
 * Created on 27/01/2005
 *
 */
package br.com.twsoftware.alfred.excecoes;

/**
 * @author Luiz Carlos G. Reis
 *
 *	Exce��o gerada quando uma consulta n�o retorna nenhum objeto / registro.
 */
public class ExcecaoConsultaNaoEncontrada extends ExcecaoPersistencia {
  private static final long serialVersionUID = 3257002138118010678L;

  public ExcecaoConsultaNaoEncontrada() {
    super();
  }

  public ExcecaoConsultaNaoEncontrada(String mensagemDeErro) {
    super(mensagemDeErro);
  }

  public ExcecaoConsultaNaoEncontrada(Throwable excecao) {
    super(excecao);
  }

  public ExcecaoConsultaNaoEncontrada(String mensagemDeErro, Throwable excecao) {
    super(mensagemDeErro, excecao);
  }
}