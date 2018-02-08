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

import cedatainjection.interfaces.IVariable;

/**
 * This will hold the memory variable operations.
 * @author Gabriel Dimitriu
 *
 */
public class MemoryVariables implements IVariable{
	
	private Map<String, Double> scalarVariable = new HashMap<>();

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set(String variable, double value, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(String variable, double value, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(final String variable) {
		if (scalarVariable.containsKey(variable)) {
			scalarVariable.remove(variable);
		}
	}

	@Override
	public void delete(String variable, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		scalarVariable.clear();
	}

}
