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
package br.com.twsoftware.alfred.testes.telefones;

import junit.framework.Assert;

import org.junit.Test;

import br.com.twsoftware.alfred.telefones.Telefones;

/**
 * Classe de Teste para Telefones.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
public class TelefonesTest {

	/**
	 * Testar a formata��o de um telefone com 8 n�meros.
	 */
	@Test
	public void testarFormatacao8Numeros() {
		String telefone = Telefones.formatar("12345678");
		if ( telefone.charAt(4) != '-' )
			Assert.fail();
	}

	/**
	 * Testar a formata��o de um telefone com 10 n�meros.
	 */
	@Test
	public void testarFormatacao10Numeros() {
		String telefone = Telefones.formatar("1234567890");
		if ( telefone.charAt(0) != '(' )
			Assert.fail();
		if ( telefone.charAt(3) != ')' )
			Assert.fail();
		if ( telefone.charAt(9) != '-' )
			Assert.fail();
	}
	
	/**
	 * Testar a formata��o de um telefone com 12 n�meros.
	 */
	@Test
	public void testarFormatacao12Numeros() {
		String telefone = Telefones.formatar("111234567890");
		if ( telefone.charAt(0) != '+' )
			Assert.fail();
		if ( telefone.charAt(4) != '(' )
			Assert.fail();
		if ( telefone.charAt(7) != ')' )
			Assert.fail();
		if ( telefone.charAt(13) != '-' )
			Assert.fail();
	}

	/**
	 * Testar a formata��o de um telefone com 12 n�meros.
	 */
	@Test
	public void testarFormatacaoNumerosTamanhoDiferente81012() {
		String telefone = Telefones.formatar("123");
		if ( ! telefone.equals("123") )
			Assert.fail();
	}

}