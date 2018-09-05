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
 * Interface for encoding and verifing check digits
 * 
 * @author nickg
 * @version 1
 */
public interface Digito {

	/**
	 * Add check digits to a string containing digits
	 * @param digits  the input data string containing only digits '0'-'9'
	 * @return a new string containing data and check digits
	 */
	public abstract String encode(String digits);
	
	/**
	 * Verify a string that has been encoded with a check digit
	 * 
	 * @param digits input digits
	 * @return true if valid, false otherwise
	 */
	public abstract boolean verify(String digits);
	
	/**
	 * Computes the check digit value
	 * 
	 * @param digits - a string containing data
	 * @return an integer representing the check digit
	 */
	public abstract int computeCheck(String digits);

	/**
	 * Extract just the check digits from an encoded string
	 * 
	 * @param digits input data containing check and data digits
	 * @return the check digit, as an int.
	 */
	public abstract int getCheckDigit(String digits);
	
	/**
	 * Extracts the raw data without the check digits
	 * 
	 * @param digits -- A string containing only digits 0-9
	 * @return a string without check digits
	 */
	public abstract String getData(String digits);
}
