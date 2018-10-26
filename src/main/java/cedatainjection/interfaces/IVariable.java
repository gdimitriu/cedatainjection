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
package cedatainjection.interfaces;

/**
 * Interface for variables with memory state.
 * This could have default implementation with memory (volatile).
 * This could have persistence implementation.
 * @author Gabriel Dimitriu
 *
 */
public interface IVariable {
	
	/**
	 * Get the value of the scalar variable from the memory.
	 * @param variable the name of the variable.
	 * @return the scalar value of the variable.
	 */
	double get(final String variable);
	
	/**
	 * Set the value of the scalar variable into the memory.
	 * This will add a new value or override the value.
	 * @param variable the name of the variable.
	 * @param value the new value of the variable to be set.
	 */
	void set(final String variable, final double value);
	
	/**
	 * Get the value of the vector variable by position. 
	 * @param variable the name of variable.
	 * @param position in the vector.
	 * @return the scalar value of the variable.
	 */
	double get(final String variable, final int position);
	
	/**
	 * Set of value of the vector variable on the desired position.
	 * This will add a new value or override the value.
	 * @param variable the name of the variable.
	 * @param value the new value of the variable.
	 * @param position inside the vector.
	 */
	void set(final String variable, final double value, final int position);
	
	/**
	 * Insert of value of the vector variable on the desired position.
	 * @param variable the name of the variable.
	 * @param value the new value of the variable.
	 * @param position inside the vector.
	 */
	void insert(final String variable, final double value, final int position);
	
	/**
	 * Delete the entire variable.
	 * @param variable to be deleted.
	 */
	void delete(final String variable);
	
	/**
	 * Delete the position from the variable vector.
	 * @param variable the name of the variable.
	 * @param position inside the vector.
	 */
	void delete(final String variable, final int position);
	
	/**
	 * Clear the entire variable stack.
	 */
	void clear();
	
	/**
	 * Get the value of the matrix variable by position. 
	 * @param variable the name of variable.
	 * @param xPosition the x index in the matrix.
	 * @param yPosition the y index in the matrix.
	 * @return the scalar value of the variable.
	 */
	double get(final String variable, final int xPosition, final int yPosition);
	
	/**
	 * Set of value of the matrix variable on the desired position.
	 * This will add a new value or override the value.
	 * @param variable the name of the variable.
	 * @param value the new value of the variable.
	 * @param xPosition the x index in the  matrix.
	 * @param yPosition the y index in the matrix.
	 */
	void set(final String variable, final double value, final int xPosition, final int yPosition);
}
