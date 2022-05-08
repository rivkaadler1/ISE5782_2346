package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
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
	//private Color calcColor(GeoPoint point){
	//	return myScene.ambientLight.getIntensity();
	//}
	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray).add(myScene.ambientLight.getIntensity());
	}
	/**
	 * Function that calculates the color for the nearest intersection point, 
	 * if no intersection points are returned the color of the background	
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param ray Ray value
	 * @return Color
	 *  */
	@Override
	public Color traceRay(Ray ray) throws IllegalArgumentException 
	{
		GeoPoint closestPoint = ray.findClosestGeoPoint(myScene.geometries.findGeoIntersections(ray));
		return closestPoint == null ? myScene.background : calcColor(closestPoint,ray);
		
	}

}
