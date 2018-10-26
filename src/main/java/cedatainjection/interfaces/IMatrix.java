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

import java.util.Vector;

/**
 * Interface for Matrix operations and matrix storage.
 * @author Gabriel Dimitriu
 *
 */
public interface IMatrix {
	
	/**
	 * Set a scalar value to the corresponding row and column.
	 * @param value to be set
	 * @param row the row
	 * @param column the column.
	 */
	void set(final double value, final int row, final int column);
	
	/**
	 * Get the scalar value from matrix.
	 * @param row the row number
	 * @param column the column number
	 * @return scalar value
	 */
	double get(final int row, final int column);
	
	
	/**
	 * Get a row
	 * @param row to be  retrieved
	 * @return Vector<Double> represent the row
	 */
	Vector<Double> getRow(final int row);
}
