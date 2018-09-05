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
 * Classe para convers�o entre medidas de comprimento.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Comprimento {
	public static double QUILOMETRO = 1D;
	public static double METRO = 1000D;
	public static double DECIMETRO  = 10000D;
	public static double CENTIMETRO = 100000D;
	public static double MILIMETRO = 1000000D;
	public static double MICROMETRO = 1000000000D;
	public static double NANOMETRO = 1000000000000D;
	public static double ANGSTROM = 10000000000000D;
	
	private Comprimento() {}
	
	/**
	 * Converter uma unidade de comprimento para outra.
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
	 * Converter quil�metros em metros.
	 * 
	 * @param km Quil�metros.
	 * @return Metros.
	 */
	@Deprecated
	public static double converterKmEmMetros(double km) {
		return (km*1000);
	}

	/**
	 * Converter metros em quil�metros.
	 * @param m Metros.
	 * @return Km.
	 */
	@Deprecated
	public static double converterMetrosEmKm(double m) {
		return (m/1000);
	}

	/**
	 * Converter Milhas em Quil�metros.
	 * @param km Km.
	 * @return Milhas.
	 */
	public static double converterKmEmMilhas(double km) {
		return (km*0.6213711922);
	}

	/**
	 * Converter Milhas em Quil�metros.
	 * @param m Milhas.
	 * @return Km.
	 */
	public static double converterMilhasEmKm(double m) {
		return (m*1.609344);
	}

	/**
	 * Converter Jardas (Yard) em Metro.
	 * 
	 * @param j Jardas.
	 * @return Metro.
	 */
	public static double converterJardasEmMetro(double j) {
		return (j*0.914399);
	}

	/**
	 * Converter metro em jardas.
	 * 
	 * @param m Metro.
	 * @return Jardas
	 */
	public static double converterMetroEmJardas(double m) {
		return (m*1.093614);
	}
	
	/**
	 * Converter Polegada em Cent�metro.
	 * 
	 * @param p Polegada.
	 * @return Cent�metro.
	 */
	public static double converterPolegadaEmCentimentro(double p) {
		return (p*2.54);
	}
	
	/**
	 * Converter Cent�metro em Polegada.
	 * 
	 * @param c Cent�metro.
	 * @return Polegada.
	 */
	public static double converterCentimetroEmPolegada(double c) {
		return (c*0.3937007874);
	}

	/**
	 * Converter Ano Luz em Quil�metro.
	 * 
	 * @param al Ano Luz.
	 * @return Km.
	 */
	public static double converterAnoLuzEmKm(double al) {
		 return (al * 9.4607304726 * 10);
	}

	/**
	 * Converter Milha Mar�tima em Quil�metro.
	 * 
	 * @param mm Milha Mar�tima.
	 * @return Km.
	 */
	public static double converterMilhaMaritimaEmKm(double mm) {
		return (mm*1.852);
	}

	/**
	 * Converter Quil�metro em Milha Mar�tima.
	 * 
	 * @param km Km.
	 * @return Milha Mar�tima.
	 */
	public static double converterKmEmMilhaMaritima(double km) {
		return (km/1.852);
	}

	/**
	 * Converter Metro em P�s.
	 * @param m Metro.
	 * @return P�s.
	 */
	public static double converterMetroEmPes(double m) {
		return (m*3.280839895);
	}
	
	/**
	 * Converter P�s em Metro.
	 * @param p P�s.
	 * @return Metro.
	 */
	public static double converterPesEmMetro(double p) {
		return (p*0.3048);
	}

}