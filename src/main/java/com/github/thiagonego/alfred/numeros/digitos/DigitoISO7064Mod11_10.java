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
 * Implements ISO 7064 Mod 11,10 check digit scheme.
 * 
 * @author nickg
 * @version 1
 */
public class DigitoISO7064Mod11_10 implements Digito {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.modp.checkdigit.CheckDigit#encode(java.lang.String)
	 */
	public String encode(String digits) {
		return digits + computeCheck(digits);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.modp.checkdigit.CheckDigit#verify(java.lang.String)
	 */
	public boolean verify(String digits) {
		try {
			int t = 10;
			for (int i = 0; i < digits.length() - 1; ++i) {
				t = (2 * f(t + digits.charAt(i) - '0')) % 11;
			}
			return (((t + getCheckDigit(digits)) % 10) == 1);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * private helper function to simplify computations.
	 * 
	 * @param x int
	 * @return int
	 */
	private int f(int x) {
		int val = x % 10;
		return (val == 0) ? 10 : val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.modp.checkdigit.CheckDigit#computeCheck(java.lang.String)
	 */
	public int computeCheck(String digits) {
		int t = 10;
		for (int i = 0; i < digits.length(); ++i) {
			int c = digits.charAt(i) - '0';
	     	if (c < 0 || c > 9) {
	     		throw new NumberFormatException("Bad digit: '" + digits.charAt(i) + "'");
	     	}
			t = (2 * f(t + c)) % 11;
		}
		return (11 - t) % 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.modp.checkdigit.CheckDigit#getCheckDigit(java.lang.String)
	 */
	public int getCheckDigit(String digits) {
		return digits.charAt(digits.length() - 1) - '0';
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.modp.checkdigit.CheckDigit#getData(java.lang.String)
	 */
	public String getData(String digits) {
		return digits.substring(0, digits.length() - 1);
	}
}
