package renderer;
import java.util.List;
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

}
