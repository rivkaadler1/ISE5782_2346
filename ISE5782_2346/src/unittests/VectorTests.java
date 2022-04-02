/**
 * 
 */
package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Vector;

/**
 *   Unit tests for primitives.Vector class
 *   @author Rivky Adler 325152346
 */
class VectorTests
{
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    
	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAdd()
	{
		Vector v1;
		Vector v2;
		Vector vTry;
		
		// ============ Equivalence Partitions Tests ==============
		try 
		{
			v1= new Vector(2,4,6);
			v2= new Vector(7,8,9);	
			vTry= new Vector(9,12,15);
			assertTrue("testAdd() Did not add the vector correct", vTry.equals(v1.add(v2)));
			
			v1= new Vector(-1,-6,-4.2);
			v2= new Vector(-2,-5.1,-9);
			vTry=new Vector(-3,-11.1,-13.2);
			assertTrue("testAdd() Did not add the vector correct", vTry.equals(v1.add(v2)));
			

			v1= new Vector(-1,8,23);
			v2= new Vector(6,-5.1,3);
			vTry=new Vector(5,2.9,26);
			assertTrue("testAdd() Did not add the vector correct", vTry.equals(v1.add(v2)));
			
		} 
		catch (Exception e) 
		{
			fail("testAdd() for vectors that not zero vector does not need throw an exception");
		}		
	}
	
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() 
	{
		// ============ Equivalence Partitions Tests ==============
		Vector v1;
		Vector v2;
		Vector vTry;
		try 
		{
			v1 = new Vector(2,4,6);
			v2=new Vector(7,8,9);		
			vTry=new Vector(-5,-4,-3);
			assertTrue("Subtract() Did not sub the vector correct", vTry.equals(v1.subtract(v2)));
		} 
		catch (Exception e) 
		{
			fail("Subtract() for vectors that not zero vector does not need throw an exception");
		}
		
		try 
		{
			v1 = new Vector(9,5,4);
			v2=new Vector(5,2,3);		
			vTry=new Vector(4,3,1);
			assertTrue("Subtract() Did not sub the vector correct", vTry.equals(v1.subtract(v2)));
		} 
		catch (Exception e) 
		{
			fail("Subtract() for vectors that not zero vector does not need throw an exception");
		}
		
		try 
		{
			v1 = new Vector(-5,-5,-4);
			v2=new Vector(-9,-1,-12);		
			vTry=new Vector(4,-4,8);
			assertTrue("Subtract() Did not sub the vector correct", vTry.equals(v1.subtract(v2)));
		} 
		catch (Exception e) 
		{
			fail("Subtract() for vectors that not zero vector does not need throw an exception");
		}
		
		try 
		{
			v1 = new Vector(-5,-5,-4);
			v2=new Vector(3,9,4);		
			vTry=new Vector(-8,-14,-8);
			assertTrue("Subtract() Did not sub the vector correct", vTry.equals(v1.subtract(v2)));
		} 
		catch (Exception e) 
		{
			fail("Subtract() for vectors that not zero vector does not need throw an exception");
		}

		
		// =============== Boundary Values Tests ==================
		
		try 
		{
			v1 = new Vector(-5,-5,-4);
			assertTrue("Subtract() Did not sub the vector correct when the other vector is the zero vector", v1.equals(v1.getXyz().subtract(Double3.ZERO)));
			
			@SuppressWarnings("unused")
			Vector vZero = new Vector(Double3.ZERO);
			//if we don't get an exception it is didn't work correct
			fail("can not create a new vector that his head equals to zero vector");

		} 
		catch (IllegalArgumentException e) {}
		catch (Exception e) {}
	}

