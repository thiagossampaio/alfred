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

import br.com.twsoftware.alfred.conversores.Massa;

/**
 * Classe de Teste para convers�o de massa.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
public class MassaTest {

	@Test
	public void testarConversaoToneladaQuilograma() {
		Assert.assertEquals(1000D, Massa.converter(1, Massa.TONELADA, Massa.QUILOGRAMA));
	}

	@Test
	public void testarConversaoQuilogramaHectograma() {
		Assert.assertEquals(10D, Massa.converter(1, Massa.QUILOGRAMA, Massa.HECTOGRAMA));
	}

	@Test
	public void testarConversaoHectogramaGrama() {
		Assert.assertEquals(100D, Massa.converter(1, Massa.HECTOGRAMA, Massa.GRAMA));
	}

	@Test
	public void testarConversaoGramaCentigrama() {
		Assert.assertEquals(100D, Massa.converter(1, Massa.GRAMA, Massa.CENTIGRAMA));
	}

	@Test
	public void testarConversaoCentigramaMiligrama() {
		Assert.assertEquals(10D, Massa.converter(1, Massa.CENTIGRAMA, Massa.MILIGRAMA));
	}

	@Test
	public void testarConversaoMiligramaMicrograma() {
		Assert.assertEquals(1000D, Massa.converter(1, Massa.MILIGRAMA, Massa.MICROGRAMA));
	}

	@Test
	public void testarConversaoMicrogramaNanograma() {
		Assert.assertEquals(1000D, Massa.converter(1, Massa.MICROGRAMA, Massa.NANOGRAMA));
	}

}