package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * interface for all the geometries that have normal
 * implements intersectable
 * @author Sarit Silverstone and Rivki Adler
 */
public interface Geometry extends Intersectable
{
	/**
	 * A function that calculates and returns the normal of a plane of the geometry
	 * @param p ,point on the geometry
	 * @return normal vector
	 */
    public Vector getNormal(Point p);
}
