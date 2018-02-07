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
	
	private final int SYMBOL_LENGTH = 80;
	
	/** working character buffer */
	private char[] workingBuffer = null;
	
	/** working character position in buffer */
	private int workingPosition = 0;
	
	/** expression buffer */
	private char[] expressionBuffer = null;
	
	/** expression possition */
	private int expressionPossition = 0;
	
	/** the symbolValue */
	private char[] symbolValue = new char[SYMBOL_LENGTH];
	
	/** type of symbolValue */
	private SymbolType symbolType = SymbolType.NONE;
	
	/** length of the symbol */
	private int symbolLength = 0;
	
	/** the expression to be evaluated */
	private String expression = null;
	
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
	 * Set the expression to be evaluated to the parser.
	 * @param expression to be evaluated.
	 */
	public void setExpression(final String expression) {
		this.expression = expression;
		expressionBuffer = expression.toCharArray();
		expressionPossition = 0;
		workingBuffer = expression.toCharArray();
		workingPosition  = 0;
		symbolLength = 0;
		symbolType = SymbolType.NONE;
	}
	
	/**
	 * Get the expression which is set to the parser.
	 * @return expression.
	 */
	public String getExpression() {
		return expression;
	}
	
	/**
	 * Reset the parser to the set expression.
	 */
	public void resetParserExpression() {
		expressionBuffer = expression.toCharArray();
		expressionPossition = 0;
		workingBuffer = expression.toCharArray();
		workingPosition  = 0;
		symbolLength = 0;
		symbolType = SymbolType.NONE;
	}
	
	/**
	 * Evaluate the expression.
	 * @param expression
	 */
	public void evaluate(final String expression) {
		this.expression = expression;
		expressionBuffer = expression.toCharArray();
		expressionPossition = 0;
		workingBuffer = expression.toCharArray();
		workingPosition  = 0;
		symbolLength = 0;
		symbolType = SymbolType.NONE;
	}
	
	/**
	 * Get the argument of expression.
	 * @return position into the expression bufffer. 
	 */
	private int getArgument() {
		char[] work = new char[workingBuffer.length];
		workingPosition++;
		int i =0;
		int parantheses = 1;
		while (parantheses != 0) {
			if(workingBuffer[workingPosition] == '(') {
				parantheses++;
			}
			if (workingBuffer[workingPosition] == ')') {
				parantheses--;
			}
			if(parantheses == 0) {
				break;
			}
			work[i]=workingBuffer[workingPosition];
			i++;
			workingPosition++;
		}
		work[i]='\0';
		workingPosition++;
		work = SymbolUtils.shrinkBuffer(work, i);
		expressionPossition = workingPosition;
		workingBuffer = work;
		return expressionPossition;
	}
	
	/**
	 * Advance to the next symbolValue (token).
	 * It will update the symbolValue type, symbolValue value and workingPosition.
	 */
	private void advanceSymbol() {
		this.symbolType = SymbolType.NONE;
		this.symbolValue = new char[SYMBOL_LENGTH];
		//rest the symbolValue
		this.symbolValue[0] = '\0';
		symbolLength = 0;
		//end of expression
		if (workingPosition >= workingBuffer.length) {
			return;
		}
		//rid of the spaces
		while(Character.isSpaceChar(workingBuffer[workingPosition])) {
			++workingPosition;
		}
		if (SymbolUtils.isDelimitator(workingBuffer[workingPosition])) {
			symbolType = SymbolType.DELIMITATOR;
			//copy data
			symbolValue[symbolLength] = workingBuffer[workingPosition];
			workingPosition++;
			symbolLength++;
		} else if (Character.isLetter(workingBuffer[workingPosition]) && workingBuffer[workingPosition +1 ] == '[') {
			while(!SymbolUtils.isDelimitator(workingBuffer[workingPosition])) {
				//copy data
				symbolValue[symbolLength] = workingBuffer[workingPosition];
				workingPosition++;
				symbolLength++;
				workingPosition++;
			}			
			symbolType = SymbolType.VARIABLE_MD; 
		} else if(Character.isLetter(workingBuffer[workingPosition]) && Character.isLetter(workingBuffer[workingPosition + 1])) {
			while(!SymbolUtils.isDelimitator(workingBuffer[workingPosition])) {
				//copy data
				symbolValue[symbolLength] = workingBuffer[workingPosition];
				workingPosition++;
				symbolLength++;
				workingPosition++;
			}
			if (workingBuffer[workingPosition] == '(') {
				symbolType = SymbolType.FUNCTION;
			} else if(workingBuffer[workingPosition] == '[') {
				symbolType = SymbolType.VARIABLE_MD;
			} else {
				symbolType = SymbolType.VARIABLE;
			}
		} else if(Character.isDigit(workingBuffer[workingPosition])) {
			while(!SymbolUtils.isDelimitator(workingBuffer[workingPosition])) {
				//copy data
				symbolValue[symbolLength] = workingBuffer[workingPosition];
				workingPosition++;
				symbolLength++;
				workingPosition++;
			}
			symbolType = SymbolType.NUMBER;
		}
		symbolValue[symbolLength] = '\0';
	}
	
	/**
	 * Put back the symbol.
	 * This will reset only the counters.
	 */
	private void putBack() {
		workingPosition -= symbolLength;
		symbolLength = 0;
	}
	
	/**
	 * Evaluate the build-in function.
	 * @param function the function
	 * @return value of the computation or 0.0 if the function doesn't exist
	 */
	private double evalMathFunction(final char[] function) {
		boolean valable = DefaultFunctionProvider.isValableDoubleArguementFunction(function);		
		int possition = getArgument();
		double rez = 0.0D;
		//eval the function
		rez = evaluateVariable(rez);
		workingBuffer = expressionBuffer;
		workingPosition = possition;
		if (!valable) {
			return 0.0D;
		}
		return DefaultFunctionProvider.evaluateDoubleArgumentFunction(function, rez);		
	}
	
	/**
	 * Process the attribution of a variable.
	 * @param argument the input value.
	 * @return the result.
	 */
	private double evaluateVariable(final double argument) {
		//get the next symbol
		advanceSymbol();
		char[] oldSymbol = null;
		double rez = 0.0D;
		SymbolType oldSymbolType = null;
		if (symbolType == SymbolType.VARIABLE) {
			//save the old symbol
			oldSymbol = SymbolUtils.shrinkBuffer(symbolValue, symbolLength);
			oldSymbolType = symbolType;
			//advance
			advanceSymbol();
			if(symbolValue.length > 0 && symbolValue[0] != '=') {
				//restore the symbol
				putBack();
				symbolValue = oldSymbol;
				symbolLength = oldSymbol.length;
				symbolType = oldSymbolType;
			} else {
				//get the next symbol
				advanceSymbol();
				rez = evaluateAddMathExpression(rez);
				variables.set(new String(oldSymbol), rez, 0);
				return rez;
			}
		}
		if (symbolType == SymbolType.VARIABLE_MD) {
			//save the old symbol
			oldSymbol = SymbolUtils.shrinkBuffer(symbolValue, symbolLength);
			oldSymbolType = symbolType;
			//TODO:
		}
		return evaluateAddMathExpression(rez);
	}

	/**
	 * Process the add or difference of two elements.
	 * @param argument the input value
	 * @return result of operation
	 */
	private double evaluateAddMathExpression(final double argument) {
		// TODO Auto-generated method stub
		return 0;
	}
}
