/*
 * 
 * Created on Nov 8, 2004
 *
 */
package com.github.thiagonego.alfred.extenso;

import java.util.StringTokenizer;

/**
 * @author Vanessa Rocha
 * 
 */
public class Extenso {
	private static final String NUMEROS[] = { "zero", "um", "dois", "tr�s",
			"quatro", "cinco", "seis", "sete", "oito", "nove", "dez", "onze",
			"doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete",
			"dezoito", "dezenove" };
	private static final String DEZENAS[] = { "vinte", "trinta", "quarenta",
			"cinquenta", "sessenta", "setenta", "oitenta", "noventa" };
	private static final String CENTENAS[] = { "cento", "cem", "duzentos",
			"trezentos", "quatrocentos", "quinhentos", "seissentos",
			"setessentos", "oitocentos", "novecentos" };
	// na casa das centenas n�o se coloca nenhum complemento
	// o complemento da casa dos milhares � o mesmo tanto no singular quanto no
	// plural
	private static final String CASAS_DECIMAIS_SINGULAR[] = { "", "mil",
			"milh�o", "bilh�o", "trilh�o", "quatrilh�o" };
	private static final String CASAS_DECIMAIS_PLURAL[] = { "", "mil",
			"milh�es", "bilh�es", "trilh�es", "quatrilh�es" };

	private static final String NUMEROS_ORDINAIS[] = { "zero", "primeiro",
			"segundo", "terceiro", "quarto", "quinto", "sexto", "s�timo",
			"oitavo", "nono", "d�cimo", "d�cimo primeiro", "d�cimo segundo",
			"d�cimo terceiro", "d�cimo quarto", "d�cimo quinto",
			"d�cimo sexto", "d�cimo s�timo", "d�cimo oitavo", "d�cimo nono" };
	private static final String DEZENAS_ORDINAIS[] = { "vig�simo", "trig�simo",
			"quadrag�simo", "q�inquag�simo", "sexag�simo", "septuag�simo",
			"octag�simo", "nonag�simo" };
	private static final String CENTENAS_ORDINAIS[] = { "cent�simo",
			"ducent�simo", "trecent�simo", "quadringent�simo", "q�ingent�simo",
			"sexcent�simo", "septingent�simo", "octingent�simo", "nongent�simo" };
	// na casa das centenas n�o se coloca nenhum complemento
	// o complemento da casa dos milhares � o mesmo tanto no singular quanto no
	// plural
	private static final String CASAS_DECIMAIS_ORDINAIS_SINGULAR[] = { "",
			"mil�simo,", "milion�simo,", "bilion�simo," };
	
	private static final String NUMEROS_ORDINAIS_FEMININOS[] = { "zero", "primeira",
		"segunda", "terceira", "quarta", "quinta", "sexta", "s�tima",
		"oitava", "nona", "d�cima", "d�cima primeira", "d�cima segunda",
		"d�cima terceira", "d�cima quarta", "d�cima quinta",
		"d�cima sexta", "d�cima s�tima", "d�cima oitava", "d�cima nona" };
	private static final String DEZENAS_ORDINAIS_FEMININOS[] = { "vig�sima", "trig�sima",
		"quadrag�sima", "q�inquag�sima", "sexag�sima", "septuag�sima",
		"octag�sima", "nonag�sima" };
	private static final String CENTENAS_ORDINAIS_FEMININOS[] = { "cent�sima",
		"ducent�sima", "trecent�sima", "quadringent�sima", "q�ingent�sima",
		"sexcent�sima", "septingent�sima", "octingent�sima", "nongent�sima" };
	// na casa das centenas n�o se coloca nenhum complemento
	// o complemento da casa dos milhares � o mesmo tanto no singular quanto no
	// plural
	private static final String CASAS_DECIMAIS_ORDINAIS_FEMININOS_SINGULAR[] = { "",
			"mil�sima,", "milion�sima,", "bilion�sima," };	

	private static int extraiUnidade(int numero) {
		return numero - (extraiCentena(numero) * 100)
				- (extraiDezena(numero) * 10);
	}

	private static int extraiDezena(int numero) {
		return (numero - (extraiCentena(numero) * 100)) / 10;
	}

	private static int extraiCentena(int numero) {
		return numero / 100;
	}

	public static String getNumeroPorExtenso(String numero) {
		if (numero.indexOf(",") < 0) {
			return decodificaNumeroInteiro(numero);
		}
		return decodificaNumeroDecimal(numero);
	}

	public static String getNumeroOrdinalPorExtenso(String numero) {
		if (numero.indexOf(",") < 0) {
			return decodificaNumeroOrdinalInteiro(numero);
		}
		return decodificaNumeroOrdinal(numero);
	}
	
	public static String getNumeroOrdinalFemininoPorExtenso(String numero) {
		if (numero.indexOf(",") < 0) {
			return decodificaNumeroOrdinalFemininoInteiro(numero);
		}
		return decodificaNumeroOrdinalFeminino(numero);
	}

