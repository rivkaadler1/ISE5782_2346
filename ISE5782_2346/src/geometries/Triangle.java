package geometries;

import java.util.ArrayList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Class Triangle is the basic class representing a triangle of Euclidean geometry extends polygon
 * @author Rivki Adler and Sarit Silverstone
*/
public class Triangle extends Polygon 
{

    /**
     * triangle constructor
     * @param p1 vertex 1 of the triangle
     * @param p2 vertex 2 of the triangle
     * @param p3 vertex 3 of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3)
    {
        super(p1, p2, p3);
    }
    

	

}