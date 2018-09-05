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
 * Implements ISO 7064 Mod97,10 check digit scheme
 * 
 * @author nickg
 * @version 1
 */
public class DigitoISO7064Mod97_10 implements Digito {

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#encode(java.lang.String)
	 */
	public String encode(String digits) {
		int c = computeCheck(digits);
		if (c == 0) {
			return digits + "00";
		} else if (c < 10) {
			return digits + '0' + c;
		} else {
			return digits + c;
		}
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#verify(java.lang.String)
	 */
	public boolean verify(String digits) {
		return (Long.parseLong(digits) % 97 == 1);
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#computeCheck(java.lang.String)
	 */
	public int computeCheck(String digits) {
		return ((int)(98 - (Long.parseLong(digits) * 100) % 97L)) % 97;
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#getCheckDigit(java.lang.String)
	 */
	public int getCheckDigit(String digits) {
		return Integer.parseInt(digits.substring(digits.length() -2));
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#getData(java.lang.String)
	 */
	public String getData(String digits) {
		return digits.substring(0, digits.length() -2);
	}
}
