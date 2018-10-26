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
package cedatainjection.tests.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import cedatainjection.interfaces.IVariable;
import cedatainjection.internal.MemoryVariables;
import cedatainjection.internal.Parser;

/**
 * Functional Unitests for evaluations.
 * @author Gabriel Dimitriu
 *
 */
public class ParserTests {

	@Test
	public void basicScalarTests() {
		IVariable memory = new MemoryVariables();
		Parser parser = new Parser(memory);
		double rez = parser.evaluate("a=10");
		assertTrue(rez == 10.0D);
		assertTrue(memory.get("a") == 10.0D);
		rez = parser.evaluate("b=a+10");
		assertTrue(rez == 20.0D);
		assertTrue(memory.get("a") == 10.0D);
		assertTrue(memory.get("b") == 20.0D);
		rez = parser.evaluate("c=(a+b)*10");
		assertTrue(rez == 300.0D);
		assertTrue(memory.get("a") == 10.0D);
		assertTrue(memory.get("b") == 20.0D);
		assertTrue(memory.get("c") == 300.0D);
		rez = parser.evaluate("d=(a+1)*(a+10)");
		assertTrue(rez == 220.0D);
		assertTrue(memory.get("a") == 10.0D);
		assertTrue(memory.get("b") == 20.0D);
		assertTrue(memory.get("c") == 300.0D);
		assertTrue(memory.get("d") == 220.0D);
		rez = parser.evaluate("d/b");
		assertTrue(rez == 11.0D);
	}
	
	@Test
	public void basicVectorTests() {
		IVariable memory = new MemoryVariables();
		Parser parser = new Parser(memory);
		double rez = parser.evaluate("a[0]=10");
		assertTrue(rez == 10.0D);
		assertTrue(memory.get("a", 0) == 10.0D);
		rez = parser.evaluate("a[1]=a[0]+10");
		assertTrue(rez == 20.0D);
		assertTrue(memory.get("a", 0) == 10.0D);
		assertTrue(memory.get("a", 1) == 20.0D);
		rez = parser.evaluate("b[2]= a[1] + 1");
		assertTrue(rez == 21.0D);
		assertTrue(memory.get("a", 0) == 10.0D);
		assertTrue(memory.get("a", 1) == 20.0D);
		assertTrue(memory.get("b", 2) == 21.0D);
	}

	@Test
	public void basicMathTests() {
		IVariable memory = new MemoryVariables();
		Parser parser = new Parser(memory);
		double rez = parser.evaluate("sin(0.3)^2 + cos(0.3)^2");
		assertTrue("evaluate trigonometric identity", rez == 1.0D);
	}
	
	@Test
	public void basicMatrixTests() {
		IVariable memory = new MemoryVariables();
		Parser parser = new Parser(memory);
		double delta = 0.01D;
		double rez = parser.evaluate("a[1][1]=112.23");
		rez = memory.get("a", 1, 1);
		assertEquals(112.23D, rez, delta);
		rez = parser.evaluate("a[0][1]=a[1][1]+2");
		rez = memory.get("a", 0, 1);
		assertEquals(114.23D, rez, delta);
	}
}
