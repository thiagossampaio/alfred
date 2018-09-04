package com.github.thiagonego.alfred.numeros;

import java.math.BigDecimal;

import com.github.thiagonego.alfred.texto.Texto;


/**
 * Utilit�rios para N�meros.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class Numeros {

	/**
	 * Verificar se o n�mero da String � um N�mero.
	 * 
	 * @param numero N�mero.
	 * @return Verdadeiro caso seja N�mero. Falso, caso contr�rio.
	 */
	public static boolean isNumber(String numero) {
		try {
			return isBigDecimal(numero);
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o n�mero da String � um Short.
	 * 
	 * @param numero N�mero.
	 * @return Verdadeiro caso seja Short. Falso, caso contr�rio.
	 */
	public static boolean isShort(String numero) {
		try {
			Short.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o n�mero da String � um inteiro.
	 * 
	 * @param numero N�mero.
	 * @return Verdadeiro caso seja inteiro. Falso, caso contr�rio.
	 */
	public static boolean isInteger(String numero) {
		try {
			Long.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o n�mero da String � um Double.
	 * 
	 * @param numero N�mero.
	 * @return Verdadeiro caso seja Double. Falso, caso contr�rio.
	 */
	public static boolean isDouble(String numero) {
		try {
			Double.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o n�mero da String � um Float.
	 * 
	 * @param numero N�mero.
	 * @return Verdadeiro caso seja Float. Falso, caso contr�rio.
	 */
	public static boolean isFloat(String numero) {
		try {
			Float.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o n�mero da String � um BigDecimal.
	 * 
	 * @param numero N�mero.
	 * @return Verdadeiro caso seja BigDecimal. Falso, caso contr�rio.
	 */
	public static boolean isBigDecimal(String numero) {
		try {
			new BigDecimal(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}

	/**
	 * Verificar se o n�mero da String � um Long.
	 * 
	 * @param numero N�mero.
	 * @return Verdadeiro caso seja Long. Falso, caso contr�rio.
	 */
	public static boolean isLong(String numero) {
		try {
			Long.valueOf(numero);
			return true;
		} catch (RuntimeException exception) {
			return false;
		}
	}
	
	/**
	 * M�todo que calcula o d�gito verificador, observando se est� correto.
	 * C�digo obtido de http://www.javafree.org/artigo/851371/Validacao-de-CPF.html.
	 * Todos os direitos s�o do autor do c�digo.
	 * 
	 * @param num
	 * @return D�gito verificador.
	 */	
	public static String gerarDigitoVerificadorModulo11Base10(String num){
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();		
	}
	
	/**
	 * Verificar se um CPF � v�lido.
	 * 
	 * @param cpf CPF a ser verificado.
	 * @return Verdadeiro caso seja v�lido. Falso, caso contr�rio.
	 */
	public static boolean isValidarDigitoVerificadorModulo11Base10(String cpf) {
		cpf = Texto.manterNumeros(cpf);
        if (cpf.length() != 11)
            return false;
        String numDig = cpf.substring(0, 9);
        return gerarDigitoVerificadorModulo11Base10(numDig).equals(cpf.substring(9, 11));
	}	

}