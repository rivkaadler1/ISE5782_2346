/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import primitives.Vector;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Ray;
/**
 *   Unit tests for primitives.Ray class
 *   @author Rivky Adler 325152346
 */
class RayTests 
{

	/**
	 * Test method for {@link primitives.Ray#getP0()}.
	 */
	@Test
	void testGetP0() 
	{
		try 
		{
			Ray ray = new Ray(new Point(0,0,1), new Vector(1,0,0));
			assertEquals("The function getPoint dont work correct", new Point(2, 0, 1), ray.getPoint(2));
			
			assertEquals("The function getPoint dont work correct", new Point(0, 0, 1), ray.getPoint(0));

		}
		catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Ray#getDir()}.
	 */
	@Test
	void testGetDir() 
	{
		
	}

}
