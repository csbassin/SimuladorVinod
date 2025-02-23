package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.UnidadeControle;
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
		frame.setBounds(100, 100, 640, 480);
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
		txtBinarioReg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				spnDecimalReg.setValue(Integer.parseInt(Conversoes.bitArrayToC2(Conversoes.stringToIntegerArray(txtBinarioReg.getText()))));
			}
		});
		txtBinarioReg.setBounds(10, 92, 281, 19);
		panelRegistradores.add(txtBinarioReg);
		txtBinarioReg.setColumns(16);
		
		spnDecimalReg = new JSpinner();
		spnDecimalReg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				txtBinarioReg.setText(Conversoes.conversaoCompleta((int) spnDecimalReg.getValue(), 16));
			}
		});
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
	}
}
