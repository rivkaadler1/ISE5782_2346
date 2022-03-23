/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 *   Unit tests for geometries.Tube class
 *   @author Rivky Adler325152346
 */
class TubeTests 
{

	/**
	 * Test method for {@link geometries.Tube#getAxis()}.
	 */
	@Test
	void testGetAxis() {
		
	}

	/**
	 * Test method for {@link geometries.Tube#getRadius()}.
	 */
	@Test
	void testGetRadius()
	{
		
	}

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() 
	{
		try 
		{
			// ============ Equivalence Partitions Tests ==============
			Ray myRay=new Ray(new Point(1, 0, 0), new Vector(0, 1, 0));
			Tube myTube=new Tube(myRay, 5);
			Vector normal = new Vector(1,0,0);
			assertEquals("Bad normal to tube", normal , myTube.getNormal(new Point(3,1,0)));
			
			// =============== Boundary Values Tests ==================
			// Check if the point is on the circumference of the circle, 
			//check if the distance between the point and the center point is the same as the radius. 
			//If so the point must be on the circumference of the circle.
			double lengthNormal=Math.sqrt(21);
			normal=new Vector(4d/lengthNormal, 0, Math.sqrt(5)/lengthNormal);
			assertEquals("Bad normal to tube", normal , myTube.getNormal(new Point(5,4,Math.sqrt(5))));
		} 
		
		catch (Exception e) 
		{		
			fail("for vectors that not zero vector does not need throw an exception");
		}
	}

}
