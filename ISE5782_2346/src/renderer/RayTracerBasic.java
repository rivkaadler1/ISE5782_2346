package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * class RayTracerBasic in package renderer
 * 
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

	@Override
	public Color traceRay(Ray ray) throws IllegalArgumentException {
		Point closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? myScene.background : calcColor(closestPoint, ray);
	}

	@Override
	protected Color traceRay(List<Ray> rays) {
		// TODO Auto-generated method stub
		return null;
	}
}
