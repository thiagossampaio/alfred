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
package com.github.thiagonego.alfred.numeros.digitos;

/**
 * Implements ISO 7064 Mod 11,2 check digit scheme.
 * 
 * @author nickg
 * @version 1
 */
public class DigitoISO7064Mod11_2 implements Digito {

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#encode(java.lang.String)
	 */
	public String encode(String digits) {
		int c = computeCheck(digits);
		if (c == 10) {
			return digits + 'X';
		} else {
			return digits + c;
		}
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#verify(java.lang.String)
	 */
	public boolean verify(String digits) {
		// normally I'd redo the algorith, but with check digit
		// using 0-9 + X, it's easiler this way.
		try {
			return computeCheck(getData(digits)) == getCheckDigit(digits);
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#computeCheck(java.lang.String)
	 */
	public int computeCheck(String digits) {
		int p = 0;
		for (int i = 0; i < digits.length(); ++i) {
			int c = digits.charAt(i) - '0';
		     if (c < 0 || c > 9) {
		     	throw new NumberFormatException("'" + digits + "' has bad digit: '" + digits.charAt(i) + "'");
			}
		    p = 2*(p + c);
		}
		p = p % 11;
		// check + p == 1 Mod 11
		return (12 - p) % 11;
		// could also do
		//if (p <= 1) { return 1 -p} else { return 12 -p}
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#getCheckDigit(java.lang.String)
	 */
	public int getCheckDigit(String digits) {
		char c = digits.charAt(digits.length() -1);
		if (c == 'X' || c == 'x') {
			return 10;
		} else {
			return c - '0';
		}
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#getData(java.lang.String)
	 */
	public String getData(String digits) {
		return digits.substring(0, digits.length() -1);
	}

}
