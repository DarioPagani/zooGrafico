package zoo;

import bestie.Animale;

public class DuplicatoEccezzione extends Exception 
{
	private static final long serialVersionUID = 1L;

	public DuplicatoEccezzione(final Animale x) 
	{
		super("L'animale \"" + x.toString() + "\"esisteva già ma con altri valori!\nINTERROTTO");
	}

}
