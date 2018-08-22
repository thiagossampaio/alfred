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
package br.com.twsoftware.alfred.conversores;

/**
 * Conversor de Unidades de Medidas de armazenamento.
 * 
 * @author Marlon Silva Carvalho
 * @since 08/06/2009
 */
final public class Armazenamento {
	public static int BIT = 1;
	public static int KILOBIT = 2;
	public static int MEGABIT = 3;
	public static int GIGABIT = 4;
	public static int TERABIT = 5;
	public static int PETABIT = 6;
	public static int EXABIT = 7;
	public static int BYTE = 8;
	public static int KILOBYTE = 9;
	public static int MEGABYTE = 10;
	public static int GIGABYTE = 11;
	public static int TERABYTE = 12;
	public static int PETABYTE = 13;
	public static int EXABYTE = 14;

	private Armazenamento() {}

	/**
	 * Converter um valor entre duas grandezas de armazenamento em inform�tica.<br><br>
	 * Exemplo de uso:
	 * <br> 
	 *  // Converter 1 megabyte em bytes.<br>
	 * 	Armazenamento.converter(1, Armazenamento.MEGABYTE, Armazenamento.BYTE);
	 *  
	 * @param valor Valor a ser convertido.
	 * @param unidadeEntrada Tipo do valor de entrada (bytes, kilobytes, megabytes, etc).
	 * @param unidadeSaida Tipo de valor de sa�da (bytes, kilobytes, megabytes, etc).
	 * @return Valor conertido.
	 */
	public static double converter(double valor, int unidadeEntrada, int unidadeSaida) {
		if ( unidadeSaida < BYTE && unidadeEntrada > EXABIT ) {
			for(int i=0; i<(unidadeEntrada-BYTE);i++)
				valor *= 1024;
			valor *= 8;
			unidadeEntrada = BIT;
		}
		if ( unidadeSaida > EXABIT && unidadeEntrada < BYTE ) {
			for(int i=0; i<(unidadeEntrada-BIT);i++)
				valor *= 1024;
			valor /= 8;
			unidadeEntrada = BYTE;
		}
		if ( unidadeEntrada < unidadeSaida ) {
			for(int i=0; i<(unidadeSaida-unidadeEntrada);i++)
				valor /= 1024;
		} else {
			for(int i=0; i<(unidadeEntrada-unidadeSaida);i++)
				valor *= 1024;			
		}
		return valor;
	}

}