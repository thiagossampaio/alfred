package br.com.twsoftware.alfred.colecoes;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//TODO: Auto-generated Javadoc
/**
* The Class MapUtils.
* 
* @author Marcos Peron
*/
public class MapUtils {

	/**
	 * Reverse.
	 * 
	 * @param map
	 *            the map
	 * 
	 * @return the map< k, v>
	 */
	public static <K extends Comparable<K>, V> Map<K, V> reverse(Map<K, V> map) {
		List<Map.Entry<K, V>> mapEntry = new LinkedList<Map.Entry<K, V>>(map
				.entrySet());
		Collections.reverse(mapEntry);
		LinkedHashMap<K, V> reversedMap = new LinkedHashMap<K, V>();
		for (Iterator<Map.Entry<K, V>> it = mapEntry.iterator(); it.hasNext();) {
			Map.Entry<K, V> entry = it.next();
			reversedMap.put(entry.getKey(), entry.getValue());
		}
		return (reversedMap);
	}

	/**
	 * Sort map by key.
	 * 
	 * @param map
	 *            the map
	 * 
	 * @return the map< k, v>
	 */
	public static <K extends Comparable<K>, V> Map<K, V> sortMapByKey(
			Map<K, V> map) {
		return (MapUtils.sortMapByKey(map, true));
	}

	/**
	 * Sort map by key.
	 * 
	 * @param map
	 *            the map
	 * @param ascending
	 *            if will be ascending or descending
	 * 
	 * @return the map< k, v>
	 */
	public static <K extends Comparable<K>, V> Map<K, V> sortMapByKey(
			Map<K, V> map, boolean ascending) {
		Comparator<K> c = new Comparator<K>() {
//			@Override
			public int compare(K o1, K o2) {
				return o1.compareTo(o2);
			}
		};
		return (MapUtils.sortMapByKey(map, c, ascending));
	}

	/**
	 * Sort map by key.
	 * 
	 * @param map
	 *            the map
	 * @param c
	 *            the comparator
	 * 
	 * @return the map< k, v>
	 */
	public static <K extends Comparable<K>, V> Map<K, V> sortMapByKey(
			Map<K, V> map, final Comparator<K> c) {
		return (MapUtils.sortMapByKey(map, c, true));
	}

	/**
	 * Sort map by key.
	 * 
	 * @param map
	 *            the map
	 * @param c
	 *            the comparator
	 * @param ascending
	 *            if will be ascending or descending
	 * 
	 * @return the map< k, v>
	 */
	public static <K extends Comparable<K>, V> Map<K, V> sortMapByKey(
			Map<K, V> map, final Comparator<K> c, boolean ascending) {
		List<Map.Entry<K, V>> mapEntry = new LinkedList<Map.Entry<K, V>>(map
				.entrySet());

		Collections.sort(mapEntry, new Comparator<Map.Entry<K, V>>() {
//			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return (c.compare(o1.getKey(), o2.getKey()));
			}
		});

		if (!ascending) {
			Collections.reverse(mapEntry);
		}

		LinkedHashMap<K, V> orderMap = new LinkedHashMap<K, V>();
		for (Iterator<Map.Entry<K, V>> it = mapEntry.iterator(); it.hasNext();) {
			Map.Entry<K, V> entry = it.next();
			orderMap.put(entry.getKey(), entry.getValue());
		}
		return (orderMap);
	}

	/**
	 * Sort map by value.
	 * 
	 * @param map
	 *            the map
	 * 
	 * @return the map< k, v>
	 */
	public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(
			Map<K, V> map) {
		return (MapUtils.sortMapByValue(map, true));
	}

	/**
	 * Sort map by value.
	 * 
	 * @param map
	 *            the map
	 * @param ascending
	 *            if will be ascending or descending
	 * 
	 * @return the map< k, v>
	 */
	public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(
			Map<K, V> map, boolean ascending) {
		Comparator<V> c = new Comparator<V>() {
//			@Override
			public int compare(V o1, V o2) {
				return o1.compareTo(o2);
			}
		};
		return (MapUtils.sortMapByValue(map, c, ascending));
	}

	/**
	 * Sort map by value.
	 * 
	 * @param map
	 *            the map
	 * @param c
	 *            the comparator
	 * 
	 * @return the map< k, v>
	 */
	public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(
			Map<K, V> map, final Comparator<V> c) {
		return (MapUtils.sortMapByValue(map, c, true));
	}

	/**
	 * Sort map by value.
	 * 
	 * @param map
	 *            the map
	 * @param c
	 *            the comparator
	 * @param ascending
	 *            if will be ascending or descending
	 * 
	 * @return the map< k, v>
	 */
	public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(
			Map<K, V> map, final Comparator<V> c, boolean ascending) {
		List<Map.Entry<K, V>> mapEntry = new LinkedList<Map.Entry<K, V>>(map
				.entrySet());
		Collections.sort(mapEntry, new Comparator<Map.Entry<K, V>>() {
//			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return (c.compare(o1.getValue(), o2.getValue()));
			}
		});

		if (!ascending) {
			Collections.reverse(mapEntry);
		}
		LinkedHashMap<K, V> orderMap = new LinkedHashMap<K, V>();
		for (Iterator<Map.Entry<K, V>> it = mapEntry.iterator(); it.hasNext();) {
			Map.Entry<K, V> entry = it.next();
			orderMap.put(entry.getKey(), entry.getValue());
		}
		return (orderMap);
	}
	
}
