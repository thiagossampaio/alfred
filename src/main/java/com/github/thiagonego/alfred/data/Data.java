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

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.github.thiagonego.alfred.excecoes.ExcecaoUtil;
import com.github.thiagonego.alfred.hora.Hora;


/**
 * Utilitï¿½rios para Data.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class Data {
	
	private static final String DIA_MES_ANO_SEM_BARRAS = "ddMMyyyy";
	private static final int MILI_SEG_POR_DIA = (1000 * 60 * 60 * 24);
	public static final String HORA = "hh";
	public static final String HORA24 = "HH";
	public static final String MINUTO = "mm";
	public static final String SEGUNDO = "ss";
	public static final String MES = "MM";
	public static final String DIA = "dd";
	public static final String ANO = "yyyy";
	public static final String DIA_MES_ANO = DIA + "/" + MES + "/" + ANO;
	public static final String HORA_MINUTO = HORA24 + ":" + MINUTO;
	public static final String TIMESTAMP = DIA_MES_ANO + " " + HORA_MINUTO;
	public static final String HORA_MINUTO_SEGUNDO = HORA_MINUTO + ":"
			+ SEGUNDO;
	public static final String DATA_CACHE = "yyyy-MM-dd";
	public static final String DATA_HORA_CACHE = "yyyy-MM-dd HH:mm:ss";
	public static final String SEGUNDA = "Segunda";
	public static final String TERCA = "Terï¿½a";
	public static final String QUARTA = "Quarta";
	public static final String QUINTA = "Quinta";
	public static final String SEXTA = "Sexta";
	public static final String SABADO = "Sï¿½bado";
	public static final String DOMINGO = "Domingo";
	public static final String[] MESES_DO_ANO = { "Janeiro", "Fevereiro",
			"Marï¿½o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
			"Outubro", "Novembro", "Dezembro" };
	public static final String[] DIAS_DA_SEMANA = { DOMINGO, SEGUNDA, TERCA,
			QUARTA, QUINTA, SEXTA, SABADO };

	/**
	 * Retorna a representaï¿½ï¿½o da data por extenso.
	 * 
	 * @param data
	 *            Data que serï¿½ descrita.
	 * @return Data por extenso
	 */
	public static String getDataPorExtenso(java.util.Date data) {
		return getDia(data) + " de " + MESES_DO_ANO[getMes(data) - 1] + " de "
				+ getAno(data);
	}

	/**
	 * Retorna a data atual por extenso.
	 * 
	 * @return Data atual por extenso.
	 */
	public static String getDataAtualPorExtenso() {
		return getDataPorExtenso(getDataAtual());
	}

	/**
	 * Retorna a data atual.
	 * 
	 * @return Data atual.
	 */
	public static Date getDataAtual() {
		return getData(getTimestampEmMilis());
	}

	/**
	 * Retorna a hora atual.
	 * 
	 * @return Hora atual.
	 */
	public static Time getHoraAtual() {
		return getHora(getTimestampEmMilis());
	}

	/**
	 * Retorna o timestamp atual (informaï¿½ï¿½o de data e hora).
	 * 
	 * @return Timestamp atual.
	 */
	public static Timestamp getTimestampAtual() {
		return getTimestamp(getTimestampEmMilis());
	}

	/**
	 * Retorna um objeto da classe timestamp apartir de um nï¿½mero de
	 * milisegundos.
	 * 
	 * @param timestampEmMilis
	 *            Nï¿½mero de milisegundos.
	 * @return Timestamp.
	 */
	public static Timestamp getTimestamp(long timestampEmMilis) {
		return new Timestamp(timestampEmMilis);
	}

	/**
	 * Retorna o nï¿½mero de milisegundos atual.
	 * 
	 * @return
	 */
	public static long getTimestampEmMilis() {
		return System.currentTimeMillis();
	}

	/**
	 * Retorna um objeto da classe Date apartir de uma data informada em formato
	 * texto.
	 * 
	 * @param dataEmFormatoTexto
	 *            Data em formato texto.
	 * @param mascaraDaData
	 *            Formato da data informada.
	 * @return Data
	 * @throws ParseException
	 *             Data invï¿½lida.
	 */
	public static Date getData(String dataEmFormatoTexto, String mascaraDaData)
			throws ParseException {
		if (dataEmFormatoTexto != null) {
			SimpleDateFormat formatador = new SimpleDateFormat(mascaraDaData);
			java.util.Date parse = formatador.parse(dataEmFormatoTexto);
			long horaEmMilis = parse.getTime();
			return new Date(horaEmMilis);
		}
		return null;
	}

	public static long getDataEmMilis(String dataEmFormatoTexto,
			String mascaraDaData) throws ParseException {
		if (dataEmFormatoTexto != null) {
			SimpleDateFormat formatador = new SimpleDateFormat(mascaraDaData);
			java.util.Date parse = formatador.parse(dataEmFormatoTexto);
			return parse.getTime();
		}
		throw new RuntimeException("Parï¿½metros invï¿½lidos para montagem da data");
	}

	/**
	 * Retorna um objeto da classe Date apartir de um nï¿½mero de milisegundos
	 * informado.
	 * 
	 * @param dataEmMilisegundos
	 *            Nï¿½mero de milisegundos que representa a data.
	 * @return Data
	 */
	public static Date getData(long dataEmMilisegundos) {
		return new Date(dataEmMilisegundos);
	}

	/**
	 * Retorna um objeto da classe Time apartir de um nï¿½mero de milisegundos.
	 * 
	 * @param horaEmMilisegundos
	 *            Nï¿½mero de milisegundos que representa a hora.
	 * @return Hora
	 */
	public static Time getHora(long horaEmMilisegundos) {
		return new Time(horaEmMilisegundos);
	}

	/**
	 * Retorna uma data formatada segundo o formato informado.
	 * 
	 * @param data
	 *            Data que serï¿½ formatada.
	 * @param formato
	 *            Formato em que a data deve ser colocada.
	 * @return Data formatada.
	 */
	public static String getDataFormatada(java.util.Date data, String formato) {
		try {
			SimpleDateFormat formatador = new SimpleDateFormat(formato);
			return formatador.format(data);
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * Retorna uma data formatada segundo o formatato informado.
	 * 
	 * @param dataEmMilisegundos
	 *            Data em milisegundos para formatar.
	 * @param formato
	 *            Formato em que a data deverï¿½ ser colocada.
	 * @return Data formatada.
	 */
	public static String getDataFormatada(long dataEmMilisegundos,
			String formato) {
		return getDataFormatada(getData(dataEmMilisegundos), formato);
	}

	/**
	 * Retorna uma hora formatada segundo o formato informado.
	 * 
	 * @param time
	 *            Hora para formatar.
	 * @param formato
	 *            Formato em que a hora deverï¿½ ser colocada.
	 * @return Hora formatada.
	 */
	public static String getHoraFormatada(Time time, String formato) {
		SimpleDateFormat formatador = new SimpleDateFormat(formato);
		return formatador.format(time);
	}

	/**
	 * Retorna a hora atual formatada.
	 * 
	 * @return Hora formatada.
	 */
	public static String getHoraAtualFormatada() {
		return getHoraFormatada(getHoraAtual(), HORA_MINUTO);
	}

	/**
	 * Soma determinado nï¿½mero de dias na data informada.
	 * 
	 * @param data
	 *            Data base.
	 * @param dias
	 *            Nï¿½mero de dias que deverï¿½ ser somado ï¿½ data informada.
	 * @return Data.
	 */
	public static Date somaDias(Date data, int dias) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		calendario.add(Calendar.DAY_OF_YEAR, dias);
		return new Date(calendario.getTimeInMillis());
	}

	public static Date somaHoras(Date data, int horas) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		calendario.add(Calendar.HOUR, horas);
		return new Date(calendario.getTimeInMillis());
	}

	public static Date somaMeses(Date data, int meses) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		calendario.add(Calendar.MONTH, meses);
		return new Date(calendario.getTimeInMillis());
	}

	public static Date somaAnos(Date data, int anos) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		calendario.add(Calendar.YEAR, anos);
		return new Date(calendario.getTimeInMillis());
	}

	/**
	 * Retorna a diferenï¿½a entre duas datas na forma de milisegundos.
	 * 
	 * @param dataFinal
	 *            Data final.
	 * @param dataInicial
	 *            Data inicial.
	 * @return Diferenï¿½a entre as datas.
	 */
	public static long diferencaEmMilis(Date dataFinal, Date dataInicial) {
		return dataFinal.getTime() - dataInicial.getTime();
	}

	/**
	 * Retorna a diferenï¿½a entre duas datas em dias.
	 * 
	 * @param dataFinal
	 *            Data final.
	 * @param dataInicial
	 *            Data inicial.
	 * @return Diferenï¿½a entre as datas.
	 */
	public static long diferencaEmDias(Date dataFinal, Date dataInicial) {
		return diferencaEmMilis(dataFinal, dataInicial) / MILI_SEG_POR_DIA;
	}

	/**
	 * Retorna a diferenï¿½a entre duas datas em anos.
	 * 
	 * @param dataFinal
	 *            Data final.
	 * @param dataInicial
	 *            Data inicial.
	 * @return Diferenï¿½a entre as datas.
	 */
	public static long diferencaEmAnos(Date dataFinal, Date dataInicial) {
		return diferencaEmDias(dataFinal, dataInicial) / 365;
	}

	/**
	 * Retorna o ano da data.
	 * 
	 * @param data
	 *            Data que se deseja extrair o ano.
	 * @return Ano.
	 */
	public static int getAno(java.util.Date data) {
		return Integer.parseInt(getDataFormatada(data, ANO));
	}

	/**
	 * Retorna o ano atual.
	 * 
	 * @return Ano atual.
	 */
	public static int getAnoAtual() {
		return getAno(getDataAtual());
	}

	/**
	 * Retorna o mï¿½s da data.
	 * 
	 * @param data
	 *            Data que se deseja extrair o mï¿½s.
	 * @return Mes.
	 */
	public static int getMes(java.util.Date data) {
		return Integer.parseInt(getDataFormatada(data, MES));
	}

	public static int getMesAtual() {
		return getMes(getDataAtual());
	}

	public static String getMesPorExtenso(Date data) {
		return MESES_DO_ANO[getMes(data) - 1];
	}

	public static String getMesAtualPorExtenso() {
		return MESES_DO_ANO[getMes(getDataAtual()) - 1];
	}

	/**
	 * Retorna o dia da data.
	 * 
	 * @param data
	 *            Data que se deseja extrair o dia.
	 * @return Dia.
	 */
	public static int getDia(java.util.Date data) {
		return Integer.parseInt(getDataFormatada(data, DIA));
	}

	public static int getDiaAtual() {
		return getDia(getDataAtual());
	}

	/**
	 * Retorna a data atual formatada.
	 * 
	 * @return Data formatada.
	 */
	public static String getDataAtualFormatada() {
		return getDataFormatada(getDataAtual(), DIA_MES_ANO);
	}

	/**
	 * Informa se uma determinada data ï¿½ vï¿½lida.
	 * 
	 * @param data
	 *            Data para verificaï¿½ï¿½o.
	 * @return Data vï¿½lida / invï¿½lida.
	 */
	public static boolean isDataValida(String data) {
		return isDataValida(data, DIA_MES_ANO);
	}

	/**
	 * Informa se uma determinada hora ï¿½ vï¿½lida.
	 * 
	 * @param hora
	 *            Hora para verificaï¿½ï¿½o.
	 * @return Hora vï¿½lida / invï¿½lida.
	 */
	public static boolean isHoraValida(String hora) {
		return isDataValida(hora, HORA_MINUTO);
	}

	/**
	 * Informa se uma data ï¿½ vï¿½lida.
	 * 
	 * @param data
	 *            Data que se deseja verificar.
	 * @param formato
	 *            Formato em que a data deve estar.
	 * @return Se a data estï¿½ correta
	 */
	public static boolean isDataValida(String data, String formato) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			sdf.setLenient(false);
			sdf.parse(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Retorna o dia da semana referente ï¿½ data passada em forma de nï¿½mero.<br>
	 * Ex: Domingo=0, Segunda=1..., Sï¿½bado=6
	 * 
	 * @param data
	 *            Data que se deseja saber o dia da semana.
	 * @return Dia da semana
	 */
	public static int getDiaDaSemana(java.util.Date data) {
		java.util.GregorianCalendar calendario = new java.util.GregorianCalendar(
				java.util.TimeZone.getDefault());
		calendario.setTime(data);
		return (calendario.get(java.util.GregorianCalendar.DAY_OF_WEEK) - 1);
	}

	/**
	 * Diz se o dia ï¿½ um sï¿½bado ou um domingo.
	 * 
	 * @param Data
	 *            Data que se deseja saber o dia da semana.
	 * @return Final de semana
	 */
	public static boolean isFinalDeSemana(Date data) {
		int dia = getDiaDaSemana(data);
		return (dia == 0 || dia == 6);
	}

	/**
	 * Diz se o dia ï¿½ nï¿½o ï¿½ um sï¿½bado ou um domingo.
	 * 
	 * @param Data
	 *            Data que se deseja saber o dia da semana.
	 * @return Se ï¿½ dia de semana
	 */
	public static boolean isDiaDeSemana(Date data) {
		return !isFinalDeSemana(data);
	}
	
	/**
	 * Verifica se uma data agendade é Sabado ou Domingo, e acrescenta proximo dia util.
	 * 
	 * @param LocalDate
	 *            Se necessita saber se uma determinada Data é Final de Semana.
	 * @return Proximo dia util.
	 */
	public static LocalDate getDiaUtilLocalDate(LocalDate data) {
		if (data.getDayOfWeek() == DayOfWeek.SATURDAY) {
			data = data.plusDays(2);
		} else if (data.getDayOfWeek() == DayOfWeek.SUNDAY) {
			data = data.plusDays(1);
		}
		return data;
	}			

	/**
	 * Retorna a descriï¿½ï¿½o do dia da semana da data informada.
	 * 
	 * @param data
	 *            Data para verificaï¿½ï¿½o.
	 * @return Dia da semana.
	 */
	public static String getDiaDaSemanaFormatado(Date data) {
		return DIAS_DA_SEMANA[getDiaDaSemana(data)];
	}

	/**
	 * Retorna um objeto da classe Time apartir de uma hora informada.
	 * 
	 * @param hora
	 *            Hora utilizada para criar o objeto.
	 * @param formatoDaHora
	 *            Formato da hora informada.
	 * @return Hora
	 */
	public static Time getHora(String hora, String formatoDaHora) {
		try {
			Date data = getData(hora, formatoDaHora);
			return new Time(data.getTime());
		} catch (ParseException e) {
			throw new ExcecaoUtil(e);
		}
	}

	/**
	 * Retorna um timestamp formatado segundo o formato especificado.
	 * 
	 * @param data
	 *            Timestamp para formatar.
	 * @param formato
	 *            Formato que deve ser aplicado.
	 * @return Timestamp formatado.
	 */
	public static String getTimestampFormatado(Timestamp data, String formato) {
		return getDataUtilFormatada(data, formato);
	}

	/**
	 * Retorna uma data formatada apatir de um objeto da classe java.util.Date e
	 * um formato especificados.
	 * 
	 * @param data
	 *            Data para formatar.
	 * @param formato
	 *            Formato deve ser aplicado ï¿½ data informada.
	 * @return Data formatada.
	 */
	private static String getDataUtilFormatada(java.util.Date data,
			String formato) {
		try {
			SimpleDateFormat formatador = new SimpleDateFormat(formato);
			return formatador.format(data);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Formata uma data informada.
	 * 
	 * @param data
	 *            Data para formatar.
	 * @return Data formatada.
	 */
	public static String getDataFormatada(java.sql.Date data) {
		return getDataUtilFormatada(data, DIA_MES_ANO);
	}

	/**
	 * Formata uma data informada.
	 * 
	 * @param data
	 *            Data para formatar.
	 * @return Data formatada.
	 */
	public static String getDataFormatada(java.util.Date data) {
		return getDataUtilFormatada(data, DIA_MES_ANO);
	}

	/**
	 * Converte uma data em formato String para Date.
	 * 
	 * @param string
	 * @return
	 */
	public static Date getData(String string) {
		try {
			return getData(string, DIA_MES_ANO);
		} catch (ParseException e) {
			throw new ExcecaoUtil("Formato da data invï¿½lido.", e);
		}
	}

	/**
	 * Retorna o nome do dia da semana.
	 * 
	 * @param hora
	 *            Data que se deseja saber o dia da semana.
	 * @return Dia da semana.
	 */
	public static String getNomeDoDiaDaSemana(Timestamp hora) {
		int diaDaSemana = getDiaDaSemana(getData(hora.getTime()));
		return DIAS_DA_SEMANA[diaDaSemana];
	}

	/**
	 * Retorna o nome do dia da semana.
	 * 
	 * @param dia
	 *            Data que se deseja saber o dia da semana.
	 * @return Dia da semana.
	 */
	public static String getNomeDoDiaDaSemana(Date dia) {
		int diaDaSemana = getDiaDaSemana(dia);
		return DIAS_DA_SEMANA[diaDaSemana];
	}

	/**
	 * Calcula o ï¿½ltimo dia do mï¿½s informado.
	 * 
	 * @param mes
	 *            Mï¿½s
	 * @param ano
	 *            Ano
	 * @return ï¿½ltimo dia.
	 */
	public static int getUltimoDiaDoMes(int mes, int ano) {
		int mesesCom30[] = { 4, 6, 9, 11 };
		if (java.util.Arrays.binarySearch(mesesCom30, mes) > -1) {
			return 30;
		}
		if (mes == 2) {
			if (ano % 4 == 0) {
				return 29;
			}
			return 28;
		}
		return 31;
	}

	public static boolean isAnoBissexto(int ano) {
		return ((ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0));
	}

	public static boolean isAnoBissexto(Date data) {
		return isAnoBissexto(getAno(data));
	}

	public static void main(String[] args) {
		System.out.println(getDataFormatada(getDataAtual(),
				"EEEEE',' dd 'de' MMMMM 'de' yyyy"));
		System.out.println(getDataUtilFormatada("14061991"));
	}

	/**
	 * Converte String no formato 02072009 em 02/07/2009 String data = "";
	 * 
	 * @param data
	 * @return
	 */
	public static Date getDataUtilFormatada(String data) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat(
					DIA_MES_ANO_SEM_BARRAS);
			return getData(getDataUtilFormatada(formato.parse(data),
					DIA_MES_ANO));
		} catch (Exception e) {
			throw new ExcecaoUtil("Formato da data invï¿½lido.", e);
		}
	}

	public static Date criaDataHora(java.util.Date data, Hora hora) {
		StringBuilder horaPorExtenso = new StringBuilder(getDataFormatada(data));
		horaPorExtenso.append(" ");
		horaPorExtenso.append(hora.getHoraCompletaComMilisegundos());
		try {
			return getData(horaPorExtenso.toString(), "dd/MM/yyyy HH:mm:ss,SSS");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retorna um Timestamp para uma data com a hora, minuto e segundo
	 * informados
	 * 
	 * @author Josï¿½ Barros
	 * @param data
	 * @param hora
	 * @param minuto
	 * @param segundo
	 * @return
	 */
	public static Timestamp getTimestamp(Date data, int hora, int minuto,
			int segundo) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		calendario.set(Calendar.HOUR, hora);
		calendario.set(Calendar.MINUTE, minuto);
		calendario.set(Calendar.SECOND, segundo);
		calendario.getTime();
		return new Timestamp(calendario.getTime().getTime());
	}

	public static Time somaMinutos(Time data, int minutos) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		calendario.add(Calendar.MINUTE, minutos);
		return new Time(calendario.getTimeInMillis());
	}	
	
	/**
	 * Formatar uma data no: dd de MM de aaaa. 
	 * Exemplo: 21 de Janeiro de 2009
	 * 
	 * @param data Data a ser formatada.
	 * @return Data formatada.
	 */
	public static String formatarDataPorExtenso(Date data) {
		Locale objLocale = new Locale("pt", "BR");
		Calendar cal = null;
		cal = new GregorianCalendar();
		cal.setTime(data);
		String mes = new DateFormatSymbols(objLocale).getMonths()[cal.get(Calendar.MONTH)];
		String dia = (cal.get(Calendar.DAY_OF_MONTH) < 10)  ?  "0" + cal.get(Calendar.DAY_OF_MONTH) : String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		return new StringBuilder().append(dia).append(" de ").append(mes).append(" de ").append(cal.get(Calendar.YEAR)).toString();
	}

	/**
     * Retorna um Date para uma data com a hora, minuto, segundo e milissegundo
     * informados
     *
     * @author Josï¿½ Barros
     * @param data
     * @param hora
     * @param minuto
     * @param segundo
     * @param milissegundo
     * @return
     */
    public static Date getData(Date data, int hora, int minuto,
                    int segundo, int milissegundo) {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(data);
            calendario.set(Calendar.HOUR, hora);
            calendario.set(Calendar.MINUTE, minuto);
            calendario.set(Calendar.SECOND, segundo);
            calendario.set(Calendar.MILLISECOND, milissegundo);
            calendario.getTime();
            return getData(calendario.getTime().getTime());
    }

}