package renderer;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Class Camera The that creates rays from the camera towards the various geometries of the scene.
 * @author Rivki Adler and Sarit Silverstone
*/
public class Camera
{
	private Point p0; //location of the camera
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;
	private int numOfRays;

	/**
	 * This constructor creates new camera
	 * @author  sarit silverstone and rivki adler
	 * @param vTo Vector value
	 * @param vUp Vector value
	 * @param p0 Point value
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
		//Ratio (pixel width & height)
		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;//for calculating the center point
		double Xj=(j-(nX-1)/2d)*Rx;//for calculating the center point
		
		if(isZero(Xj) && isZero(Yi))
			return new Ray (p0, Pc.subtract(p0));
		//Pixel[i,j] center
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

    /**
     * setter for image writer
     * @param i1
     * @return camera
     */
	public Camera setImageWriter(ImageWriter i1) {
		this.imageWriter = i1;
		return this;
	}
     /***
      * setter for ray tracer
      * @param r1 :RayTracerBase
      * @return
      */
	public Camera setRayTracer(RayTracerBase r1) {
		this.rayTracer = r1;
		return this;
	}
	
	/**
	 * setter for number of rays
	 * @param numOfRays
	 * @return this class-a builder pattern
	 */
	public Camera setNumOfRays(int numOfRays)
	{
		if(numOfRays <= 0)
			this.numOfRays=1;
		else
		 this.numOfRays = numOfRays;
		return this;
	}
	
	/**
	 * this function  renders the picture
	 * @throws MissingResourceException
	 * @throws IllegalArgumentException
	 */
	public Camera renderImage() throws MissingResourceException, IllegalArgumentException
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
		for (int i = 0; i < imageWriter.getNx(); i++)
		{
			for (int j = 0; j < imageWriter.getNy(); j++)	
			{
				if(numOfRays == 1 || numOfRays == 0)
				{
					Color rayColor=castRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
					imageWriter.writePixel(j, i, rayColor); 
				}
				else
				{	
					List<Ray> rays = constructBeamThroughPixel(imageWriter.getNx(), imageWriter.getNy(), j, i,numOfRays);
					Color rayColor = rayTracer.traceRay(rays);
					imageWriter.writePixel(j, i, rayColor); 
				}
				
			}
		}
       }
	   catch(MissingResourceException e)
       {
	    	throw new MissingResourceException("No implemented yet",e.getClassName(),e.getKey());
       }
       return this;
   }
	
	/**
	 * 
	 * @param nX the amount of horizontal pixels
	 * @param nY the amount of vertical pixels
	 * @param j the row of a pixel
	 * @param i the column of a pixel
	 * @param raysAmount
	 * @return list of rays 
	 */
   public List<Ray> constructBeamThroughPixel (int nX, int nY, int j, int i, int raysAmount)
   {
		int numOfRays = (int)Math.floor(Math.sqrt(raysAmount)); //num of rays in each row or column		
		if (numOfRays==1) 
			return List.of(constructRayThroughPixel(nX, nY, j, i));
		//Ratio (pixel width & height)
		double Ry= height/nY;
		double Rx=width/nX;
		double Yi=(i-(nY-1)/2d)*Ry;
		double Xj=(j-(nX-1)/2d)*Rx;      
        double PRy = Ry / numOfRays; //height distance between each ray
        double PRx = Rx / numOfRays; //width distance between each ray       
	    //The distance between the view plane and the camera cannot be 0
        if (isZero(distance))
        {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        List<Ray> sampleRays = new ArrayList<>();

        //for each pixel in the pixels grid
        for (int i1 = 0; i1 < numOfRays; ++i1) 
        {
            for (int j1 = 0; j1< numOfRays; ++j1)
            {
                sampleRays.add(constructRaysThroughPixel(PRy,PRx,Yi, Xj, i1, j1));//add the ray
            }
        }
        sampleRays.add(constructRayThroughPixel(nX, nY, j, i));//add the center  ray to pixel
        return sampleRays;
   }
	
	 /**
     * the function treats each pixel like a little screen of its own and divide it to smaller "pixels".
     * Through each one we construct a ray. This function is similar to ConstructRayThroughPixel.
     * @param Ry height of each grid block we divided the pixel into
     * @param Rx width of each grid block we divided the pixel into
     * @param yi distance of original pixel from (0,0) on Y axis
     * @param xj distance of original pixel from (0,0) on X axis
     * @param j j coordinate of small "pixel"
     * @param i i coordinate of small "pixel"
     * @param distance distance of camera from the view plane
     * @return beam of rays through pixel
     */
    private Ray constructRaysThroughPixel(double Ry,double Rx, double yi, double xj, int j, int i)
    {
        Point Pc = p0.add(vTo.scale(distance)); //the center of the screen point

        double ySampleI =  (i *Ry + Ry/2d); //The pixel starting point on the y axis
        double xSampleJ=   (j *Rx + Rx/2d); //The pixel starting point on the x axis

        Point Pij = Pc; //The point at the pixel through which a beam is fired
        //Moving the point through which a beam is fired on the x axis
        if (!isZero(xSampleJ + xj))
        {
            Pij = Pij.add(vRight.scale(xSampleJ + xj));
        }
        //Moving the point through which a beam is fired on the y axis
        if (!isZero(ySampleI + yi))
        {
            Pij = Pij.add(vUp.scale(-ySampleI -yi ));
        }
        Vector Vij = Pij.subtract(p0);
        
        return new Ray(p0,Vij);//create the ray throw the point we calculate here
    }
	  
	/**
	 * Cast ray from camera in order to color a pixel
	 * @param nX resolution on X axis (number of pixels in row)
	 * @param nY resolution on Y axis (number of pixels in column)
	 * @param j pixel's column number (pixel index in row)
	 * @param i pixel's row number (pixel index in column)
	 */
	 private Color castRay(int nX,int nY,int j,int i)
	 {
		 Ray ray = constructRayThroughPixel(nX, nY, j, i);
		 Color color=rayTracer.traceRay(ray);
		 return color;
	 }
	 
	/**
	 * A function that creates a grid of lines
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
	 * @author sarit silverstone and rivki adler
	 * */
	public void writeToImage()
	{
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all the fileds", "ImageWriter", "imageWriter");
		
		imageWriter.writeToImage();
	}

	
}
