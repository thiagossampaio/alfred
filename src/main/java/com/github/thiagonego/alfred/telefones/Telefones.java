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
package br.com.twsoftware.alfred.telefones;

import java.util.regex.Pattern;

import br.com.twsoftware.alfred.texto.Texto;


/**
 * Classe utilit�ria para Telefones.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Telefones {

	private Telefones() {}

	/**
	 * Formatar um Telefone.
	 * A formata��o depende da quantidade de n�meros informados.
	 * Por exemplo:
	 *  12345678 ser� formatado para 1234-5678
	 *  1234567890 ser� formatado para (12) 3456-7890
	 *  1112345678 ser� formatado para +11 (12) 3456-7890
	 *  
	 * @param telefone Telefone a ser formatado.
	 * @return Telefone formatado.
	 */
	public static String formatar(String telefone) {
		String soNumeros = Texto.manterNumeros(telefone);
		StringBuilder s = new StringBuilder();
		if ( soNumeros.length() == 8 ) {
			s.append(telefone.substring(0,4));
			s.append("-");
			s.append(telefone.substring(4,8));
		}
		if ( soNumeros.length() == 10 ) {
			s.append("(");
			s.append(telefone.substring(0,2));
			s.append(") ");
			s.append(telefone.substring(2,6));
			s.append("-");
			s.append(telefone.substring(6,10));
		}
		if ( soNumeros.length() == 12 ) {
			s.append("+");
			s.append(telefone.substring(0,2));
			s.append(" (");
			s.append(telefone.substring(2,4));
			s.append(") ");
			s.append(telefone.substring(4,8));
			s.append("-");
			s.append(telefone.substring(8,12));
		}
		return (s.length() == 0 ? telefone : s.toString());
	}
	
	/**
	 * Verifica se o texto indicado representa um n�mero de telefore v�lido.
	 * N�meros de telefone devem possuir 8 d�gitos, opcionalmente separados por
	 * um tra�o ('-'). C�digo de �rea (DDD) tamb�m � opcional.
	 * 
	 * @param texto
	 *            O texto a ser verificado
	 * @return <code>true</code> se o texto for um n�mero de telefone v�lido;
	 *         caso contr�rio, retorna <code>false</code>.
	 */
	public static boolean isValido(String texto) {
		final String foneRegEx = "(\\([0-9]{2}\\)\\s?)?[0-9]{4}\\-?[0-9]{4}";
		Pattern fonePattern = Pattern.compile(foneRegEx);
		return fonePattern.matcher(texto).matches();
	} 	

}