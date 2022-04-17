/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import primitives.Vector;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Point;

/**
 *   Unit tests for geometries.Sphere class
 *   @author Rivky Adler325152346
 */
class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() 
	{
		try
		{
			// ============ Equivalence Partitions Tests ==============
			double radius=1.0;
			Point center=new Point(0,0,1);		
			Sphere mySphere=new Sphere(center, radius);
			Vector normal=new Vector(0,0,1);
			assertEquals("bad normal to sphere", normal, mySphere.getNormal(new Point(0,0,2)));

		}
		catch(Exception ex)
		{
			fail("for vectors that not zero vector does not need throw an exception");
		}
	}

}
