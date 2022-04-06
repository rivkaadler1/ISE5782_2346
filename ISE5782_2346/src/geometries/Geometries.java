package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable  {
	List<Intersectable> l;
	public Geometries()
	{
		//we chosen in ArrayList because this class works better when the application demands storing the data and accessing it.
		l = new ArrayList<Intersectable>();
	}
	/**
	 * Constructor that recives list of geomeries and put them in new arrayList
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param geometries
	 * */
	public Geometries(Intersectable... geometries)
	{
		l =  new ArrayList<Intersectable>(Arrays.asList(geometries));
	}
	/**
	 * A function that add the geometries the receive to the list.
	 * 
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
	public List<Point> findIntsersections(Ray ray) {
		List<Point> temp = new ArrayList<Point>();
		for (Intersectable intersectable : geometriesInScene) 
		{
			List<Point> intersection = intersectable.findIntersections(ray);
			if (intersection != null)
				temp.addAll(intersection); 
		}
		
		if (temp.isEmpty())
			return null;
		return temp;
	}
	
}
