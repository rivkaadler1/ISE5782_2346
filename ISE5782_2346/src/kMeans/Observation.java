package kMeans;

import java.util.Random;

import geometries.*;
import primitives.*;
/**
 * Class Observation represents an observation in the k means algorithm
 * @author Sarit Silverstone & Rivki Adler
 */
public class Observation 
{
	private Geometry geometry;
    private Point PositionPoint;
    private int clusterNumber = 0;
    
    /**
     * Constructor that initializes the fields geometry & positionpoint
     * @param g geometry
     */
    public Observation(Geometry g)
    {
        this.geometry=g;
        this.PositionPoint=g.getPositionPoint();
    }
  

	/**
	 * @return the PositionPoint
	 */
	public Point getPositionPoint() {
		return PositionPoint;
	}
    /**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}
	/**
	 * @param geometry 
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	/**
	 * @param PositionPoint the point to set
	 */
	public void setPositionPoint(Point positionPoint) {
		this.PositionPoint = positionPoint;
	}
	/**
	 * @return the clusterNumber
	 */
	public int getClusterNumber() {
		return clusterNumber;
	}
	/**
	 * @param clusterNumber 
	 */
	public void setClusterNumber(int cluster_number) {
		this.clusterNumber = cluster_number;
	}

  /**
  * calculates the distance between an observation and one of the k means
   * @param o -observation
   * @param mean -one of the k means
   * @return the distance between o and mean
   */
    protected static double distance(Observation o, Observation mean) {
    	return o.getPositionPoint().distanceSquared(mean.getPositionPoint());
    }

  /**
   * the function calls to function createRandomPoints with parameters
   * @return a random centroid point
   */
    protected static Observation createRandomPoint() {
    	return Observation.createRandomPoint(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
    }
    /**
     * 
     * @param min NEGATIVE_INFINITY
     * @param max POSITIVE_INFINITY
     * @return a random observation
     */
    protected static Observation createRandomPoint(double min, double max)
    {
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        double z = min + (max - min) * r.nextDouble();
        return new Observation(new Sphere(1,new Point(x,y,z)));
    }
}
