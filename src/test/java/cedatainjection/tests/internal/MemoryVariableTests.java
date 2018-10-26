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
package cedatainjection.tests.internal;

import static org.junit.Assert.*;

import org.junit.Test;

import cedatainjection.interfaces.IVariable;
import cedatainjection.internal.MemoryVariables;

/**
 * Unitests for Variable in memory.
 * @author Gabriel Dimitriu
 *
 */
public class MemoryVariableTests {

	@Test
	public void scalarVariableTests() {
		IVariable memory = new MemoryVariables();
		memory.set("a", 1);
		assertTrue(memory.get("a") == 1.0D);
		memory.set("b", 2);
		assertTrue(memory.get("a") == 1.0D);
		assertTrue(memory.get("b") == 2.0D);
		memory.clear();
		assertTrue(memory.get("a") == 0.0D);
		assertTrue(memory.get("b") == 0.0D);
	}
	
	@Test
	public void vectorVariableTests() {
		IVariable memory = new MemoryVariables();
		memory.set("a", 1, 0);
		assertTrue(memory.get("a", 0) == 1.0D);
		memory.set("a", 2, 1);
		assertTrue(memory.get("a", 0) == 1.0D);
		assertTrue(memory.get("a", 1) == 2.0D);
		memory.set("b", 1, 2);
		assertTrue(memory.get("a", 0) == 1.0D);
		assertTrue(memory.get("a", 1) == 2.0D);
		assertTrue(memory.get("b", 0) == 0.0D);
		assertTrue(memory.get("b", 1) == 0.0D);
		assertTrue(memory.get("b", 2) == 1.0D);
		memory.delete("b", 2);
		assertTrue(memory.get("a", 0) == 1.0D);
		assertTrue(memory.get("a", 1) == 2.0D);
		assertTrue(memory.get("b", 0) == 0.0D);
		assertTrue(memory.get("b", 1) == 0.0D);
		assertTrue(memory.get("b", 2) == 0.0D);
		memory.delete("a");
		assertTrue(memory.get("a", 0) == 0.0D);
		assertTrue(memory.get("a", 1) == 0.0D);
		memory.clear();
		assertTrue(memory.get("a") == 0.0D);
		assertTrue(memory.get("b") == 0.0D);
	}

	@Test
	public void matrixVariableTest() {
		IVariable memory = new MemoryVariables();
		double expectedValue = 3.9D;
		double expectedSecondValue = 10.9D;
		double value;
		double delta = 0.01D;
		memory.set("a", expectedValue, 2, 3);
		value = memory.get("a", 2, 3);
		assertEquals(expectedValue, value, delta);
		memory.set("b", expectedSecondValue, 2, 3);
		value = memory.get("b", 2, 3);
		assertEquals(expectedSecondValue, value, delta);
		
		memory.delete("a");
		value = memory.get("a", 2, 3);
		assertEquals(0.0D, value, delta);
		value = memory.get("b", 2, 3);
		assertEquals(expectedSecondValue, value, delta);
		memory.delete("b");
		value = memory.get("b", 2, 3);
		assertEquals(0.0D, value, delta);
	}
}
