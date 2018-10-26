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
package cedatainjection.tests.manual;

import cedatainjection.interfaces.IVariable;
import cedatainjection.internal.MemoryVariables;
import cedatainjection.internal.Parser;

/**
 * Functional Unitests for evaluations.
 * @author Gabriel Dimitriu
 *
 */
public class ParserTests {

	
	public static void main(String[] args) {
		IVariable memory = new MemoryVariables();
		Parser parser = new Parser(memory);
		double rez = parser.evaluate("b[1]=12");
		rez = memory.get("b", 1);
		System.out.println(rez);
		rez = parser.evaluate("b[1]=b[1]+1");
		rez = memory.get("b", 1);
		System.out.println(rez);
		rez = parser.evaluate("a[1][1]=112");
		rez = memory.get("a", 1, 1);
		System.out.println(rez);
	}
}
