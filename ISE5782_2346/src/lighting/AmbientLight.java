package lighting;

import primitives.Color;
import primitives.Double3;

/**

 * Class AmbientLight - the basic light in the scene
 * An ambient light source represents a fixed-intensity
 * and fixedcolor light source that affects all objects in the scene equally.
 * @author Rivki Adler & Sarit Silverstone
 */
public class AmbientLight 
{
	private Color intensity;
	
	/**
	 * constructor with 2 parameters:
	 * @param Ia For original fill light (light intensity according to RGB components) - I_A
	 * @param Ka Filling light attenuation coefficient - k_A
	 */
	
	public AmbientLight(Color Ia,Double3 Ka)
	{
		this.intensity=Ia.scale(Ka);
	}
	
	/**
	 * A default constructor
	 * this c-tor put the default color - black to the ambition light
	 */
	public AmbientLight() 
	{
		intensity=Color.BLACK;
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
