/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 *   Unit tests for primitives.Point class
 *   @author Rivky Adler 325152346
 */
class PointTests
{
	Point p1 = new Point(1, 2, 3);
	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared()
	{
		Point point1=new Point(0,0,3);
		Point point2=new Point(0,4,0);
		assertEquals("Function distanceSquared doesnt work correct",25, point1.distanceSquared(point2), 0.0001);
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() 
	{
		Point point1=new Point(0,0,3);
		Point point2=new Point(0,4,0);
		assertEquals("Function distance doesnt work correct",5, point1.distance(point2), 0.0001);
	}

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd()
	{
		assertFalse(!(p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0)))
			,"ERROR: Point + Vector does not work correctly");		
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() 
	{
		assertFalse(!new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)),
			"ERROR: Point - Point does not work correctly");
	}
		

}
