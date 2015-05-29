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
package br.com.twsoftware.alfred.correios;

import java.util.Collection;
import java.util.Map;

import br.com.twsoftware.alfred.AlfredException;
import br.com.twsoftware.alfred.io.CSVReader;


/**
 * Classe utilit�ria para CEPs.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class CEP {

	private CEP() {}

	/**
	 * Realizar a formata��o do CEP passado.
	 * A formata��o � do tipo XX.XXX-XXX.
	 * 
	 * @param cep CEP a ser formatado.
	 * @return CEP formatado.
	 */
	public static String formatar(String cep) {
		if ( "".equals(cep) || cep.length() != 8 )
			throw new AlfredException("Informe um CEP v�lido.");
		return new StringBuilder().append(cep.substring(0,2)).append(".").append(cep.substring(2,5)).append("-").append(cep.substring(5,8)).toString();
	}

	/**
	 * Realizar a formata��o do CEP passado.
	 * A formata��o � do tipo XXXXX-XXX.
	 * 
	 * @param cep CEP a ser formatado.
	 * @return CEP formatado.
	 */
	public static String formatarSimples(String cep) {
		if ( "".equals(cep) || cep.length() != 8 )
			throw new AlfredException("Informe um CEP v�lido.");
		return new StringBuilder().append(cep.substring(0,5)).append("-").append(cep.substring(5,8)).toString();
	}

	/**
	 * Consultar um Endere�o pelo CEP.
	 * Ser� retornado um Array contendo 6 posi��es, que conter�o, respectivamente, os campos (tipo de logradouro, logradouro, bairro, cidade, sigla do estado, estado).
	 * 
	 * @param cep CEP a ser consultado.
	 * @return Array contendo o resultado da consulta.
	 */
	public static String[] consultarEndereco(String cep) { 
		String cepFormatado = formatarSimples(cep);
		Collection<Map<String, String>> r = CSVReader.interpretar("http://ceplivre.pc2consultoria.com/index.php?module=cep&cep=" + cepFormatado + "&formato=csv");
		if ( r.size() <= 0 )
			throw new AlfredException("Endere�o n�o encontrado.");
		Map<String, String> endereco = (Map<String, String>) r.iterator().next();
		if ( endereco == null )
			throw new AlfredException("Endere�o n�o encontrado.");
		return new String[] {endereco.get("tipo_logradouro"),endereco.get("logradouro"), endereco.get("bairro"), endereco.get("cidade"),endereco.get("sigla"),endereco.get("estado")};
	}

}