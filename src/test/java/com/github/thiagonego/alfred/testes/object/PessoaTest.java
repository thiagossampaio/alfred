
package br.com.twsoftware.alfred.testes.object;

public class PessoaTest{

     private String nome;

     private String email;

     private PessoaTest outraPessoa;

     public PessoaTest(){

          super();
     }

     public PessoaTest(String nome, String email){

          super();
          this.nome = nome;
          this.email = email;
     }
     
     public PessoaTest(String nome, String email, PessoaTest outraPessoa){
          
          super();
          this.nome = nome;
          this.email = email;
          this.outraPessoa = outraPessoa;
     }

     public String getNome() {

          return nome;
     }

     public void setNome(String nome) {

          this.nome = nome;
     }

     public String getEmail() {

          return email;
     }

     public void setEmail(String email) {

          this.email = email;
     }

     public PessoaTest getOutraPessoa() {

          return outraPessoa;
     }

     public void setOutraPessoa(PessoaTest outraPessoa) {

          this.outraPessoa = outraPessoa;
     }
}
