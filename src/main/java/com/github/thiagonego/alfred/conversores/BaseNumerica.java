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
package com.github.thiagonego.alfred.conversores;


/**
 * Utilit�rio para convers�o entre bases num�ricas.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class BaseNumerica {

	/**
	 * Converter um n�mero Decimal para Bin�rio. 
	 * 
	 * @param decimal N�mero decimal.
	 * @return N�mero em base bin�ria.
	 */
	public static String converterDecimalEmBinario(String decimal) {
		return Long.toBinaryString(Long.valueOf(decimal));
	}

	/**
	 * Converter um n�mero Bin�rio para Decimal.
	 * 
	 * @param binario N�mero bin�rio.
	 * @return N�mero em base decimal.
	 */
	public static String converterBinarioEmDecimal(String binario) {
		return String.valueOf(Long.parseLong(binario,2));
	}

	/**
	 * Converter um n�mero Decimal para Hexadecimal.
	 * 
	 * @param decimal N�mero decimal.
	 * @return N�mero em base hexadecimal.
	 */
	public static String converterDecimalEmHexadecimal(String decimal) {
		return Long.toHexString(Long.valueOf(decimal)).toUpperCase();
	}

	/**
	 * Converter um n�mero Hexadecimal para Decimal.
	 * 
	 * @param hexadecimal N�mero hexadecimal.
	 * @return N�mero em base decimal.
	 */
	public static String converterHexadecimalEmDecimal(String hexadecimal) {
		return String.valueOf(Long.parseLong(hexadecimal,16));
	}

	/**
	 * Converter um n�mero Decimal para Octa.
	 * 
	 * @param decimal N�mero decimal.
	 * @return N�mero em base octa.
	 */
	public static String converterDecimalEmOcta(String decimal) {
		return Long.toOctalString(Long.valueOf(decimal)).toUpperCase();
	}

	/**
	 * Converter um n�mero Octa para Decimal.
	 * C�digo obtido do Linha de C�digo no endere�o
	 * <link>http://www.linhadecodigo.com.br/Codigo.aspx?id=407</link>
	 * 
	 * @param octa N�mero octa.
	 * @return N�mero em base decimal.
	 */
	public static String converterOctaEmDecimal(String octa) {
		return String.valueOf(Long.parseLong(octa,8));
	}

}