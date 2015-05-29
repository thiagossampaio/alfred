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
package br.com.twsoftware.alfred.testes.colecoes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.twsoftware.alfred.colecoes.Colecoes;

/**
 * Testar utilit�rio de Cole��es.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/07/2009
 */
@SuppressWarnings("all")
public class ColecoesTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testarColecaoOrdenar() {
		ClasseBean a = new ClasseBean("A");
		ClasseBean b = new ClasseBean("B");
		ClasseBean c = new ClasseBean("C");
		List<ClasseBean> colecao = new ArrayList<ClasseBean>();
		colecao.add(c); colecao.add(a); colecao.add(b);
		colecao = Colecoes.ordenar(colecao, "campo");
		String res = "";
		for(ClasseBean t:colecao) { 
			res += t.getCampo();
		}
		Assert.assertEquals("ABC", res);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testarColecaoRemover() {
		ClasseBean a = new ClasseBean("A");
		ClasseBean b = new ClasseBean("B");
		ClasseBean c = new ClasseBean("C");
		Collection<ClasseBean> colecao = new ArrayList<ClasseBean>();
		colecao.add(a); colecao.add(b); colecao.add(c);
		colecao = Colecoes.removerItem(colecao, "campo", "A");
		String res = "";
		for(ClasseBean t:colecao) { 
			res += t.getCampo();
		}
		Assert.assertEquals("BC", res);
	}

}