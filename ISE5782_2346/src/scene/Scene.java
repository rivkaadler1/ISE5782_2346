package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
/**
 * class Scene for PDS
 * Scene of occurrence of the photographed image
 * @author Sarit Silverstone& Rivki Adler
 */
public class Scene
{
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight();
	public Geometries geometries;

	/**
	 * constructor 
	 * 
	 * @author Sarit Silverstone&Rivki Adler
	 * @param name
	 * */
	public Scene(String name)
	{
		geometries = new Geometries();
	}
	
	
	/**
	 * setter function to background, and return this for builder pattern
	 * @param background the background to set
	 */
	public Scene setBackground(Color background) 
	{
		this.background = background;
		return this;
	}
	

	/**
	 * setter function to ambientLight, and return this for builder pattern
	 * @param ambientLight the ambientLight to set
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) 
	{
		this.ambientLight = ambientLight;
		return this;
		
	}
	

	/**
	 * setter function to geometries, and return this for builder pattern
	 * @param geometries the geometries to set
	 */
	public Scene setGeometries(Geometries geometries) 
	{
		this.geometries = geometries;
		return this;
	}
}
