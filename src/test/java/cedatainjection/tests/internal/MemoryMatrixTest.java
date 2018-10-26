/**
 * 
 */
package cedatainjection.tests.internal;

import static org.junit.Assert.*;

import org.junit.Test;

import cedatainjection.internal.MemoryMatrix;

/**
 * @author gdimitriu
 *
 */
public class MemoryMatrixTest {

	@Test
	public void testMatrixElements() {
		int row = 3;
		int column = 4;
		MemoryMatrix matrix = new MemoryMatrix(row, column);
		double value;
		double expectedValue = 2.0D;		
		double delta =0.01D;
		matrix.set(expectedValue, 2,3);
		value= matrix.get(2, 3);
		assertEquals(expectedValue, value, delta);
		matrix.set(expectedValue, 3, 4);
		value= matrix.get(3, 4);
		assertEquals(expectedValue, value, delta);
		matrix.set(expectedValue, 5, 6);
		value= matrix.get(5, 6);
		assertEquals(expectedValue, value, delta);
	}

}
