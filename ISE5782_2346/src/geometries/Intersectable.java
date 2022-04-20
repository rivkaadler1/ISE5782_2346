package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * @author sarit silverstone and Rivki Adler
 * interface for ray intersections
 * 
 **/

/** 
 * @author sarit silverstone and Rivki Adler
 *A function that return all the intersection points with geometry
 */
public interface Intersectable
{
	public List<Point> findIntersections(Ray ray);
}
