package primitives;
import java.util.List;
import static primitives.Util.*;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;
/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in Cartesian
 * defined as a part of a line that has a fixed starting point but no end point. It can extend infinitely in one direction. 
 * @author Rivki Adler and Sarit Silverstone
*/
public class Ray 
{
    final Point p0;
    final Vector dir;

	/**
	 * A constant for the size of moving first rays for shading rays
	 * */
	private static final double DELTA = 0.1;
    /**
     * get the point of the ray
     * @return point
     */
    public Point getP0() 
    {
        return p0;
    }

    /**
     * get the direction of the ray
     * @return direction vector
     */
    public Vector getDir() 
    {
        return dir;
    }

    /**
     * Ray ctor
     * @param p0 object of type Point
     * @param dir direction - object of type Vector
     */
    public Ray(Point p0, Vector dir) 
    {
        if(!(dir.length() == 1))
            this.dir = dir.normalize();
        else this.dir = dir;
        this.p0 = p0;
    }
    
	public Ray(Point head, Vector lightDirection, Vector n) 
	{
		if(alignZero(lightDirection.dotProduct(n)) < 0)
			 p0= head.add(n.scale(-DELTA));
		else if(alignZero(lightDirection.dotProduct(n)) > 0)
			 p0= head.add(n.scale(DELTA));
	else if(isZero(lightDirection.dotProduct(n)))
			 p0=head;
		else
			p0=head;
		dir=lightDirection;
		dir.normalize();		
	}

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray)obj;
        return this.dir.equals(other.dir) && this.p0.equals(other.p0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString()
    {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
    
    /**
     *  calculates p0+t*v
     * @param t double
     * @return point the result of the calculation
     * @throws IllegalArgumentException
     */
	public Point getPoint(double t) throws IllegalArgumentException
	{
		return p0.add(dir.scale(t));
	}

	/**
	 * The function returns the point closest to the beginning of the ray
	 * from all the intersection points of the resulting list.
	 * @param points List<Point> value
	 * @return Point value
	 * */

	public Point findClosestPoint(List<Point> points) {
	    return points == null || points.isEmpty() ? null
	           : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}

	
	/**
	 * The function calculates and returns the geopoint that closest to the head of the ray
	 * from all the intersection geopoints of the resulting list.
	 * @param intersections list of geopoints
	 * @return the geopoint that closest to the head of the ray
	 */
	
	public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections)
	{
		
		if(intersections == null)
			return null;
		GeoPoint closet = intersections.get(0);
		for (GeoPoint geoPoint : intersections) 
		{
			if(geoPoint.point.distance(p0) < closet.point.distance(p0))
				closet= geoPoint;
			
		}
		return closet;
	}
}