/*
 * Created on 20/08/2003
 *
 *Utilitï¿½rios para trabalhar com a API Reflection do Java.
 */
package com.github.thiagonego.alfred.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.github.thiagonego.alfred.excecoes.ExcecaoUtil;


/**
 * @author luizcgr
 * 
 */
public class Reflexao {
	@SuppressWarnings("unchecked")
	private static Map<String, Class> mapaDeClasses;


	/**
	 * Retorna o valor do atributo separado por seus vï¿½rios nï¿½veis. Por exemplo:
	 * aluno.titular.nome; titulo.conta.agencia;
	 * 
	 * @param objeto
	 *            Objeto do qual será extraï¿½do o valor
	 * @param atributo
	 *            Atributo do objeto do qual será extraï¿½do o valor
	 * @return Valor do atributo
	 */
	public static Object getValorDoAtributoComposto(Object objeto,
			String atributo) {
		Object valorDoAtributo = objeto;
		StringTokenizer token = new StringTokenizer(atributo, ".");
		while (token.hasMoreTokens()) {
			if (valorDoAtributo == null) {
				return null;
			}
			valorDoAtributo = getValorDoAtributo(valorDoAtributo, token
					.nextToken());
		}
		return valorDoAtributo;
	}

	/**
	 * @return Returns the mapaDeClasses.
	 */
	@SuppressWarnings("unchecked")
	private static final Map<String, Class> getMapaDeClasses() {
		if (mapaDeClasses == null) {
			mapaDeClasses = new HashMap<String, Class>();
		}
		return mapaDeClasses;
	}

	private static final String getNomeBaseDoMetodo(String nomeDoAtributo) {
		String nomeBaseDoMetodo = nomeDoAtributo.substring(0, 1).toUpperCase()
				+ nomeDoAtributo.substring(1, nomeDoAtributo.length());
		return nomeBaseDoMetodo;
	}

	public static final String getNomeDoMetodoGet(String nomeDoAtributo) {
		return "get" + getNomeBaseDoMetodo(nomeDoAtributo);
	}

	/**
	 * 
	 * @param nomeDoAtributo
	 * @return
	 */
	private static final String getNomeDoMetodoSet(String nomeDoAtributo) {
		return "set" + getNomeBaseDoMetodo(nomeDoAtributo);
	}

	/**
	 * @param nomeDaClasse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Object criaInstancia(String nomeDaClasse) {
		return criaInstancia(getClasse(nomeDaClasse));
	}

	public static final <T extends Object> T criaInstancia(Class<T> classe) {
		try {
			return classe.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <T extends Object> T criaInstancia(Class<T> classe,
			Object... parametros) {
		try {
			Class[] tipos = new Class[parametros.length];
			for (int i = 0; i < parametros.length; i++) {
				tipos[i] = parametros[i].getClass();
			}
			Constructor<T> construtor = classe.getConstructor(tipos);
			return construtor.newInstance(parametros);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param nomeDaClasse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Class getClasse(String nomeDaClasse) {
		return consultaCacheDeClasses(nomeDaClasse);
	}

	/**
	 * 
	 * @param nomeDaClasse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static final Class consultaCacheDeClasses(String nomeDaClasse) {
		Class classe = getMapaDeClasses().get(nomeDaClasse);
		if (classe == null) {
			classe = carregaCacheDeClasses(nomeDaClasse);
		}
		return classe;
	}

	/**
	 * @param nomeDaClasse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static final Class carregaCacheDeClasses(String nomeDaClasse) {
		try {
			Class classe = Class.forName(nomeDaClasse);
			getMapaDeClasses().put(nomeDaClasse, classe);
			return classe;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param parametros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Class[] getClassesDosParametros(Object[] parametros) {
		if (parametros == null) {
			return null;
		}
		Class[] classes = new Class[parametros.length];
		for (int i = 0; i < classes.length; i++) {
			classes[i] = parametros[i].getClass();
		}
		return classes;
	}

	@SuppressWarnings("unchecked")
	public static final Object executaMetodoEstatico(String nomeDaClasse,
			String nomeDoMetodo, Object[] parametros) {
		Class classe = getClasse(nomeDaClasse);
		try {
			Method metodo = classe.getMethod(nomeDoMetodo,
					getClassesDosParametros(parametros));
			return metodo.invoke(classe, parametros);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static final Object executaMetodo(String nomeDaClasse,
			String nomeDoMetodo, Object[] parametros) {
		Object objeto = criaInstancia(nomeDaClasse);
		try {
			Method metodo = objeto.getClass().getMethod(nomeDoMetodo,
					getClassesDosParametros(parametros));
			return metodo.invoke(objeto, parametros);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static final Object executaMetodo(Object objeto,
			String nomeDoMetodo, Object[] parametros) {
		Method metodo = getMetodo(objeto, nomeDoMetodo, parametros);
		try {
			return metodo.invoke(metodo, parametros);
		} catch (Exception e) {
			throw new ExcecaoUtil(e);
		}
	}

	@SuppressWarnings("unchecked")
	private static final Method getMetodo(Object objeto, String nomeDoMetodo,
			Object[] parametros) {
		Class[] tiposDosParametros = getTiposDosParametros(parametros);
		try {
			return objeto.getClass()
					.getMethod(nomeDoMetodo, tiposDosParametros);
		} catch (Exception e) {
			throw new ExcecaoUtil(e);
		}
	}

	@SuppressWarnings("unchecked")
	private static Class[] getTiposDosParametros(Object[] parametros) {
		Class[] tiposDosParametros = new Class[parametros.length];
		for (int i = 0; i < parametros.length; i++) {
			tiposDosParametros[i] = parametros[i].getClass();
		}
		return tiposDosParametros;
	}

	/**
	 * @param objetoDestino
	 * @param nomeDoCampo
	 * @param valor
	 */
	public static final void setValorDoAtributo(Object objetoDestino,
			String nomeDoCampo, Object valor) {
		Method metodo = getMetodoSet(objetoDestino, nomeDoCampo);
		Object[] parametros = { valor };
		executaMetodo(objetoDestino, metodo, parametros);
	}

