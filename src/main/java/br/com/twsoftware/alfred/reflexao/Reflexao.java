/*
 * Created on 20/08/2003
 *
 *Utilit�rios para trabalhar com a API Reflection do Java.
 */
package br.com.twsoftware.alfred.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import br.com.twsoftware.alfred.excecoes.ExcecaoUtil;


/**
 * @author luizcgr
 * 
 */
public class Reflexao {
	@SuppressWarnings("unchecked")
	private static Map<String, Class> mapaDeClasses;
	private static Map<String, Map<String, Method>> mapaDeSets;
	private static Map<String, Map<String, Method>> mapaDeGets;

	/**
	 * @return Returns the mapaDeSets.
	 */
	private static final Map<String, Map<String, Method>> getMapaDeSets() {
		if (mapaDeSets == null) {
			mapaDeSets = new HashMap<String, Map<String, Method>>();
		}
		return mapaDeSets;
	}

	/**
	 * Retorna o valor do atributo separado por seus v�rios n�veis. Por exemplo:
	 * aluno.titular.nome; titulo.conta.agencia;
	 * 
	 * @param objeto
	 *            Objeto do qual ser� extra�do o valor
	 * @param atributo
	 *            Atributo do objeto do qual ser� extra�do o valor
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

	private static final String getNomeDoMetodoGet(String nomeDoAtributo) {
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
	 * @param visao
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
	 * @return Returns the mapaDeGets.
	 */
	private static final Map<String, Map<String, Method>> getMapaDeGets() {
		if (mapaDeGets == null) {
			mapaDeGets = new HashMap<String, Map<String, Method>>();
		}
		return mapaDeGets;
	}

	/**
	 * @param objeto
	 * @param nomeDoCampo
	 * @return
	 */
	private static final Method getMetodoGet(Object objeto, String nomeDoCampo) {
		Map<String, Method> getsDaClasse = getGetsDaClasse(objeto.getClass()
				.getName());
		Method metodoSet = getsDaClasse.get(nomeDoCampo);
		if (metodoSet == null) {
			metodoSet = criaMetodoGetDoAtributo(objeto, nomeDoCampo);
			getsDaClasse.put(nomeDoCampo, metodoSet);
		}
		return metodoSet;
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
	 * @param name
	 * @return
	 */
	private static final Map<String, Method> getGetsDaClasse(String nomeDoCampo) {
		Map<String, Method> getsDaClasse = getMapaDeGets().get(nomeDoCampo);
		if (getsDaClasse == null) {
			getsDaClasse = new HashMap<String, Method>();
			getMapaDeGets().put(nomeDoCampo, getsDaClasse);
		}
		return getsDaClasse;
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
	 * Retorna o descritor do m�todo <code>set()</code> para um campo no objeto
	 * indicado.
	 * 
	 * @param objetoDestino
	 *            O objeto de onde extrair o m�todo
	 * @param nomeDoCampo
	 *            O nome do campo para o qual se deseja obter o m�todo
	 * @return O descritor do m�todo <code>set()</code> para o campo no objeto.
	 */
	@SuppressWarnings("unchecked")
	public static final Method getMetodoSet(Object objetoDestino,
			String nomeDoCampo) {
		Map<String, Method> setsDaClasse = getSetsDaClasse(objetoDestino
				.getClass());
		Method metodo = setsDaClasse.get(nomeDoCampo);
		if (metodo == null) {
			Class classeDoParametro = descobreClasseDoParametro(objetoDestino,
					nomeDoCampo);
			metodo = criaMetodoSetDoAtributo(objetoDestino, nomeDoCampo,
					classeDoParametro);
			setsDaClasse.put(nomeDoCampo, metodo);
		}
		return metodo;
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

	/**
	 * @param classe
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static final Map<String, Method> getSetsDaClasse(Class classe) {
		Map<String, Method> setsDaClasse = getMapaDeSets().get(classe);
		if (setsDaClasse == null) {
			setsDaClasse = new HashMap<String, Method>();
			getMapaDeSets().put(classe.getName(), setsDaClasse);
		}
		return setsDaClasse;
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
	 * Verifica se existe o m�todo <code>set()</code> para o campo indicado.
	 * Este m�todo ir� indicar que o campo n�o existe se n�o existir um m�todo
	 * <code>get()</code> equivalente para o campo.
	 * 
	 * @param objeto
	 *            O objeto no qual ser� verificada a exist�ncia do m�todo
	 * @param nomeDoCampo
	 *            O nome do campo para o qual ser� verificada a exist�ncia do
	 *            m�todo
	 * @return <code>true</code> se existir um m�todo <code>set()</code> v�lido
	 *         para o campo indicado; caso contr�rio, retorna <code>false</code>
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