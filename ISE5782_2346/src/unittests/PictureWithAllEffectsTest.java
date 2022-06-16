package unittests;
import org.junit.jupiter.api.Test;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;


public class PictureWithAllEffectsTest
{
	private Scene scene = new Scene("Test scene");


	@Test
	public void lovelyDayPicture() 
	{
		Camera camera = new Camera(new Point(0, 100, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //vto,vup
				.setViewPlaneSize(2500,2500).setDistance(10000); //height and width

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
		scene.setBackground(new Color(135,206,250));
		scene.geometries.add( 
				//six big triangles					
				new Triangle(new Point(-930, -1500, -1000), new Point(-1250, -804, -1000),new Point(-1500, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(-500, -1500, -1050), new Point(-750, -780, -1000),new Point(-1000, -1500, -2000)).setEmission(new Color(20, 190, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(0, -1500, -1000), new Point(-250, -800, -1000),new Point(-500, -1500, -2000)).setEmission(new Color(20, 185, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(500, -1500, -1000), new Point(250, -880, -1000),new Point(0, -1500, -2000)).setEmission(new Color(20, 195, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(1000, -1500, -1008), new Point(750, -842, -1000),new Point(500, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(1400, -1500, -1095),new Point(1250, -839, -1000),new Point(1050, -1500, -2000)).setEmission(new Color(20, 180, 20)).setMaterial(new Material().setKr(0.36)),
				
				//5 smaller triangles
				new Triangle(new Point(-750, -1500, -1000), new Point(-1000, -960, -1000),new Point(-1250, -1500, -2000)).setEmission(new Color(20,200,20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(-250, -1500, -1000), new Point(-500, -895, -1000),new Point(-700, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(250, -1500, -1000), new Point(0, -950, -1000),new Point(-250, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(750, -1500, -1000), new Point(500, -900, -1000),new Point(250, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(1250, -1500, -1000), new Point(1000, -950, -1000),new Point(750, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
                //the red points on the grass
				new Sphere(50, new Point(-1000, -960, -1000)).setMaterial(new Material().setKd(0.25).setKr(0.025).setKs(0.95).setShininess(25)).setEmission(new Color(102,0,102)),
				new Sphere(50, new Point(250, -900, -1000)).setEmission(new Color(102,0,102)).setMaterial(new Material().setKd(0.30).setKr(0.1).setKs(0.40).setShininess(5)),
				new Sphere(50, new Point(-120, -1180, -1000)).setEmission(new Color(102,0,102)).setMaterial(new Material().setKd(0.1).setKr(0.001).setKs(0.54).setShininess(12)),
				new Sphere(50, new Point(1100, -1150, -1000)).setEmission(new Color(102,0,102)).setMaterial(new Material().setKd(0.8).setKr(0.0005).setKs(0.7).setShininess(40)),

				//the paper
				new Triangle(new Point(-100, -35, -150), new Point(150, -150, -150), new Point(75, 75, -150)).setEmission(new Color(10,0,255)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300).setKr(0.5)), //the lower
				new Triangle( new Point(-600, -300, -600), new Point(-500, 400, -500), new Point(75, 75, -150)).setEmission(new Color(10,0,255)).setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(500).setKr(0.26)),//the lefter
				new Triangle( new Point(400, 100, 600), new Point(-500, 400, -500), new Point(75, 75, -150)).setEmission(new Color(10,0,255)).setMaterial(new Material().setKd(0.68).setKr(0.30).setKs(0.2).setShininess(300)),//the rightest
				//
				new Triangle(new Point(-100,-35,-150),new Point(150,-100,-100),new Point(100,100,100)).setEmission(new Color(102,0,102)),
				//the suns
				new Sphere(370, new Point(750, 730, 760))
				.setEmission(new Color(255,0,0)) 
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
		new Sphere(250, new Point(720, 700, 450)) //the inner sphere
				.setEmission(new Color(java.awt.Color.YELLOW)) 
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));

		
		scene.lights.add(new DirectionalLight(new Color(990, 869, 100), new Vector(1000, 850, 950)));
		scene.lights.add(new SpotLight(new Color(720, 400, 400), new Point(-200, -200, -200), new Vector(1, 10, -4)) 
				.setKl(0.00001).setKq(0.000005));
		scene.lights.add(new PointLight(new Color(500, 300, 0), new Point(700, 700, 950))
				.setKl(0.00001).setKq(0.000001));
		scene.lights.add(new PointLight(new Color(100, 300, 0), new Point(-1400, -1400, 1000))
				.setKl(0.00001).setKq(0.000001));
		camera.setImageWriter(new ImageWriter("lovely day", 500, 500))
		.setRayTracer(new RayTracerBasic(scene)) 
		.renderImage() 
		.writeToImage();
	}
	
	/**
	 * Produce a picture of a three spheres lighted by a spot light, the red one is a mirror and the smallest is transparency
	 *  
	 */
	@Test
	public void ourPicture() {
		Camera camera = new Camera(new Point(0, 0,0), new Vector(0, 0, -1), new Vector(0, -1, 0)) //
				.setViewPlaneSize(300, 300).setDistance(400);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new Double3( 0.15)));

		scene.geometries.add( //
				new Sphere(120,new Point(0,50,-800)) //the turquise sphere
				.setEmission(new Color(0,100,100)) //
				.setMaterial(new Material().setKd(0.6).setKs(0.9).setShininess(1000).setKt(0).setKr(1)),
				new Sphere(300,new Point(-210,110,-900)) //the large purple sphere
				.setEmission(new Color(30,0,50)) //
				.setMaterial(new Material().setKd(0).setKs(0.2).setShininess(1000).setKt(0.6).setKr(0.2)),
				new Sphere(80,new Point(300,50,-200)) //the blue sphere (not seen boldly)
				.setEmission(new Color(0,0,100)) //
				.setMaterial(new Material().setKd(0.6).setKs(0.9).setShininess(1000).setKt(0).setKr(0.2)),
				new Sphere(10,new Point(10, 30, -100)) //the red sphere
				.setEmission(new Color(java.awt.Color.yellow)) //
				.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0).setKr(0.8)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),//the triangle
				new Point(-1500, -1500, -2000))
				.setMaterial(new Material().setKd(0.25).setKs(0.9).setShininess(20).setKt(0).setKr(1))); 


		scene.lights.add(new SpotLight(new Color(200,200,200), new Point(1000,-550,1000), new Vector(0,-1,0)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new PointLight(new Color(300, 500, 500), new Point(-50, -50, 50))//
				.setKl(0.00001).setKq(0.000001));
		
		ImageWriter imageWriter = new ImageWriter("ourPicture", 600, 600);	
				camera.setImageWriter(imageWriter) 
				.setRayTracer(new RayTracerBasic(scene)).setNumOfRays(81)
				.renderImage()
				.writeToImage();
	}
}	
	
