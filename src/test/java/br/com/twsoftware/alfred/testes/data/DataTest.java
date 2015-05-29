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
package br.com.twsoftware.alfred.testes.data;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import junit.framework.Assert;


import org.junit.Test;

import br.com.twsoftware.alfred.data.Data;

/**
 * Classe de Teste para Data.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
public class DataTest {

	@Test
	public void testarFormatacaoExtensa() {
		Date agora = Data.getDataAtual();
		String data = Data.formatarDataPorExtenso(agora);
		
		Calendar cal = null;
		cal = new GregorianCalendar();
		cal.setTime(agora);
		String mes = new DateFormatSymbols(Locale.getDefault()).getMonths()[cal.get(Calendar.MONTH)];
		String dia = (cal.get(Calendar.DAY_OF_MONTH) < 10)  ?  "0" + cal.get(Calendar.DAY_OF_MONTH) : String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		String b = new StringBuilder().append(dia).append(" de ").append(mes).append(" de ").append(cal.get(Calendar.YEAR)).toString();
		Assert.assertEquals(b,data);
	}

}