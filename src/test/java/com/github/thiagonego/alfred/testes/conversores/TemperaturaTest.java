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
package br.com.twsoftware.alfred.testes.conversores;

import junit.framework.Assert;

import org.junit.Test;

import br.com.twsoftware.alfred.conversores.Temperatura;

/**
 * Teste de convers�o de Temperatura.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
public class TemperaturaTest {

	@Test
	public void testarConverterCelsiusFahrenheit() {
		Assert.assertEquals(33.8F, Temperatura.converterCelciusEmFahrenheit(1));
	}

	@Test
	public void testarConverterCelsiusKelvin() {
		Assert.assertEquals(274.15F, Temperatura.converterCelciusEmKelvin(1));
	}

	@Test
	public void testarConverterFahrenheitCelcius() {
		Assert.assertEquals(-17.222221F, Temperatura.converterFahrenheitEmCelcius(1));
	}
	
	@Test
	public void testarConverterFahrenheitKelvin() {
		Assert.assertEquals(255.92776F, Temperatura.converterFahrenheitEmKelvin(1));
	}

	@Test
	public void testarConverterKelvinCelsius() {
		Assert.assertEquals(-272.15F, Temperatura.converterKelvinEmCelcius(1));
	}

	@Test
	public void testarConverterKelvinFahrenheit() {
		Assert.assertEquals(-457.86996F, Temperatura.converterKelvinEmFahrenheit(1));
	}

}