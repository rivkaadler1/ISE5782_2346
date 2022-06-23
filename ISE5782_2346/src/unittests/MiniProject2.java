package unittests;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import kMeans.Cluster;
import kMeans.Observation;
import kMeans.kMeansAlgorithm;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

class MiniProject2 {
    private static Geometries square = new Geometries(new Polygon(new Point(0,0,-50),new Point(50,0,-100),new Point(50,50,-150),new Point(0,50,-100))
            .setEmission(new Color(java.awt.Color.BLUE)) 
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
            new Polygon(new Point(0,0,-50),new Point(-50,0,-100),new Point(-50,50,-150),new Point(0,50,-100))
                    .setEmission(new Color(java.awt.Color.BLUE)) 
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
            new Polygon(new Point(0,0,-50),new Point(-50,0,-100),new Point(-50,-50,-150),new Point(0,-50,-100))
                    .setEmission(new Color(java.awt.Color.BLUE)) 
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
            new Polygon(new Point(0,0,-50),new Point(50,0,-100),new Point(50,-50,-150),new Point(0,-50,-100))
                    .setEmission(new Color(java.awt.Color.BLUE)) 
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
    );
   


    private static Geometry triangle1 = new Triangle( 
            new Point(-150, -150, -150), new Point(150, -150, -150), new Point(75, 75, -150));
    @Test
    public void TestMiniProject2() {
    	 Scene scene1 = new Scene("Test scene") //
    	            .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200) //
                .setDistance(1000).setNumOfRays(144).
    			setMultithreading(3).setDebugPrint(0.1);

