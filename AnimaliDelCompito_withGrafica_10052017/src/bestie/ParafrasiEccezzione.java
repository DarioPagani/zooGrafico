package bestie;

public class ParafrasiEccezzione extends Exception 
{
	private static final long serialVersionUID = 1L;

	public ParafrasiEccezzione(final Exception e) 
	{
		super("Qualcosa è andato storto:\n" + e.toString() + "\nL'ogetto non è stato creato!");
	}

}
