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
		List<GeoPoint> rayPoints = plane.findGeoIntersections(ray);
		if (rayPoints == null)
			return null;
		for (GeoPoint geoPoint : rayPoints) 
		{
			geoPoint.geometry = this;
		}
		//check if the point in out or on the triangle:
		List<Vector> normalsList = new ArrayList<Vector>();
		Vector vI;
		Vector vIplus1; 
		for (int i = 0; i<= vertices.size()-1; i++)
		{
			vI = vertices.get(i).subtract(ray.getP0());
			vIplus1 = vertices.get(i+1).subtract(ray.getP0());
			normalsList.add((vI.crossProduct(vIplus1).normalize()));
		}
		//the last:
		vI = vertices.get(vertices.size()).subtract(ray.getP0());
		vIplus1 = vertices.get(0).subtract(ray.getP0());
		normalsList.add((vI.crossProduct(vIplus1).normalize()));
		
		//The point is inside if all 𝒗 ∙ 𝑵𝒊 have the same sign (+/-)
		
		//boolean poasitive = true;
		int countPositive = 0;
		int countNegative = normalsList.size();
		for (Vector vector : normalsList) 
		{
			if (alignZero((ray.getDir()).dotProduct(vector)) > 0)
			{
				countPositive++;
			}
			else if (alignZero((ray.getDir()).dotProduct(vector)) <= 0)
			{
				countNegative--;
			}
			
		}
		if (countPositive != normalsList.size() /*all normals in the positive side*/ && countNegative != 0 /*all normals in the negative side*/)
		{
			return null; //there is no instruction point
		}
		

		return rayPoints;
	
	}
}