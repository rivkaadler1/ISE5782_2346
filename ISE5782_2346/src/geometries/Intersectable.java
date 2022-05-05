package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;



/** 
 * interface Intersectable for ray intersections with geometries
 * @author sarit silverstone and Rivki Adler
 */
public interface Intersectable
{
	/**
	 * finds all the intersection points with geometry
	 * @param ray
	 * @return list of intersection points
	 */
	public List<Point> findIntersections(Ray ray);
}
