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

import cedatainjection.interfaces.IVariable;

/**
 * @author Gabriel Dimitriu
 *
 */
public class Parser {

	/** internal variables */
	private IVariable variables = null;
	
	/** working character buffer */
	private char[] workingBuffer = null;
	
	/**
	 * 
	 */
	public Parser() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor with desired variable implementation.
	 * @param variableImpl the implementation of the variable;
	 */
	public Parser(final IVariable variableImpl) {
		this.variables = variableImpl;
	}

	/**
	 * Evaluate the expression.
	 * @param expression
	 */
	public void evaluate(final String expression) {
		
	}
	
	/**
	 * Get the argument of expression.
	 * @param expression to be evaluated
	 * @return the argument of the expression.
	 */
	private char[] getArgument(final char[] expression) {
		return expression;
	}
}
