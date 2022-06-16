package kMeans;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;

public class kMeansAlgorithm 
{
    //Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 3;    
    private int MAX_ITERATIONS = 100;

    private List<Observation> points;
    private List<Cluster> clusters;
    
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

	//Initializes the process
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

    //The process to calculate the K Means, with iterating method.
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

    private void clearClusters()
    {
        for(Cluster cluster : clusters) 
        {
            cluster.clear();
        }
    }

    private List<Observation> getCentroids()
    {
        List<Observation> centroids = new ArrayList<Observation>(NUM_CLUSTERS);
        for(Cluster cluster : clusters) 
        {
        	Observation aux = cluster.getCentroid();
        	Observation point = new Observation(aux.getGeometry());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() 
    {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
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
                    cluster = i;
                }
            }
            point.setClusterNumber(cluster);
            clusters.get(cluster).addPoint(point);
        }
        
    }

    private void calculateCentroids() 
    {
        for(Cluster cluster : clusters) 
        {
            double sumX = 0;
            double sumY = 0;
            double sumZ = 0;
            List<Observation> listPoints = cluster.getPoints();
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

