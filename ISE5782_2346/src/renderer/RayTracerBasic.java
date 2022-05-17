package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 *  RayTracerBasic class extends RayTracerBase and implement the abstract function traceRay
 */
public class RayTracerBasic extends RayTracerBase {
	
	/**const for the size of moving the beginning of the rays for shading rays**/
	private static final double DELTA = 0.1;

    /**
     * Constructor
     * @param scene that the ray cross
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * find intersections of the scene geometries with the
     * received ray ad calculate the color of the intersection points
     * @param ray ray that was shot from the camera
     * @return color of the intersection point
     */
    public Color traceRay(Ray ray) {
    	var intersections = myScene.geometries.findGeoIntersections(ray);
    	if (intersections == null) return myScene.background;
    	GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
    	return calcColor(closestPoint, ray);
    	}


    /**
     * Calculate the color intensity on the point
     * @param point on the geometry
     * @return the color intensity
     */
    private Color calcColor(Intersectable.GeoPoint point, Ray ray) {
        return myScene.ambientLight.getIntensity()
                .add(point.geometry.getEmission())
                .add(calcLocalEffects(point, ray));//we send the ray(v) for the specular light calculation -v*r
    }

    /**
     * calculate the local effects of the lights on the geometry
     * @param intersection -geopoint
     * @param ray-the ray from the camera
     * @return the local color of the geopoint
     */
    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray){

        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;
        Double3 kd = intersection.geometry.getMaterial().KD;
        Double3 ks = intersection.geometry.getMaterial().KS;
        Color color = Color.BLACK;
        for (LightSource lightSource : myScene.lights) {
            Vector l = lightSource.getL(intersection.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
            	if (unshaded(l,n,intersection, lightSource)){              
            	Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n,nl, v, nShininess, lightIntensity));
            	}
            }
        }
        return color;
    }

    /**
     * calculate the specular color
     * @param ks  the level of the speculation
     * @param l the vector from the light to the geometry
     * @param n normal of the geometry
     * @param nl the product between n and l
     * @param v the vector from the camera
     * @param nShininess the shininess of the geometry
     * @param lightIntensity 
     * @return specular color
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v,int nShininess, Color lightIntensity) {
        l = l.normalize();
        Vector r = l.subtract(n.scale(2*nl)).normalize();
        double d = alignZero(-v.dotProduct(r));
        if(d <= 0)
            return Color.BLACK;
        return lightIntensity.scale(ks.scale(Math.pow(d,nShininess)));
    }
     /**
      * calculate the diffusive light
      * @param kd the level of the diffuse
      * @param nl 
      * @param lightIntensity
      * @return
      */
    private Color calcDiffusive(Double3 kd, double nl, Color lightIntensity) {
        if(nl < 0)
           nl = -nl;
        return lightIntensity.scale(kd).scale(nl);
    }
    /**
	 * A function that check if there is shaded or not
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param light LightSource value
	 * @param l Vector value
	 * @param n Vector value
	 * @param geopoint GeoPoint value
	 * @return true or false
	 * */
    private boolean unshaded(Vector l, Vector n, GeoPoint geopoint, LightSource ls) {
        double maxDistance= ls.getDistance(geopoint.point);
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = geopoint.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = myScene.geometries.findGeoIntersections(lightRay);
       if (intersections == null) {
           return true;
       }
        int count=intersections.size();
        for (GeoPoint gp : intersections) {
            double distance = gp.point.distance(geopoint.point);
            //maybe the intersections are behind the light source 
            if(distance>= maxDistance) {
            	count--;

            }
        }
        return count==0;
        
    }



}