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
package br.com.twsoftware.alfred.cpf;

import br.com.twsoftware.alfred.AlfredException;
import br.com.twsoftware.alfred.numeros.Numeros;

/**
 * Utilit�rios para CPF.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class CPF {

	/**
	 * Construtor privado para evitar instancia��o desta classe.
	 */
	private CPF() {
	}

	/**
     * Gerar um CPF arbitr�rio.
 	 * C�digo obtido de http://www.javafree.org/artigo/851371/Validacao-de-CPF.html.
	 * Todos os direitos s�o do autor do c�digo.
	 *  
     * @return CPF gerado arbitrariamente.
     */
    public static String gerar() {
        StringBuilder iniciais = new StringBuilder();
        Integer numero;
        for (int i = 0; i < 9; i++) {
            numero = Integer.valueOf((int) (Math.random() * 10));
            iniciais.append(numero.toString());
        }
        return iniciais.toString() + gerarDigitoVerificador(iniciais.toString());
    }

	/**
	 * Obter um CPF qualquer e format�-lo. Qualquer caracter diferente de
	 * n�meros ser� ignorado. Portanto, um CPF do tipo 1111c11b11122a ser�
	 * formatado para 111.111.111-11
	 * 
	 * @param cpf N�mero do CPF.
	 * @return CPF formatado.
	 */
	public static String formatar(String cpf) {
		String cpfSoNumeros = limpar(cpf);
		// Verificar tamanho do CPF.
		if (cpfSoNumeros.length() != 11)
			throw new AlfredException(
					"CPF inv�lido. Tamanho de um CPF v�lido � 11. Este CPF possui "
							+ cpfSoNumeros.length() + " n�meros.");
		StringBuilder sb = new StringBuilder();
		sb.append(cpfSoNumeros.substring(0, 3));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(3, 6));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(6, 9));
		sb.append("-");
		sb.append(cpfSoNumeros.substring(9, 11));
		return sb.toString();
	}

	/**
	 * Verificar se um CPF � v�lido.
	 * 
	 * @param cpf CPF a ser verificado.
	 * @return Verdadeiro caso seja v�lido. Falso, caso contr�rio.
	 */
	public static boolean isValido(String cpf) {
		return Numeros.isValidarDigitoVerificadorModulo11Base10(cpf);
	}

	/**
	 * Limpar o CPF mantendo somente os n�meros. N�o verifica se � um CPF
	 * v�lido.
	 * 
	 * @param cpf CPF que deve ser limpado.
	 * @return CPF apenas com n�meros.
	 */
	public static String limpar(String cpf) {
		if (cpf == null)
			throw new AlfredException("O CPF informado � nulo.");
		if ("".equals(cpf))
			throw new AlfredException("O CPF informado � vazio.");
		char[] chars = cpf.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int indice = 0; indice < chars.length; indice++) {
			if (Numeros.isInteger(String.valueOf(chars[indice]))) {
				sb.append(chars[indice]);
			}
		}
		return sb.toString();
	}

	/**
	 * M�todo que calcula o d�gito verificador, observando se est� correto.
	 * C�digo obtido de http://www.javafree.org/artigo/851371/Validacao-de-CPF.html.
	 * Todos os direitos s�o do autor do c�digo.
	 * 
	 * @param num
	 * @return D�gito verificador.
	 */
	public static String gerarDigitoVerificador(String num) {
		return Numeros.gerarDigitoVerificadorModulo11Base10(num);
	}

}