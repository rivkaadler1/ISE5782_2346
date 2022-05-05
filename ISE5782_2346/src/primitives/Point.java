package primitives;
import java.util.Objects;
/**
 * Point is the basic class representing a point of 3 coordinates
 * 3-Dimensional coordinate system.
 * @author Rivki Adler and Sarit Silverstone
*/
public class Point 
{
    Double3 xyz;

    /**
     * primary constructor for point
     * @param xyz double3 value for _xyz
     */
    Point(Double3 xyz)
    {
        this.xyz = xyz;
    }

    /**
     * @param x coordinate x axis
     * @param y coordinate y axis
     * @param z coordinate z axis
     */
    public Point(double x, double y, double z)
    {
        xyz = new Double3(x,y,z);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point)obj;
        return xyz.equals(other.xyz);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() { return xyz.toString(); }

/**
 * calculates and returns a distance squred between another point and this
 * @param other :Point
 * @return a square distance :double
 */
    public double distanceSquared(Point other)
    {
        return (this.xyz.d1 - other.xyz.d1) * (this.xyz.d1 - other.xyz.d1) +
                (this.xyz.d2 - other.xyz.d2) * (this.xyz.d2 - other.xyz.d2) +
                (this.xyz.d3 - other.xyz.d3) * (this.xyz.d3 - other.xyz.d3);
    }

    /**
     * calculate the distance between two points
     * @param other Point
     * @return the calculated distance
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     * adds a vector to the point
     * @param vector object of type Vector
     * @return a new point
     */
    public Point add(Vector vector) 
    {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * receives a Point as a parameter, returns a vector from second point to the point which called the method
     * @param point object of type Point
     * @return vector between 2 points
     */
    public Vector subtract(Point point)
    { 
    	return new Vector(xyz.subtract(point.xyz));
    }
    
    /**
     * getter for the point's d1 coordinate
     * @return xyz.d1
     */
	public double getX()
	{	
		return xyz.d1;
	}

    /**
     * getter for the point's d2 coordinate
     * @return xyz.d2
     */
	public double getY()
	{	
		return xyz.d2;
	}
	
    /**
     * getter for the point's d3 coordinate
     * @return xyz.d3
     */
	public double getZ()
	{	
		return xyz.d3;
	}

	
}