	public static final Object getValorDoAtributo(Object objeto,
			String nomeDoCampo) {
		Method metodo = getMetodoGet(objeto, nomeDoCampo);
		return executaMetodo(objeto, metodo, null);
	}

	/**
	 * @param objeto
	 * @param nomeDoCampo
	 * @return
	 */
	private static final Method getMetodoGet(Object objeto, String nomeDoCampo) {
		return criaMetodoGetDoAtributo(objeto, nomeDoCampo);
	}

	/**
	 * @param objeto
	 * @param nomeDoCampo
	 * @return
	 */
	private static final Method criaMetodoGetDoAtributo(Object objeto,
			String nomeDoCampo) {
		return criaMetodo(objeto.getClass(), getNomeDoMetodoGet(nomeDoCampo),
				null);
	}

	/**
	 * @param objetoDestino
	 * @param metodo
	 * @param parametro
	 */
	private static final Object executaMetodo(Object objetoDestino,
			Method metodo, Object[] parametros) {
		try {
			return metodo.invoke(objetoDestino, parametros);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getCause());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retorna o descritor do método <code>set()</code> para um campo no objeto
	 * indicado.
	 * 
	 * @param objetoDestino
	 *            O objeto de onde extrair o método
	 * @param nomeDoCampo
	 *            O nome do campo para o qual se deseja obter o método
	 * @return O descritor do método <code>set()</code> para o campo no objeto.
	 */
	@SuppressWarnings("unchecked")
	public static final Method getMetodoSet(Object objetoDestino,
			String nomeDoCampo) {
		Class classeDoParametro = descobreClasseDoParametro(objetoDestino,
					nomeDoCampo);
			
		return criaMetodoSetDoAtributo(objetoDestino, nomeDoCampo,
				classeDoParametro);
	}

	/**
	 * @param objetoDestino
	 * @param nomeDoCampo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Class descobreClasseDoParametro(Object objetoDestino,
			String nomeDoCampo) {
		Class classeDoParametro = getMetodoGet(objetoDestino, nomeDoCampo)
				.getReturnType();
		if (classeDoParametro == null) {
			classeDoParametro = objetoDestino.getClass();
		}
		return classeDoParametro;
	}

	/**
	 * @param objetoDestino
	 * @param nomeDoCampo
	 * @param classeDoParametro
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static final Method criaMetodoSetDoAtributo(Object objetoDestino,
			String nomeDoCampo, Class classeDoParametro) {
		Class[] classesDosParametros = { classeDoParametro };
		Method metodo = criaMetodo(objetoDestino.getClass(),
				getNomeDoMetodoSet(nomeDoCampo), classesDosParametros);
		return metodo;
	}

	/**
	 * @param classeDoObjetoDestino
	 * @param nomeDoCampo
	 * @param classeDoParametro
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static final Method criaMetodo(Class<?> classeDoObjetoDestino,
			String nomeDoMetodo, Class[] classesDosParametros) {
		Method metodo;
		try {
			metodo = classeDoObjetoDestino.getMethod(nomeDoMetodo,
					classesDosParametros);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return metodo;
	}

	public static boolean existeGet(Object objeto, String nomeDoAtributo) {
		if (nomeDoAtributo != null) {
			try {
				objeto.getClass().getMethod(getNomeDoMetodoGet(nomeDoAtributo));
				return true;
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Verifica se existe o método <code>set()</code> para o campo indicado.
	 * Este método irá indicar que o campo não existe se não existir um método
	 * <code>get()</code> equivalente para o campo.
	 * 
	 * @param objeto
	 *            O objeto no qual será verificada a existência do método
	 * @param nomeDoCampo
	 *            O nome do campo para o qual será verificada a existência do
	 *            método
	 * @return <code>true</code> se existir um método <code>set()</code> vï¿½lido
	 *         para o campo indicado; caso contrário, retorna <code>false</code>
	 *         .
	 */
	public static boolean existeSet(Object objeto, String nomeDoCampo) {
		if (nomeDoCampo != null) {
			try {
				getMetodoSet(objeto, nomeDoCampo);
				return true;
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (RuntimeException e) {
				return false;
			}
		}
		return false;
	}
}