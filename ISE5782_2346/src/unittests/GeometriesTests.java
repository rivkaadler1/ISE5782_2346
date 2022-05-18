package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

class GeometriesTests {


	@Test
	void testFindIntsersections() {
		try 
		{
			//=====Empty body collection (BVA)=====//
			Geometries collection= new Geometries();
			assertEquals("An empty body collection must return null",  new Geometries().findIntersections(new Ray(new Point(0, -8, 0), new Vector(-10,-1,0))), collection.findIntersections(new Ray(new Point(0, -8, 0), new Vector(-10,-1,0))));
			
			//=====No cut shape (BVA)=====//
			Sphere sphere = new Sphere(1,new Point(1, 0, 0)); 
			Triangle triangle = new Triangle(new Point(-4,0,0), new Point(0, 0, 5), new Point(0, -5, 0));
			Plane plane = new Plane (new Point(0, 0, 1), new Point(1, 0, 0), new Point(4, 0, 2));
			
			collection.add(sphere, triangle, plane/*, tube ,cylinder*/);
		
			assertNull("No cut shape must return 0",collection.findIntersections(new Ray(new Point(0, -8, 0), new Vector(-10,-1,0))));
			
			
			//=====Some (but not all) shapes are cut (EP)=====//
			//triangle and plan cut
			assertEquals("wrong number of intersactions", 2, collection.findIntersections(new Ray(new Point(-4, -3, 2), new Vector(9,5,-1))).size());
			
			//=====Only one shape is cut (BVA)=====//
			//the plane cut
			assertEquals("wrong number of intersactions", 1, collection.findIntersections(new Ray(new Point(-0.8, -3, 1), new Vector(3.4,3,1.57))).size());

			
			//=====All shapes are cut (BVA)=====//
			assertEquals("wrong number of intersactions", 4, collection.findIntersections(new Ray(new Point(-4, -3, 0), new Vector(6,3,0.5))).size());			

		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("dont need throws exceptions!!!");
		}
	}

}
