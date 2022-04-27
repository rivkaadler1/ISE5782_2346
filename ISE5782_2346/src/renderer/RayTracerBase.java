package renderer;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * class RayTracerBase of renderer package
 * 
 * @author sarit silverstone and rivki adler
 */
public abstract class RayTracerBase {

	protected Scene myScene;
	
	/**
	 * constructor 
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param myscene Scene value	
	 */
	public  RayTracerBase(Scene myscene){
		this.myScene=myscene;
	}
	
	/**
	 * Statement of an abstract function that calculates the color for the nearest intersection point, 
	 * if no intersection points are returned the color of the background	
	 * @author Sarit Silverstone & Rivki Adler
	 * @param ray Ray value
	 * @throws Exception
	 * @return Color
	 *  */
	public abstract Color traceRay(Ray ray) throws IllegalArgumentException ;

}
