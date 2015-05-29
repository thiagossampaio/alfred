
package br.com.twsoftware.alfred.testes.object;

public class PessoaFisicaTest extends PessoaTest{

     private String sobrenome;

     private String fisica;

     public PessoaFisicaTest(){

          super();
     }

     public PessoaFisicaTest(String sobrenome, String fisica){

          super();
          this.sobrenome = sobrenome;
          this.fisica = fisica;
     }



     public String getSobrenome() {
     
          return sobrenome;
     }

     
     public void setSobrenome(String sobrenome) {
     
          this.sobrenome = sobrenome;
     }

     
     public String getFisica() {
     
          return fisica;
     }

     
     public void setFisica(String fisica) {
     
          this.fisica = fisica;
     }
     
     

}
