package geometries;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface for all the geometries that have normal
 * implements intersectable
 * @author Sarit Silverstone and Rivki Adler
 */
public abstract class Geometry extends Intersectable{
	protected Color emission = Color.BLACK;
	
	
	    
	  //  @Override
		//public boolean equals(Object obj) 
		//{
		//	if (this == obj) return true;
		//	if (obj == null) return false;
		//	if (!(obj instanceof GeoPoint)) return false;
		//	GeoPoint other = (GeoPoint)obj;
		//	return this.geometry.equals(other.geometry) && this.point.equals(other.point);
		//}
	

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
}
