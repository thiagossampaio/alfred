package com.github.thiagonego.alfred.html;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Html {
	private static Map<String, String> mapaDeCaracteres;
	static {
		mapaDeCaracteres = new HashMap<String, String>();
		// mapaDeCaracteres.put("<","&lt;");
		// mapaDeCaracteres.put(">","&gt;");
		// mapaDeCaracteres.put("&","&amp;");
		mapaDeCaracteres.put("\"", "&quot;");
		mapaDeCaracteres.put("�", "&aacute;");
		mapaDeCaracteres.put("�", "&acirc;");
		mapaDeCaracteres.put("�", "&agrave;");
		mapaDeCaracteres.put("�", "&atilde;");
		mapaDeCaracteres.put("�", "&ccedil;");
		mapaDeCaracteres.put("�", "&eacute;");
		mapaDeCaracteres.put("�", "&ecirc;");
		mapaDeCaracteres.put("�", "&iacute;");
		mapaDeCaracteres.put("�", "&oacute;");
		mapaDeCaracteres.put("�", "&ocirc;");
		mapaDeCaracteres.put("�", "&otilde;");
		mapaDeCaracteres.put("�", "&uacute;");
		mapaDeCaracteres.put("�", "&uuml;");
		mapaDeCaracteres.put("�", "&Aacute;");
		mapaDeCaracteres.put("�", "&Acirc;");
		mapaDeCaracteres.put("�", "&Agrave;");
		mapaDeCaracteres.put("�", "&Atilde;");
		mapaDeCaracteres.put("�", "&Ccedil;");
		mapaDeCaracteres.put("�", "&Eacute;");
		mapaDeCaracteres.put("�", "&Ecirc;");
		mapaDeCaracteres.put("�", "&Iacute;");
		mapaDeCaracteres.put("�", "&Oacute;");
		mapaDeCaracteres.put("�", "&Ocirc;");
		mapaDeCaracteres.put("�", "&Otilde;");
		mapaDeCaracteres.put("�", "&Uacute;");
		mapaDeCaracteres.put("�", "&Uuml;");
		mapaDeCaracteres.put("'", "&#39;");
		mapaDeCaracteres.put("�", "&ordf;");
		mapaDeCaracteres.put("�", "&ordm;");
	}

	public static String converteCaracteresEspaciais(String entrada) {
		String saida = entrada;
		for (Iterator<String> iter = mapaDeCaracteres.keySet().iterator(); iter
				.hasNext();) {
			String simbolo = (String) iter.next();
			saida = saida.replaceAll(simbolo, mapaDeCaracteres.get(simbolo));
		}
		return saida;
	}

	public static String converteCaracteresNormais(String entrada) {
		String saida = entrada;
		for (Iterator<String> iter = mapaDeCaracteres.keySet().iterator(); iter
				.hasNext();) {
			String simbolo = (String) iter.next();
			saida = saida.replaceAll(mapaDeCaracteres.get(simbolo), simbolo);
		}
		return saida;
	}

	public static void main(String[] args) {
		String teste = new String("1� aaa 1� abc�������''\"");
		System.out.println(Html.converteCaracteresEspaciais(teste));
		System.out.println(Html.converteCaracteresNormais(teste));
	}

//	public static String formataXHTML(String html) {
//		InputStream in = converteParaXHTML(new ByteArrayInputStream(html
//				.getBytes()));
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		try {
//			Arquivo.copiaStreamDeDados(in, out);
//			return new String(out.toByteArray());
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}

//	public static InputStream converteParaXHTML(InputStream documento) {
//		try {
//			Tidy tidy = new Tidy();
//			tidy.setTidyMark(false);
//			tidy.setXHTML(false);
//			tidy.setXmlOut(true);
//			tidy.setQuiet(true);
//			tidy.setDocType("omit");
//			tidy.setAsciiChars(false);
//			tidy.setShowWarnings(false);
//			tidy.setQuoteAmpersand(true);
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			tidy.parse(documento, out);
//			return new ByteArrayInputStream(out.toByteArray());
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
}
