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
	public void manyGeometries() 
	{
		Camera camera = new Camera(new Point(0, 100, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //vto,vup
				.setViewPlaneSize(2500, 2500).setDistance(10000); //height and width

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
		scene.setBackground(new Color(141,182,243));
		scene.geometries.add( 
				//six big triangles					
				new Triangle(new Point(-930, -1500, -1000), new Point(-1250, -804, -1000),new Point(-1500, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(-500, -1500, -1050), new Point(-750, -780, -1000),new Point(-1000, -1500, -2000)).setEmission(new Color(20, 190, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(0, -1500, -1000), new Point(-250, -800, -1000),new Point(-500, -1500, -2000)).setEmission(new Color(20, 185, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(500, -1500, -1000), new Point(250, -880, -1000),new Point(0, -1500, -2000)).setEmission(new Color(20, 195, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(1000, -1500, -1008), new Point(750, -842, -1000),new Point(500, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(1400, -1500, -1095), new Point(1250, -839, -1000),new Point(1050, -1500, -2000)).setEmission(new Color(20, 180, 20)).setMaterial(new Material().setKr(0.36)),
				
				//5 smaller triangles
				new Triangle(new Point(-750, -1500, -1000), new Point(-1000, -960, -1000),new Point(-1250, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(-250, -1500, -1000), new Point(-500, -895, -1000),new Point(-700, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(250, -1500, -1000), new Point(0, -950, -1000),new Point(-250, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(750, -1500, -1000), new Point(500, -900, -1000),new Point(250, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
				new Triangle(new Point(1250, -1500, -1000), new Point(1000, -950, -1000),new Point(750, -1500, -2000)).setEmission(new Color(20, 200, 20)).setMaterial(new Material().setKr(0.36)),
                //the red points on the grass
				new Sphere(50, new Point(-1000, -960, -1000)).setMaterial(new Material().setKd(0.25).setKr(0.025).setKs(0.95).setShininess(25)).setEmission(new Color(200,12,59)),
				new Sphere(50, new Point(250, -900, -1000)).setEmission(new Color(200,12,59)).setMaterial(new Material().setKd(0.30).setKr(0.1).setKs(0.40).setShininess(5)),
				new Sphere(50, new Point(-120, -1180, -1000)).setEmission(new Color(200,12,59)).setMaterial(new Material().setKd(0.1).setKr(0.001).setKs(0.54).setShininess(12)),
				new Sphere(50, new Point(1100, -1150, -1000)).setEmission(new Color(200,12,59)).setMaterial(new Material().setKd(0.8).setKr(0.0005).setKs(0.7).setShininess(40)),

				//the paper
				new Triangle(new Point(-100, -35, -150), new Point(150, -150, -150), new Point(75, 75, -150)).setEmission(new Color(10,0,255)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300).setKr(0.5)), //the lower
				new Triangle( new Point(-600, -300, -600), new Point(-500, 400, -500), new Point(75, 75, -150)).setEmission(new Color(10,0,255)).setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(500).setKr(0.26)),//the lefter
				new Triangle( new Point(400, 100, 600), new Point(-500, 400, -500), new Point(75, 75, -150)).setEmission(new Color(10,0,255)).setMaterial(new Material().setKd(0.68).setKr(0.30).setKs(0.2).setShininess(300)),//the rightest
				new Sphere(370, new Point(750, 730, 760)) //the sun
				.setEmission(new Color(java.awt.Color.RED)) 
				.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
		new Sphere(250, new Point(720, 700, 450)) //the inner sphere
				.setEmission(new Color(java.awt.Color.YELLOW)) 
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));

		
		scene.lights.add(new DirectionalLight(new Color(990, 869, 100), new Vector(1000, 850, 950)));
		scene.lights.add(new SpotLight(new Color(720, 400, 400), new Point(-100, -100, -150), new Vector(1, 10, -4)) 
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
}	
	