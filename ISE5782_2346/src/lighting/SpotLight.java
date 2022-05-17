package lighting;
import static primitives.Util.*;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
/***
 * a class for point light -
A lamp in a space that emits light  in one direction
 * @author Rivki Adler and Sarit Silverstone
 *
 */
public class SpotLight extends PointLight
{
    private Vector direction;
	/**
	 * constructor for spotlight that receives 3 params
	 * @param direction Vector value 
	 * @param intensity Color value
	 * @param position Point value
	 */
	public SpotLight(Color intensity, Point position, Vector direction)  
	{
		super(intensity, position);
		this.direction=direction.normalize();
	}
	
	@Override
	public Color getIntensity(Point p) throws IllegalArgumentException
	{
		double pl = alignZero(direction.dotProduct(getL(p)));
		if(getL(p) == null)
			return Color.BLACK;
		if (pl <= 0)
			return Color.BLACK;
		return super.getIntensity(p).scale(pl);
	}
	@Override
	public double getDistance(Point point) {
		return super.getDistance(point);
	}
}
