package br.com.twsoftware.alfred.excecoes;

public class ExcecaoXML extends ExcecaoObrigatoriaTJPB {
  private static final long serialVersionUID = 1L;

  public ExcecaoXML() {
    super(mensagemErroXML());
  }

  private static String mensagemErroXML() {
    return "Erro ao tentar ler o arquivo XML";
  }

  public ExcecaoXML(String mensagem) {
    super(mensagem);
  }

  public ExcecaoXML(String mensagem, Throwable e) {
    super(mensagem, e);
  }

  public ExcecaoXML(Throwable e) {
    super(mensagemErroXML(), e);
  }
}