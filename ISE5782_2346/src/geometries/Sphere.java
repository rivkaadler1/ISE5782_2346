package geometries;

import java.util.List;
import static primitives.Util.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Class Sphere is the basic class representing a sphere of Euclidean geometry implements the Geometry interface
 * @author Rivki Adler and Sarit Silverstone
*/
public class Sphere extends Geometry 
{
    Point center;
    double radius;

    /**
     * sphere constructor
     * @param radius radius if the sphere
     * @param p0 center point parameter
     */
    public Sphere(double radius, Point p0) 
    {
        this.center = p0;
        this.radius = radius;
    }

    /**
     * getter for center
     * @return center
     */
    public Point getCenter() 
    {
        return center;
    }

    /**
     * getter for radius
     * @return the radius
     */
    public double getRadius() 
    {
        return radius;
    }


    @Override
    public String toString() 
    {
        return "Sphere{" +
                "p0=" + center +
                ", radius=" + radius +
                '}';
    }


    @Override
    public Vector getNormal(Point p) 
    {
        Vector N = p.subtract(center);
        return N.normalize();
    
    }


	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)throws IllegalArgumentException  {
		if (ray.getP0().equals(center)) // if the begin of the ray in the center, the point, is on the radius
			return List.of(new GeoPoint(this,ray.getPoint(radius)));
		//List<Point3D> rayPoints = new ArrayList<Point3D>();
		Vector u = center.subtract(ray.getP0());
		double tM = alignZero(ray.getDir().dotProduct(u));
		double d = alignZero(Math.sqrt(u.length()*u.length()- tM * tM));
		double tH = alignZero(Math.sqrt(radius*radius - d*d));
		double t1 = alignZero(tM+tH);
		double t2 = alignZero(tM-tH);
		
		
		if (d > radius)
			return null; // there are no instructions

		
		if (t1 <=0 && t2<=0)
			return null;
		
		if (t1 > 0 && t2 >0)
			return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
		if (t1 > 0)
		{
			return List.of(new GeoPoint(this,ray.getPoint(t1)));
		}

		else
			return List.of(new GeoPoint(this,ray.getPoint(t2)));
	}
}
