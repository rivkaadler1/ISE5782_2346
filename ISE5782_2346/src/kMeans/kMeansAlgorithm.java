package kMeans;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;

/**
 * class kMeansAlgorithm-
 * k-means clustering is a method of vector quantization, originally from signal processing, that aims to partition n observations into k clusters in which
 *  each observation belongs to the cluster with the nearest mean (cluster centers or cluster centroid) , serving as a prototype of the cluster.
 * @author Sarit Silverstone & Rivki Adler
 */
public class kMeansAlgorithm 
{
    private int NUM_CLUSTERS = 3;    
    private int MAX_ITERATIONS = 100;

    private List<Observation> points;
    private List<Cluster> clusters;
    
    /**
     * Default constructor
     */
    public kMeansAlgorithm()
    {
        this.points = new ArrayList<Observation>();
        this.clusters = new ArrayList<Cluster>();        
    }
    
    /**
	 * @return the clusters
	 */
	public List<Cluster> getClusters() 
	{
		
		return clusters;
	}

    /**
     * the function initializes the fields for the algorithm
     * @param points
     */
    public void init(List<Observation> points) 
    {
        //add Points
        this.points = points;
        //Create Clusters
        //Set random centroids
        for (int i = 0; i < NUM_CLUSTERS; i++) 
        {
            Cluster cluster = new Cluster(i);
            Observation centroid = Observation.createRandomPoint(-100,100);
            cluster.setCentroid(centroid);
            this.clusters.add(cluster);
        }
    }

    /**
     * this function is implements the algorithm of k means 
     as described in the class documentation  by calling help functions
     */
    public void calculate()
    {
        boolean finish = false;
        int iteration = 0;
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish && iteration < MAX_ITERATIONS) 
        {
            //Clear cluster state
            clearClusters();
            List<Observation> lastCentroids = getCentroids();
            //Assign points to the closer cluster
            assignCluster();
            //Calculate new centroids.
            calculateCentroids();
            iteration++;
            List<Observation> currentCentroids = getCentroids();
            //Calculates total distance between new and old Centroids
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) 
            {
                distance += Observation.distance(lastCentroids.get(i),currentCentroids.get(i));
            }
            if(distance == 0) { //if everything is as before - finish
                finish = true;
            }
        }
    }
    
    /**
     * function that clear all the clusters
     */
    private void clearClusters()
    {
        for(Cluster cluster : clusters) 
        {
            cluster.clear();
        }
    }
    
    /**
     * function that returns a list of centroids
     * @return a list of centroids
     */
    private List<Observation> getCentroids()
    {
        List<Observation> centroids = new ArrayList<Observation>(NUM_CLUSTERS);
        for(Cluster cluster : clusters) 
        {
        	Observation aux = cluster.getCentroid();
        	Observation point = new Observation(aux.getGeometry());//i think it is not necessary
            centroids.add(point);
        }
        return centroids;
    }

    /**
     * the function Assigns each observation to the cluster with the nearest mean: that with the least squared Euclidean distance.
     */
    private void assignCluster() 
    {
        double max = Double.MAX_VALUE;
        double min = max; 
        int clusterId = 0;                 
        double distance = 0.0; 

        for(Observation point : points)
        {
            min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) 
            {
                Cluster c = clusters.get(i);
                distance = Observation.distance(point, c.getCentroid());
                if(distance < min)
                {
                    min = distance;
                    clusterId = i;
                }
            }
            point.setClusterNumber(clusterId);
            clusters.get(clusterId).addPoint(point);
        }
        
    }
      
    /**
     * the function calculates the centroids of the clusters by calculating the mean values of the observations
     */
    private void calculateCentroids() 
    {
        for(Cluster cluster : clusters) 
        {
            double sumX = 0;
            double sumY = 0;
            double sumZ = 0;
            List<Observation> listPoints = cluster.getObservations();
            int n_points = listPoints.size();

            for(Observation point : listPoints) {
                sumX += point.getPositionPoint().getX();
                sumY += point.getPositionPoint().getY();
                sumZ += point.getPositionPoint().getZ();}

            Observation centroid = cluster.getCentroid();
            if(n_points > 0) {
                double newX = sumX / n_points;
                double newY = sumY / n_points;
                double newZ = sumZ / n_points;
                Point newCentroid=new Point(newX,newY,newZ);
                centroid.setPositionPoint(newCentroid);
           }
        }
    }
}

