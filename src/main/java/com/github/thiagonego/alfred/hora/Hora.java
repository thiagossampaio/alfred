package br.com.twsoftware.alfred.hora;

import java.util.StringTokenizer;

import br.com.twsoftware.alfred.excecoes.ExcecaoUtil;


/**
 * Representa uma hora qualquer n�o levando em conta nenhuma data espec�fica,
 * apenas o hor�rio.
 * 
 * @author T312587
 * 
 */
public class Hora {
	private int hora;
	private int minuto;
	private int segundo;
	private int milisegundos;

	public int getMilisegundos() {
		return milisegundos;
	}

	public void setMilisegundos(int milisegundos) {
		this.milisegundos = milisegundos;
	}

	/**
	 * N�mero de segundos do hor�rio informado.
	 * 
	 * @return Segundos
	 */
	public int getSegundo() {
		return segundo;
	}

	/**
	 * Carrega novo n�mero de segundos para o hor�rio.
	 * 
	 * @param N�mero
	 *            de segundos
	 */
	public void setSegundo(int segundo) {
		testaSegundo(segundo);
		this.segundo = segundo;
	}

	private void testaSegundo(int segundo) {
		if (segundo > 59) {
			excecaoHoraInvalida();
		}
	}

	/**
	 * N�mero de horas do hor�rio informado.
	 * 
	 * @return Horas
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * Carrega novo n�mero de horas para o hor�rio.
	 * 
	 * @param N�mero
	 *            de horas
	 */
	public void setHora(int hora) {
		testaHora(hora);
		this.hora = hora;
	}

	private void testaHora(int hora) {
		if (hora > 23) {
			excecaoHoraInvalida();
		}
	}

	private void excecaoHoraInvalida() {
		throw new ExcecaoUtil("Hora inv�lida");
	}

	/**
	 * N�mero de minutos do hor�rio informado.
	 * 
	 * @return Minutos
	 */
	public int getMinuto() {
		return minuto;
	}

	/**
	 * Carrega novo n�mero de horas para o hor�rio.
	 * 
	 * @param N�mero
	 *            de minutos
	 */
	public void setMinuto(int minuto) {
		testaMinuto(minuto);
		this.minuto = minuto;
	}

	private void testaMinuto(int minuto) {
		if (minuto > 59) {
			excecaoHoraInvalida();
		}
	}

	/**
	 * Cria um objeto com a hora informada. A hora informada dever� estar num
	 * dos seguintes formatos:<br>
	 * <br>
	 * &nbsp;&nbsp;- hh:nn:ss<br>
	 * &nbsp;&nbsp;- hh:nn<br>
	 */
	public Hora(String hora) {
		carregaHora(hora);
	}

	/**
	 * Verifica se esta hora � anterior a outra hora informada.
	 * <p>
	 * Ex.:
	 * 
	 * <pre>
	 * Hora h1 = new Hora(15, 20);
	 * Hora h2 = new Hora(09, 45);
	 * System.out.println(h1.antes(h2)); // imprime &quot;false&quot;
	 * </pre>
	 * 
	 * @param hora
	 *            A hora a ser comparada
	 * @return <code>true</code> se esta hora for anterior � informada; caso
	 *         contr�rio, retorna <code>false</code>.
	 * @see #depois(Hora)
	 */
	public boolean antes(Hora hora) {
		return getNumeroDeTotalDeSegundos() < hora.getNumeroDeTotalDeSegundos();
	}

	/**
	 * Verifica se esta hora � posterior a outra hora informada.
	 * <p>
	 * Ex.:
	 * 
	 * <pre>
	 * Hora h1 = new Hora(15, 20);
	 * Hora h2 = new Hora(09, 45);
	 * System.out.println(h1.depois(h2)); // imprime &quot;true&quot;
	 * </pre>
	 * 
	 * @param hora
	 *            A hora a ser comparada
	 * @return <code>true</code> se esta hora for posterior � informada; caso
	 *         contr�rio, retorna <code>false</code>.
	 * @see #antes(Hora)
	 */
	public boolean depois(Hora hora) {
		return getNumeroDeTotalDeSegundos() > hora.getNumeroDeTotalDeSegundos();
	}

	private void carregaHora(String hora) {
		StringTokenizer token = new StringTokenizer(hora, ":");
		try {
			setHora(Integer.parseInt(token.nextToken()));
			setMinuto(Integer.parseInt(token.nextToken()));
			if (token.hasMoreTokens()) {
				setSegundo(Integer.parseInt(token.nextToken()));
			}
		} catch (Exception e) {
			throw new ExcecaoUtil("Hora inv�lida", e);
		}
	}

	/**
	 * Cria um objeto que representa a primeira hora (00:00).
	 */
	public Hora() {
		// setHora(0);
		// setMinuto(0);
	}

	/**
	 * 
	 * @param N�mero
	 *            de segundos desde as zero horas do dia.
	 */
	public Hora(int numeroTotalDeSegundos) {
		setHora(numeroTotalDeSegundos / 3600);
		setMinuto((numeroTotalDeSegundos % 3600) / 60);
		setSegundo(((numeroTotalDeSegundos % 3600) % 60));
	}

	/**
	 * Cria um novo objeto informando a hora e o minuto do hor�rio.
	 * 
	 * @param N�mero
	 *            de horas.
	 * @param N�mero
	 *            de minutos.
	 */
	public Hora(int hora, int minuto) {
		setHora(hora);
		setMinuto(minuto);
		setSegundo(0);
	}

	/**
	 * Cria um novo objeto informando a hora, o minuto e o segundo do hor�rio.
	 * 
	 * @param N�mero
	 *            de horas.
	 * @param N�mero
	 *            de minutos.
	 * @param N�mero
	 *            de segundos.
	 */
	public Hora(int hora, int minuto, int segundo) {
		setHora(hora);
		setMinuto(minuto);
		setSegundo(segundo);
	}

	private String formataValorHorario(int valor) {
		if (valor < 10) {
			return "0" + valor;
		}
		return valor + "";
	}

	@Override
	public String toString() {
		return formataValorHorario(getHora()) + ":"
				+ formataValorHorario(getMinuto());
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Hora) {
			return getNumeroDeTotalDeSegundos() == ((Hora) objeto)
					.getNumeroDeTotalDeSegundos();
		}
		return false;
	}

	/**
	 * Retorna o hor�rio devidamente formatado (hh:nn:ss).
	 * 
	 * @return Hor�rio
	 */
	public String getHoraCompleta() {
		return formataValorHorario(getHora()) + ":"
				+ formataValorHorario(getMinuto()) + ":"
				+ formataValorHorario(getSegundo());
	}

	public String getHoraCompletaComMilisegundos() {
		return getHoraCompleta() + ","
				+ formataValorDoMilisegundo(getMilisegundos());
	}

	private String formataValorDoMilisegundo(int milisegundos) {
		if (milisegundos < 10) {
			return "00" + milisegundos;
		} else if (milisegundos < 100) {
			return "0" + milisegundos;
		}
		return milisegundos + "";
	}

	/**
	 * Retorna o n�mero de segundos desde as zero horas.
	 * 
	 * @return N�mero de segundos.
	 */
	public int getNumeroDeTotalDeSegundos() {
		return ((getHora() * 3600) + (getMinuto() * 60) + getSegundo());
	}
}