package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import primitives.Point;
import primitives.Ray;
/**
 * Class Geometries is a class representing a collection of geometries
 * implements the interface intersectable
 * @author Rivki Adler and Sarit Silverstone
*/
public class Geometries extends Intersectable 
{
	List<Intersectable> l;
	/**
	 * ctor initalizes the list of the geometries
	 */
	public Geometries()
	{
		//we chose in ArrayList because this class works better than linked list when the application demands storing the data and accessing it.
		l = new ArrayList<Intersectable>();
	}

	/**
	 * Constructor that recives list of geomeries and put them in new arrayList
	 * @author sarit silverstone and rivki adler
	 * @param geometries
	 * */
	public Geometries(Intersectable... geometries)
	{
		l =  new ArrayList<Intersectable>(Arrays.asList(geometries));
	}
	
	/**
	 * A function that add  geometries to  the list l.
	 * @author sarit silverstone and rivki adler
	 * @param geometries 
	 * */
	public void add(Intersectable... geometries)
	{
		if (geometries != null)
		{
			l.addAll(Arrays.asList(geometries));
		}
	}
	
	@Override
	public List<Point> findIntersections(Ray ray) 
	{
	   List<Point> temp = new ArrayList<Point>();
		for ( Intersectable intersectable : l) 
		{
			List<Point> intersection = intersectable.findIntersections(ray);
			if (intersection != null)
				temp.addAll(intersection); 
		}
		
		if (temp.isEmpty())
			return null;
		return temp;		
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
