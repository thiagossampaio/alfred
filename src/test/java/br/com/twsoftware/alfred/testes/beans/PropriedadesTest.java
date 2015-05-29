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
package br.com.twsoftware.alfred.testes.beans;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;


import org.junit.Test;

import br.com.twsoftware.alfred.beans.Propriedades;

/**
 * Teste do Utilit�rio de Propriedades.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
public class PropriedadesTest {

	@Test
	public void testarTransformarEmMap() {
		ClasseTeste a1 = new ClasseTeste("A1","A2");
		Map<String,Object> retorno = Propriedades.transformarEmMap(a1, ClasseTeste.class);
		String rKeys = "";
		String rRet = "";
		for(String key:retorno.keySet()) {
			rKeys += key;
			rRet += retorno.get(key);
		}
		Assert.assertEquals("campo2campo1", rKeys);
		Assert.assertEquals("A2A1", rRet);
	}

	@Test
	public void testarCopiarPropriedades() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("campo1","A1");
		map.put("campo2","A2");
		ClasseTeste c = new ClasseTeste("a","a");
		Propriedades.copiarPropriedades(map, c);
		Assert.assertEquals(c.getCampo1(), "A1");
		Assert.assertEquals(c.getCampo2(), "A2");
	}
	
}