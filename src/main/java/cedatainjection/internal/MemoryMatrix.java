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

import java.util.Vector;

import cedatainjection.interfaces.IMatrix;

/**
 * Memory matrix implementation.
 * @author Gabriel Dimitriu
 *
 */
public class MemoryMatrix implements IMatrix {

	private Vector<Vector<Double>> matrixInMemory;
	
	public MemoryMatrix(final int rows, final int columns) {
		matrixInMemory = new Vector<>(rows);
		for (int i = 0 ;i < rows; i++) {
			matrixInMemory.add(new Vector<>(columns));
		}
	}
	
	@Override
	public double get(int row, int column) {
		if (row >= matrixInMemory.get(row).size()) {
			return 0.0D;
		}
		if (column >= matrixInMemory.get(row).size()) {
			return 0.0D;
		}
		return matrixInMemory.get(row).get(column);
	}

	@Override
	public Vector<Double> getRow(int row) {
		if (row >= matrixInMemory.get(row).size()) {
			return new Vector<>();
		}
		return matrixInMemory.get(row);
	}

	@Override
	public void set(final double value, final int row, final int column) {
		Vector<Double> matrixColumn = null;
		if (row >= matrixInMemory.size()) {
			int currentSize = matrixInMemory.size();
			matrixInMemory.setSize(row +1);
			for (int i = currentSize; i < row + 1; i++) {
				matrixColumn =new Vector<Double>();
				matrixInMemory.set(i,  matrixColumn);
			}
		}
		matrixColumn = matrixInMemory.get(row);
		if (column >= matrixColumn.size()) {
			matrixColumn.setSize(column + 1);
		}
		matrixColumn.set(column, value);
		matrixInMemory.set(row, matrixColumn);
	}
	
}
