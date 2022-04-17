package primitives;

import java.util.Objects;


public class Ray 
{
    final Point p0;
    final Vector dir;

    /**
     * get the point of the ray
     * @return point
     */
    public Point getP0() 
    {
        return p0;
    }

    /**
     * get the direction of the ray
     * @return direction vector
     */
    public Vector getDir() 
    {
        return dir;
    }

    /**
     * Ray ctor
     * @param p0 object of type Point
     * @param dir direction - object of type Vector
     */
    public Ray(Point p0, Vector dir) 
    {
        if(!(dir.length() == 1))
            this.dir = dir.normalize();
        else this.dir = dir;
        this.p0 = p0;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray)obj;
        return this.dir.equals(other.dir) && this.p0.equals(other.p0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString()
    {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
    /**
     * this function isn't requried in exercise 1 and maybe not required at all
     * @param t
     * @return point
     */
	public Point getPoint(int t) 
	{
		return p0.add(dir.scale(t));
	}
	
	public Point getPoint(double t) throws IllegalArgumentException
	{
		return p0.add(dir.scale(t));
	}


}