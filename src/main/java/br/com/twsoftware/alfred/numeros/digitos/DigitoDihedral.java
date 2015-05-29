/*
 * Copyright 2005, Nick Galbreath
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software for any purpose
 * with or without fee is hereby granted, provided that the above copyright
 * notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT OF THIRD PARTY RIGHTS. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * Except as contained in this notice, the name of a copyright holder shall not
 * be used in advertising or otherwise to promote the sale, use or other dealings
 * in this Software without prior written authorization of the copyright holder. 
 */
package br.com.twsoftware.alfred.numeros.digitos;

/**
 * Implemention of Verhoeff's Dihedral Check Digit
 * 
 * @author nickg
 * @version 1
 *
 */
public class DigitoDihedral implements Digito {

	/**
	 * dihedral addition matrix A + B = a[A][B]
	 */ 
	private static final int a[][] = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
			{ 1, 2, 3, 4, 0, 6, 7, 8, 9, 5 }, { 2, 3, 4, 0, 1, 7, 8, 9, 5, 6 },
			{ 3, 4, 0, 1, 2, 8, 9, 5, 6, 7 }, { 4, 0, 1, 2, 3, 9, 5, 6, 7, 8 },
			{ 5, 9, 8, 7, 6, 0, 4, 3, 2, 1 }, { 6, 5, 9, 8, 7, 1, 0, 4, 3, 2 },
			{ 7, 6, 5, 9, 8, 2, 1, 0, 4, 3 }, { 8, 7, 6, 5, 9, 3, 2, 1, 0, 4 },
			{ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 } };

	/**
	 *  dihedral inverse map, A + inverse[A] = 0
	 */
	private static final int inverse[] = { 0, 4, 3, 2, 1, 5, 6, 7, 8, 9 };

	/**
	 * permutation weighting matrix P[position][value]
	 */
	private static final int p[][] = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
			{ 1, 5, 7, 6, 2, 8, 3, 0, 9, 4 }, { 5, 8, 0, 3, 7, 9, 6, 1, 4, 2 },
			{ 8, 9, 1, 6, 0, 4, 3, 5, 2, 7 }, { 9, 4, 5, 3, 1, 2, 6, 8, 7, 0 },
			{ 4, 2, 8, 6, 5, 7, 3, 9, 0, 1 }, { 2, 7, 9, 3, 8, 0, 6, 4, 1, 5 },
			{ 7, 0, 4, 6, 9, 1, 3, 2, 5, 8 } };

	/**
	 * Constructor.
	 *
	 * This class is stateless and threadsafe.  It could have been
	 * implemented as all static methods.
	 * 
	 */
	public DigitoDihedral() {
		super();
	}

	public String encode(String digits) {
		return Integer.toString(computeCheck(digits)) + digits;
	}

	public boolean verify(String digits) {
		try {
			int check = 0;
			for (int i = 0; i < digits.length(); ++i) {
				check = a[check][p[i % 8][digits.charAt(i) - '0']];
			}
			return (check == 0);
		} catch (Exception e) {
			return false;
		}
	}

	public int computeCheck(String digits) {
		int check = 0;
		for (int i = 0; i < digits.length(); ++i) {
			int c = digits.charAt(i) - '0';
	     	if (c < 0 || c > 9) {
	     		throw new NumberFormatException("Bad digit: '" + digits.charAt(i) + "'");
	     	}
			check = a[check][p[(i + 1) % 8][c]];
		}
		return inverse[check];
	}
	
	public int getCheckDigit(String digits) {
		return Integer.parseInt(digits.substring(0,1));
	}
	
	public String getData(String digits) {
		return digits.substring(1);
	}
}
