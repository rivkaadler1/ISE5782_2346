package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Cylinder extends Tube 
{
	
	private double height;
	
	/**
	 * Constructor that receives radius, ray and height, and use in the constructor of the base class
	 * 
	
	 * @param radius double value
	 * @param axis Ray value
	 * @param height double value
	 * */
	public Cylinder(double radius, Ray axis, double height)
	{
		super(axis,radius);
		this.height = height;
	}
	
	/**
	 * A getter function for the field height that return double value for height
	 * 	
	 * @return double value for height
	 * */
	public double getHeight() 
	{
		return height;
	}

	/******************************************************************/
	
	@Override
	public String toString() 
	{
		return "Cylinder: "+super.toString()+" height=" + height;
	}

	@Override
	public Vector getNormal(Point point) 
	{
		//bonus
		return null;
	}
}
