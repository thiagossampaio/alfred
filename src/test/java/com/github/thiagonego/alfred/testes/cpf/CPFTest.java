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
package com.github.thiagonego.alfred.testes.cpf;

import junit.framework.Assert;

import org.junit.Test;

import com.github.thiagonego.alfred.cpf.CPF;


/**
 * Classe de Testes para CPF.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
public class CPFTest {

	/**
	 * Testar a valida��o de um CPF que � v�lido.
	 */
	@Test
	public void testarValidarCPFValido() {
		if ( !CPF.isValido("111.111.111-11") ) {
			Assert.fail();
		}
	}

	/**
	 * Testar a valida��o de um CPF que � inv�lido.
	 */
	@Test
	public void testarValidarCPFInvalido() {
		if ( CPF.isValido("111.111.111-12") ) {
			Assert.fail();
		}
	}

}