/*
 *  This file is part of Alfred Library.
 *
 *  Alfred Library is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Alfred Library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Alfred Library.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.twsoftware.alfred.testes.email;


import org.junit.Assert;
import org.junit.Test;

import br.com.twsoftware.alfred.email.Email;

/**
 * Classe de Teste para E-mails.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
public class EmailTest {

	/**
	 * Testar e-mails inválidos.
	 */
	@Test
	public void testarEmailInvalido() {
	     
		if ( Email.isValido("marlon=@t.com") )
			Assert.fail();
		if ( Email.isValido("marlon=@gmail.com") )
               Assert.fail();
		if ( Email.isValido("Abc.example.com") )
			Assert.fail();
		if ( Email.isValido("marlon.carvalhogmail.com") )
			Assert.fail();
		if ( Email.isValido("marlon@g@g.com") )
			Assert.fail();
		if ( Email.isValido("marlong@g....com") )
			Assert.fail();
		if ( Email.isValido("marlon@.com") )
			Assert.fail();
		if ( Email.isValido("marlon@ccom") )
			Assert.fail();
		if ( Email.isValido("asf@1.com") )
			Assert.fail();
		if ( Email.isValido(".asf@test.com") )
               Assert.fail();
		if ( Email.isValido("asf.@test.com") )
               Assert.fail();
		if ( Email.isValido(".asf.@test.com") )
               Assert.fail();
		if ( Email.isValido("=marlon=@t.com") )
               Assert.fail();
		if ( Email.isValido("=marlon@t.com") )
               Assert.fail();
		if ( Email.isValido("A@b@c@example.com") )
		     Assert.fail();
		if ( Email.isValido("1234567890123456789012345678901234567890123456789012345678901234+x@example.com") )
		     Assert.fail();
		if ( Email.isValido("john..doe@example.com") )
		     Assert.fail();
//		if ( Email.isValido("example@localhost") )
//		     Assert.fail();
//		if ( Email.isValido("admin@mailserver1") )
//             Assert.fail();
	}
	
//	@Test
	public void testarEmailValido() {
	     
	   //Emails válidos
          if ( !Email.isValido("123marlon123@teste.com") )
               Assert.fail();
          if ( !Email.isValido("www.thebest@gmail.com") )
               Assert.fail();
          if ( !Email.isValido("m.a.r.l.o.n@gmail.com") )
               Assert.fail();
          if ( !Email.isValido("m.a.r.l.o.n-test@gmail.com") )
               Assert.fail();
          if ( !Email.isValido("m.a.r.l.o.n_test@gmail.com") )
               Assert.fail();
          if ( !Email.isValido("m.a.r.l.o.n-test_te@gmail.com") )
               Assert.fail();
          if ( !Email.isValido("marlon@gmail.com") )
               Assert.fail();
          if ( !Email.isValido("marlon.carvalho@gmail.com") )
               Assert.fail();
          if ( !Email.isValido("other.email-with-dash@example.com") )
               Assert.fail();
          if ( !Email.isValido("user@localserver") )
               Assert.fail();
          
        //Não validados
//        if ( !Email.isValido("example@s.solutions") )
//             Assert.fail();
//        if ( !Email.isValido("admin@mailserver1") )
//             Assert.fail();
//        if ( !Email.isValido("x@example.com") )
//             Assert.fail();
//        if ( !Email.isValido("disposable.style.email.with+symbol@example.com") )
//             Assert.fail();
          
	}

}