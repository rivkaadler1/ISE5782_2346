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
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

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


	public Camera setImageWriter(ImageWriter i1) {
		this.imageWriter = i1;
		return this;
	}

	public Camera setRayTracer(RayTracerBase r1) {
		this.rayTracer = r1;
		return this;
	}
	public void renderImage() throws MissingResourceException, IllegalArgumentException
	{
       try 
       {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "i");
	    if (rayTracer == null)
	     	throw new MissingResourceException("this function must have values in all the fileds", "RayTracerBase", "r");
		if (p0 == null) 
	     	throw new MissingResourceException("this function must have values in all the fileds", "Point", "p0");
		if (vUp == null) 
	     	throw new MissingResourceException("this function must have values in all the fileds", "Vector", "vUp");
		if (vTo == null) 
	     	throw new MissingResourceException("this function must have values in all the fileds", "Vector", "vTo");
		if (vRight == null) 
	     	throw new MissingResourceException("this function must have values in all the fileds", "Vector", "vRight");

        int nX=imageWriter.getNx();
        int nY=imageWriter.getNy();
        
	    for (int i= 0; i< nX; i++)
		{
			for (int j = 0; j < nY; j++)	
			{
				imageWriter.writePixel(j, i, castRay(nX,nY,j,i));
		   }
			
	     }
       }
	   catch(MissingResourceException e)
       {
	    	throw new MissingResourceException("No implemented yet",e.getClassName(),e.getKey());
       }
}
	  
	/**
	 * Cast ray from camera in order to color a pixel
	 * @param nX resolution on X axis (number of pixels in row)
	 * @param nY resolution on Y axis (number of pixels in column)
	 * @param col pixel's column number (pixel index in row)
	 * @param row pixel's row number (pixel index in column)
	 */
	 private Color castRay(int nX,int nY,int j,int i)
	 {
		 Ray ray = constructRayThroughPixel(nX, nY, j, i);
		 Color color=rayTracer.traceRay(ray);
		 return color;
	 }
	 
	/**
	 * A function that creates a grid of lines
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param interval int value
	 * @param color Color value
	 * */
	public void printGrid(int interval, Color color)
	{
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "i");
		

		for (int i1 = 0; i1 < imageWriter.getNx(); i1++)
		{
			for (int j = 0; j < imageWriter.getNy(); j++)	
			{
				if(i1 % interval == 0 || j % interval == 0)
				    imageWriter.writePixel(i1, j, color); 
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
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "imageWriter");
		
		imageWriter.writeToImage();
	}

	
}
