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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.twsoftware.alfred.AlfredException;
import br.com.twsoftware.alfred.net.WorldWideWeb;


/**
 * Utilit�rios para obter informa��es de entrega para Sedex Hoje atrav�s do site dos Correios.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/06/2009
 */
final public class SedexHoje {

	/**
	 * Verificar o Prazo e o Pre�o para entrega via Sedex Hoje de um CEP de origem para um CEP de destino com uma encomenda com o peso especificado.
	 * Exemplo de uso:
	 * Sedex.obterPrecoPrazoEntrega("40290280", "40290280",1);
	 * Retorno: {"11,20","1"}
	 * 
	 * @param cepOrigem CEP de Origem.
	 * @param cepDestino CEP de Destino.
	 * @param peso Peso da Encomenda.
	 * @return Prazo e Pre�o para entrega. Primeira posi��o corresponde ao pre�o. 
	 * 			   Segunda posi��o corresponde ao prazo em dias.
	 */
	public static String[] obterPrecoPrazoEntrega(String cepOrigem, String cepDestino, int peso) {
		// Checar se os par�metros foram informados.
		if ( "".equals(cepOrigem) )
			throw new AlfredException("Informe o CEP de Origem.");
		if ( "".equals(cepDestino) )
			throw new AlfredException("Informe o CEP de Destino.");
		if ( peso <= 0 )
			throw new AlfredException("Informe o Peso da encomenda.");

		// Montar os par�metros.
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("resposta","paginaCorreios");
		parametros.put("servico","40290");
		parametros.put("cepOrigem", cepOrigem);
		parametros.put("cepDestino", cepDestino);
		parametros.put("embalagem","");
		parametros.put("peso", Integer.toString(peso));
		// Realizar a requisi��o.
		String conteudo = WorldWideWeb.getConteudoSite("http://www.correios.com.br/encomendas/prazo/prazo.cfm", parametros);
		
		// Usar express�o regular para achar o pre�o.
		Pattern padrao = Pattern.compile("<b>R\\$ \\d{1,3},\\d{2}</b>");  
		Matcher pesquisa = padrao.matcher(conteudo);

		// Deve encontrar apenas um.
		String preco = "";
		while(pesquisa.find()) {
			preco = pesquisa.group();
		}

		// Se pre�o n�o encontrado, emitir erro.
		if ( "".equals(preco) ) {
			throw new AlfredException("N�o foi poss�vel obter as informa��es do site dos Correios. Verifique se os CEPs informados est�o corretos.");
		}

		String prazo = "No mesmo dia da postagem";

		// Remover as tags <b></b> das strings.
		String precoFinal = preco.replace("<b>","").replace("</b>","");
		String prazoFinal = prazo.replace("<b>","").replace("</b>","");

		return new String[] {precoFinal,prazoFinal};
	}

}