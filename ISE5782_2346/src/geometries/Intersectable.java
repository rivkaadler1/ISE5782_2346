package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;



/** 
 * interface Intersectable for ray intersections with geometries
 * @author sarit silverstone and Rivki Adler
 */
public abstract class Intersectable{

	/**
	 * Static Internal Auxiliary class (as a completely passive data structure - PDS)
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param geometry Geometry value
	 * @param point Point value
	 * */
	public static class GeoPoint
	{
	    public Geometry geometry;
	    public Point point;
	    /**
	     * constructor for geo point
	     * 
	     * @author sarit silverstone and rivki adler
	     * @param geometry Geometry
	     * @param point Point
	     * */
	    public GeoPoint(Geometry geometry,Point point){
	    	this.geometry = geometry;
	    	this.point = point;
	    }
	    

		 
	}
	

	
	/**
	 * finds all the intersection points with geometry
	 * @param ray
	 * @return list of intersection points
	 */
	public abstract List<Point> findIntersections(Ray ray);
	/*public List<Point> findIntersections(Ray ray) {
	    var geoList = findGeoIntersections(ray);
	    return geoList == null ? null
	                           : geoList.stream().map(gp -> gp.point).toList();
	}*/

	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
    public List<GeoPoint> findGeoIntersections (Ray ray){
		return findGeoIntersectionsHelper(ray);
    }

}
