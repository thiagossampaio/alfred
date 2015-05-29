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
 * Implement the Luhn Formula Mod 10 check digit scheme
 * 
 * @author nickg
 * @version 1
 */
public class DigitoLuhnMod10 implements Digito {

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#encode(java.lang.String)
	 */
	public String encode(String digits) {
		return digits + computeCheck(digits);
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#verify(java.lang.String)
	 */
	public boolean verify(String digits) {
		try {
			return ((computeSum(digits) % 10) == 0);
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#computeCheck(java.lang.String)
	 */
	public int computeCheck(String digits) {
		int val = computeSum(digits);
		return (val == 0) ? 0 : (10 - val);
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#getCheckDigit(java.lang.String)
	 */
	public int getCheckDigit(String digits) {
		return digits.charAt(digits.length() - 1) - '0';
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#getData(java.lang.String)
	 */
	public String getData(String digits) {
		return digits.substring(0, digits.length() - 1);
	}

	// computes the special sum function
	private int computeSum(String numero) {
		
		if(numero == null || "".equals(numero)) 
			throw new NumberFormatException("O par�metro n�mero deve ser informado.");
		
		String totalStr = "";
		for (int i = 0; i < numero.length(); i++) {
			
			if(i % 2 == 0)
				totalStr += (Integer.valueOf(String.valueOf(numero.charAt(i))) * 2);
			else
				totalStr += Integer.valueOf(String.valueOf(numero.charAt(i)));
			
		}
		
		char[] x = totalStr.toCharArray();
		int total = 0;
		for (char c : x) {
			total += Integer.valueOf(String.valueOf(c));
		}
		
		total = total % 10;
		
		return total;
	}
	
}
