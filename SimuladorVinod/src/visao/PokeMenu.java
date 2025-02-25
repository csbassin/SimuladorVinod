package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.UnidadeControle;
import modelo.MemoriaPrincipal;
import modelo.registradores.Registrador;
import util.Conversoes;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PokeMenu {

	private JFrame frame;
	private JPanel panelRegistradores;
	private JComboBox<String> cmbRegistrador;
	private JTextField txtBinarioReg;
	private JSpinner spnDecimalReg;
	private JButton btnAplicarReg;
	private UnidadeControle uc;
	private Registrador selectedReg;
	private JTextField txtBinarioMem;
	private JPanel panelRegistradores_1;
	private JLabel lblEndereo;
	private JSpinner spnDecimalMem;
	private JTextField spnEndereco;

	public PokeMenu(UnidadeControle uc) {
		this.uc = uc;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				uc.setPause(true);
			}
			@Override
			public void windowClosed(WindowEvent e) {
				uc.setPause(false);
			}
		});
		frame.setBounds(100, 100, 337, 374);
		frame.setTitle("Alterar valores da simulação");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panelRegistradores = new JPanel();
		panelRegistradores.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Registradores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRegistradores.setBounds(10, 10, 301, 153);
		frame.getContentPane().add(panelRegistradores);
		panelRegistradores.setLayout(null);
		
		txtBinarioReg = new JTextField();
		txtBinarioReg.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				javali();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				javali();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				javali();
				
			}
			
			private void javali() {
				try {
					spnDecimalReg.setValue(Integer.parseInt(Conversoes.bitArrayToC2(Conversoes.stringToIntegerArray(txtBinarioReg.getText()))));
				}catch(Exception e) {
					
				}
			}
			
		});
		
		txtBinarioReg.setBounds(10, 92, 281, 19);
		panelRegistradores.add(txtBinarioReg);
		txtBinarioReg.setColumns(16);
		
		spnDecimalReg = new JSpinner();
		spnDecimalReg.setEnabled(false);
		spnDecimalReg.setBounds(174, 47, 117, 20);
		panelRegistradores.add(spnDecimalReg);
		
		cmbRegistrador = new JComboBox<>();
		cmbRegistrador.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					selectedReg = uc.getGr().get(cmbRegistrador.getSelectedIndex());
					txtBinarioReg.setText(Conversoes.intArrayToString(selectedReg.getRegistrador()));
					spnDecimalReg.setValue(Integer.parseInt(Conversoes.bitArrayToC2(selectedReg.getRegistrador())));
				}
			}
		});
		cmbRegistrador.setBounds(10, 46, 154, 21);
		cmbRegistrador.addItem("PC");
		cmbRegistrador.addItem("AC");
		cmbRegistrador.addItem("SP");
		cmbRegistrador.addItem("IR");
		cmbRegistrador.addItem("TIR");
		cmbRegistrador.addItem("A");
		cmbRegistrador.addItem("B");
		cmbRegistrador.addItem("C");
		cmbRegistrador.addItem("D");
		cmbRegistrador.addItem("E");
		cmbRegistrador.addItem("F");

		panelRegistradores.add(cmbRegistrador);
		
		
		
		JLabel lblReg = new JLabel("Registrador:");
		lblReg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblReg.setBounds(10, 24, 95, 21);
		panelRegistradores.add(lblReg);
		
		JLabel lblValordecimal = new JLabel("Valor (decimal):");
		lblValordecimal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblValordecimal.setBounds(174, 24, 117, 21);
		panelRegistradores.add(lblValordecimal);
		
		JLabel lblValorbinrio = new JLabel("Valor (binário):");
		lblValorbinrio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblValorbinrio.setBounds(10, 68, 117, 21);
		panelRegistradores.add(lblValorbinrio);
		
		btnAplicarReg = new JButton("Aplicar");
		btnAplicarReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedReg.set(Conversoes.stringToIntegerArray(txtBinarioReg.getText()));
			}
		});
		btnAplicarReg.setBounds(206, 121, 85, 21);
		panelRegistradores.add(btnAplicarReg);
		
		panelRegistradores_1 = new JPanel();
		panelRegistradores_1.setLayout(null);
		panelRegistradores_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Mem\u00F3ria", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelRegistradores_1.setBounds(10, 173, 301, 153);
		frame.getContentPane().add(panelRegistradores_1);
		
		txtBinarioMem = new JTextField();
		txtBinarioMem.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				javali();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				javali();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				javali();
				
			}
			
			private void javali() {
				try {
					spnDecimalMem.setValue(Conversoes.binaryIntToDecimal(Conversoes.stringToIntegerArray(txtBinarioMem.getText())));
				}catch(Exception e) {
					
				}
				
			}
			
		});
		txtBinarioMem.setColumns(16);
		txtBinarioMem.setBounds(10, 92, 281, 19);
		panelRegistradores_1.add(txtBinarioMem);
		
		spnDecimalMem = new JSpinner();
		spnDecimalMem.setEnabled(false);
		spnDecimalMem.setBounds(174, 47, 117, 20);
		panelRegistradores_1.add(spnDecimalMem);
		
		lblEndereo = new JLabel("Endereço:");
		lblEndereo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEndereo.setBounds(10, 24, 95, 21);
		panelRegistradores_1.add(lblEndereo);
		
		JLabel lblValordecimal_1 = new JLabel("Valor (decimal):");
		lblValordecimal_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblValordecimal_1.setBounds(174, 24, 117, 21);
		panelRegistradores_1.add(lblValordecimal_1);
		
		JLabel lblValorbinrio_1 = new JLabel("Valor (binário):");
		lblValorbinrio_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblValorbinrio_1.setBounds(10, 68, 117, 21);
		panelRegistradores_1.add(lblValorbinrio_1);
		
		JButton btnAplicarReg_1 = new JButton("Aplicar");
		btnAplicarReg_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemoriaPrincipal mem = uc.getMemoriaPrincipal();
				int enderecoSelecionado = Integer.parseInt(spnEndereco.getText());
				mem.mem.set(enderecoSelecionado, Conversoes.stringToIntegerArray(txtBinarioMem.getText()));
			}
		});
		btnAplicarReg_1.setBounds(206, 121, 85, 21);
		panelRegistradores_1.add(btnAplicarReg_1);
		
		spnEndereco = new JTextField();
		spnEndereco.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				javali();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				javali();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				javali();
				
			}
			
			private void javali() {
				try{
					Integer[] memValue = uc.memoriaPrincipal.get(Integer.parseInt(spnEndereco.getText()));
					spnDecimalMem.setValue(Conversoes.binaryIntToDecimal(memValue));
					txtBinarioMem.setText(Conversoes.integerArrayToString(memValue));
				}catch(Exception e) {
					
				}
				
				
			}
		});
		spnEndereco.setBounds(10, 47, 154, 20);
		panelRegistradores_1.add(spnEndereco);
	}
}
