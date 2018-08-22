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
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import br.com.twsoftware.alfred.AlfredException;
import br.com.twsoftware.alfred.excecoes.ExcecaoArquivoNaoEncontrado;
import br.com.twsoftware.alfred.excecoes.ExcecaoClasseNaoEncontrada;
import br.com.twsoftware.alfred.excecoes.ExcecaoErroDeEntradaESaidaDeArquivo;
import br.com.twsoftware.alfred.texto.Texto;


/**
 * Utilitarios para Arquivos.
 * 
 * @author Mario Jorge Pereira
 * @author Marlon Silva Carvalho
 * @since 09/06/2009
 */
final public class Arquivo {
	
	private static final int TAMANHO_DO_BUFFER = 2048;

	private Arquivo() {}
	
	/**
	 * @return: String
	 * @param caminhoCompleto
	 */
	public static String extraiNomeDoArquivo(String caminhoCompleto) {
		// Dessa forma conseguimos trabalhar com o formato do Windows/unix/linux
		int ultimaBarra = caminhoCompleto.lastIndexOf("/") + 1;
		if (ultimaBarra == 0) {
			ultimaBarra = caminhoCompleto.lastIndexOf("\\") + 1;
		}
		String nome = caminhoCompleto.substring(ultimaBarra);
		return nome;
	}

	/**
	 * @return: String
	 * @param caminhoDoArquivo
	 */
	public static String extraiDiretorioDoArquivo(String caminhoDoArquivo) {
		String diretorio;
		int pos = caminhoDoArquivo.lastIndexOf('/');
		if (pos < 0) {
			pos = caminhoDoArquivo.lastIndexOf('\\');
		}
		diretorio = caminhoDoArquivo.substring(0, pos + 1);
		return diretorio;
	}

	/**
	 * @return: void
	 * @param origem
	 * @param destino
	 * @throws IOException
	 */
	public static void gravaArquivoNoDestino(InputStream origem, String destino)
			throws IOException {
		OutputStream arquivoDestino = new FileOutputStream(destino);
		copiaStreamDeDados(origem, arquivoDestino);
		arquivoDestino.close();
	}

	/**
	 * @return: void
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public static void copiaStreamDeDados(InputStream input, OutputStream output)
			throws IOException {
		if (input != null && output != null) {
			byte[] buffer = new byte[TAMANHO_DO_BUFFER];
			int bytesLidos;
			while ((bytesLidos = input.read(buffer)) > 0) {
				output.write(buffer, 0, bytesLidos);
			}
		}
	}

	/**
	 * @return: boolean
	 * @param diretorio
	 */
	public static boolean existe(String diretorio) {
		return new File(diretorio).exists();
	}

	/**
	 * @return: boolean
	 * @param diretorio
	 */
	public static boolean criaDiretorio(String diretorio) {
		return new File(diretorio).mkdirs();
	}

	/**
	 * @return: boolean
	 * @param arquivo
	 */
	public static boolean apagaArquivo(String arquivo) {
		return apagaArquivo(new File(arquivo));
	}

	/**
	 * @return: boolean
	 * @param file
	 */
	public static boolean apagaArquivo(File file) {
		return apagaDiretorio(file);
	}

