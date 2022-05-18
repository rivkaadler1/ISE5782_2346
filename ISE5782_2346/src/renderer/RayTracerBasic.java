package renderer;
import static primitives.Util.*;
import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 *  RayTracerBasic class extends RayTracerBase and implement the abstract function traceRay
 */
public class RayTracerBasic extends RayTracerBase
{

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;

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
     * @return color of the intersection point if there is otherwise color of the background
     */
    public Color traceRay(Ray ray) 
    {
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? myScene.background : calcColor(closestPoint, ray);
    }

    private Color calcColor(GeoPoint gp, Ray ray) 
    {
    	return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K))//i am not sure about the initial_k
    			.add(myScene.ambientLight.getIntensity());
    }
    
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k)
    {

		/*𝑰𝑷 = 𝒌𝑨 ∙ 𝑰𝑨 + 𝑰𝑬 + (𝒌𝑫 ∙ |𝒍 ∙ 𝒏| + 𝒌𝑺 ∙ (−𝒗 ∙ 𝒓)^ 𝒏𝒔𝒉)) ∙ 𝑰L*/
		Color Ie = gp.geometry.getEmission(); 
    	Color color = Ie.add(calcLocalEffects(gp, ray,k));
    	return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }
    
    private Color calcGlobalEffects(GeoPoint gp,Ray v, int level, Double3 k)
    {
    	Color color = Color.BLACK; Vector n = gp.geometry.getNormal(gp.point);
    	Material material = gp.geometry.getMaterial();
    	Double3 kkr = material.KR.product(k);
    	if (!kkr.lowerThan( MIN_CALC_COLOR_K))//if (kkr > MIN_CALC_COLOR_K)
    	color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.KR, kkr);
    	Double3 kkt = material.KT.product(k);
    	if (!kkt.lowerThan( MIN_CALC_COLOR_K))//if (kkt > MIN_CALC_COLOR_K
    	color = color.add(
    	calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.KT, kkt));
    	return color;
    	}
    	private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
    	GeoPoint gp = findClosestIntersection (ray);
    	return (gp == null ? myScene.background : calcColor(gp, ray, level-1, kkx)
    	.scale(kx));

    }


    
	private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) 
	{
		Vector v = ray.getDir().normalize();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0) //לא רואים את הנקודה עליה האור משפיע מחזיר שחור
			return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().nShininess;
        Double3 kd = intersection.geometry.getMaterial().KD;
        Double3 ks = intersection.geometry.getMaterial().KS;
		Color color = Color.BLACK;
		for (LightSource lightSource : myScene.lights) //עוברים כעל כל מקור אור בסצנה ובודקים איך הוא משפיע על הצבע בנקודה המסויימת
		{
			Vector l = lightSource.getL(intersection.point);//וקטור ממקור אור עד לנקודה
			double nl = alignZero(n.dotProduct(l));//רוצים לדעת שאני באותו כיוון כי אם לא לא רואים את ההשפעות
			if (nl * nv > 0) 
			{ 
				Double3 ktr = transparency(lightSource, l, n, intersection);
				if (!ktr.product(k).lowerThan( MIN_CALC_COLOR_K) )//if (ktr * k > MIN_CALC_COLOR_K) 
				{
				// sign(nl) == sing(nv)

					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection, n); // refactored ray head move
		List<GeoPoint> intersections = myScene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) 
			return true;
		double lightDistance = ls.getDistance(geopoint.point);
		for (GeoPoint gp : intersections) 
		{
			if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0 && gp.geometry.getMaterial().KT.equals(new Double3(0)) )
				      return false;
		}
		return true;     
    }
    
	/**
	 * A function that calculates the refracted rays.
	 * @param normal Vector value
	 * @param point Point value
	 * @param ray Ray value
	 * @return ray for refracted
	 * */
	private Ray constructRefractedRay( Point point, Ray ray,Vector normal)
	{
		Vector v = ray.getDir();
		return new Ray(point, v ,normal);
	}	
	
	/**
	 * A function that calculates the reflected rays.
	 * @param normal Vector value
	 * @param point Point value
	 * @param ray Ray value
	 * @return ray for reflected
	 * */
	private Ray constructReflectedRay( Point point, Ray ray,Vector normal) 
	{
		// 𝒓 = 𝒗 − 𝟐 ∙ (𝒗 ∙ 𝒏) ∙ n
		Vector v = ray.getDir();
		double nv = alignZero(normal.dotProduct(v));
		if (isZero(nv))
			return null;
		Vector r = v.subtract(normal.scale(nv*2));
		return new Ray(point, r, normal);
	}

	/**
	 * A function that find the most closet point to the ray
	 * @param ray Ray value
	 * @return the closet point
	 * */
	private GeoPoint findClosestIntersection(Ray ray)
	{
		List<GeoPoint> intersections = myScene.geometries.findGeoIntersections(ray);
		if(intersections == null)
			return  null;
		return ray.findClosestGeoPoint(intersections);
	}
	
	/**
	 * A function that allows partial shading
	 * @param light LightSource value
	 * @param l Vector value
	 * @param n Vector value
	 * @param geoPoint GeoPoint value
	 * */
	private Double3 transparency(LightSource light, Vector l, Vector n, GeoPoint geoPoint)
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geoPoint.point, lightDirection, n); // refactored ray head move

		double lightDistance = light.getDistance(geoPoint.point);
		var intersections = myScene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) 
			return Double3.ONE;
		Double3 ktr =Double3.ONE ;
		for (GeoPoint gp : intersections) 
		{
			if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0)
			{
				ktr = gp.geometry.getMaterial().KT.product(ktr);
				if (ktr.lowerThan(MIN_CALC_COLOR_K))
					return Double3.ZERO;
			}
		}

		return ktr;

	}

}