package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry 
{
    protected Ray axis;
    protected double radius;

    /**
     * tube constructor
     * @param axis parameter for axis
     * @param radius parameter for radius
     */
    public Tube(Ray axis, double radius)
    {
        this.axis = axis;
        this.radius = radius;
    }

    /**
     * axis getter
     * @return the axis
     */
    public Ray getAxis() 
    {
        return axis;
    }

    /**
     * radius getter
     * @return the radius
     */
    public double getRadius()
    {
        return radius;
    }

    /**
     * override toString
     * @return string representing the tube
     */
    @Override
    public String toString()
    {
        return "Tube{" +
                "axis=" + axis +
                ", radius=" + radius +
                '}';
    }

    /**
     * implement interface Geometry function
     * @param p the point from which we want the normal
     * @return the perpendicular vector to the point that was received
     */
    @Override
    public Vector getNormal(Point p) 
    {
    	double t=axis.getDir().dotProduct(p.subtract(axis.getP0()));
		Point pointO =axis.getP0().add(axis.getDir().scale(t));
		Vector myVec=p.subtract(pointO);
		return myVec.normalize();
    }
}
