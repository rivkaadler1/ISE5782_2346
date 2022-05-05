package primitives;
/**
 * Class Vector is the basic class representing a vectorof Euclidean geometry in Cartesian
 *a quantity that has magnitude and direction 
 * @author Rivki Adler and Sarit Silverstone
*/
public class Vector extends Point
{


    /**
     * Vector ctor which receives three coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector(double x, double y, double z) 
    {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector (0,0,0) is not allowed");
    }

    /**
     * Vector ctor which receives an object of type Double3
     * @param xyz an object of type Double3
     */
    Vector(Double3 xyz)
    {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
        {
            throw new IllegalArgumentException("Vector (0,0,0) is not allowed");
        }
    }
    
    @Override
    public boolean equals(Object obj)
    {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof Point)) return false;
    Point other = (Point)obj;
    return super.equals(other);
    }

    @Override
    public String toString() { return "->" + super.toString(); }

    /**
     * add two vectors
     * @param v
     * @return the new vector
     */
    public Vector add(Vector v) 
    {
        return new Vector(this.xyz.add(v.xyz));
    }

    /**
     * multiplication vector in scalar
     * @param d
     * @return the new vector
     */
    public Vector scale(double d)
    {
        return new Vector(this.xyz.scale(d));
    }
    
    /**
     * algebraic cross product
     * @param other object of type vector
     * @return the result
     */
    public Vector crossProduct(Vector other)
    {
        double ax = xyz.d1;
        double ay = xyz.d2;
        double az = xyz.d3;

        double bx = other.xyz.d1;
        double by = other.xyz.d2;
        double bz = other.xyz.d3;

        double cx = ay*bz - az*by;
        double cy = az*bx - ax*bz;
        double cz = ax*by - ay*bx;

        return new Vector(cx, cy, cz);
    }
    /**
     * the squared value of the vector’s length
     * @return the result
     */
    public double lengthSquared()
    {
        return (xyz.d1 * xyz.d1)  +
                (xyz.d2 * xyz.d2) +
                (xyz.d3 * xyz.d3);
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    
    /**
     * normalize the vector
     * @return new vector which is the original vector, normalized
     */
    public Vector normalize() 
    {
    	double sum=length();
		Vector v=new Vector(this.xyz.d1/sum, this.xyz.d2/sum, this.xyz.d3/sum);
		return v;		   		
    }

    /**
     * algebraic dot product
     * @param other object of type vector
     * @return the result
     */
    public double dotProduct(Vector other)
    {
        return (this.xyz.d1 * other.xyz.d1) +
                (this.xyz.d2 * other.xyz.d2) +
                (this.xyz.d3 * other.xyz.d3);
    }
}


