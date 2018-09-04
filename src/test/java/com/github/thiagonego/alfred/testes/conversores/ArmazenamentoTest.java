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

import junit.framework.Assert;


import org.junit.Test;

import com.github.thiagonego.alfred.conversores.Armazenamento;

/**
 * Teste de convers�o de Armazenamento.
 * 
 * @author Marlon 
 * @since 10/07/2009
 */
public class ArmazenamentoTest {

	@Test
	public void testarConverterExabytePetabyte() {
		Assert.assertEquals(1024D, Armazenamento.converter(1, Armazenamento.EXABYTE, Armazenamento.PETABYTE));
	}

	@Test
	public void testarConverterPetabyteTerabyte() {
		Assert.assertEquals(1024D, Armazenamento.converter(1, Armazenamento.PETABYTE, Armazenamento.TERABYTE));
	}

	@Test
	public void testarConverterTerabyteGigabyte() {
		Assert.assertEquals(1024D, Armazenamento.converter(1, Armazenamento.TERABYTE, Armazenamento.GIGABYTE));
	}

	@Test
	public void testarConverterGigabyteMegabyte() {
		Assert.assertEquals(1024D, Armazenamento.converter(1, Armazenamento.GIGABYTE, Armazenamento.MEGABYTE));
	}

	@Test
	public void testarConverterMegabyteKilobyte() {
		Assert.assertEquals(1024D, Armazenamento.converter(1, Armazenamento.MEGABYTE, Armazenamento.KILOBYTE));
	}

	@Test
	public void testarConverterKilobyteByte() {
		Assert.assertEquals(1024D, Armazenamento.converter(1, Armazenamento.KILOBYTE, Armazenamento.BYTE));
	}

}