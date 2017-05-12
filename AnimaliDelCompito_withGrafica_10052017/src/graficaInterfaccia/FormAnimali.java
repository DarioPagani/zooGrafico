package graficaInterfaccia;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class FormAnimali extends JFrame implements WindowListener
{

	/**
	 * Roba di Eclissi
	 */
	private static final long serialVersionUID = 4327599785454763120L;
	
	private static final String VALOREPROFONDITA [] = {"100", "200", "300", "400", "500"};
	private static final String VALOREALLATTAMENTO[] = {"1", "2", "3", "4", "5"};
	
	private JPanel contentPane;
	private JTextField textName;
	private JFormattedTextField textBorn;
	private JTextField textSpecie;
	private JFormattedTextField textStima;
	private JRadioButton rdbtnPesce;
	private JRadioButton rdbtnMammifero;
	private JLabel lblProfondita;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel_2;
	private JButton invia;
	private JButton btnCancella;
	private JScrollPane scrollPane;
	@SuppressWarnings("rawtypes")
	private JList listValore;
	
	// Roba seria
	private GestioneAnimali superiore;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FormAnimali(GestioneAnimali superiore)
	{
		super("Inserimento di un Animale");
		//setType(Type.UTILITY);
		
		//
		this.superiore = superiore;
		
		// Costruzione della finestra
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 3, 0, 0));
		panel.add(new JLabel("TIPO ANIMALE"));
		
		rdbtnPesce = new JRadioButton("Pesce");
		rdbtnPesce.setSelected(true);
		rdbtnPesce.addActionListener(new Cambia(this));
		buttonGroup.add(rdbtnPesce);
		panel.add(rdbtnPesce);
		
		rdbtnMammifero = new JRadioButton("Mammifero");
		rdbtnMammifero.addActionListener(new Cambia(this));
		buttonGroup.add(rdbtnMammifero);
		panel.add(rdbtnMammifero);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel lblIdentificatore = new JLabel("Identificatore");
		panel_1.add(lblIdentificatore);
		
		textName = new JTextField();
		panel_1.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Data di nascita");
		panel_1.add(lblNewLabel);
		
		textBorn = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		panel_1.add(textBorn);
		textBorn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Specie");
		panel_1.add(lblNewLabel_1);
		
		textSpecie = new JTextField();
		panel_1.add(textSpecie);
		textSpecie.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Valore Stimato");
		panel_1.add(lblNewLabel_2);
		
		NumberFormat xy = NumberFormat.getNumberInstance();
		xy.setMaximumFractionDigits(2);
		textStima = new JFormattedTextField(xy);
		panel_1.add(textStima);
		textStima.setColumns(10);
		
		lblProfondita = new JLabel("Profondita");
		panel_1.add(lblProfondita);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);
		
		listValore = new JList(FormAnimali.VALOREPROFONDITA);
		listValore.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listValore.setSelectedIndex(0);
		scrollPane.setViewportView(listValore);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		invia = new JButton("Invia");
		panel_2.add(invia);
		
		invia.addActionListener(new Invia());
		
		btnCancella = new JButton("cancella");
		panel_2.add(btnCancella);
		
		super.addWindowListener(this);
		super.setVisible(true);
	}
	
	private class Cambia implements ActionListener {

		private FormAnimali select;
		
		public Cambia(final FormAnimali select) {
			this.select = select;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(this.select.rdbtnMammifero.isSelected()) 
			{
				this.select.lblProfondita.setText("Mesi di allattamento");
				this.select.listValore.setListData(FormAnimali.VALOREALLATTAMENTO);
			}
			else 
			{
				this.select.lblProfondita.setText("Profondita");
				this.select.listValore.setListData(FormAnimali.VALOREPROFONDITA);
			}
			this.select.listValore.setSelectedIndex(0);
		}
	}

	private class Invia implements ActionListener
	{
		private FormAnimali genitrice = FormAnimali.this;
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// Aggiungo le informazioni
			String toAdd = new String();
			
			toAdd+=Boolean.toString(this.genitrice.rdbtnMammifero.isSelected())+';';
			toAdd+=this.genitrice.textName.getText()+';';
			toAdd+=this.genitrice.textBorn.getText()+';';
			toAdd+=this.genitrice.textSpecie.getText()+';';
			toAdd+=this.genitrice.textStima.getText()+';';
			toAdd+=this.genitrice.listValore.getSelectedValue().toString()+';';
			
			System.out.println(toAdd);
			
			try
			{
				FormAnimali.this.superiore.aggiungiAnimale(toAdd);
			}
			catch (Exception e2)
			{
				JOptionPane.showMessageDialog(FormAnimali.this, "Qualcosa è andato storto:\n"+e2.toString(), "Errore di parafrasi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.genitrice.windowClosing(null);
		}
	}

	@Override
	public void windowActivated(WindowEvent e){;}

	@Override
	public void windowClosed(WindowEvent e){;}

	@Override
	public void windowClosing(WindowEvent e)
	{
		FormAnimali.this.superiore.updateList();
		try
		{
			this.dispose();
			this.superiore.setVisible(true);
		}
		catch (Throwable e1)
		{
			System.err.println("Sembra sia impossibile chiudere la finestra...");
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e){;}

	@Override
	public void windowDeiconified(WindowEvent e){;}

	@Override
	public void windowIconified(WindowEvent e){;}

	@Override
	public void windowOpened(WindowEvent e){;}
}
