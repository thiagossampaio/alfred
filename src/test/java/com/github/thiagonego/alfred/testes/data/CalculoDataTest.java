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
package com.github.thiagonego.alfred.testes.data;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;


import org.junit.Test;

import com.github.thiagonego.alfred.data.CalculoData;

/**
 * Testar classe de utilit�ria de soma de dias.
 * 
 * @author 79325645572
 * @since 13/07/2009
 */
public class CalculoDataTest {

	@Test
	public void testarSomarDias() {
		Date data = new Date();
		Date somado = CalculoData.somarDias(data, 1);
		
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.DAY_OF_MONTH, 1);
		
		Calendar calendarSomado = Calendar.getInstance();
		calendarSomado.setTime(somado);
		
		Assert.assertEquals(calendarData.get(Calendar.DAY_OF_MONTH), calendarSomado.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(calendarData.get(Calendar.MONTH), calendarSomado.get(Calendar.MONTH));
		Assert.assertEquals(calendarData.get(Calendar.YEAR), calendarSomado.get(Calendar.YEAR));
	}

	@Test
	public void testarSomarMeses() {
		Date data = new Date();
		Date somado = CalculoData.somarMeses(data, 1);
		
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.MONTH, 1);
		
		Calendar calendarSomado = Calendar.getInstance();
		calendarSomado.setTime(somado);
		
		Assert.assertEquals(calendarData.get(Calendar.DAY_OF_MONTH), calendarSomado.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(calendarData.get(Calendar.MONTH), calendarSomado.get(Calendar.MONTH));
		Assert.assertEquals(calendarData.get(Calendar.YEAR), calendarSomado.get(Calendar.YEAR));
	}

	@Test
	public void testarSomarAnos() {
		Date data = new Date();
		Date somado = CalculoData.somarAnos(data, 1);
		
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.YEAR, 1);
		
		Calendar calendarSomado = Calendar.getInstance();
		calendarSomado.setTime(somado);
		
		Assert.assertEquals(calendarData.get(Calendar.DAY_OF_MONTH), calendarSomado.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(calendarData.get(Calendar.MONTH), calendarSomado.get(Calendar.MONTH));
		Assert.assertEquals(calendarData.get(Calendar.YEAR), calendarSomado.get(Calendar.YEAR));
	}


	@Test
	public void testarSubtrairDias() {
		Date data = new Date();
		Date somado = CalculoData.subtrairDias(data, 1);
		
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.DAY_OF_MONTH, -1);
		
		Calendar calendarSomado = Calendar.getInstance();
		calendarSomado.setTime(somado);
		
		Assert.assertEquals(calendarData.get(Calendar.DAY_OF_MONTH), calendarSomado.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(calendarData.get(Calendar.MONTH), calendarSomado.get(Calendar.MONTH));
		Assert.assertEquals(calendarData.get(Calendar.YEAR), calendarSomado.get(Calendar.YEAR));
	}

	@Test
	public void testarSubtrairMeses() {
		Date data = new Date();
		Date somado = CalculoData.subtrairMeses(data, 1);
		
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.MONTH, -1);
		
		Calendar calendarSomado = Calendar.getInstance();
		calendarSomado.setTime(somado);
		
		Assert.assertEquals(calendarData.get(Calendar.DAY_OF_MONTH), calendarSomado.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(calendarData.get(Calendar.MONTH), calendarSomado.get(Calendar.MONTH));
		Assert.assertEquals(calendarData.get(Calendar.YEAR), calendarSomado.get(Calendar.YEAR));
	}

	@Test
	public void testarSubtrairAnos() {
		Date data = new Date();
		Date somado = CalculoData.subtrairAnos(data, 1);
		
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.YEAR, -1);
		
		Calendar calendarSomado = Calendar.getInstance();
		calendarSomado.setTime(somado);
		
		Assert.assertEquals(calendarData.get(Calendar.DAY_OF_MONTH), calendarSomado.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(calendarData.get(Calendar.MONTH), calendarSomado.get(Calendar.MONTH));
		Assert.assertEquals(calendarData.get(Calendar.YEAR), calendarSomado.get(Calendar.YEAR));
	}
	
	@Test
	public void testarDiferencaDias() {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal1.set(Calendar.MONTH, 1);
		cal1.set(Calendar.YEAR, 2000);

		cal2.set(Calendar.DAY_OF_MONTH, 20);
		cal2.set(Calendar.MONTH, 1);
		cal2.set(Calendar.YEAR, 2000);

		Assert.assertEquals(19, CalculoData.calcularDiferencaDias(cal1.getTime(), cal2.getTime()));
	}
}