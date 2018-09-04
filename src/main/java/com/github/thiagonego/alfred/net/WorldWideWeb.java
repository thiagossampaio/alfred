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
package com.github.thiagonego.alfred.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import com.github.thiagonego.alfred.AlfredConfig;
import com.github.thiagonego.alfred.AlfredException;


/**
 * Utilit�rios para WWW.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class WorldWideWeb {

	public static InputStream getConteudoArquivo(String u) {
		URL url;
		try {
			url = new URL(u);
			URLConnection conn = null;
			if ( AlfredConfig.getInstancia().isUsingProxy() )
				conn = url.openConnection(AlfredConfig.getInstancia().getProxy());
			else conn = url.openConnection();

		    return new DataInputStream(conn.getInputStream());
		} catch (MalformedURLException e) {
			throw new AlfredException(e);
		} catch (IOException e) {
			throw new AlfredException(e);
		}
	}

	/**
	 * Obter o conte�do de um site.
	 * 
	 * @param u URL do Site.
	 * @return String contendo todo o conte�do do site em HTML.
	 */
	public static String getConteudoSite(String u) {
        URL url;
		try {
			url = new URL(u);
			URLConnection conn = null;
			if ( AlfredConfig.getInstancia().isUsingProxy() )
				conn = url.openConnection(AlfredConfig.getInstancia().getProxy());
			else conn = url.openConnection();
	        conn.setDoOutput(true);
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        StringBuilder resultado = new StringBuilder();
	        while ((line = rd.readLine()) != null) {
	            resultado.append(line);
	            resultado.append("\n");
	        }
	        rd.close();
	        return resultado.toString();
		} catch (MalformedURLException e) {
			throw new AlfredException("N�o foi poss�vel obter contato com o site " + u, e);
		} catch (IOException e) {
			throw new AlfredException("N�o foi poss�vel obter contato com o site " + u, e);
		}
	}

	/**
	 * Obter o conte�do de um site usando o m�todo POST.
	 * 
	 * @param u URL do Site.
	 * @param parametros Par�metros que ser�o usandos na requisi��o ao site.
	 * @return String contendo todo o conte�do do site em HTML.
	 */
	public static String getConteudoSite(String u, Map<String,String> parametros) {
        URL url;
		try {
			StringBuilder strParams = new StringBuilder();
			if ( parametros != null ) {
				for(String chave:parametros.keySet()) {
					strParams.append(URLEncoder.encode(chave,"UTF-8"));
					strParams.append("=");
					strParams.append(URLEncoder.encode(parametros.get(chave),"UTF-8"));
					strParams.append("&");
				}
			}
			url = new URL(u);
			URLConnection conn = null;
			if ( AlfredConfig.getInstancia().isUsingProxy() )
				conn = url.openConnection(AlfredConfig.getInstancia().getProxy());
			else conn = url.openConnection();
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(strParams.toString());
	        wr.flush();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        StringBuilder resultado = new StringBuilder();
	        while ((line = rd.readLine()) != null) {
	            resultado.append(line);
	        }
	        wr.close();
	        rd.close();
	        return resultado.toString();
		} catch (MalformedURLException e) {
			throw new AlfredException("N�o foi poss�vel obter contato com o site " + u, e);
		} catch (IOException e) {
			throw new AlfredException("N�o foi poss�vel obter contato com o site " + u, e);
		}
	}
}