package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * an abstract class for all the geometries that have normal
 * extends intersectable
 * @author Sarit Silverstone and Rivki Adler
 */
public abstract class Geometry extends Borderable 
{
	protected Color emission = Color.BLACK;
	private Material material=new Material();	

	/**
	 * A function that calculates and returns the normal of a plane of the geometry
	 * @param p ,point on the geometry
	 * @return normal vector
	 */
    public abstract Vector getNormal(Point p);
    
    /**
	 * getter function for the color filed in geometry class
	 * 
	 * @author sarit silverstone and rivki adler
	 * @return emission Color value
	 * */
	public Color getEmission() {
		return emission;
	}
	/**
	 * setter function for the color filed and return this- geometry class
	 * 
	 * @author sarit silverstone and rivki adler
	 * @return the geometry-this
	 * */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
	
	/**
	 * getter function for material
	 * @author Rivki Adler & Sarit Silverstone
	 * @return the material
	 */
	public Material getMaterial() 
	{
		return material;
	}
	
	/**
	 * setter function for material 
	 * @param material the material to set
	 * @return the object - builder
	 */
	public Geometry setMaterial(Material material) 
	{
		this.material = material;
		return this;
	}
	
	public abstract Point getPositionPoint();
}
