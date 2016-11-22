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
package br.com.twsoftware.alfred.texto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import br.com.twsoftware.alfred.numeros.Numeros;
import br.com.twsoftware.alfred.object.Objeto;
import br.com.twsoftware.alfred.telefones.Telefones;

import sun.misc.BASE64Encoder;

/**
 * Utilitï¿½rios para manipulaï¿½ï¿½o de Textos.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
public class Texto {
	
	private static final String VOGAIS_ACENTUADAS[] = {"á", "é", "í", "ó", "ú", "Á", "É", "Í", "Ó", "Ú", "ý", "Ý",
	          "à", "è", "ì", "ò", "ù", "À", "È", "Ì", "Ò", "Ù",
	          "â", "ê", "î", "ô", "û", "Â", "Ê", "Î", "Ô", "Û",
	          "ã", "õ", "Ã", "Õ", "ñ", "Ñ",
	          "ä", "ë", "ï", "ö", "ü", "Ä", "Ë", "Ï", "Ö", "Ü", "ÿ",
	          "ç", "Ç"};
	private static final String VOGAIS_NAO_ACENTUADAS[] = {"a", "e", "i", "o", "u", "A", "E", "I", "O", "U", "y", "Y",
	          "a", "e", "i", "o", "u", "A", "E", "I", "O", "U",
	          "a", "e", "i", "o", "u", "A", "E", "I", "O", "U",
	          "a", "o", "A", "O", "n", "N",
	          "a", "e", "i", "o", "u", "A", "E", "I", "O", "U", "y",
	          "c", "C"};	
	
	private static final String ABRE_PARENTESES = "(";
	private static final String FECHA_PARENTESES = ")";
    private static final String ESPACO = " ";
    private static final String PONTO_ = ".";
    private static final String BARRA = "/";
    private static final String TRACO = "-";
    private static final String ESPACO_BRANCO = "";
    private static final String PONTO = "\\.";
    private static final String UNDER_LINE = "_";
    
    
	public static byte[] converteStringHexadecimalEmBytes(
			String stringHexadecimal) {
		if (stringHexadecimal == null || stringHexadecimal.equals("")) {
			return new byte[0];
		}
		int tamanho = stringHexadecimal.length();
		if (tamanho % 2 != 0) {
			throw new NumberFormatException(
					"O tamanho da string hexadecimal estï¿½ incorreto!");
		}
		int numeroDeBytes = tamanho / 2;
		byte[] seq = new byte[numeroDeBytes];
		for (int i = 0; i < numeroDeBytes; i++) {
			String hex = stringHexadecimal.substring(i * 2, i * 2 + 2);
			seq[i] = parseByte(hex);
		}
		return seq;
	}

	public static byte parseByte(String hex) throws NumberFormatException {
		if (hex == null) {
			throw new IllegalArgumentException(
					"A notaï¿½ï¿½o hexadecimal estï¿½ nula.");
		}
		if (hex.equals("")) {
			return 0;
		}
		Integer num = Integer.decode("0x" + hex);
		int n = num.intValue();
		if (n > 255 || n < 0) {
			throw new NumberFormatException(" O nï¿½mero " + n
					+ " nï¿½o pode ser convertido em byte!");
		}
		return num.byteValue();
	}

	public static String converteNumeroParaRomano(Integer numero) {
		String resultado = "";
		String numeroString = numero.toString();
		for (int c = 0; c < numeroString.length(); c++) {
			resultado += calculaDigitoRomano(numeroString.substring(c, c + 1),
					numeroString.length() - c);
		}
		return resultado;
	}

	private static String calculaDigitoRomano(String dString, int l) {
		Integer d = Integer.valueOf(dString);
		if (l > 2) {
			String str = "";
			for (int m = 1; m <= d * Math.pow(10, l - 3); m++) {
				str += 'M';
			}
			return str;
		} else if (d == 1) {
			return getMapaDeNiveis().get(l).getNivel_I();
		} else if (d == 2) {
			return getMapaDeNiveis().get(l).getNivel_I()
					+ getMapaDeNiveis().get(l).getNivel_I();
		} else if (d == 3) {
			return getMapaDeNiveis().get(l).getNivel_I()
					+ getMapaDeNiveis().get(l).getNivel_I()
					+ getMapaDeNiveis().get(l).getNivel_I();
		} else if (d == 4) {
			return getMapaDeNiveis().get(l).getNivel_I()
					+ getMapaDeNiveis().get(l).getNivel_V();
		} else if (d == 5) {
			return getMapaDeNiveis().get(l).getNivel_V();
		} else if (d == 6) {
			return getMapaDeNiveis().get(l).getNivel_V()
					+ getMapaDeNiveis().get(l).getNivel_I();
		} else if (d == 7) {
			return getMapaDeNiveis().get(l).getNivel_V()
					+ getMapaDeNiveis().get(l).getNivel_I()
					+ getMapaDeNiveis().get(l).getNivel_I();
		} else if (d == 8) {
			return getMapaDeNiveis().get(l).getNivel_V()
					+ getMapaDeNiveis().get(l).getNivel_I()
					+ getMapaDeNiveis().get(l).getNivel_I()
					+ getMapaDeNiveis().get(l).getNivel_I();
		} else if (d == 9) {
			return getMapaDeNiveis().get(l).getNivel_I()
					+ getMapaDeNiveis().get(l).getNivel_X();
		} else {
			return "";
		}
	}
	
	private static Map<Integer, NivelRomano> mapaDeNiveis;

	private static Map<Integer, NivelRomano> getMapaDeNiveis() {
		if (mapaDeNiveis == null) {
			mapaDeNiveis = new Hashtable<Integer, NivelRomano>();
			mapaDeNiveis.put(1, new NivelRomano("I", "V", "X"));
			mapaDeNiveis.put(2, new NivelRomano("X", "L", "C"));
			mapaDeNiveis.put(3, new NivelRomano("C", "D", "M"));
		}
		return mapaDeNiveis;
	}	
    
	/**
	 * Inclui o caracter informado ï¿½ esquerda do texto fornecido, atï¿½ que este
	 * tenha o tamanho desejado
	 * 
	 * @param texto
	 *            O texto que se quer completar
	 * @param caracter
	 *            O caracter para o preenchimento
	 * @param tamanhoTotal
	 *            O tamanho total que o texto deve atigir para ficar completo
	 * @return O texto completado com o caracter
	 */
	public static String completaPosicoesEsquerda(String texto, char caracter,
			int tamanhoTotal) {
		return completaPosicoes(texto, caracter, tamanhoTotal,
				TipoDeCompletamento.ESQUERDA);
	}
	
	/**
	 * Inclui o caracter informado ï¿½ direita do texto fornecido, atï¿½ que este
	 * tenha o tamanho desejado
	 * 
	 * @param texto
	 *            O texto que se quer completar
	 * @param caracter
	 *            O caracter para o preenchimento
	 * @param tamanhoTotal
	 *            O tamanho total que o texto deve atigir para ficar completo
	 * @return O texto completado com o caracter
	 */
	public static String completaPosicoesDireita(String texto, char caracter,
			int tamanhoTotal) {
		return completaPosicoes(texto, caracter, tamanhoTotal,
				TipoDeCompletamento.DIREITA);
	}

	private static String completaPosicoes(String texto, char caracter,
			int tamanhoTotal, TipoDeCompletamento tipo) {
		if (texto == null || tamanhoTotal < 1) {
			return texto;
		}
		String textoNovo = texto;
		while (textoNovo.length() < tamanhoTotal) {
			if (tipo == TipoDeCompletamento.ESQUERDA) {
				textoNovo = caracter + textoNovo;
			} else if (tipo == TipoDeCompletamento.DIREITA) {
				textoNovo += caracter;
			}
		}
		return textoNovo;
	}

	private enum TipoDeCompletamento {
		DIREITA, ESQUERDA
	}	
    
    /**
	 * Compara duas Strings sem levar em conta diferenï¿½as entre maiï¿½sculas e
	 * minï¿½sculas. Esclui carcteres em branco do inï¿½cio e do fim da String. 
     * @return: boolean
     * @param palavra1
     * @param palavra2
     */
	public static boolean equalsCanonico(String palavra1, String palavra2) {
		return palavra1.trim().equalsIgnoreCase(palavra2.trim());
	}    
    
	@SuppressWarnings("unchecked")
	public static String extraiNomeDaClasse(Class classe) {
		return extraiNomeDaClasse(classe.getName());
	}

	public static String extraiNomeDaClasse(String nomeDaClasse) {
		int posPonto = nomeDaClasse.lastIndexOf(".");
		if (posPonto >= 0) {
			return nomeDaClasse.substring(posPonto + 1);
		}
		return nomeDaClasse;
	}

	public static String extraiNomeDoPacote(String nomeDaClasse) {
		int posPonto = nomeDaClasse.lastIndexOf(".");
		if (posPonto >= 0) {
			return nomeDaClasse.substring(0, posPonto);
		}
		return nomeDaClasse;
	}

	@SuppressWarnings("unchecked")
	public static String extraiNomeDoPacote(Class classe) {
		return extraiNomeDoPacote(classe.getName());
	}    
    
	public static String retiraAcentos(String palavra) {
		String palavraAlterada = palavra;
		for (int i = 0; i < VOGAIS_ACENTUADAS.length; i++) {
			palavraAlterada = palavraAlterada.replaceAll(VOGAIS_ACENTUADAS[i],
					VOGAIS_NAO_ACENTUADAS[i]);
		}
		return palavraAlterada;
	}

	public static String retiraCaracteresEspeciais(String texto) {
		String novo = retiraAcentos(texto);
		novo = novo.replaceAll("ï¿½", "c");
		novo = novo.replaceAll("ï¿½", "C");
		return novo;
	}

	@SuppressWarnings("unchecked")
	public static String criaStringComPieces(Collection col, String separador) {
		return criaStringComPieces(col.iterator(), separador);
	}

	@SuppressWarnings("unchecked")
	public static String criaStringComPieces(Iterator iter, String separador) {
		String html = "";
		while (iter.hasNext()) {
			if (html.length() > 0) {
				html = html + separador;
			}
			html = (String) iter.next();
		}
		return html;
	}
    
	@SuppressWarnings("unchecked")
	public static String[] criaArrayDeString(Collection colecaoDeValores) {
		String[] array;
		if (colecaoDeValores.size() == 0) {
			array = new String[1];
		} else {
			array = new String[colecaoDeValores.size()];
		}
		Iterator iter = colecaoDeValores.iterator();
		for (int i = 0; i < colecaoDeValores.size(); i++) {
			array[i] = (String) iter.next();
		}
		return array;
	}    
    
    /**
     * Retorna se ï¿½ nï¿½mero
     * @return: boolean
     * @param numero
     * @return
     */
	public static boolean isNumerico(String numero) {
		for (int i = 0; i < numero.length(); i++) {
			if (Character.isDigit(numero.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retorna se ï¿½ alfanumï¿½rico
	 * @return: boolean
	 * @param numero
	 * @return
	 */
	public static boolean isAlfaNumerico(String numero) {
		for (int i = 0; i < numero.length(); i++) {
			if (Character.isDigit(numero.charAt(i)) == false
					&& Character.isLetter(numero.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retorna se ï¿½ letra
	 * @return: boolean
	 * @param letra
	 * @return
	 */
	public static boolean isLetra(char letra) {
		return Character.isLetter(letra);
	}    
    
	/**
	 * @param linhasConcatenadas
	 * @param separador
	 * @return
	 */
	public static String[] stringToArray(String linhasConcatenadas,
			String separador) {
		StringTokenizer to = new StringTokenizer(linhasConcatenadas, separador);
		int posicoes = to.countTokens();
		String[] linhas = new String[posicoes];
		for (int i = 0; i < posicoes; i++) {
			linhas[i] = to.nextToken();
		}
		return linhas;
	}    
    
	public static String adiciona(String texto, String caracterParaAdicionar,
			int tamanhoMaximo) {
		StringBuffer sb = new StringBuffer(texto);
		if (sb.length() < tamanhoMaximo) {
			for (int i = texto.length(); i < tamanhoMaximo; i++) {
				sb.append(caracterParaAdicionar);
			}
			return sb.toString();
		}
		return texto;
	}    
    
	public static String getPiece(String texto, String separador, int pos) {
		StringTokenizer token = new StringTokenizer(texto, separador);
		String pedaco = "";
		for (; pos > 0 && token.hasMoreTokens(); pos--) {
			pedaco = token.nextToken();
		}
		if (pos == 0) {
			return pedaco;
		}
		return "";
	}

	public static String trocaCaracter(String caracter, String texto) {
		StringBuilder retorno = new StringBuilder();
		for (int i = 0; i < texto.length(); i++) {
			if (texto.charAt(i) != caracter.charAt(0)) {
				retorno.append(texto.charAt(i));
			}
		}
		return retorno.toString();
	}    
    
    /**
     * Retrona os caracteres da direita
     * @return: String
     * @param texto
     * @param numeroDeCaracteres
     */
	public static String right(String texto, int numeroDeCaracteres) {
		if (numeroDeCaracteres > texto.length()) {
			return texto.toString();
		}
		return texto.substring(texto.length() - numeroDeCaracteres);
	}

	/**
	 * Retrona os caracteres da direita
	 * @return: String
	 * @param texto
	 * @param numeroDeCaracteres
	 */
	public static String left(String texto, int numeroDeCaracteres) {
		if (numeroDeCaracteres > texto.length()) {
			return texto.toString();
		}
		return texto.substring(0, numeroDeCaracteres);
	}    
    
    /**
     * Mï¿½todo que valida e-mail.
     * @author Thiago Sampaio
     * @param email O e-mail a ser validado.
     * @return Indicaï¿½ï¿½o de sucesso ou nï¿½o.
     */
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-z]{2,4}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    /**
     * Mï¿½todo que valida uma URL.
     * @param url A URL a ser validada.
     * @return Indicaï¿½ï¿½o de sucesso ou nï¿½o.
     */
    public static boolean validateUrl(String url) {
        final String QUALQUER_CARACTER = "\\w+";
        final String PONTO = "\\.";
        final String INICIO = "((ftp|http|https|gopher|mailto|news|nntp|" +
                "telnet|wais|file|prospero|aim|webcal)://)?";
        final String FIM = "(\\.(\\w+)(.+))?";
        final String EXPRESSAO = INICIO + QUALQUER_CARACTER + PONTO + QUALQUER_CARACTER + PONTO
                + QUALQUER_CARACTER + FIM;
        Pattern pattern = Pattern.compile(EXPRESSAO);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
    
    /**
     * Mï¿½todo que valida um CEP.
     * @param cep O CEP a ser validado.
     * @return Indicaï¿½ï¿½o de sucesso ou nï¿½o.
     */
    public static boolean validateCep(String cep) {
        Pattern pattern = Pattern.compile("[0-9]{2}\\.[0-9]{3}-[0-9]{3}");
        Matcher matcher = pattern.matcher("58.102-400");
        return matcher.matches();
    }
    
	/**
	 * Verifica se o texto indicado representa um nï¿½mero de telefore vï¿½lido.
	 * Nï¿½meros de telefone devem possuir 8 dï¿½gitos, opcionalmente separados por
	 * um traï¿½o ('-'). Cï¿½digo de ï¿½rea (DDD) tambï¿½m ï¿½ opcional.
	 * 
	 * @param texto
	 *            O texto a ser verificado
	 * @return <code>true</code> se o texto for um nï¿½mero de telefone vï¿½lido;
	 *         caso contrï¿½rio, retorna <code>false</code>.
	 */
	public static boolean validateValido(String texto) {
		return Telefones.isValido(texto);
	}    

	/**
	 * Mï¿½todo que remove a mï¿½scara de um processo.
	 * @param String
	 * @return
	 */
	public static String limpaMascaraProcesso(String processo) {
		if (processo != null) {
			processo = processo.replaceAll("\\.", "");
			processo = processo.replaceAll("/", "");
			processo = processo.replaceAll("-", "");
			processo = processo.replaceAll(" ", "");
		}
		return processo;
	}    
    
    /**
     * Mï¿½todo que remove a mï¿½scara de um CPF.
     * @param cpf O CPF o qual deve ter a mï¿½scara removida.
     * @return O CPF sem a mï¿½scara.
     */
    public static String retiraMascaraCpf(String cpf) {
        if (isBlankOrNull(cpf))
            return cpf;
        String retorno = cpf.replaceAll(PONTO, ESPACO_BRANCO);
        retorno = retorno.replaceAll(TRACO, ESPACO_BRANCO);
        retorno = retorno.trim();
        return retorno;
    }

    /**
     * Mï¿½todo que remove a mï¿½scara de uma DATA.
     * @param data A DATA a qual deve ter a mï¿½scara removida.
     * @return A DATA sem mï¿½scara.
     */
    public static String retiraMascaraData(String data) {
        String retorno = data.replaceAll(BARRA, ESPACO_BRANCO);
        retorno.replaceAll(ESPACO, ESPACO_BRANCO);
        retorno = retorno.trim();
        return retorno;
    }

    /**
     * Mï¿½todo que remove a mï¿½scara de um VALOR.
     * @param valor O VALOR o qual deve ter a mï¿½scara removida.
     * @return O VALOR sem mï¿½scara.
     */
    public static String retiraMascaraValor(String valor) {
        String retorno = valor.replaceAll(PONTO, ESPACO_BRANCO);
        retorno.replaceAll(ESPACO, ESPACO_BRANCO);
        retorno = retorno.trim();
        return retorno;
    }

    /**
     * Mï¿½todo que remove a mï¿½cara de um CNPJ.
     * @param cnpj O CNPJ o qual deve ter a mï¿½cara removida.
     * @return O CNPJ sem mï¿½scara.
     */
    public static String retiraMascaraCnpj(String cnpj) {
        String retorno = cnpj.replaceAll(PONTO, ESPACO_BRANCO);
        retorno = retorno.replaceAll(TRACO, ESPACO_BRANCO);
        retorno = retorno.replaceAll(BARRA, ESPACO_BRANCO);
        return retorno;
    }

    /**
     * Mï¿½todo que remove a mï¿½scara de um CEP.
     * @param cep O CEP o qual deve ter a mï¿½scara removida.
     * @return O CEP sem a mï¿½scara.
     */
    public static String retiraMascaraCep(String cep) {
        String retorno = cep.replaceAll(PONTO, ESPACO_BRANCO);
        retorno = retorno.replaceAll(TRACO, ESPACO_BRANCO);
        return retorno;
    }
    
    /**
     * Mï¿½todo que remove a mï¿½scara de um processo 
     * @author: Thiago Sampaio
     * @return: String
     * @param processo
     */
    public static String retiraMascaraProcesso(String processo){
    	if (isBlankOrNull(processo))
    		return "";
    	return processo.replaceAll(PONTO, ESPACO_BRANCO).replaceAll(TRACO, ESPACO_BRANCO).replaceAll(UNDER_LINE, ESPACO_BRANCO);
    }
    
    /**
     * Mï¿½todo que coloca uma mï¿½scara no CEP.
     * @param cep O CEP o qual a mï¿½scara deve ser colocada.
     * @return O CEP com mï¿½scara.
     */
    public static String colocaMascaraCep(String cep) {
        StringBuffer retorno = new StringBuffer();
        retorno.append(cep.substring(0, 2));
        retorno.append(PONTO_);
        retorno.append(cep.substring(3, 6));
        retorno.append(TRACO);
        retorno.append(cep.substring(7));
        return retorno.toString();
    }
    
    /**
     * Mï¿½todo que coloca uma mï¿½scara no CNPJ.
     * @param cnpj O CNPJ o qual a mï¿½scara deve ser colocada.
     * @return O CNPJ com mï¿½scara.
     */
    public static String colocaMascaraCnpj(String cnpj) {
        StringBuffer retorno = new StringBuffer();
        retorno.append(cnpj.substring(0, 2));
        retorno.append(PONTO_);
        retorno.append(cnpj.substring(2, 5));
        retorno.append(PONTO_);
        retorno.append(cnpj.substring(5, 8));
        retorno.append(BARRA);
        retorno.append(cnpj.substring(8, 12));
        retorno.append(TRACO);
        retorno.append(cnpj.substring(12));
        return retorno.toString();
    }

    /**
     * Mï¿½todo que coloca uma mï¿½scara na DATA.
     * @param data A DATA a qual a mï¿½scara deve ser colocada.
     * @return A DATA com mï¿½scara.
     */
    public static String colocaMascaraData(String data) {
        StringBuffer retorno = new StringBuffer();
        retorno.append(data.substring(0, 1));
        retorno.append(BARRA);
        retorno.append(data.substring(2, 3));
        retorno.append(BARRA);
        return retorno.toString();
    }

    /**
     * Mï¿½todo que coloca uma mï¿½scara no CPF.
     * @param cpf O CPF o qual a mï¿½scara deve ser colocada.
     * @return O CPF com mï¿½scara.
     */
    public static String colocaMascaraCpf(String cpf) {
        StringBuffer retorno = new StringBuffer();
        retorno.append(cpf.substring(0, 3));
        retorno.append(PONTO_);
        retorno.append(cpf.substring(3, 6));
        retorno.append(PONTO_);
        retorno.append(cpf.substring(6, 9));
        retorno.append(TRACO);
        retorno.append(cpf.substring(9));
        return retorno.toString();
    }

    /**
     * Mï¿½todo que verifica se uma string ï¿½ vazia ou nula.
     * @param texto A string a ser verificada.
     * @return Indicaï¿½ï¿½o de sucesso ou nï¿½o.
     */
    public static boolean isBlankOrNull(String texto) {
        return texto == null || texto.equals("") || texto.trim().equals("");
    }
    
    /**
     * Mï¿½todo que verifica se uma string ï¿½ vazia ou nula ou zero.
     * @param texto A string a ser verificada.
     * @return Indicaï¿½ï¿½o de sucesso ou nï¿½o.
     */
    public static boolean isBlankOrNullOrZero(String texto) {
         return isBlankOrNull(texto) || texto.contains("0") && texto.replace("0", "").replace(".", "").replace(",", "").trim().equals("");
    }

    /**
     * Mï¿½todo que preenche um campo com mï¿½scara.
     * @param value O valor a ser colocado no campo.
     * @param sizeOfMask Tamanho da mï¿½scara.
     * @return O campo preenchido.
     */
    public static String fillField(String value, int sizeOfMask) {
        int sizeOfValue = value.substring(0, value.indexOf('.')).length();
        if (sizeOfValue > sizeOfMask) {
            return null;
        }
        boolean hasFourDigits = ((sizeOfValue == sizeOfMask) ? true : false);
        if (hasFourDigits) {
            return value.concat("0");
        }
        int difference = sizeOfMask - sizeOfValue;
        StringBuffer newValue = new StringBuffer(value);
        char append = '0';
        while (difference != 0) {
            newValue.insert(0, append);
            difference--;
        }
        return newValue.append('0').toString();
    }
   
    public static String limitaTamanhoString(String texto, int tamanho){
        if (texto != null){
            if (texto.length() > tamanho){
                texto = texto.substring(0, tamanho-1);
            }
        }
        return texto;
    }    
    
    

	/**
	 * Manter no Texto apenas os nï¿½meros.
	 * 
	 * @param str Texto.
	 * @return Texto contendo apenas os nï¿½meros.
	 */
	public static String manterNumeros(String str) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < str.length() ; i++) {
			if ( Numeros.isNumber(String.valueOf(str.charAt(i))) ) {
				s.append(str.charAt(i));
			}
		}
		return s.toString();
	}

	/**
	 * Incluir uma determinada quantidade de vezes um determinado caracter no inï¿½cio do texto.
	 * 
	 * @param texto Texto que terï¿½ o caracter inserido no inï¿½cio.
	 * @param c Caracter que serï¿½ incluï¿½do.
	 * @param q Quantidade de vezes que o caracter serï¿½ incluï¿½do.
	 * @return Texto com os caracteres incluï¿½dos.
	 */
	public static String incluirCaracterInicio(String texto, char c, int q) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < q; i++)
			s.append(c);
		s.append(texto);
		return s.toString();
	}

	/**
	 * Capitalizar a primeira letra de todas as palavras do texto.
	 * 
	 * @param texto Texto que terï¿½ as palavras capitalizadas.
	 * @return Texto com a formataï¿½ï¿½o aplicada.
	 */
	public static String capitalizarIniciais(String texto) {
		String[] split = texto.split(" ");
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < split.length; i++) {
			s.append(String.valueOf(split[i].charAt(0)).toUpperCase());
			s.append(split[i].substring(1, split[i].length()));
			s.append(" ");
		}
		return s.toString().trim();
	}

	/**
	 * Contar a quantidade de vezes que uma palavra ocorre em um texto.
	 * Nï¿½o usar caracteres especiais na palavra a ser pesquisada.
	 * 
	 * @param texto Texto.
	 * @param palavra Palavra que serï¿½ contada no texto.
	 * @param ignoreCase Considerar maiï¿½sculas ou minï¿½sculas.
	 * @return Quantidade de vezes que a palavra ocorre.
	 */
	public static int contarQuantidadePalavra(String texto, String palavra, boolean ignoreCase) {
		if ( ignoreCase) {
			texto = texto.toLowerCase();
			palavra = palavra.toLowerCase();
		}
		Pattern padrao = Pattern.compile(palavra);  
		Matcher pesquisa = padrao.matcher(texto);
		int r = 0;
		while(pesquisa.find()) 
			r++;
		return r;
	}

	/**
	 * Obter a cadeia de caracteres que forma o alfabeto brasileiro em minï¿½sculas.
	 * 
	 * @return Array de caracteres.
	 */
	public static char[] obterAlfabetoMinusculas() {
		return new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x','y','w','z'};
	}

	/**
	 * Obter a cadeia de caracteres que forma o alfabeto brasileiro em minï¿½sculas.
	 * 
	 * @return Array de caracteres.
	 */
	public static char[] obterAlfabetoMaiusculas() {
		return new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','Y','W','Z'};
	}
	
	/**
	 * Formata um string no formato passado
	 * @param valor
	 * @param formato
	 * @return
	 */
	public static String formata(Object valor, String formato) {
		if (valor == null || valor.equals("")) {
			return "";
		}
		try {
			MaskFormatter formatador = new MaskFormatter(formato);
			formatador.setValueContainsLiteralCharacters(false);
			return formatador.valueToString(valor);
		} catch (Exception e) {
			System.err.println("Nï¿½o foi possï¿½vel aplicar mï¿½scara ao valor: "
					+ valor);
			e.printStackTrace();
			return (String) valor;
		}
	}
	
	/**
	 * Mï¿½todo responsï¿½vel por encriptar um texto usando o algoritmo MD5
	 * @param texto
	 * @return
	 */
	public static String encripta(String texto){
    	try {
    		
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(texto.getBytes());

            return bytesToHex( messageDigest.digest() );
            
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e.getMessage());
        } 		
	}
	
    private static String bytesToHex(byte[] b) {

    	char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuffer buf = new StringBuffer();
        for (int j=0; j<b.length; j++) {
        	buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
        
    } 	
    
    /**
     * Método responsável por reverter um texto
     * 
     * @param texto 
     * Texto a ser revertido
     * @return
     * Testo revertido
     */
    public static String reverse(String texto) {
         
         String stringReverse = null;
         if (Objeto.notBlank(texto)) {
              
              StringBuffer buf = new StringBuffer(texto);
              stringReverse = buf.reverse().toString();
         }
         return stringReverse;
    }
}