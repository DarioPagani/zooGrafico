package bestie;

public class ParafrasiEccezzione extends Exception 
{
	private static final long serialVersionUID = 1L;

	public ParafrasiEccezzione(final Exception e) 
	{
		super("Qualcosa � andato storto:\n" + e.toString() + "\nL'ogetto non � stato creato!");
	}

}