        for (int i = -6; i <=6; i++) {
            for (int j = -6; j <=6; j++) {
                scene1.geometries.add(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.PINK)) //
                                .setMaterial(new Material().setKs(0.4).setKd(0.3).setShininess(100).setKt(0.3)));
            }
        }
        scene1.geometries.add(square,triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
                new Sphere(50, new Point(0, 0, -50)) 
                        .setEmission(new Color(java.awt.Color.RED)) 
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(25, new Point(0, 0, -50)) 
                        .setEmission(new Color(java.awt.Color.BLACK)) 
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(20, new Point(50, 0, 0)) 
                        .setEmission(new Color(java.awt.Color.BLACK)) 
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(15, new Point(70, 0, 0)) 
                        .setEmission(new Color(java.awt.Color.RED)) 
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(20, new Point(-50, 0, 0)) 
                        .setEmission(new Color(java.awt.Color.BLACK)) 
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                 new Sphere(15, new Point(-70, 0, 0)) 
                     .setEmission(new Color(java.awt.Color.RED)) 
                     .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Triangle( //
                        new Point(50, 50, 50), new Point(-15, 15, 15), new Point(-75, -75, 150))
                        .setEmission(new Color(java.awt.Color.BLACK)),
                new Triangle( //
                        new Point(-150, -100, -300), new Point(-50, 50, 1), new Point(-150, 150, -150))
                        .setEmission(new Color(java.awt.Color.YELLOW)));
        scene1.lights.add(new SpotLight(new Color(500, 250, 250), new Point(10, -10, -130), new Vector(-2, -2, -1))
                .setKl(0.0001).setKq(0.000005));
        scene1.lights.add( new SpotLight(new Color(1000, 600, 0),new Point(-100, -100, 500),  new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));
        ImageWriter imageWriter = new ImageWriter("MiniProject2slower", 500, 500);        
                camera1.setImageWriter(imageWriter) 
                .setRayTracer(new RayTracerBasic(scene1).setBVH())
                 .renderImage()
            .writeToImage();
    }
    
    @Test
    public void TestMiniProject2A() {
    	 Scene scene1 = new Scene("Test scene") //
    	            .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) 
                .setViewPlaneSize(200, 200) //
                .setDistance(1000).setNumOfRays(144).
    			setMultithreading(3).setDebugPrint(0.1);

        for (int i = -9; i <=9; i++) {
            for (int j = -9; j <=-8; j++) {
                scene1.geometries.add(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) 
                                .setMaterial(new Material().setKs(0.4).setKd(0.3).setShininess(100).setKt(0.3)));
            }
        }
        

        for (int i = -9; i <=9; i++) {
            for (int j = 8; j <=9; j++) {
                scene1.geometries.add(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) 
                                .setMaterial(new Material().setKs(0.4).setKd(0.3).setShininess(100).setKt(0.3)));
            }
        }
        

        for (int i = -9; i <=9; i++) {
            for (int j = -1; j <=1; j++) {
                scene1.geometries.add(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) //
                                .setMaterial(new Material().setKs(0.4).setKd(0.3).setShininess(100).setKt(0.3)));
            }
        }
        scene1.geometries.add(square,
                new Sphere(50, new Point(0, 0, -50)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(25, new Point(0, 0, -50)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(20, new Point(50, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(15, new Point(70, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(20, new Point(-50, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                 new Sphere(15, new Point(-70, 0, 0)) //
                     .setEmission(new Color(java.awt.Color.RED)) //
                     .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)));
        scene1.lights.add(new SpotLight(new Color(500, 250, 250), new Point(10, -10, -130), new Vector(-2, -2, -1))
                .setKl(0.0001).setKq(0.000005));
        scene1.lights.add( new SpotLight(new Color(1000, 600, 0),new Point(-100, -100, 500),  new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));
        ImageWriter imageWriter = new ImageWriter("MiniProject2A", 500, 500);        
                camera1.setImageWriter(imageWriter) 
                .setRayTracer(new RayTracerBasic(scene1).setBVH())
                 .renderImage()
            .writeToImage();
    }
    
    @Test
    public void TestMiniProject2B(){   	
         Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200) //
                .setDistance(1000).setNumOfRays(144).
    			setMultithreading(3).setDebugPrint(0.1); 
    	 Scene scene2 = new Scene("Test scene") //
    	            .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
       Geometries g1=new Geometries();
       for (int i = -9; i <=9; i++) {
            for (int j = -9; j <=-8; j++) {
                g1.add(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) //
                                .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)));
            }
        }
       
       Geometries g2=new Geometries();
        for (int i =-9; i <=9; i++) {
            for (int j =8; j <=9; j++) {
                g2.add(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) //
                                .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)));
            }
        }

        Geometries g3=new Geometries();
        for (int i =-9; i <=9; i++) {
            for (int j =-1; j <=1; j++) {
                g3.add(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) //
                                .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)));
            }
        }
        g3.add(square,
                new Sphere(50, new Point(0, 0, -50)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(25, new Point(0, 0, -50)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(20, new Point(50, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(15, new Point(70, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(20, new Point(-50, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                 new Sphere(15, new Point(-70, 0, 0)) //
                     .setEmission(new Color(java.awt.Color.RED)) //
                     .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)));
        scene2.geometries.add(g1,g2,g3);
        scene2.lights.add(new SpotLight(new Color(500, 250, 250), new Point(10, -10, -130), new Vector(-2, -2, -1))
                .setKl(0.0001).setKq(0.000005));
        scene2.lights.add( new SpotLight(new Color(1000, 600, 0),new Point(-100, -100, 500),  new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));

        ImageWriter imageWriter = new ImageWriter("MiniProject2B", 500, 500);       
                camera2.setImageWriter(imageWriter) 
                .setRayTracer(new RayTracerBasic(scene2).setBVH())
                 .renderImage()
            .writeToImage();
    }
    
    @Test
    public void TestMiniProject2C() {

   	 Scene scene3 = new Scene("Test scene") //
   	            .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
    	
       Camera camera3 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) 
                .setViewPlaneSize(200, 200) //
                .setDistance(1000).setNumOfRays(144).
    			setMultithreading(3).setDebugPrint(0.1);
        List<Observation> observations = new LinkedList<Observation>();
        
       for (int i = -9; i <=9; i++) {
            for (int j = -9; j <=-8; j++) {
                observations.add(new Observation(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) 
                                .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))));
            }
        }
       
        for (int i =-9; i <=9; i++) {
            for (int j =8; j <=9; j++) {
                observations.add(new Observation(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) 
                                .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))));
            }
        }
        
        for (int i =-9; i <=9; i++) {
            for (int j =-1; j <=1; j++) {
                observations.add(new Observation(
                        new Sphere(5, new Point(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.CYAN)) //
                                .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))));
            }
        }

        //the square
        observations.add(new Observation(new Polygon(new Point(0,0,-50),new Point(50,0,-100),new Point(50,50,-150),new Point(0,50,-100))
                .setEmission(new Color(java.awt.Color.BLUE)) 
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))));
        observations.add(new Observation(new Polygon(new Point(0,0,-50),new Point(-50,0,-100),new Point(-50,50,-150),new Point(0,50,-100))
                        .setEmission(new Color(java.awt.Color.BLUE)) 
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))));
        observations.add(new Observation(new Polygon(new Point(0,0,-50),new Point(-50,0,-100),new Point(-50,-50,-150),new Point(0,-50,-100))
                        .setEmission(new Color(java.awt.Color.BLUE)) 
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))));
        observations.add(new Observation( new Polygon(new Point(0,0,-50),new Point(50,0,-100),new Point(50,-50,-150),new Point(0,-50,-100))
                        .setEmission(new Color(java.awt.Color.BLUE)) 
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))));

        
                observations.add(new Observation(new Sphere(50, new Point(0, 0, -50)) 
                        .setEmission(new Color(java.awt.Color.RED)) 
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))));
                observations.add(new Observation(new Sphere(25, new Point(0, 0, -50)) 
                        .setEmission(new Color(java.awt.Color.BLACK)) 
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))));
                observations.add(new Observation(new Sphere(20, new Point(50, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))));
                observations.add(new Observation(new Sphere(15, new Point(70, 0, 0)) 
                        .setEmission(new Color(java.awt.Color.RED)) 
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))));
                observations.add(new Observation(new Sphere(20, new Point(-50, 0, 0)) 
                        .setEmission(new Color(java.awt.Color.BLACK)) 
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))));
                observations.add(new Observation(new Sphere(15, new Point(-70, 0, 0)) 
                     .setEmission(new Color(java.awt.Color.RED)) 
                     .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3))));
                
        kMeansAlgorithm Kmeans = new kMeansAlgorithm();
        Kmeans.init(observations);
        Kmeans.calculate();
        List<Cluster> clusters = Kmeans.getClusters(); // Divide all the geometries into 3 clusters
		for (Cluster c : clusters)
		{ // Put all of the geometries in this cluster in one box
			Geometries geos = new Geometries();
			for (Observation p : c.getObservations()) 
			{
				geos.add(p.getGeometry());
			}
			scene3.geometries.add(geos);

		}
        scene3.lights.add(new SpotLight(new Color(500, 250, 250), new Point(10, -10, -130), new Vector(-2, -2, -1))
                .setKl(0.0001).setKq(0.000005));
        scene3.lights.add( new SpotLight(new Color(1000, 600, 0),new Point(-100, -100, 500),  new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));
        ImageWriter imageWriter = new ImageWriter("MiniProject2C", 500, 500);       
                camera3.setImageWriter(imageWriter) 
                .setRayTracer(new RayTracerBasic(scene3).setBVH())
                 .renderImage()
            .writeToImage();
    }

}
