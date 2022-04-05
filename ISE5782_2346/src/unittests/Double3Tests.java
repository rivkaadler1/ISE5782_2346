/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Vector;

/**
 *   Unit tests for primitives.Double3 class
 *   @author Rivky Adler 325152346
 */
class Double3Tests {

	/**
	 * Test method for {@link primitives.Double3#add(primitives.Double3)}.
	 */
	@Test
	void testAdd()
	{
		try 
		{
			Double3 point1=new Double3(1,1,1);
			Double3 point2= new Double3(1,2,3);
			assertEquals("Function add doesnt work correct", new Double3(2,3,4), point1.add(point2));
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			fail("No need to throw exception");
		}
		
		try 
		{
			Double3 point=new Double3(1,1,1);
			assertEquals("Function add doesnt work correct", point, point.add(Double3.ZERO));
			fail("No need to throw exception");
		} 
		catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Double3#subtract(primitives.Double3)}.
	 */
	@Test
	void testSubtract()
	{
		try 
		{
			Double3 point1=new Double3(1,1,1);
			Double3 point2=new Double3(1,2,3);
			assertEquals("Function subtract doesnt work correct", new Double3(0,-1,-2), point1.subtract(point2));
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			fail("No need to throw exception");
		}
		
		try 
		{
			Double3 point=new Double3(1,1,1);
			assertEquals("Function subtract doesnt work correct", point, point.subtract(Double3.ZERO));
		} 
		catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Double3#scale(double)}.
	 */
	@Test
	void testScale() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Double3#reduce(double)}.
	 */
	@Test
	void testReduce() {
		fail("Not yet implement");
	}

	/**
	 * Test method for {@link primitives.Double3#product(primitives.Double3)}.
	 */
	@Test
	void testProduct() {
		fail("Not yet implemented");
	}

}
