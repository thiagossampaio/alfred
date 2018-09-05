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
package com.github.thiagonego.alfred.testes.conversores;


import org.junit.Assert;
import org.junit.Test;

import com.github.thiagonego.alfred.conversores.BaseNumerica;

/**
 * Teste de conversor de base num�rica.
 * 
 * @author Marlon Silva Carvalho
 * @since 08/06/2009
 */
public class BaseNumericaTest {

	/**
	 * Testar a convers�o de n�mero decimal para bin�rio.
	 */
	@Test
	public void testarConversaoDecimalBinario() {
		String binario = BaseNumerica.converterDecimalEmBinario("2");
		Assert.assertEquals("10", binario);

		binario = BaseNumerica.converterDecimalEmBinario("3");
		Assert.assertEquals("11", binario);
	}

	/**
	 * Testar a convers�o de bin�rio para decimal.
	 */
	@Test
	public void testarConversaoBinarioDecimal() {
		String binario = BaseNumerica.converterDecimalEmBinario("2");
		Assert.assertEquals("10",binario);
		binario = BaseNumerica.converterDecimalEmBinario("3");
		Assert.assertEquals("11",binario);
	}

	/**
	 * Testar convers�o de decimal para hexadecimal.
	 */
	@Test
	public void testarConversaoDecimalHexadecimal() {
		String hexa = BaseNumerica.converterDecimalEmHexadecimal("10");
		Assert.assertEquals("A", hexa);
		hexa = BaseNumerica.converterDecimalEmHexadecimal("1");
		Assert.assertEquals("1", hexa);
		hexa = BaseNumerica.converterDecimalEmHexadecimal("11");
		Assert.assertEquals("B", hexa);
	}

}