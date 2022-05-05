package primitives;
import java.util.List;
import java.util.Objects;
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
	 * The function returns the point closest to the beginning of the beam
	 * from all the intersection points of the resulting list.
	 * @param points List<Point> value
	 * @return Point value
	 * */
	public Point findClosestPoint (List<Point> points)
	{
		if(points == null)
			return null;
		Point closet = points.get(0);
		for (Point point : points) 
		{
			if(point.distance(p0) < closet.distance(p0))
				closet= point;
		}
		return closet;
	}
}