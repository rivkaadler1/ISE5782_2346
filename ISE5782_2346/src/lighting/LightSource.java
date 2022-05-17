package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface to LightSource
 * @author Sarit Silverstone and Rivki Adler
 *
 */
public interface LightSource 
{
	/**
	 * A function that return the intensity at a point
	 * @author Rivki Adler & Sarit Silverstone
	 * @param p Point value
	 * @return intensity color in this point
	 */
	public Color getIntensity(Point p);
	
	/**
	 * A function that return the  vector L of the lighting direction at a point
	 * 
	 * @author Rivki Adler & Sarit Silverstone
	 * @param p Point value
	 * @return the lighting direction on a point
	 */
	public Vector getL(Point p);
	/**
	 * calculates and returns the distance between a light source and a point
	 * @param point
	 * @return distance from the point
	 */
	double getDistance(Point point);
}
