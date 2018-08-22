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
package br.com.twsoftware.alfred.testes.cnpj;


import org.junit.Assert;
import org.junit.Test;

import br.com.twsoftware.alfred.AlfredException;
import br.com.twsoftware.alfred.cnpj.CNPJ;

/**
 * Classe de Teste para CNPJ.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
public class CNPJTest {

	/**
	 * Testar um CNPJ que tenha menos de 15 n�meros.
	 * Deve lan�ar uma exce��o.
	 */
	@Test
	public void testarFormatarCNPJMenos15Numeros() {
		try {
			CNPJ.formatar("15.13.923/0001-84");
			Assert.fail();
		} catch(AlfredException ex) {
		}
	}

	/**
	 * Testar um CNPJ correto. 
	 * N�o deve lan�ar exce��o.
	 */
	@Test
	public void testarFormatarCNPJCorreto() {
		try {
			CNPJ.formatar("15.193.923/0001-84");
		} catch(AlfredException ex) {
			Assert.fail();
		}
	}

	/**
	 * Verificar se a valida��o de CNPJ funciona com um n�mero v�lido.
	 */
	@Test
	public void testarValidarCNPJValido() {
		try {
			if ( !CNPJ.isValido("15.193.923/0001-84") ) {
				Assert.fail();
			}
		} catch(AlfredException ex) {
			Assert.fail();
		}
	}

	/**
	 * Verificar se a valida��o de CNPJ funciona com um n�mero inv�lido.
	 */
	@Test
	public void testarValidarCNPJInvalido() {
		try {
			if ( CNPJ.isValido("15.193.923/0001-85") ) {
				Assert.fail();
			}
		} catch(AlfredException ex) {
			Assert.fail();
		}
	}

	/**
	 * Testar a gera��o de um CNPJ.
	 */
	@Test
	public void testarGeracao() {
		try {
			String cnpj = CNPJ.gerar();
			Assert.assertTrue(CNPJ.isValido(cnpj));
		} catch ( AlfredException ae ) {
			Assert.fail();
		}
	}

	/**
	 * Testar a gera��o de d�gito verificador.
	 */
	@Test
	public void testarGeracaoDigitoVerificador() {
		try {
			// Deve ser 84.
			String digito = CNPJ.gerarDigitoVerificador("151939230001");
			Assert.assertEquals("84", digito);
		} catch ( AlfredException ae ) {
			Assert.fail();
		}
	}

}