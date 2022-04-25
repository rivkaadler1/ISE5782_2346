package renderer;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera {
	private Point p0; //location of the camera
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;
	private ImageWriter i;
	private RayTracerBase r;

	/**
	 * This constructor that create new camera
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @param vTo Vector value
	 * @param vUp Vector value
	 * @param p0 Point3D value
	 * @return Camera
	 * @throws Exception 
	 */
	public Camera(Point p0, Vector vTo, Vector vUp) throws IllegalArgumentException {
		if(!isZero(vTo.dotProduct(vUp))) // if vTo doesn't orthogonal to vUp
			throw new IllegalArgumentException("vUp doesnt ortogonal to vTo");
		
		//all the vectors need to be normalize:
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		vRight = (vTo.crossProduct(vUp)).normalize();
		
		this.p0 = p0;

	}
	
	/**
	 * Update function for View Plane size  
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @param width double value
	 * @param height double value
	 * @return Camera	 
	 */
	public Camera setViewPlaneSize(double width, double height){
		this.width = width;
		this.height = height;
		return this;
	}
	
	
	/**
	 * Update function for distance
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @param distance double value
	 * @return Camera	 
	 */
	public Camera setDistance(double distance){
		this.distance = distance;
		return this;
	}

	/**
	 * The function is responsible for creating the rays from the camera
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @param nX int value - resolution of pixel in X
	 * @param nY int value - resolution of pixel in Y
	 * @param j int value - index of column
	 * @param i int value - index of row
	 * @return Ray that created	 
	 * @throws Exception 
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i ) {
		Point Pc;
		if (isZero(distance))
			Pc=p0;
		else
			Pc=p0.add(vTo.scale(distance));
		
		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;
		double Xj=(j-(nX-1)/2d)*Rx;
		
		if(isZero(Xj) && isZero(Yi))
			return new Ray (p0, Pc.subtract(p0));
		
		Point Pij = Pc;
		
		if(!isZero(Xj))
			Pij = Pij.add(vRight.scale(Xj));
		
		if(!isZero(Yi))
			Pij = Pij.add(vUp.scale(-Yi));
		
		Vector Vij = Pij.subtract(p0);
		
		if(Pij.equals(p0))
			return new Ray(p0, new Vector(Pij.getX(),Pij.getY(),Pij.getZ()));
		return new Ray(p0, Vij);

	}
	
	


	/**
	 * Getter for p0
	 * 
	 * @author sarit silverstone and rivki adler
	 * @return Point value for p0	 
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * Getter for vUp
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @return Vector value for vUp	 
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * Getter for vTo
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @return Vector value for vTo	 
	 */
	public Vector getvTo() {
		return vTo;
	}
	
	/**
	 * Getter for vRight
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @return Vector value for vRight	 
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * Getter for width
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @return double value for width	 
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Getter for height
	 * 
	 * @author  sarit silverstone and rivki adler
	 * @return double value for height	 
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Getter for distance
	 * 
	 * @author sarit silverstone and rivki adler
	 * @return double value for distance	 
	 */
	public double getDistance() {
		return distance;
	}


	public void setI(ImageWriter i1) {
		this.i = i1;
	}

	public void setR(RayTracerBase r1) {
		this.r = r1;
	}
	public void renderImage() throws MissingResourceException, IllegalArgumentException {

		if (i == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "i");
	    if (r == null)
	     	throw new MissingResourceException("this function must have values in all the fileds", "RayTracerBase", "r");
		if (p0 == null) 
	     	throw new MissingResourceException("this function must have values in all the fileds", "Point", "p0");
		if (vUp == null) 
	     	throw new MissingResourceException("this function must have values in all the fileds", "Vector", "vUp");
		if (vTo == null) 
	     	throw new MissingResourceException("this function must have values in all the fileds", "Vector", "vTo");
		if (vRight == null) 
	     	throw new MissingResourceException("this function must have values in all the fileds", "Vector", "vRight");

	    /*for (int i1 = 0; i1 < i.getNx(); i1++)
		{
			for (int j = 0; j < i.getNy(); j++)	
			{
				if(numOfRays == 1 || numOfRays == 0)
				{
					Ray ray = camera.constructRayThroughPixel(i.getNx(), i.getNy(), j, i1);
					Color rayColor = r.traceRay(ray);
					i.writePixel(j, i1, rayColor); 
				}
				else
				{	
					List<Ray> rays = camera.constructBeamThroughPixel(i.getNx(), i.getNy(), j, i1,numOfRays);
					Color rayColor = r.traceRay(rays);
					i.writePixel(j, i1, rayColor); 
				}
			
		}*/
	}
	/**
//	 * A function that creates a grid of lines
//	 * 
//	 * @author sarit silverstone and rivki adler
//	 * @param interval int value
//	 * @param color Color value
//	 * */
	public void printGrid(int interval, Color color)
	{
		if (i == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "i");
		

		for (int i1 = 0; i1 < i.getNx(); i1++)
		{
			for (int j = 0; j < i.getNy(); j++)	
			{
				if(i1 % interval == 0 || j % interval == 0)
				    i.writePixel(i1, j, color); 
			}
		}

	}
	/**
	 * A function that finally creates the image.
	 * This function delegates the function of a class imageWriter
	 * 
	 * @author sarit silverstone and rivki adler
	 * */
	public void writeToImage()
	{
		if (i == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "imageWriter");
		
		i.writeToImage();
	}
	
}
