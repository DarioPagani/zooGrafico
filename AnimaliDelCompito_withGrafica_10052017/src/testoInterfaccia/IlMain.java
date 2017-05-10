package testoInterfaccia;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import bestie.ParafrasiEccezzione;
import zoo.DuplicatoEccezzione;
import zoo.PesciOutOfMemory;
import zoo.Zoo;

public class IlMain 
{
	final static String out = "Finita la memoria. Nulla da fare. Uscita...\n";
	
	public static void main(String[] args) throws Exception 
	{
		// Variabili
		String tmp0;
		int		menu = -1;
		Zoo parco = null;
		Scanner cin = new Scanner(System.in);
		
		// Input lunghezza
		System.out.print("Inserire numero di pesci: ");
		parco = new Zoo((new Long(cin.nextLine())).longValue(), false);
		
		while(menu != 0)
		{
			System.err.flush();
			System.out.flush();
			System.out.print("\n" + 
					"1. Carica animali da un documento\n" + 
					"2. Stampa animali su un documento\n" +
					"3. Stampa animali sulla console\n" +
					"4. Rimuovi un animale dalla targa\n" +
					"5. Mostra targa animale più caro\n" +
					"0. Esci\n" +
					": ");
			try
			{				
				menu = new Integer(cin.nextLine()).intValue();
				switch(menu)
				{
				case 0:
					System.out.println("Uscita...");
					break;
				case 1:
					System.out.println("Inserire percorso del documento (Lasciare vuoto per \"./files/sampleInput\": ");
					tmp0 = cin.nextLine();
					if(tmp0.equals(""))
					{
						cin.reset();
						tmp0 = "./files/sampleInput";
					}
					parco.parse(new Scanner(new File(tmp0)));
					break;
				case 2:
					System.out.println("Inserire percorso del documento (Lasciare vuoto per \"./files/sampleOutput\": ");
					tmp0 = cin.nextLine();
					if(tmp0.equals(""))
					{
						cin.reset();
						tmp0 = "./files/sampleOutput";
					}
					parco.stampa(new PrintStream(new File(tmp0)));
					break;
				case 3:
					parco.stampa(System.out);
					break;
				case 4:
					System.out.println("Inserire targa animale: ");
					tmp0 = cin.nextLine();
					parco.cancella(tmp0);
					break;
				case 5:
					tmp0 = parco.cerca();
					if(tmp0 == null)
						System.out.println("Lista vuota");
					else
						System.out.println("L'animale più caro è " + tmp0);
					break;
				default:
					System.err.println("Opzione non valida!");
					break;
				}
			}
			catch(NumberFormatException e)
			{
				System.err.println("Non era cosa giusta!");
			}
			catch(PesciOutOfMemory e)
			{
				System.err.println("Nessun pesce è più amesso, Interruzione degli input in corso...");
			}
			catch(ParafrasiEccezzione e)
			{
				System.err.println(e.toString()+ "\nInterruzione degli input in corso...");
			}
			catch(DuplicatoEccezzione e)
			{
				System.err.println(e.toString() + "\nInterruzione degli input in corso...");
			}
			catch(OutOfMemoryError e)
			{
				System.err.print(out);
				System.exit(2);
			}
			catch(Exception e)
			{
				long time = System.currentTimeMillis();
				System.err.println("Qualcosa è andato storto! Le informazioni sull'errore:\n" + e.toString() + "\nUscita... tutta la memoria sarà esportata in \"./files/crash" + time + "\"");
				File bak = new File("./files/crash" + time);
				bak.createNewFile();
				PrintStream cout = new PrintStream(bak);
				parco.stampa(cout);
				cout.close();
				System.exit(1);
			}
			System.err.flush();
			System.out.flush();
		}
		cin.close();
	}
}
