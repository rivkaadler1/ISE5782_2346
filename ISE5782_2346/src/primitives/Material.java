package primitives;

/**
 * the material class PDS the material of a geometry
 * @author Rivki Adler & Sarit Silverstone
 *
 */
public class Material 
{


	
	public int nShininess=0;
	public Double3 KD=new Double3(0);
	public Double3 KS=new Double3(0);
//	public Object KT; 


	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setShininess(int nShininess) 
	{
		this.nShininess = nShininess;
		return this;
		
	}

	
	/**
	 * @param kD the kD to set
	 */
	public Material setKd(double kD) 
	{
		KD = new Double3(kD);
		return this;
	}

	/**
	 * @param kS the kS to set
	 */
	public Material setKs(double kS) 
	{
		KS = new Double3(kS);
		return this;
	}

	/**
	 * @param kD the kD to set
	 */
	public Material setKd(Double3 kD) 
	{
		KD = kD;
		return this;
	}

	/**
	 * @param kS the kS to set
	 */
	public Material setKs(Double3 kS) 
	{
		KS = kS;
		return this;
	}
}