    /**
     * testVectorZero {@link Vector#Vector(double,double,double)}
     */
    @Test
    void testVectorZero() 
    {
        assertThrows(
        		IllegalArgumentException.class,
        		() -> {new Vector(0, 0, 0.0000000000000000034);},

                "Vector(0,0,0) should have thrown Exception");
    }
	

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared()
	{
		Vector v1;
		try 
		{
			// ============ Equivalence Partitions Tests ==============
			v1 = new Vector(1, 2, 3);
			assertTrue("ERROR: lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
		} 
		catch (Exception e) 
		{
			fail("LengthSquared() for vectors that not zero vector does not need throw an exception");
		}

	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength()
	{
		try
		{
			// ============ Equivalence Partitions Tests ==============
			assertTrue("ERROR: length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
		}
		catch (Exception e) 
		{
			fail("Length() for vectors that not zero vector does not need throw an exception");
		}
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct()
	{
		try 
		{
			// ============ Equivalence Partitions Tests ==============

		    assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
		    // =============== Boundary Values Tests ==================
		    assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
		} 
		catch (Exception e) 
		{
			fail("DotProduct() for vectors that not zero vector does not need throw an exception");
		}

	}


    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                     "crossProduct() for parallel vectors does not throw an exception");
        
        
        
		/* test Cross-Product   //the test from the main above there are the tests that were given as example
		try { // test zero vector
			v1.crossProduct(v2);
			out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
		} catch (Exception e) {
		}
		Vector vr = v1.crossProduct(v3);
		if (!isZero(vr.length() - v1.length() * v3.length()))
			out.println("ERROR: crossProduct() wrong result length");
		if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
			out.println("ERROR: crossProduct() result is not orthogonal to its operands");*/
    }



	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() 
	{
		// test vector normalization vs vector length and cross-product
		Vector v = new Vector(1, 2, 3);
		Vector u = v.normalize();
		// ============ Equivalence Partitions Tests ==============
		try 
		{
			assertTrue("ERROR: the normalized vector is not a unit vector", isZero(u.length() - 1));    
		} 
		catch (Exception e) 
		{
			fail("Normalize() for vectors that not zero vector does not need throw an exception");
		}

		try 
		{
			v = new Vector(3.5,-5,10);
			v.normalize();
			assertEquals("ERROR: normalize() result is not a unit vector", 1, v.length(),1e-10);
		}
		catch (Exception e) {}
		 // =============== Boundary Values Tests ==================
		try
		{
			v = new Vector(0,0,0);
			v.normalize();
			fail("Didn't throw divide by zero exception!");
		} 
		catch (ArithmeticException e) 
		{
			assertTrue(true);
		} 
		catch (Exception e) {}
		
		// test that the vectors are co-lined
		try 
		{ 
			v.crossProduct(u);
			fail("ERROR: the normalized vector is not parallel to the original one");
		} catch (Exception e)
		{}
		assertTrue (!(v1.dotProduct(u) < 0),"ERROR: the normalized vector is opposite to the original one" );
		

	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() //what is the difference between this and testnormalize?
	{
		Vector v;
		try
		{
			// ============ Equivalence Partitions Tests ==============
			v = new Vector(1, 2, 3);
			Vector u = v.normalize();//it was written v.normalized maybe i dont have this function
			assertTrue("ERROR: normalizated() result is not a unit vector", isZero(u.length() - 1));    
			assertFalse("ERROR: normalizated() function does not create a new vector", u == v );//?
		} 
		catch (Exception e) 
		{
			fail("Normalized() for vectors that not zero vector does not need throw an exception");
		}    
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScaling()
	{
		// ============ Equivalence Partitions Tests ==============
		Vector v1;
		Vector vTry;
		
		try 
		{
			v1 = new Vector(2,4,6);
			//scalar: 3.5
			vTry=new Vector(7,14,21);
			assertTrue("testScale() Did not double the vector correct", vTry.equals(v1.scale(3.5)));
		} 
		catch (Exception e) 
		{
			fail("testScale() for vectors that not zero vector does not need throw an exception");
		}
		
		try 
		{
			v1 = new Vector(2,4,6);
			//scalar: -5
			vTry=new Vector(-10,-20,-30);
			assertTrue("testScale() Did not double the vector correct", vTry.equals(v1.scale(-5)));	
		} 
		catch (Exception e) 
		{
			fail("testScale() for vectors that not zero vector does not need throw an exception");
		}


		
		// =============== Boundary Values Tests ==================
		//scalar: 0
		try 
		{
			v1 = new Vector(-5,-5,-4);
			assertTrue("testScale() Did not double the vector correct when the scalar is 0", v1.equals(v1.scale(0)));			

		} 
		catch (IllegalArgumentException e) {}
		catch (Exception e) {}
	}

}
