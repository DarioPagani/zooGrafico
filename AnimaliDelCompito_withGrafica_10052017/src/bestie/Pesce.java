package bestie;

import java.util.GregorianCalendar;

public class Pesce extends Animale
{
	private	float			profonditaMassima;
	public	static	final	long	annoInMillis		= 365l * 24l * 60l * 60l * 60l;

	public Pesce(String targa, GregorianCalendar nascita, String specie, double valoreUSD, float profonditaMassima)
	{
		super(targa, nascita, specie, valoreUSD);
		this.profonditaMassima = profonditaMassima;
	}
	
	public Pesce(String parse) throws Exception
	{
		super(parse);
		try
		{
			String tmp0 = parse;
			
			for(int i = 0; i < 4; i++)
				tmp0 = tmp0.substring(tmp0.indexOf(";") + 1);
			
			tmp0 = tmp0.substring(0, tmp0.length() - 1);
			this.profonditaMassima = (new Float(tmp0)).floatValue();
		}
		catch(Exception e)
		{
			throw new ParafrasiEccezzione(e);
		}
	}
	
	public String toString()
	{
		return super.toString() + this.profonditaMassima + ";";
	}
	
	public double getValoreUSD()
	{
		if((System.currentTimeMillis() - super.getNascita().getTimeInMillis()) > Pesce.annoInMillis*10l)
			return super.getValoreUSD() - (super.getValoreUSD() / 10d);
		
		return super.getValoreUSD();
	}
}
