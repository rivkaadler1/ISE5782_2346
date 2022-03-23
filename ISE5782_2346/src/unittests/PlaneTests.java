/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;
import primitives.Vector;

/**
 *   Unit tests for geometries.Plane class
 *   @author Rivky Adler325152346
 */
class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getP0()}.
	 */
	@Test
	public void testPlaneConstructor()
	{
		try
		{
			Point p1=new Point(1,2,3);
			Point p2=new Point(1,2,3);
			Point p3=new Point(1,7,5);
			Plane myPlane =new Plane(p1, p2 , p3);
			fail ("The points coalesce and the constructor should send an exception");
		}
		catch(Exception ex) {}
		
		try
		{
			Point p1=new Point(1,2,3);
			Point p2=new Point(2,4,6);
			Point p3=new Point(1,2,3);
			Plane myPlane =new Plane(p1, p2 , p3);
			fail ("The dots are on the same line");
		}
		catch(Exception ex) {}
		
	}
	@Test
	void testGetP0() 
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint()
	{
		try
		{
			// ============ Equivalence Partitions Tests ==============
			Point p1=new Point(1,2,3);
			Point p2=new Point(4,5,6);
			Point p3=new Point(1,7,5);
			Plane myPlane =new Plane(p1, p2 , p3);
			double normalLength=Math.sqrt(38)*3;
			Vector normal=new Vector(-9/normalLength, -6/normalLength, 15/normalLength);
			assertEquals("Bad normal to plane", normal, myPlane.getNormal());
			// =============== Boundary Values Tests ==================
		
		}
		catch(Exception ex)
		{
			fail("for vectors that not zero vector does not need throw an exception");
		}
	}

}
