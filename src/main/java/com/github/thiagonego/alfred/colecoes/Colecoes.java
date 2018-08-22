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
package br.com.twsoftware.alfred.colecoes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import br.com.twsoftware.alfred.AlfredException;
import br.com.twsoftware.alfred.texto.Texto;


/**
 * Utilit�rios para Cole��es.
 * 
 * @since 04/06/2009
 * @author Marlon Silva Carvalho
 */
final public class Colecoes {

	/**
	 * Remover de uma cole��o todos os itens que possuam o campo "nomeCampo" com
	 * o valor "valor". N�o passar cole��o de tipos primitivos como par�metro. A
	 * cole��o deve ser composta de objetos que seguem o padr�o JavaBean.
	 * <br>
	 * <code> 
	 * class MeuObjeto { private String id;<br><br>
	 * 
	 * 		public String getId() { return id; }<br>
	 * 		public void setId(String id) { this.id = id; } <br>
	 * }<br>
	 * <br><br>
	 * Collection c = new ArrayList(); MeuObjeto mo = new MeuObjeto();<br>
	 * mo.setId("1"); c.add(mo);<br>
	 * <br><br>
	 * c = Colecoes.removerItem(c,"id", "1");<br>
	 * <br><br>
	 * // Ir� remover o item anteriormente adicionado. </code>
	 * 
	 * @param colecao Cole��o.
	 * @param nomeCampo Nome do campo que ser� checado.
	 * @param valor Valor do campo.
	 * @return Cole��o sem os itens.
	 */
	@SuppressWarnings("all")
	public static Collection removerItem(Collection colecao, String nomeCampo, Object valor) {
		try {
			Collection retorno = colecao.getClass().newInstance();
			for (Iterator it = colecao.iterator(); it.hasNext();) {
				Object o = it.next();
				Method m = o.getClass().getMethod(
						"get" + Texto.capitalizarIniciais(nomeCampo), null);
				Object r = m.invoke(o, null);
				if (r != null && r.equals(valor))
					continue;
				retorno.add(o);
			}
			return retorno;
		} catch (InstantiationException e) {
			throw new AlfredException("N�o foi poss�vel instanciar um tipo de cole��o igual ao tipo informado.");
		} catch (IllegalAccessException e) {
			throw new AlfredException(e);
		} catch (SecurityException e) {
			throw new AlfredException(e);
		} catch (NoSuchMethodException e) {
			throw new AlfredException("N�o existe o m�todo de acesso ao campo informado. Verifique se sua classe implementa o padr�o JavaBean.",e);
		} catch (IllegalArgumentException e) {
			throw new AlfredException(e);
		} catch (InvocationTargetException e) {
			throw new AlfredException(e);
		}
	}

	/**
	 * Ordenar uma cole��o conforme um determinado atributo do objeto contido na cole��o.
	 * Os objetos contidos na cole��o devem ser do mesmo tipo e implementarem a interface Comparable.
	 * 
	 * @param colecao Cole��o.
	 * @param nomeCampo Nome do campo.
	 * @return Cole��o ordenada.
	 */
	@SuppressWarnings("all")
	public static List ordenar(List colecao, final String nomeCampo) {
		if ( colecao.size() <= 0 ) 
			return colecao;
		Comparator comparator = new Comparator() {
			public int compare(Object ob1, Object ob2) {
				try {
					Method m1 = ob1.getClass().getMethod("get" + Texto.capitalizarIniciais(nomeCampo), null);
					Object r1 = m1.invoke(ob1, null);
					
					Method m2 = ob2.getClass().getMethod("get" + Texto.capitalizarIniciais(nomeCampo), null);
					Object r2 = m2.invoke(ob2, null);

					Comparable c1 = (Comparable) r1;
					Comparable c2 = (Comparable) r2;
					return c1.compareTo(c2);
				} catch (Throwable e) {
					throw new AlfredException(e);
				} 
			}
		};
		Collections.sort(colecao, comparator);
		return colecao;
	}
	
	/**
	 * M�todo para verificar se todos os elementor de uma cole��o s�o nulos
	 * @author: Thiago Sampaio
	 * @return: boolean - Verdadeiro caso esteja nulo
	 * @param colecao
	 */
	@SuppressWarnings("unchecked")
	public static boolean isEmpty(Collection colecao){
		
		if (colecao == null) return true;
		
		boolean result = true;
		for (Object obj : colecao) {
			if (obj == null) 
				result = true; 
			else 
				return false;
		}
		return result;
	}
}