package br.com.twsoftware.alfred.io;

import java.util.ArrayList;
import java.util.List;

/**
 * C�digo obtido do Blog de Amar Maniar no endere�o http://amarkm.wordpress.com/2007/05/30/csv/.
 * Editado para remover os coment�rios.
 * 
 * @author Amar Maniar
 */
@SuppressWarnings("all")
class CSV {
	private int nfield;
	char fieldSep;
	List field = new ArrayList();
	String fld = new String();

	public int split(String line) {
		int i, j;
		fld = "";
		nfield = 0;
		if (line.length() == 0)
			return 0;
		i = 0;
		do {
			if (i < line.length() && line.charAt(i) == '"')
				j = advquoted(line, ++i);
			else
				j = advplain(line, i);

			field.add(fld);
			nfield++;
			i = j + 1;
		} while (j < line.length());
		return nfield;
	}

	public int advplain(String line, int i) {
		int j;
		j = line.indexOf(fieldSep, i);
		if (j == -1) {
			fld = line.substring(i);
			return line.length();
		} else
			fld = line.substring(i, j);
		return j;
	}

	public int advquoted(String line, int i) {
		int j;
		fld = "";
		for (j = i; j < line.length(); j++) {
			if (line.charAt(j) == '"' && j + 1 < line.length()) {
				if (line.charAt(j + 1) == '"') {
					j++; // go to the next location
				} else if (line.charAt(j + 1) == fieldSep) {
					j++;
					break;
				}
			} else if (line.charAt(j) == '"' && j + 1 == line.length()) {
				break;
			}
			fld = fld + line.charAt(j);
		}
		return j;
	}

	/** Creates a new instance of Csv */
	public CSV() {
		fieldSep = ',';
	}

	public CSV(char sep) {
		fieldSep = sep;
	}

	public String getField(int n) {
		if (n < 0 || n >= nfield)
			return "";
		else
			return field.get(n).toString();
	}

	public int getNField() {
		return nfield;
	}
}