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
	/**
	 * refractive/transparency index
	 */
	public Double3 KT=new Double3(0);
	/**
	 *reflection coefficient
	 */
	public Double3 KR=new Double3(0);

	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setShininess(int nShininess) 
	{
		this.nShininess = nShininess;
		return this;
		
	}

	
	/**
	 * setter to KD
	 * @param kD 
	 */
	public Material setKd(double kD) 
	{
		KD = new Double3(kD);
		return this;
	}

	/**
	 * setter to KS
	 * @param kS 
	 */
	public Material setKs(double kS) 
	{
		KS = new Double3(kS);
		return this;
	}
	
	/**
	 * setter to KR
	 * @param kR  
	 */
	public Material setKr(double kR) 
	{
		KR = new Double3(kR);
		return this;
	}

	/**
	 * setter to KT
	 * @param kT 
	 */
	public Material setKt(double kT) 
	{
		KT = new Double3(kT);
		return this;
	}

	/**
	 * setter to KD
	 * @param kD 
	 */
	public Material setKd(Double3 kD) 
	{
		KD = kD;
		return this;
	}

	/**
	 * setter to KS
	 * @param kS 
	 */
	public Material setKs(Double3 kS) 
	{
		KS = kS;
		return this;
	}
	
	/**
	 * setter to KR
	 * @param kR 
	 */
	public Material setKr(Double3 kR) 
	{
		KR = kR;
		return this;
	}

	/**
	 * setter to KT 
	 * @param kT 
	 */
	public Material setKt(Double3 kT) 
	{
		KT = kT;
		return this;
	}
}