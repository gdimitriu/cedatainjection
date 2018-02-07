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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gabriel Dimitriu
 *
 */
public class DefaultFunctionProvider {
	
	/** this hold all mathematical method with one argument */
	private static Map<String, Method> functionsOneDoubleArgument = new HashMap<>();
	
	static {
		try {
			Class<?> math = Class.forName("java.lang.Math");
			Method[] methods = math.getDeclaredMethods();
			for (Method function : methods) {
				Class<?>[] types = function.getParameterTypes();
				if (types.length == 1 && types[0].isAssignableFrom(double.class)) {
					functionsOneDoubleArgument.put(function.getName(), function);
				}
			}
 		} catch (ClassNotFoundException e) {
			//this should never happen
		} 
	}
	/**
	 * 
	 */
	public DefaultFunctionProvider() {
		//nothing
	}

	/**
	 * Validate if the function is valable in java.lang.Math with one double argument.
	 * @param function to be check
	 * @return true if the function exist with one double argument.
	 */
	public static boolean isValableDoubleArguementFunction(final char[] function) {
		if (functionsOneDoubleArgument.containsKey(new String(function))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Evaluate a function with a double argument.
	 * @param function to be evaluated.
	 * @param argument of the function.
	 * @return result of evaluation as double 
	 */
	public static double evaluateDoubleArgumentFunction(final char[] function, final double argument) {
		Method func = functionsOneDoubleArgument.get(new String(function));
		try {
			return (double) func.invoke(null, argument);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return 0.0D;
		}
	}
	
	public static void main(String[] args) {
		DefaultFunctionProvider.isValableDoubleArguementFunction("sin".toCharArray());
		System.out.println(DefaultFunctionProvider.evaluateDoubleArgumentFunction("sin".toCharArray(), 0.1D));
	}
}