	/**
	 * @return: Object
	 * @param arquivo
	 * @throws ExcecaoArquivoNaoEncontrado
	 * @throws ExcecaoErroDeEntradaESaidaDeArquivo
	 * @throws ExcecaoClasseNaoEncontrada
	 */
	public static Object leObjeto(String arquivo)
			throws ExcecaoArquivoNaoEncontrado,
			ExcecaoErroDeEntradaESaidaDeArquivo, ExcecaoClasseNaoEncontrada {
		FileInputStream fis;
		Object o = null;
		try {
			fis = new FileInputStream(arquivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			o = ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			throw new ExcecaoArquivoNaoEncontrado(e);
		} catch (IOException e) {
			throw new ExcecaoErroDeEntradaESaidaDeArquivo(e);
		} catch (ClassNotFoundException e) {
			throw new ExcecaoClasseNaoEncontrada(e);
		}
		return o;
	}

	/**
	 * @return: void
	 * @param objeto
	 * @param arquivo
	 * @throws ExcecaoErroDeEntradaESaidaDeArquivo
	 * @throws ExcecaoArquivoNaoEncontrado
	 */
	public static void gravaObjeto(Object objeto, String arquivo)
			throws ExcecaoErroDeEntradaESaidaDeArquivo,
			ExcecaoArquivoNaoEncontrado {
		File file = new File(arquivo);
		try {
			FileOutputStream saida = new FileOutputStream(file);
			ObjectOutputStream o = new ObjectOutputStream(saida);
			o.writeObject(objeto);
			o.close();
			saida.close();
		} catch (FileNotFoundException e) {
			throw new ExcecaoArquivoNaoEncontrado(e);
		} catch (IOException e) {
			throw new ExcecaoErroDeEntradaESaidaDeArquivo(e);
		}
	}

	/**
	 * @return: void
	 * @param objeto
	 * @param nomeDoArquivo
	 * @param diretorio
	 * @throws ExcecaoErroDeEntradaESaidaDeArquivo
	 */
	public static void escreveStringNoArquivo(String objeto,
			String nomeDoArquivo, String diretorio)
			throws ExcecaoErroDeEntradaESaidaDeArquivo {
		Writer saida = null;
		try {
			saida = new BufferedWriter(new FileWriter(new File(diretorio
					+ nomeDoArquivo)));
			saida.write(objeto);
		} catch (IOException e) {
			throw new ExcecaoErroDeEntradaESaidaDeArquivo(e);
		} finally {
			try {
				if (saida != null) {
					saida.close();
				}
			} catch (IOException e) {
				throw new ExcecaoErroDeEntradaESaidaDeArquivo(e);
			}
		}
	}

	/**
	 * @return: StringBuilder
	 * @param nomeDoArquivo
	 * @throws ExcecaoErroDeEntradaESaidaDeArquivo
	 */
	public static StringBuilder getStringDoArquivo(String nomeDoArquivo)
			throws ExcecaoErroDeEntradaESaidaDeArquivo {
		String quebraDeLinha = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		String line;
		try {
			br = new BufferedReader(new FileReader(new File(nomeDoArquivo)));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(quebraDeLinha);
			}
		} catch (FileNotFoundException e) {
			throw new ExcecaoErroDeEntradaESaidaDeArquivo(e);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				throw new ExcecaoErroDeEntradaESaidaDeArquivo(e);
			}
		}
		return sb;
	}

	/**
	 * @return: boolean
	 * @param diretorio
	 */
	public static boolean apagaDiretorio(String diretorio) {
		return apagaDiretorio(new File(diretorio));
	}

	/**
	 * @return: boolean
	 * @param diretorio
	 */
	public static boolean apagaDiretorio(File diretorio) {
		if (diretorio.isDirectory()) {
			File[] arquivos = diretorio.listFiles();
			for (int i = 0; i < arquivos.length; i++) {
				if (!apagaDiretorio(arquivos[i])) {
					return false;
				}
			}
		}
		return diretorio.delete();
	}

	/**
	 * @return: InputStream
	 * @param conteudoHexadecimal
	 * @throws IOException
	 */
	public static synchronized InputStream geraArquivoDeStringHexadecimal(
			String conteudoHexadecimal) throws IOException {
		byte[] bytesDoArquivo = Texto.converteStringHexadecimalEmBytes(conteudoHexadecimal);
		InputStream arquivo = new ByteArrayInputStream(bytesDoArquivo);
		return arquivo;
	}	
	
	/**
	 * @return: void
	 * @param arquivoDeOrigem
	 * @param arquivoDestino
	 */
	public static void moveArquivo(String arquivoDeOrigem, String arquivoDestino) {
		try {
			File destino = new File(arquivoDestino);
			if (destino.exists()) {
				destino.delete();
			}
			File origem = new File(arquivoDeOrigem);
			origem.renameTo(new File(arquivoDestino));
		} catch (Exception ex) {
			throw new RuntimeException(
					"N�o foi possivel mover o arquivo "
							+ arquivoDeOrigem
							+ " para "
							+ arquivoDestino
							+ ". Verifique as permiss�es de acesso e se o arquivo de origem existe.");
		}
	}	
	
	/**
	 * @return: void
	 * @param arquivoDeOrigem
	 * @param arquivoDestino
	 */
	public static void copiaArquivo(String arquivoDeOrigem,
			String arquivoDestino) {
		try {
			// C�digo do Tiago
			FileChannel sourceChannel = new FileInputStream(arquivoDeOrigem)
					.getChannel();
			FileChannel destinationChannel = new FileOutputStream(
					arquivoDestino).getChannel();
			sourceChannel.transferTo(0, sourceChannel.size(),
					destinationChannel);
			sourceChannel.close();
			destinationChannel.close();
			//
			sourceChannel = null;
			destinationChannel = null;
		} catch (IOException ex) {
			throw new RuntimeException("N�o foi poss�vel copiar o arquivo.");
		}
	}	

	/**
	 * Salvar um arquivo.
	 *  
	 * @param local Caminho do arquivo.
	 * @param fileData Dados.
	 * @param nome Nome do arquivo.
	 * @return Verdadeiro caso a opera tenha sido realizada com sucesso.
	 */
	public static boolean salvar(String local, byte[] fileData, String nome) {
		FileOutputStream out = null;
		File f = new File(local + nome);
		if (f.exists()) {
			return false;
		} else {
			try {
				out = new FileOutputStream(f);
				out.write(fileData);
				out.flush();
				out.close();
			} catch (FileNotFoundException ex) {
				throw new AlfredException("Arquivo n�o encontrado! ", ex);
			} catch (IOException ex) {
				throw new AlfredException("Erro ao gravar o arquivo! ", ex);
			}

		}
		return true;
	}

	/**
	 * Sobrescrever um arquivo.
	 * 
	 * @param local Caminho do arquivo.
	 * @param fileData Dados.
	 * @param nome Nome do arquivo.
	 * @return Verdadeiro caso a opera tenha sido realizada com sucesso.
	 */
	public static boolean sobrescrever(String local, byte[] fileData, String nome) {
		FileOutputStream out = null;
		File f = new File(local + nome);
		if (f.exists()) {
			f.delete();
		}
		try {
			out = new FileOutputStream(f);
			out.write(fileData);
			out.flush();
			out.close();
		} catch (FileNotFoundException ex) {
			throw new AlfredException("Arquivo n�o encontrado! ", ex);
		} catch (IOException ex) {
			throw new AlfredException("Erro ao gravar o arquivo! ", ex);
		}
		return true;
	}

	/**
	 * Listar arquivos espec�ficos de um determinado diret�rio.
	 * 
	 * @param local Diret�rio.
	 * @param terminacoes Termina��es dos arquivos.
	 * @return Lista de arquivos encontrados.
	 */
	public static File[] listarArquivosEspecificosDiretorio(String local, final List<String> terminacoes) {
		File f = new File(local);
		File[] arquivos = f.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				String arquivo = pathname.getName().toLowerCase();
				boolean resposta = false;
				for(String extensao : terminacoes){
					if(arquivo.endsWith(extensao.toLowerCase())){
						resposta=true;
					}
				}
				return resposta;
			}
		});
		return arquivos;
	}

	/**
	 * Listar arquivos de um diret�rio conforme um filtro e podendo ser recursivo.
	 * 
	 * @param directory Diret�rio.
	 * @param filter Filtro.
	 * @param recurse Se � recursivo.
	 * @return Lista de arquivos.
	 */
	public static File[] listarArquivosArray(File directory, FilenameFilter filter, boolean recurse) {
		Collection<File> files = listarArquivos(directory, filter, recurse);
		File[] arr = new File[files.size()];
		return files.toArray(arr);
	}

	/**
	 * Listar arquivos de um diret�rio conforme um filtro e podendo ser recursivo.
	 * 
	 * @param directory Diret�rio.
	 * @param filter Filtro.
	 * @param recurse Se � recursivo.
	 * @return Lista de arquivos.
	 */
	public static Collection<File> listarArquivos(File directory,FilenameFilter filter, boolean recurse) {
		Vector<File> files = new Vector<File>();
		File[] entries = directory.listFiles();
		for (File entry : entries) {
			if (filter == null || filter.accept(directory, entry.getName())) {
				files.add(entry);
			}
			if (recurse && entry.isDirectory()) {
				files.addAll(listarArquivos(entry, filter, recurse));
			}
		}
		return files;
	}

	/**
	 * Obter a extens�o de um arquivo.
	 * 
	 * @param file Arquivo.
	 * @return Extens�o.
	 */
	public static String obterExtensao(File file) {
		String nome = file.getName();
		return obterExtensao(nome);
	}

	/**
	 * Obter a extens�o de um arquivo.
	 * 
	 * @param nome Nome do arquivo.
	 * @return Extens�o.
	 */
	public static String obterExtensao(String nome) {
		String resp = "";
		int i = nome.lastIndexOf(".");
		if (i != -1) {
			resp = nome.substring(i + 1);
		}
		return resp.toLowerCase();
	}

}
