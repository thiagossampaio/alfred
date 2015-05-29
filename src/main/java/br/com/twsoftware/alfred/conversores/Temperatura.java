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

import br.com.twsoftware.alfred.AlfredException;

/**
 * Classe utilit�ria para convers�o de temperaturas.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Temperatura {
	public static int CELSIUS = 1;
	public static int FAHRENHEIT = 2;
	public static int KELVIN = 3;

	private Temperatura() {}

	/**
	 * Converter um valor de temperatura para outro.
	 * 
	 * @param valor Valor que ser� convertido.
	 * @param entrada Em que tipo de representa��o o valor est� representado.
	 * @param saida Em que tipo de representa��o o valor de sa�da ser� representado.
	 * @return Valor convertido.
	 */
	public static float converter(float valor, int entrada, int saida) {
		if ( entrada == CELSIUS && saida == FAHRENHEIT )
			return converterCelciusEmFahrenheit(valor);
		if ( entrada == CELSIUS && saida == KELVIN )
			return converterCelciusEmKelvin(valor);
		if ( entrada == FAHRENHEIT && saida == CELSIUS )
			return converterFahrenheitEmCelcius(valor);
		if ( entrada == FAHRENHEIT && saida == KELVIN )
			return converterFahrenheitEmKelvin(valor);
		if ( entrada == KELVIN && saida == CELSIUS )
			return converterKelvinEmCelcius(valor);
		if ( entrada == KELVIN && saida == FAHRENHEIT )
			return converterKelvinEmFahrenheit(valor);
		throw new AlfredException("N�o foi poss�vel realizar a convers�o de temperatura solicitada.");
	}

	/**
	 * Converter de Celcius para Fahrenheit.
	 * C�digo "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 * 
	 * @param degCelcius Temperatura em Celcius.
	 * @return Temperatura em Fahrenheit.
	 */
	public static float converterCelciusEmFahrenheit(float degCelcius) {
		float degFahrenheit;
		degFahrenheit = degCelcius * 9 / 5 + 32;
		return degFahrenheit;
	}

	/**
	 * Converter de Fahrenheit para Celcius.
	 * C�digo "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 * 
	 * @param degFahrenheit Temperatura em Farenheit.
	 * @return Temperatura em Celcius.
	 */
	public static float converterFahrenheitEmCelcius(float degFahrenheit) {
		float degCelcius;
		degCelcius = (degFahrenheit - 32) * 5 / 9;
		return degCelcius;
	}

	/**
	 * Converter de Fahrenheit para Kelvin.
	 * 
	 * @param f Temperatura em Fahrenheit.
	 * @return Temperatura em Kelvin.
	 */
	public static float converterFahrenheitEmKelvin(float f) {
		float celcius = converterFahrenheitEmCelcius(f);
		return converterCelciusEmKelvin(celcius);
	}

	/**
	 * Converter uma temperatura de Kelvin para Fahrenheit.
	 * 
	 * @param k Temperatura em Kelvin.
	 * @return Temperatura em Fahrenheit.
	 */
	public static float converterKelvinEmFahrenheit(float k) {
		float celcius = converterKelvinEmCelcius(k);
		return converterCelciusEmFahrenheit(celcius);
	}
	
	/**
	 * Converter de Celcius para Kelvin.
	 * C�digo "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 * 
	 * @param degCelcius Temperatura em Celcius.
	 * @return Temperatura em Kelvin.
	 */
	public static float converterCelciusEmKelvin(float degCelcius) {
		float degKelvin;
		degKelvin = degCelcius + 273.15f;
		return degKelvin;
	}

	/**
	 * Converter de Kelvin para Celcius.
	 * C�digo "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 *  
	 * @param degKelvin Temperatura em Kelvin.
	 * @return Temperatura em Celcius.
	 */
	public static float converterKelvinEmCelcius(float degKelvin) {
		float degCelcius;
		degCelcius = degKelvin - 273.15f;
		return degCelcius;
	}

}