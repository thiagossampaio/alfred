/*
 * Created by Luiz Carlos G. Reis
 * Created on 29/04/2004
 */
package br.com.twsoftware.alfred.excecoes;

/**
 * @author Luiz Carlos G. Reis
 */
public class ExcecaoApresentacao extends Excecao {
  private static final long serialVersionUID = 3257002138118010678L;

  public ExcecaoApresentacao() {
    super();
  }

  public ExcecaoApresentacao(String arg0) {
    super(arg0);
  }

  public ExcecaoApresentacao(Throwable arg0) {
    super(arg0);
  }

  public ExcecaoApresentacao(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }
}