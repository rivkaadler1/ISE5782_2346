package unittests;


import org.junit.jupiter.api.Test;

import geometries.Sphere;
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

public class MiniProject1Test {
	private Scene scene = new Scene("Test scene");


		@Test
		public void sphereBefore() 
		{
			Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
					.setViewPlaneSize(200, 200).setDistance(1000);

			scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new Double3( 0.15)));
		
			scene.geometries.add( //
					new Sphere(80,new Point(0, 0, 0)) //
							.setEmission(new Color(255,105,180)) //
							.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

			scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
					.setKl(4E-5).setKq(2E-7));

			ImageWriter imageWriter = new ImageWriter("miniproject1-before", 600, 600);
			
					camera.setImageWriter(imageWriter) //
					.setRayTracer(new RayTracerBasic(scene))
					.renderImage()
					.writeToImage();
			

		}
		
		@Test
		public void sphereAfter() 
		{
			int numOfRays=144;
			Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
					.setViewPlaneSize(200, 200).setDistance(1000);

			scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new Double3( 0.15)));

			scene.geometries.add( //
					new Sphere(80,new Point(0, 0, 0)) //
					.setEmission(new Color(255,105,180)) //
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

			scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
					.setKl(4E-5).setKq(2E-7));

			ImageWriter imageWriter = new ImageWriter("miniproject1-after"
					+ "", 600, 600);
			camera.setNumOfRays(numOfRays) //
					.setImageWriter(imageWriter) //

					.setRayTracer(new RayTracerBasic(scene))

					.renderImage()
					.writeToImage();

		}

	}

