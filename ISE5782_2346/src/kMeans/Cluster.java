package kMeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Cluster is a class that represents a collection of close observation points
 * @author Sarit Silverstone & Rivki Adler
 *
 */
 public class Cluster 
 {
    public List<Observation> observations;
    public Observation centroid;
    public int id;

    /**
     * Constructor 
     * @param id the id of the cluster
     */
    public Cluster(int id) {
        this.id = id;
        this.observations = new ArrayList<Observation>();
        this.centroid = null;
    }
    
    /**
     * getter for observations
     * @return the list of observations
     */
    public List<Observation> getObservations(){
        return observations;
    }
     
    /**
     * Function that adds observation to observations
     * @param o
     */
    public void addPoint(Observation o) 
    {   	
    		observations.add(o);    	
    }
    /**
     * setter for observations
     * @param obs
     */
    public void setObservations(List<Observation> obs) 
    {
        this.observations = obs;
    }
    
    /**
     * getter for centroid
     * @return centroid
     */
    public Observation getCentroid() 
    {
        return centroid;
    }

    /**
     * setter for centroid
     * @param centroid
     */
    public void setCentroid(Observation centroid)
    {
        this.centroid = centroid;
    }
    
    /**
     * getter for id
     * @return
     */
    public int getId() 
    {
        return id;
        
    }
  
    /**
     * function that clears observations
     */
    public void clear() 
    {
        observations.clear();
    }

}
