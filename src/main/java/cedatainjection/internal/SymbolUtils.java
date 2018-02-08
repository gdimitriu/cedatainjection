/**
    Copyright (c) 2018 Gabriel Dimitriu All rights reserved.
	DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.

    This file is part of cedatainjection project.

    cedatainjection is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    cedatainjection is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with cedatainjection.  If not, see <http://www.gnu.org/licenses/>.
 */
package cedatainjection.internal;

/**
 * Static utils for symbols.
 * @author Gabriel Dimitriu
 *
 */
public final class SymbolUtils {

	/**
	 * 
	 */
	public SymbolUtils() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * find if the character is an expression delimitator.
	 * @param character to be checked.
	 * @return true if is delimitator.
	 */
	static boolean isExpressionDelimitator(final char character) {
		if (isDelimitator(character) || Character.isSpaceChar(character) 
				||character == 0 || character == 9 || character == '\r') {
			return true;
		}
		return false;
	}
	
	/**
	 * find if the character is an delimitator.
	 * @param character to be checked.
	 * @return true if is delimitator.
	 */
	static boolean isDelimitator(final char character) {
		int index = "+-/*%^=()[]".indexOf(character);
		if (index >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Shrink the buffer.
	 * @param source the buffer source
	 * @param length of the data
	 * @return buffer of shrinking data
	 */
	static char[] shrinkBuffer(final char[] source, final int length) {
		char[] temp = new char[length];
		for(int i = 0; i < length; i++) {
			temp[i]=source[i];
		}
		return temp;
	}
}
