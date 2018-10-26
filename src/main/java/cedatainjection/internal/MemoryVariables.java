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

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import cedatainjection.interfaces.IMatrix;
import cedatainjection.interfaces.IVariable;

/**
 * This will hold the memory variable operations.
 * @author Gabriel Dimitriu
 *
 */
public class MemoryVariables implements IVariable{
	
	/** memory for scalar variable */
	private Map<String, Double> scalarVariable = new HashMap<>();
	
	/** memory for vector variable */
	private Map<String, Vector<Double>> vectorVariable = new HashMap<>();
	
	/** memory for matrix variable */
	private Map<String, IMatrix> matrixVariable = new HashMap<>();

	/**
	 * 
	 */
	public MemoryVariables() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double get(final String variable) {
		if (scalarVariable.containsKey(variable)) {
			return scalarVariable.get(variable);
		}
		return 0.0D;
	}
	
	@Override
	public void set(final String variable, final double value) {
		scalarVariable.put(variable, value);
	}

	@Override
	public double get(String variable, int position) {
		if (vectorVariable.containsKey(variable)) {
			return vectorVariable.get(variable).get(position);
		}
		return 0.0D;
	}

	@Override
	public void set(String variable, double value, int position) {
		if (vectorVariable.containsKey(variable)) {
			Vector<Double> array = vectorVariable.get(variable); 
			if (array.size() > position) {
				array.set(position, value);
			} else {
				array.add(position, value);
			}
		} else {
			Vector<Double> array = new Vector<>();
			for (int i = 0 ; i < position; i++) {
				array.add(0.0D);
			}
			array.add(position, value);
			vectorVariable.put(variable, array);
		}
	}

	@Override
	public void insert(String variable, double value, int position) {
		if (vectorVariable.containsKey(variable)) {
			vectorVariable.get(variable).add(position, value);
		} else {
			Vector<Double> array = new Vector<>();
			array.add(position, value);
			vectorVariable.put(variable, array);
		}
	}

	@Override
	public void delete(final String variable) {
		if (scalarVariable.containsKey(variable)) {
			scalarVariable.remove(variable);
			return;
		}
		if (vectorVariable.containsKey(variable)) {
			vectorVariable.remove(variable);
			return;
		}
		if (matrixVariable.containsKey(variable)) {
			matrixVariable.remove(variable);
		}
	}

	@Override
	public void delete(String variable, int position) {
		if (vectorVariable.containsKey(variable)) {
			Vector<Double> array = vectorVariable.get(variable); 
			if(array.size() > position) {
				array.set(position, 0.0D);
			} else if (array.size() - 1  == position) {
				array.remove(position);
			}
		}
	}

	@Override
	public void clear() {
		scalarVariable.clear();
		vectorVariable.clear();
		matrixVariable.clear();
	}

	@Override
	public double get(final String variable, final int xPosition, final int yPosition) {
		if (matrixVariable.containsKey(variable)) {
			return matrixVariable.get(variable).get(xPosition, yPosition);
		}
		return 0.0D;
	}

	@Override
	public void set(final String variable, final double value, final int xPosition, final int yPosition) {
		if (matrixVariable.containsKey(variable)) {
			matrixVariable.get(variable).set(value, xPosition, yPosition);
			return;
		}
		IMatrix newMatrix = new MemoryMatrix(xPosition, yPosition);
		newMatrix.set(value, xPosition, yPosition);
		matrixVariable.put(variable, newMatrix);
	}
}
