package lighting;
import primitives.Color;
/**
 * an abstract class for light
 * @author Sarit Silverstone & Rivki Adler
 *
 */
abstract class Light {
	
	protected Color intensity;
	
	protected Light(Color intensity)
	{
		this.intensity=intensity;
	}

	/**
	 * getter to intensity
	 * @return intensity Color 
	 * */
	public Color getIntensity() 
	{
		return intensity;
	}
}
