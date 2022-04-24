package lighting;

import primitives.Color;
import primitives.Double3;
//@author Sarit Silverstone & Rivki Adler
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
	 * this c-tor put the defalt color - black to the ambition light
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