	private static String decodificaNumeroDecimal(String numero) {
		StringTokenizer partes = new StringTokenizer(numero, ",");
		String parte1 = decodificaNumeroInteiro(partes.nextToken());
		String decimal = partes.nextToken();
		if (Integer.parseInt(decimal) == 0) {
			return parte1;
		}
		String parte2 = decodificaNumeroInteiro(decimal);
		return parte1 + " v�rgula " + parte2;
	}

	private static String decodificaNumeroOrdinal(String numero) {
		StringTokenizer partes = new StringTokenizer(numero, ",");
		String parte1 = decodificaNumeroOrdinalInteiro(partes.nextToken());
		String decimal = partes.nextToken();
		if (Integer.parseInt(decimal) == 0) {
			return parte1;
		}
		String parte2 = decodificaNumeroOrdinalInteiro(decimal);
		return parte1 + " v�rgula " + parte2;
	}
	
	private static String decodificaNumeroOrdinalFeminino(String numero) {
		StringTokenizer partes = new StringTokenizer(numero, ",");
		String parte1 = decodificaNumeroOrdinalFemininoInteiro(partes.nextToken());
		String decimal = partes.nextToken();
		if (Integer.parseInt(decimal) == 0) {
			return parte1;
		}
		String parte2 = decodificaNumeroOrdinalFemininoInteiro(decimal);
		return parte1 + " v�rgula " + parte2;
	}

	public static String getValorMonetarioPorExtenso(String numero) {
		String numeroSemPontos = "";
		for (int i = 0; i < numero.length(); i++) {
			if ('.' != numero.charAt(i)) {
				numeroSemPontos += numero.charAt(i);
			}
		}
		StringTokenizer partes = new StringTokenizer(numeroSemPontos, ",");
		String extenso = decodificaNumeroInteiro(partes.nextToken());
		if ("Um".equals(extenso)) {
			extenso += " Real";
		} else {
			extenso += " Reais";
		}
		if (!partes.hasMoreTokens()) {
			return extenso;
		}
		String centavos = partes.nextToken();
		// se houver somente uma casa decimal, acrescenta o zero
		if (centavos.length() < 2) {
			centavos = centavos + '0';
		}
		int centavosInt = Integer.parseInt(centavos);
		if (centavosInt == 1) {
			extenso = extenso + " e " + decodificaNumeroInteiro(centavos)
					+ " Centavo";
			// } else if (centavosInt > 1 || centavosInt == 0) {
		} else if (centavosInt > 1) {
			extenso = extenso + " e " + decodificaNumeroInteiro(centavos)
					+ " Centavos";
		}
		return extenso;
	}

	private static String decodificaNumeroInteiro(String numero) {
		long numeroInt = Integer.parseInt(numero);
		if (numeroInt == 0) {
			return NUMEROS[0];
		}
		String extenso = "";
		int casaDecimal = 0;
		for (int i = numero.length() - 1; i > -1;) {
			String trio = "";
			for (int j = 0; j < 3 && i > -1; j++, i--) {
				trio = numero.charAt(i) + trio;
			}
			int trioInt = Integer.parseInt(trio);
			extenso = decodificaCentena(trioInt, casaDecimal) + " " + extenso;
			if (casaDecimal == 0 && numeroInt >= 1000 && trioInt < 10
					&& trioInt != 0) {
				extenso = "e " + extenso;
			}
			casaDecimal++;
		}
		return extenso.trim();
	}

	private static String decodificaNumeroOrdinalInteiro(String numero) {
		long numeroInt = Integer.parseInt(numero);
		if (numeroInt == 0) {
			return NUMEROS_ORDINAIS[0];
		}
		String extenso = "";
		int casaDecimal = 0;
		for (int i = numero.length() - 1; i > -1;) {
			String trio = "";
			for (int j = 0; j < 3 && i > -1; j++, i--) {
				trio = numero.charAt(i) + trio;
			}
			int trioInt = Integer.parseInt(trio);
			extenso = decodificaCentenaOrdinal(trioInt, casaDecimal) + " "
					+ extenso;
			if (casaDecimal == 0 && numeroInt >= 1000 && trioInt < 10
					&& trioInt != 0) {
				extenso = "e " + extenso;
			}
			casaDecimal++;
		}
		return extenso.trim();
	}
	
	private static String decodificaNumeroOrdinalFemininoInteiro(String numero) {
		long numeroInt = Integer.parseInt(numero);
		if (numeroInt == 0) {
			return NUMEROS_ORDINAIS_FEMININOS[0];
		}
		String extenso = "";
		int casaDecimal = 0;
		for (int i = numero.length() - 1; i > -1;) {
			String trio = "";
			for (int j = 0; j < 3 && i > -1; j++, i--) {
				trio = numero.charAt(i) + trio;
			}
			int trioInt = Integer.parseInt(trio);
			extenso = decodificaCentenaOrdinalFeminina(trioInt, casaDecimal) + " "
					+ extenso;
			if (casaDecimal == 0 && numeroInt >= 1000 && trioInt < 10
					&& trioInt != 0) {
				extenso = "e " + extenso;
			}
			casaDecimal++;
		}
		return extenso.trim();
	}

