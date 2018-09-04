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
package com.github.thiagonego.alfred.testes.email;


import static com.github.thiagonego.alfred.email.Email.isValido;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
	public void testEmails() {

		//Válidos
          assertTrue(isValido("123marlon123@teste.com"));
          assertTrue(isValido("marlon.carvalho@gmail.com"));
          assertTrue(isValido("123marlon123@teste.com"));
          assertTrue(isValido("www.thebest@gmail.com"));
          assertTrue(isValido("m.a.r.l.o.n@gmail.com"));
          assertTrue(isValido("m.a.r.l.o.n-test@gmail.com"));
          assertTrue(isValido("m.a.r.l.o.n_test@gmail.com"));
          assertTrue(isValido("m.a.r.l.o.n-test_te@gmail.com"));
          assertTrue(isValido("marlon@gmail.com"));
          assertTrue(isValido("marlon.carvalho@gmail.com"));
          assertTrue(isValido("other.email-with-dash@example.com"));
          assertTrue(isValido("asf@1.com"));
          assertTrue(isValido("example@s.solutions"));
          assertTrue(isValido("x@example.com"));
          
          //Inválidos
          assertFalse(isValido("A@b@c@example.com"));
          assertFalse(isValido("marlon@g@g.com"));
          assertFalse(isValido("marlong@g....com"));
          assertFalse(isValido("marlon@.com"));
          assertFalse(isValido("marlon@ccom"));
          assertFalse(isValido("marlon=@t.com"));
          assertFalse(isValido("marlon=@gmail.com"));
          assertFalse(isValido("Abc.example.com"));
          assertFalse(isValido("marlon.carvalhogmail.com"));
          assertFalse(isValido("=marlon=@t.com"));
          assertFalse(isValido("=marlon@t.com"));
          assertFalse(isValido("admin@mailserver1"));
          assertFalse(isValido(".asf@test.com"));
          assertFalse(isValido(".asf.@test.com"));
          assertFalse(isValido("asf.@test.com"));
          assertFalse(isValido("user@localserver"));
          assertFalse(isValido("john..doe@example.com"));
          assertFalse(isValido("example@localhost"));
          assertFalse(isValido("example?@localhost"));
          
	}
}