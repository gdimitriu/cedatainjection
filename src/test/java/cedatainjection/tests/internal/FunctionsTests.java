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

import cedatainjection.internal.DefaultFunctionProvider;

/**
 * @author Gabriel Dimitriu
 *
 */
public class FunctionsTests {

	@Test
	public void basicMathTests() {
		assertTrue(DefaultFunctionProvider.isValableDoubleArguementFunction("sin".toCharArray()));
		assertTrue(DefaultFunctionProvider.isValableDoubleArguementFunction("cos".toCharArray()));
		assertEquals("evaluate sin failed",Double.valueOf(DefaultFunctionProvider.evaluateDoubleArgumentFunction("sin".toCharArray(),0.3D)),
				Double.valueOf(Math.sin(0.3D)));
		assertEquals("evaluate cos failed",Double.valueOf(DefaultFunctionProvider.evaluateDoubleArgumentFunction("cos".toCharArray(),0.3D)),
				Double.valueOf(Math.cos(0.3D)));
	}

}
