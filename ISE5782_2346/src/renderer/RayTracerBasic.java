package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * class RayTracerBasic extends RayTracerBase class 
 * @author sarit silverstone and rivki adler
 */
public class RayTracerBasic extends RayTracerBase {
	
	/**
	 * constructor of RayTracerBasic
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param myscene Scene value
	 */
	public RayTracerBasic(Scene myscene) {
		super(myscene);
	}
	/**
	 * a function that returns the color of a point
	 * @author sarit silverstone and rivki adler
	 * @param point
	 */
	private Color calcColor(Point point){
		return myScene.ambientLight.getIntensity();
	}
	
	@Override
	public Color traceRay(Ray ray) throws IllegalArgumentException 
	{
		Point closestPoint = ray.findClosestPoint(myScene.geometries.findIntersections(ray));
		return closestPoint == null ? myScene.background : calcColor(closestPoint);
		
	}

}
