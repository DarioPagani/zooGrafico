package graficaInterfaccia;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
		elimina.addActionListener(new Elimina());
		
		controlli.add(invia);
		controlli.add(elimina);
		
		JPanel menu = new JPanel(new GridLayout(1, 3));
		panello.add(menu, BorderLayout.NORTH);
		
		JButton salva = new JButton("Salva");
		menu.add(salva);
		salva.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Variabili
				String file = null;
				File out = null;
				
				file = JOptionPane.showInputDialog(GestioneAnimali.this, "Inserire percoso del documento da salvare\nEsempio: files/sampleOutput");
				if(file == null || file.equals(""))
					return;
				
				out = new File(file);
				try
				{
					GestioneAnimali.this.parco.stampa(new PrintStream(out));
				}
				catch (Exception e3)
				{
					JOptionPane.showMessageDialog(GestioneAnimali.this, "Qualcosa è andato storto:\n"+e3.toString(), "Errore",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		
		JButton carica = new JButton("Carica");
		menu.add(carica);
		carica.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Variabili
				String file = null;
				boolean esiste = false;
				
				file = JOptionPane.showInputDialog(GestioneAnimali.this, "Inserire percoso del documento da caricare\nDefualt: files/sampleInput");
				if(file == null || file.equals(""))
					file = new String("files/sampleInput");
				
				while(!esiste)
					try
					{
						GestioneAnimali.this.parco.parse(new Scanner(new File(file)));
						esiste = true;
					}
					catch (FileNotFoundException e1)
					{
						JOptionPane.showMessageDialog(GestioneAnimali.this, "Il file non esiste!\n"+e1.toString(), "File non esistente",JOptionPane.ERROR_MESSAGE);
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog(GestioneAnimali.this, "Errore di parafrasi:\n"+e1.toString(), "Errore",JOptionPane.ERROR_MESSAGE);
						esiste = true;
					}
				
				GestioneAnimali.this.updateList();
			}
		});
		
		JButton svuota = new JButton("Più caro");
		svuota.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(GestioneAnimali.this, "L'animale più caro è:\n"+GestioneAnimali.this.parco.cerca(),"Informazioni" ,JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		menu.add(svuota);
		
		super.setVisible(true);
		super.pack();
		super.setMinimumSize(new Dimension(400, 200));
		super.setLocationRelativeTo(null);
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
			GestioneAnimali.this.setEnabled(false);
		}
	}
	
	private class Elimina implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(GestioneAnimali.this, "Non sono ancora programmato per questo...", "Muble", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	// Main
	public static void main(String[] args) throws Exception 
	{
		Zoo parco = new Zoo(40);
		//parco.parse(new Scanner(new File("files/sampleInput")));
		new GestioneAnimali(parco);

	}
}
