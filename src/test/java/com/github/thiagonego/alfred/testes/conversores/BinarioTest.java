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

import br.com.twsoftware.alfred.conversores.Binario;

/**
 * Testar convers�o de Bin�rio.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
public class BinarioTest {

	@Test
	public void testarConversaoBase64() {
		String valorOriginal = "Marlon";
		String codificado = Binario.codificarBase64(valorOriginal.getBytes());
		String descodificado = new String(Binario.decodificarBase64(codificado));
		Assert.assertEquals(valorOriginal, descodificado);
	}

}