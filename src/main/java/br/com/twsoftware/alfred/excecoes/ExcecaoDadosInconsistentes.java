package br.com.twsoftware.alfred.excecoes;

public class ExcecaoDadosInconsistentes extends Excecao {
  private static final long serialVersionUID = 1L;

  public ExcecaoDadosInconsistentes() {
    super();
  }

  public ExcecaoDadosInconsistentes(String message) {
    super(message);
  }

  public ExcecaoDadosInconsistentes(Throwable cause) {
    super(cause);
  }

  public ExcecaoDadosInconsistentes(String message, Throwable cause) {
    super(message, cause);
  }
}
