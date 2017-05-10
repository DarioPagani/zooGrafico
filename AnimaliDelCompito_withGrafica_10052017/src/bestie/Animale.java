package bestie;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Animale 
{
	private	String					targa		= null;
	private	GregorianCalendar		nascita		= null;
	private	String					specie		= null;
	private	double					valoreUSD;

	public Animale(String targa, GregorianCalendar nascita, String specie, double valoreUSD) 
	{
		this.targa = targa;
		this.nascita = nascita;
		this.specie = specie;
		this.valoreUSD = valoreUSD;
	}
	
	public Animale(final String parse) throws Exception
	{
		// Variabili
		String tmp0 = new String();
		final int l = parse.length();
		int i = 0;
		
		try
		{
			// Ottengo l'indetificatore
			for(i = 0; i < l && (parse.charAt(i) != ';'); i++)
				tmp0 = tmp0.concat(String.valueOf(parse.charAt(i)));
			
			this.targa = tmp0;
			
			// Ottengo la data di nascita
			for(tmp0 = "", i++; i < l && (parse.charAt(i) != ';'); i++)
				tmp0 = tmp0.concat(String.valueOf(parse.charAt(i)));
			
			this.nascita = new GregorianCalendar();
			this.nascita.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(tmp0));
			
			// Ottengo la specie
			for(tmp0 = "", i++; i < l && (parse.charAt(i) != ';'); i++)
				tmp0 = tmp0.concat(String.valueOf(parse.charAt(i)));
			
			this.specie = tmp0;
			
			// Ottengo il valore
			for(tmp0 = "", i++; i < l && (parse.charAt(i) != ';'); i++)
				tmp0 = tmp0.concat(String.valueOf(parse.charAt(i)));
			
			this.valoreUSD = (new Double(tmp0)).doubleValue();
		}
		catch(Exception e)
		{
			throw new ParafrasiEccezzione(e);
		}
	}
	public double getValoreUSD() {return valoreUSD;}

	public void setValoreUSD(double valoreUSD) {this.valoreUSD = valoreUSD;}

	public String getTarga() {return targa;}

	public GregorianCalendar getNascita() {return nascita;}

	public String getSpecie() { return specie; }
	
	public String toString()
	{
		return this.targa + ';' + (new SimpleDateFormat("dd/MM/yyyy")).format(this.nascita.getTime()) + ';' + this.specie + ';' + this.valoreUSD + ";";
	}
}
