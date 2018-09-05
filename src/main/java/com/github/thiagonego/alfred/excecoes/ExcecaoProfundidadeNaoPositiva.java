package com.github.thiagonego.alfred.excecoes;


public class ExcecaoProfundidadeNaoPositiva extends Excecao {
    private static final long serialVersionUID = 3976735887050551348L;

    public ExcecaoProfundidadeNaoPositiva() {
        super();
      }

      /**
       * @param Mensagem de erro
       */
      public ExcecaoProfundidadeNaoPositiva(String mensagemDeErro) {
        super(mensagemDeErro);
      }

      /**
       * @param Objeto da exce��o
       */
      public ExcecaoProfundidadeNaoPositiva(Throwable excecao) {
        super(excecao);
      }

      /**
       * @param Mensagem de erro
       * @param Objeto da exce��o
       */
      public ExcecaoProfundidadeNaoPositiva(String mensagemDeErro, Throwable excecao) {
        super(mensagemDeErro, excecao);
      }
}
