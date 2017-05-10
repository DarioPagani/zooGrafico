package bestie;

import java.util.GregorianCalendar;

public class Mammifero extends Animale 
{
	private	int				mesiAllamento;

	public int getMesiAllamento() {
		return mesiAllamento;
	}

	public void setMesiAllamento(int mesiAllamento) {
		this.mesiAllamento = mesiAllamento;
	}

	public Mammifero(String targa, GregorianCalendar nascita, String specie, double valoreUSD, int mesiAllamento) {
		super(targa, nascita, specie, valoreUSD);
		this.mesiAllamento = mesiAllamento;
	}
	
	public Mammifero(final String parse) throws Exception
	{
		super(parse);
		try
		{
			String tmp0 = parse;
			
			for(int i = 0; i < 4; i++)
				tmp0 = tmp0.substring(tmp0.indexOf(";") + 1);
	
			tmp0 = tmp0.substring(0, tmp0.length() - 1);
			this.mesiAllamento = (new Integer(tmp0)).intValue();
		}
		catch(Exception e)
		{
			throw new ParafrasiEccezzione(e);
		}
	}
	
	public String toString()
	{
		return super.toString()  + this.mesiAllamento + ";";
	}
}
