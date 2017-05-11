package graficaInterfaccia;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import zoo.Zoo;

public class GestioneAnimali extends JFrame 
{
	private static final long serialVersionUID = 1253155082617631450L;
	
	// Fields
	private Zoo parco;
	@SuppressWarnings("rawtypes")
	private JList animali;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GestioneAnimali(Zoo parco_) throws HeadlessException 
	{
		super("Gestione Animali");
		this.parco = parco_;
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container panello = super.getContentPane();
		panello.setLayout(new BorderLayout());
		
		this.animali = new JList(this.parco.toArrayString());
		JScrollPane animaliScorro = new JScrollPane(this.animali);
		animaliScorro.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		animaliScorro.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panello.add(animaliScorro, BorderLayout.CENTER);
		
		JPanel controlli = new JPanel(new GridLayout(1, 2));
		panello.add(controlli, BorderLayout.SOUTH);
		
		JButton invia = new JButton("Aggiungi");
		invia.addActionListener(new Aggiungi());
		
		JButton elimina = new JButton("Elimina");
		
		controlli.add(invia);
		controlli.add(elimina);
		super.setVisible(true);
		super.pack();
	}
	// Funzioni
	protected void aggiungiAnimale(String toAdd) throws Exception
	{
		this.parco.aggiungiAnimale(toAdd);
	}
	
	// Gestione degli eventi
	@SuppressWarnings("unchecked")
	protected void updateList()
	{
		this.animali.setListData(this.parco.toArrayString());
	}
	
	private class Aggiungi implements ActionListener
	{		
		@Override
		public void actionPerformed(ActionEvent e)
		{		
			new FormAnimali(GestioneAnimali.this);
			GestioneAnimali.this.setVisible(false);
		}
		
	}
	
	
	// Main
	public static void main(String[] args) throws Exception 
	{
		Zoo parco = new Zoo(40);
		parco.parse(new Scanner(new File("files/sampleInput")));
		new GestioneAnimali(parco);

	}
}
