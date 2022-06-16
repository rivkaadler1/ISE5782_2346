package kMeans;

import java.util.ArrayList;
import java.util.List;

 public class Cluster 
 {

    public List<Observation> points;
    public Observation centroid;
    public int id;

    //Creates a new Cluster
    public Cluster(int id) 
    {
        this.id = id;
        this.points = new ArrayList<Observation>();
        this.centroid = null;
    }

    public List<Observation> getPoints()
    {
        return points;
    }

    public void addPoint(List<Observation> points)
    {
    	for(Observation p :points)
    	{
    		points.add(p);
    	}
    }
    public void addPoint(Observation p) 
    {   	
    		points.add(p);    	
    }

    public void setPoints(List<Observation> points) 
    {
        this.points = points;
    }

    public Observation getCentroid() 
    {
        return centroid;
    }

    public void setCentroid(Observation centroid)
    {
        this.centroid = centroid;
    }

    public int getId() 
    {
        return id;
        
    }

    public void clear() 
    {
        points.clear();
    }

}
