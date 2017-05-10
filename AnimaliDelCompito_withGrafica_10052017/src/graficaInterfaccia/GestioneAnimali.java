package graficaInterfaccia;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JList;

import zoo.Zoo;

public class GestioneAnimali extends JFrame 
{
	private static final long serialVersionUID = 1253155082617631450L;
	
	// Fields
	private Zoo parco;
	
	
	public GestioneAnimali(Zoo parco) throws HeadlessException 
	{
		super("Gestione Animali");
		this.parco = parco;
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container panello = super.getContentPane();
		panello.setLayout(new BorderLayout());
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList animali = new JList(parco.toArrayString());
		
		panello.add(animali, BorderLayout.CENTER);
		
		super.setVisible(true);
		super.pack();
	}
	
	
	
	// Main
	public static void main(String[] args) throws Exception 
	{
		Zoo parco = new Zoo(40);
		parco.parse(new Scanner(new File("files/sampleInput")));
		new GestioneAnimali(parco);

	}






}
