package zoo;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

import bestie.Animale;
import bestie.Mammifero;
import bestie.Pesce;

public class Zoo 
{
	private	ArrayList<Animale>			animali		= null;
	private	long						numeroPesci	= 0;
	private	final	long				capacitaPesci;
	private boolean						logg = true;
	
	public Zoo(long numeroPesci)
	{
		if(numeroPesci <= 0)
			numeroPesci = 0;
		
		this.animali = new ArrayList<Animale>();
		this.capacitaPesci = numeroPesci;
	}
	
	public Zoo(long numeroPesci, boolean loggin)
	{
		this(numeroPesci);
		this.logg = loggin;
	}

	
	public void aggiungiAnimale(final Animale toAdd) throws Exception
	{
		if(toAdd.getClass().getSimpleName().equals(Pesce.class.getSimpleName()))
			if(this.numeroPesci >= this.capacitaPesci)
				throw new PesciOutOfMemory();
		
		// Controllo se ci sono altri animali con la stessa targa
		int trovato = -1;
		for(int i = 0, l = this.animali.size(); i < l && trovato == -1; i++)
		{
			if(this.animali.get(i).getTarga().equals(toAdd.getTarga()))
				trovato = i;
		}
		
		// Ci sono dublicati
		if(trovato != -1)
		{
			// Se l'aggiunta è uguale faccio finta di nulla
			if(this.animali.get(trovato).toString().equals(toAdd.toString()))
			{
				if(this.logg)
					System.err.println("LOG: Skipping \"" + toAdd.getTarga() + "\" same plate and same values!");
			}
			else
				throw new DuplicatoEccezzione(toAdd);
				return;
		}
		else
		{
			this.animali.add(toAdd);
			if(toAdd.getClass().getSimpleName().equals(Pesce.class.getSimpleName()))
				this.numeroPesci++;
		}
	}

	public void aggiungiAnimale(final String toAdd) throws Exception
	{
		String tmp0 = new String();
		final int l = toAdd.length();
		int i = 0;
		
		// Ottengo l'indetificatore
		for(i = 0; i < l && (toAdd.charAt(i) != ';'); i++)
			tmp0 = tmp0.concat(String.valueOf(toAdd.charAt(i)));
		
		String tmp1 = toAdd.substring(i + 1);
		
		if((new Boolean(tmp0)).booleanValue())
			// Se era true debbo creare il Pesce
			this.aggiungiAnimale((Animale)(new Pesce(tmp1)));
		else
			this.aggiungiAnimale((Animale)(new Mammifero(tmp1)));
	}
	
	public void parse(final Scanner cin) throws Exception
	{
		String tmp0 = "";
		
		while(!tmp0.equals("HALT"))
		{
			tmp0 = cin.nextLine();
			if(!tmp0.equals("HALT"))
				this.aggiungiAnimale(tmp0);
		}
	}
	
	// Metodi di output
	public String toString()
	{
		String output = new String();
		for(int i = 0, l = this.animali.size(); i < l; i++)
			output = output.concat("" + (this.animali.get(i).getClass().getSimpleName().equals(Pesce.class.getSimpleName())) + ';' + this.animali.get(i).toString() + "\n");
		return output + "HALT";
	}
	
	public String[] toArrayString()
	{
		String output[] = new String[this.animali.size()];
		
		for(int i = 0, l = output.length; i < l; i++)
			output[i] = ("" + (this.animali.get(i).getClass().getSimpleName().equals(Pesce.class.getSimpleName())) + ';' + this.animali.get(i).toString() + "\n");
		
		return output;
	}
	
	public void stampa(final PrintStream cout)
	{
		cout.println(this.toString());
	}
	
	// Alto
	public void cancella(final String targa)
	{
		Predicate<Animale> rimuoviTaleTarga = x->x.getTarga().equals(targa);
		this.animali.removeIf(rimuoviTaleTarga);
	}
	
	public String cerca()
	{
		// Giammai si inizi la ricerca se l'arraylist è vuoto
		if(this.animali.size() == 0)
			return null;
		
		// Cerco il più caro
		Animale massimo = this.animali.get(0);
		for(int i = 1, l = this.animali.size();i < l; i++)
			if(this.animali.get(i).getValoreUSD() > massimo.getValoreUSD())
				massimo = this.animali.get(i);
		
		return massimo.getTarga();
	}
}
