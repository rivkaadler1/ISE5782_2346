package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import primitives.Ray;
/**
 * Class Geometries is a class representing a collection of geometries
 * extends the abstract class Bordable
 * @author Rivki Adler and Sarit Silverstone
*/
public class Geometries extends Borderable
{
	List<Borderable> l;
	/**
	 * ctor initalizes the list of the geometries
	 */
	public Geometries()
	{
		//we chose in ArrayList because this class works better than linked list when the application demands storing the data and accessing it.
		l = new ArrayList<Borderable>();
	}

	/**
	 * Constructor that recives list of geomeries and put them in new arrayList
	 * @author Sarit Silverstone and Rivki Adler
	 * @param geometries
	 * */
	public Geometries(Borderable... geometries)
	{
		l =  new ArrayList<Borderable>(Arrays.asList(geometries));
	}

	/**
	 * Iterator function
	 * @return iterator of l
	 */
	public  Iterator<Borderable> iterator()//wasnt before
	{
		return l.iterator();
	}
	/**
	 * A function that add  geometries to  the list l.
	 * @author sarit silverstone and rivki adler
	 * @param geometries 
	 * */
	public void add(Borderable... geometries)
	{
		if (geometries != null)
		{
			l.addAll(Arrays.asList(geometries));
		}
	}

	/**
	 * A getter function for l
	 * @return List<Borderable> value for l
	 * */
	public List<Borderable> getIntsersectionPoints() //i am not sure we asked to write getter it it is not for mp2
	{
		return l;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometries other = (Geometries) obj;
		if (l == null) {
			if (other.l!= null)
				return false;
		} else if (!l.equals(other.l))
			return false;
		return true;
	}
	
	@Override
	protected void findMinMaxParticular() 
	{
		minX = Double.POSITIVE_INFINITY;
		maxX = Double.NEGATIVE_INFINITY;
		minY = Double.POSITIVE_INFINITY;
		maxY = Double.NEGATIVE_INFINITY;
		minZ = Double.POSITIVE_INFINITY;
		maxZ = Double.NEGATIVE_INFINITY;	
		/**
	     * find the minimum and the maximum of the geometry border
	     */
	        for (Borderable g : l)
	        {
	            g.findMinMax();	            
	            if (g.minX < minX)
	                minX = g.minX;
	            if (g.minY < minY)
	                minY = g.minY;
	            if (g.minZ < minZ)
	                minZ = g.minZ;	           
	            if (g.maxX > maxX)
	                maxX = g.maxX;
	            if (g.maxY > maxY)
	                maxY = g.maxY;
	            if (g.maxZ > maxZ)
	                maxZ = g.maxZ;
	        }
	}
	
	@Override
	public List<GeoPoint> findGeoIntersectionsParticular(Ray ray)
	{
		List<GeoPoint> temp = new ArrayList<GeoPoint>();
		for (Intersectable intersectable :l) 
		{
			List<GeoPoint> intersection = intersectable.findGeoIntersections(ray);
			if (intersection != null)
				temp.addAll(intersection); 
		}
		
		if (temp.isEmpty())
			return null;
		return temp;	
	}

}

