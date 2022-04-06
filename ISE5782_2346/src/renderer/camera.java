package renderer;
import static primitives.Util.*;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * class camera in the package renderer
 * The purpose of the class is to create rays from the camera towards the various geometries of the scene.
 * 
 * @author sarit silverstone and rivki adler
 */
public class camera {
    private Point p0;//location
    private Vector vRight;
    private Vector vUp;
    private Vector vTo;
    private double width;
	private double height;
	private double distance;
	/**
	 * This constructor that creat new camera
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param vTo Vector value
	 * @param vUp Vector value
	 * @param p0 Point3D value
	 * @return Camera
	 * @throws Exception 
	 */
	public camera(Point p0, Vector vTo, Vector vUp) throws IllegalArgumentException {
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
	 * @author sarit silverstone and rivki adler
	 * @param width double value
	 * @param height double value
	 * @return Camera	 
	 */
	public camera setVPSize(double width, double height){
		this.width = width;
		this.height = height;
		return this;
	}
	/**
	 * Update function for distance
	 * 
	 * @author sarit silverstone and rivki adler
	 * @param distance double value
	 * @return Camera	 
	 */
	public camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}
	public Ray constructRay(int nX, int nY, int j, int i) {
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
	
}
