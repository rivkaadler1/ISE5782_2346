package geometries;

import java.util.ArrayList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Class Triangle is the basic class representing a triangle of Euclidean geometry extends polygon
 * @author Rivki Adler and Sarit Silverstone
*/
public class Triangle extends Polygon 
{

    /**
     * triangle constructor
     * @param p1 vertex 1 of the triangle
     * @param p2 vertex 2 of the triangle
     * @param p3 vertex 3 of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3)
    {
        super(p1, p2, p3);
    }
    
	@Override
	public List<Point> findIntersections(Ray ray) 
	{
		List<Point> rayPoints = plane.findIntersections(ray);
		if (rayPoints == null)
			return null;

		//check if the point in out or on the triangle:
		Vector v1 = vertices.get(0).subtract(ray.getP0());
		Vector v2 = vertices.get(1).subtract(ray.getP0());
		Vector v3 = vertices.get(2).subtract(ray.getP0());
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		
		//The point is inside if all the calculations have the same sign (+/-)
		
		if (alignZero(n1.dotProduct(ray.getDir())) > 0 && alignZero(n2.dotProduct(ray.getDir())) > 0 && alignZero(n3.dotProduct(ray.getDir())) > 0)
		{
			return rayPoints;
		}
		else if (alignZero(n1.dotProduct(ray.getDir())) < 0 && alignZero(n2.dotProduct(ray.getDir())) < 0 && alignZero(n3.dotProduct(ray.getDir())) < 0)
		{
			return rayPoints;
		}
		if (isZero(n1.dotProduct(ray.getDir())) || isZero(n2.dotProduct(ray.getDir())) || isZero(n3.dotProduct(ray.getDir())))
			return null; //there is no instruction point
		return null;
	}
	
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)throws IllegalArgumentException  {
		return null;
	}
}