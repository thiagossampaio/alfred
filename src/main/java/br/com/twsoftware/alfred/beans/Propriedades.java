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
package br.com.twsoftware.alfred.beans;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import br.com.twsoftware.alfred.AlfredException;
import br.com.twsoftware.alfred.texto.Texto;


/**
 * Utilit�rios para trabalhar com propriedades de objetos que seguem o padr�o Java Bean.
 * 
 * @author Marlon Silva Carvalho
 * @since 17/06/2009
 */
final public class Propriedades {

	private Propriedades() { }
	
	/**
	 * Colocar em um Map todas as propriedades de um objeto.
	 * 
	 * @param objeto Objeto.
	 * @param classe Classe do objeto.
	 * @return Map.
	 */
	@SuppressWarnings("all")
	public static Map<String,Object> transformarEmMap(Object objeto, Class classe) {
		Field[] fields = classe.getDeclaredFields();
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i = 0 ; i < fields.length; i++) {
			Field field = fields[i];
			try {
				if ( ! field.isAccessible() ) {
					Method method;
					try {
						method = objeto.getClass().getMethod("get" + Texto.capitalizarIniciais(field.getName()), null);
						Object valor = method.invoke(objeto, null);
						map.put(field.getName(),valor);
					} catch (NoSuchMethodException e) { 
						// Ignorar a exce��o e copiar os demais campos. 
					}
				} else {
					Object valor = field.get(objeto);
					map.put(field.getName(),valor);
				}
			} catch (IllegalArgumentException e) {
				throw new AlfredException(e);
			} catch (IllegalAccessException e) {
				throw new AlfredException(e);
			} catch (SecurityException e) {
				throw new AlfredException(e);
			} catch (InvocationTargetException e) {
				throw new AlfredException(e);
			}
		}
		return map;
	}

	/**
	 * Copiar os valores contidos em um Map para um objeto.
	 *  
	 * @param propriedades Map com os valores.
	 * @param objeto Objeto que receber� os valores
	 */
	public static void copiarPropriedades(Map<String,Object> propriedades, Object objeto) {
		for(String chave:propriedades.keySet()) {
			try {
				if ( propriedades.get(chave) == null )
					continue;
				Method method = objeto.getClass().getMethod("set" + Texto.capitalizarIniciais(chave), new Class[] {propriedades.get(chave).getClass()});
				method.invoke(objeto, new Object[] {propriedades.get(chave)});
			} catch (SecurityException e) {
				throw new AlfredException(e);
			} catch (NoSuchMethodException e) {
				throw new AlfredException(e);
			} catch (IllegalArgumentException e) {
				throw new AlfredException(e);
			} catch (IllegalAccessException e) {
				throw new AlfredException(e);
			} catch (InvocationTargetException e) {
				throw new AlfredException(e);
			}
		}
	}

}