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
	 * Static Internal Auxiliary Department (as a completely passive data structure - PDS)
	 * @author sarit silverstone and rivki adler
	 * @param geometry Geometry value
	 * @param point Point3D value
	 * */
	
	public static class GeoPoint {
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
	    

		 
	    @Override
		public String toString(){
			return "GeoPoint [geometry=" +geometry + ", point=" + point + "]";
		}
	    @Override
	    public boolean equals(Object obj) {
	    	if(this==obj)return true;
	    	if(obj==null) return false;
	    	if(!(obj instanceof GeoPoint))return false;
	    	GeoPoint other =(GeoPoint)obj;
	    	return this.geometry==other.geometry&&this.point.equals(other.point);
	    }

	}
	
	/**
	 * finds all the intersection points with geometry
	 * @param ray
	 * @return list of intersection points
	 */
	public List<Point> findIntersections(Ray ray) 
	{
	    var geoList = findGeoIntersections(ray);
	    return geoList == null ? null
	                           : geoList.stream().map(gp -> gp.point).toList();
	}
	
   /**
    * the main function for finding  the intersection points with geometry
    * @param ray
    * @return list of Geopoints that the ray intersects
    */
    public List<GeoPoint> findGeoIntersections (Ray ray)
    {
		return findGeoIntersectionsHelper(ray);
    }
    
	/**
	 * helper function for findgeointersections function ,the function checks if the functionality of borderable is on and calls the findGeoIntersectionsParticular function
	 * @param ray
	 * @return list of Geopoints that the ray intersects
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
    
	/**
	 * finds all the intersection points with geometry
	 * @param ray
	 * @return list of Geopoints that the ray intersects
	 */
	protected abstract  List<GeoPoint> findGeoIntersectionsParticular(Ray ray);
}
