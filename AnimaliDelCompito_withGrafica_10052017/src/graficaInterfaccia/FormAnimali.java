package graficaInterfaccia;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import zoo.Zoo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class FormAnimali extends JFrame
{

	/**
	 * Roba di Eclissi
	 */
	private static final long serialVersionUID = 4327599785454763120L;
	
	private static final String VALOREPROFONDITA [] = {"100", "200", "300", "400", "500"};
	private static final String VALOREALLATTAMENTO[] = {"1", "2", "3", "4", "5"};
	
	private JPanel contentPane;
	private JTextField textField;
	private JFormattedTextField textBorn;
	private JTextField textField_2;
	private JTextField textField_3;
	private JRadioButton rdbtnPesce;
	private JRadioButton rdbtnMammifero;
	private JLabel lblProfondita;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel_2;
	private JButton invia;
	private JButton btnCancella;
	private JScrollPane scrollPane;
	@SuppressWarnings("rawtypes")
	private JList list;
	
	// Roba seria
	private Zoo parco;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FormAnimali(Zoo parco)
	{
		super("Inserimento Bestia");
		
		//
		this.parco = parco;
		
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
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Data di nascita");
		panel_1.add(lblNewLabel);
		
		textBorn = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		panel_1.add(textBorn);
		textBorn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Specie");
		panel_1.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Valore Stimato");
		panel_1.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		lblProfondita = new JLabel("Profondita");
		panel_1.add(lblProfondita);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);
		
		list = new JList(FormAnimali.VALOREPROFONDITA);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		invia = new JButton("Invia");
		panel_2.add(invia);
		
		btnCancella = new JButton("cancella");
		panel_2.add(btnCancella);
		
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
				this.select.list.setListData(FormAnimali.VALOREALLATTAMENTO);
			}
			else 
			{
				this.select.lblProfondita.setText("Profondita");
				this.select.list.setListData(FormAnimali.VALOREPROFONDITA);
			}
		}
	}

	private class Invia implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{			
		}
		
	}
}