	private static String decodificaCentena(int numero, int casaDecimal) {
		String extenso = "";
		int centena = extraiCentena(numero);
		int dezena = extraiDezena(numero);
		int unidade = extraiUnidade(numero);
		// unidade
		if (numero != 0 && unidade != 0) {
			extenso = NUMEROS[unidade];
		}
		// dezena
		if (dezena != 0) {
			if (!extenso.equals("")) {
				extenso = " e " + extenso;
			}
			if (dezena == 1) {
				// Tratamento dos n�meros 11 ao 19
				extenso = NUMEROS[dezena * 10 + unidade];
			} else {
				extenso = DEZENAS[dezena - 2] + extenso;
			}
		}
		// centena
		if (centena != 0) {
			if (!"".equals(extenso)) {
				extenso = " e " + extenso;
			}
			if ((centena != 0 && (dezena + unidade) == 0) || centena > 1) {
				extenso = CENTENAS[centena] + extenso;
			} else {
				extenso = CENTENAS[0] + extenso;
			}
		}
		if (!"".equals(extenso)) {
			if (numero == 1) {
				extenso = extenso + " " + CASAS_DECIMAIS_SINGULAR[casaDecimal];
			} else {
				extenso = extenso + " " + CASAS_DECIMAIS_PLURAL[casaDecimal];
			}
		}
		return extenso;
	}

	private static String decodificaCentenaOrdinal(int numero, int casaDecimal) {
		String extenso = "";
		int centena = extraiCentena(numero);
		int dezena = extraiDezena(numero);
		int unidade = extraiUnidade(numero);
		// unidade
		if (numero != 0 && unidade != 0) {
			extenso = NUMEROS_ORDINAIS[unidade];
		}
		// dezena
		if (dezena != 0) {
			if (!extenso.equals("")) {
				extenso = " " + extenso;//e " + extenso;
			}
			if (dezena == 1) {
				// Tratamento dos n�meros 11 ao 19
				extenso = NUMEROS_ORDINAIS[dezena * 10 + unidade];
			} else {
				extenso = DEZENAS_ORDINAIS[dezena - 2] + extenso;
			}
		}
		// centena
		if (centena != 0) {
			if (!"".equals(extenso)) {
				extenso = " " + extenso;//e " + extenso;
			}
			if ((centena != 0 && (dezena + unidade) == 0) || centena > 1) {
				extenso = CENTENAS_ORDINAIS[centena - 1] + extenso;
			} else {
				extenso = CENTENAS_ORDINAIS[0] + extenso;
			}
		}
		if (!"".equals(extenso)) {
		//	if (numero == 1) {
				extenso = extenso + " "
						+ CASAS_DECIMAIS_ORDINAIS_SINGULAR[casaDecimal];
			/*} else {
				extenso = extenso + " "
						+ CASAS_DECIMAIS_ORDINAIS_PLURAL[casaDecimal];
			}*/
		}
		return extenso;
	}
	
	private static String decodificaCentenaOrdinalFeminina(int numero, int casaDecimal) {
		String extenso = "";
		int centena = extraiCentena(numero);
		int dezena = extraiDezena(numero);
		int unidade = extraiUnidade(numero);
		// unidade
		if (numero != 0 && unidade != 0) {
			extenso = NUMEROS_ORDINAIS_FEMININOS[unidade];
		}
		// dezena
		if (dezena != 0) {
			if (!extenso.equals("")) {
				extenso = " " + extenso;//e " + extenso;
			}
			if (dezena == 1) {
				// Tratamento dos n�meros 11 ao 19
				extenso = NUMEROS_ORDINAIS_FEMININOS[dezena * 10 + unidade];
			} else {
				extenso = DEZENAS_ORDINAIS_FEMININOS[dezena - 2] + extenso;
			}
		}
		// centena
		if (centena != 0) {
			if (!"".equals(extenso)) {
				extenso = " " + extenso;//e " + extenso;
			}
			if ((centena != 0 && (dezena + unidade) == 0) || centena > 1) {
				extenso = CENTENAS_ORDINAIS_FEMININOS[centena - 1] + extenso;
			} else {
				extenso = CENTENAS_ORDINAIS_FEMININOS[0] + extenso;
			}
		}
		if (!"".equals(extenso)) {
		//	if (numero == 1) {
				extenso = extenso + " "
						+ CASAS_DECIMAIS_ORDINAIS_FEMININOS_SINGULAR[casaDecimal];
			/*} else {
				extenso = extenso + " "
						+ CASAS_DECIMAIS_ORDINAIS_PLURAL[casaDecimal];
			}*/
		}
		return extenso;
	}


	public static void main(String[] args) {
		String numero = "1.256.352.524,00";
		System.out.println(getValorMonetarioPorExtenso(numero));
		System.out.println(getNumeroPorExtenso("1143215258"));
		System.out.println(getNumeroOrdinalPorExtenso("6048693"));
		System.out.println(getNumeroOrdinalFemininoPorExtenso("1143215258"));
	}
}