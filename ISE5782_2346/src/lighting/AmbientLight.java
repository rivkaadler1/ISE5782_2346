package lighting;

import primitives.Color;
import primitives.Double3;

/**

 * Class AmbientLight - the basic light in the scene
 * An ambient light source represents a fixed-intensity
 * and fixedcolor light source that affects all objects in the scene equally.
 * @author Rivki Adler & Sarit Silverstone
 */
public class AmbientLight extends Light
{
	
	
	/**
	 * constructor with 2 parameters:
	 * @param Ia For original fill light (light intensity according to RGB components) - I_A
	 * @param double3 Filling light attenuation coefficient - k_A
	 */
	
	public AmbientLight(Color Ia,Double3 double3)
	{
		super(Ia.scale(double3));
	}
	
	/**
	 * A default constructor
	 * this c-tor put the default color - black to the ambition light
	 */
	public AmbientLight() 
	{
		super(Color.BLACK);
	}
	


}
