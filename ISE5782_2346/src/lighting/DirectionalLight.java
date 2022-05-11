package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
/***
 * a class for directional light -evenly simulates a light source like the sun. Does not weaken over time. Contains the power
(intensity)
 * @author Rivki Adler and Sarit Silverstone
 *
 */
public class DirectionalLight extends Light implements LightSource 
{

	private Vector direction;
	/**
	 * Constructor for DirectionalLight
	 * this c-tor is normalize the direction vector
	 * @param intensity Color value
	 * @param direction Vector value
	 */
	public DirectionalLight(Color intensity, Vector direction) 
	{
		super(intensity);
		this.direction=direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		return super.getIntensity();
	}

	@Override
	public Vector getL(Point p) {
		return direction;
	}

}
