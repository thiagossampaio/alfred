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
package com.github.thiagonego.alfred.data;

import java.util.Calendar;
import java.util.Date;

/**
 * Utilit�rio para realizar c�lculos com Data.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class CalculoData {

	/**
	 * Somar uma determinada quantidade de dias a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeDias Quantidade de dias a somar.
	 * @return Data com a soma de dias.
	 */
	public static Date somarDias(Date data, int quantidadeDias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, quantidadeDias);
		return calendar.getTime();
	}

	/**
	 * Subtrair uma determinada quantidade de dias a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeDias Quantidade de dias a subtrair.
	 * @return Data com a subtra��o de dias.
	 */
	public static Date subtrairDias(Date data, int quantidadeDias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, -quantidadeDias);
		return calendar.getTime();		
	}

	/**
	 * Somar uma determinada quantidade de meses a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeMeses Quantidade de meses a somar.
	 * @return Data com a soma de meses.
	 */
	public static Date somarMeses(Date data, int quantidadeMeses) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, quantidadeMeses);
		return calendar.getTime();
	}

	/**
	 * Subtrair uma determinada quantidade de meses a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeMeses Quantidade de meses a subtrair.
	 * @return Data com a subtra��o de meses.
	 */
	public static Date subtrairMeses(Date data, int quantidadeMeses) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, -quantidadeMeses);
		return calendar.getTime();
	}
	
	/**
	 * Somar uma determinada quantidade de anos a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeAnos Quantidade de anos a somar.
	 * @return Data com a soma de anos.
	 */
	public static Date somarAnos(Date data, int quantidadeAnos) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, quantidadeAnos);
		return calendar.getTime();
	}

	/**
	 * Subtrair uma determinada quantidade de anos a uma data.
	 * 
	 * @param data Data.
	 * @param quantidadeAnos Quantidade de anos a subtrair.
	 * @return Data com a subtra��o de anos.
	 */
	public static Date subtrairAnos(Date data, int quantidadeAnos) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, -quantidadeAnos);
		return calendar.getTime();
	}

	/**
	 * Calcular a quantidade de dias que existe entre as duas datas informadas.
	 * 
	 * @param data1 Data 1.
	 * @param data2 Data 2.
	 * @return Diferen�a em dias.
	 */
	public static int calcularDiferencaDias(Date data1, Date data2) {
		Calendar calendarData1 = Calendar.getInstance();
		calendarData1.setTime(data1);
		Calendar calendarData2 = Calendar.getInstance();
		calendarData2.setTime(data2);
		return Math.abs(calendarData1.get(Calendar.DAY_OF_YEAR) - calendarData2.get(Calendar.DAY_OF_YEAR));
	}

}