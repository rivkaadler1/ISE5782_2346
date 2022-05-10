package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 *  RayTracerBasic class extends RayTracerBase and implement the abstract function traceRay
 */
public class RayTracerBasic extends RayTracerBase {

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
                .add(calcLocalEffects(point, ray));
    }

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
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n,nl, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v,int nShininess, Color lightIntensity) {
        l = l.normalize();
        Vector r = l.subtract(n.scale(2*nl)).normalize();
        double d = alignZero(-v.dotProduct(r));
        if(d <= 0)
            return Color.BLACK;
        return lightIntensity.scale(ks.scale(Math.pow(d,nShininess)));
//        int specularN = 1;
//        double nl = alignZero(n.dotProduct(l));
//        Vector r=l.subtract(n.scale(nl*2));
//        double vr=Math.pow(v.scale(-1).dotProduct(r),nShininess);
//        return lightIntensity.scale(ks*Math.pow(vr,specularN));
    }

    private Color calcDiffusive(Double3 kd, double nl, Color lightIntensity) {
        if(nl < 0)
           nl = -nl;
        return lightIntensity.scale(kd).scale(nl);
//        double ln=alignZero(Math.abs(n.dotProduct(l)));
//        return lightIntensity.scale(kd*ln);
    }

}