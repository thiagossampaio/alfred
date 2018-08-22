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
 * Classe para convers�o entre medidas de massa.
 * 
 * @author Marlon Silva Carvalho
 * @since 07/06/2009
 */
final public class Massa {
	public static double TONELADA = 1D;
	public static double QUILOGRAMA = 1000D;
	public static double HECTOGRAMA = 10000D;
	public static double GRAMA = 1000000D;
	public static double CENTIGRAMA = 100000000D;
	public static double QUILATE = 5000000D;
	public static double MILIGRAMA = 1000000000D;
	public static double MICROGRAMA = 1000000000000D;
	public static double NANOGRAMA = 1000000000000000D;

	private Massa() {}

	/**
	 * Converter uma unidade de massa para outra.
	 * 
	 * @param valor Valor a ser convertido.
	 * @param unidadeEntrada Unidade de Entrada.
	 * @param unidadeSaida Unidade de Sa�da.
	 * @return Valor convertido.
	 */
	public static double converter(double valor, double unidadeEntrada, double unidadeSaida) {
		if ( unidadeEntrada > unidadeSaida )
			return (valor/(unidadeEntrada/unidadeSaida));
		else return (valor*(unidadeSaida/unidadeEntrada));
	}

	/**
	 * Converter Quilograma em Grama.
	 * 
	 * @param kg Quilograma.
	 * @return Grama.
	 */
	@Deprecated
	public static double converterKgEmGrama(double kg) {
		return (kg*1000);
	}

	/**
	 * Converter Grama em Quilograma.
	 * 
	 * @param g Grama.
	 * @return Quilograma.
	 */
	@Deprecated
	public static double converterGramaEmKg(double g) {
		return (g/1000);
	}

}