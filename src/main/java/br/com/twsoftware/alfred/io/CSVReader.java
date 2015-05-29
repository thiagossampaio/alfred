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
package br.com.twsoftware.alfred.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.twsoftware.alfred.AlfredException;


/**
 * Utilit�rio para arquivos do tipo CSV.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class CSVReader {

	/**
	 * L� um arquivo CSV de um local e interpreta.
	 * Retorna um Map onde o cabe�alho forma as chaves do Map.
	 * 
	 * @param u URL do arquivo CSV.
	 * @return Mapa.
	 */
	public static Collection<Map<String,String>> interpretar(String u) {
        URL url;
        Collection<Map<String,String>> c = new ArrayList<Map<String,String>>();
		try {
			url = new URL(u);
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        boolean primeiraLinha = true;
	        String[] keys = null;
	        while ((line = rd.readLine()) != null) {
	        	// Se for a primeira linha, ent�o obter o cabe�alho.
	        	if ( line.trim().charAt(0) == '#' )
	        		continue;
	        	if ( line.trim().split(",").length == 0 )
	        		continue;
	        	if ( primeiraLinha ) {
	        		primeiraLinha = false;
	        		CSV csv = new CSV();
	        		csv.split(line);
	        		keys = new String[csv.getNField()-1];
	        		for(int i = 0; i < csv.getNField()-1; i++)
	        			keys[i] = csv.getField(i);
	        	} else {
		        	// Montar o Map.
	        		CSV csv = new CSV();
	        		Map<String,String> retorno = new HashMap<String, String>();
	        		csv.split(line);
		        	for(int i = 0; i < csv.getNField()-1; i++) {
		        		retorno.put(keys[i],csv.getField(i));
		        	}
		        	c.add(retorno);
	        	}
	        }
	        rd.close();
	        return c;
		} catch (MalformedURLException e) {
			throw new AlfredException("N�o foi poss�vel obter contato com o site " + u, e);
		} catch (IOException e) {
			throw new AlfredException("N�o foi poss�vel obter contato com o site " + u, e);
		}
	
	}

}