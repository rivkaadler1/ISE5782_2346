package geometries;
import java.util.List;
import java.util.stream.Collectors;

import primitives.Point;
import primitives.Ray;
/**
 * @author sarit silverstone
 * interface that check intersection points
 * */

public interface Intersectable {
	
	public List<Point> findIntsersections(Ray ray);
}

