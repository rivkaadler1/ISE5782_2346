
package geometries;
import static primitives.Util.*;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Class Plane is the basic class representing a plane of Euclidean geometry implements the Geometry interface
 * described using a point in the plane and a vector orthogonal to it (the normal vector) to indicate its "inclination" or 3 points on it.
 * @author Rivki Adler and Sarit Silverstone
*/
public class Plane implements Geometry
{
    final Point p0;
    final Vector normal;

    /**
     * plane 
     * @param p0 parameter for p0
     * @param normal parameter for normal
     */
    public Plane(Point p0, Vector normal)
    {
        this.p0 = p0;
        this.normal = normal;
    }

    /**
     * plane constructor
     * @param p1 point 1 to calculate plane
     * @param p2 point 2 to calculate plane
     * @param p3 point 3 to calculate plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        p0 = p1;
        Vector U = (Vector)p2.subtract(p1);
        Vector V = (Vector)p3.subtract(p1);
        Vector N = U.crossProduct(V);
        normal = N.normalize();
    }

    /**
     * getter for p0
     * @return p0
     */
    public Point getP0() 
    {
        return p0;
    }

    /**
     * getter for the normal
     * @return the normal
     */
    public Vector getNormal()
    {
        return normal;
    }


	@Override
	public Vector getNormal(Point p)
	{
		return normal;		
	}

	@Override
	public List<Point> findIntersections(Ray ray) 
	{
		double nv = normal.dotProduct(ray.getDir());
		if (isZero(nv))
		{
			return null;
		}
		
		try 
		{
			Vector pSubtractP0 = p0.subtract(ray.getP0());
			//if they are in opossite directions
			double t = alignZero((normal.dotProduct(pSubtractP0))/nv);
			if(t <= 0)
			{
				return null;
			}
			return List.of(ray.getPoint(t));
		}
		catch(Exception ex) 
		{
			return null;
		}  
    
   }
}

