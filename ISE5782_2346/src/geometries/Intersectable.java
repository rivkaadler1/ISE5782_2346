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
	
	default List<Point> findIntsersections(Ray ray) throws Exception {
		 var geoList = findGeoIntersections(ray);
		    return geoList == null ? null
		                           : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

List<Point> findGeoIntersections (Ray ray) throws IllegalArgumentException;
