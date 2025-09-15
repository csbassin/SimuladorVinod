package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JLabel;
import main.UnidadeControle;
import util.Conversoes;
import util.Montador;
import util.StaticObjects;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JSeparator;

public class MemoriaWindow{
	private String[] colunas = {"Endereço", "Valor Binário", "Valor Decimal", "Valor Hexadecimal"};
	private DefaultTableModel dtm = new DefaultTableModel(colunas, 0);
	private JTable tabelaMemoria;
	private final JLabel lblNewLabel = new JLabel("Memória Principal");
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	}
	
	public MemoriaWindow() {
		initialize();
	}
	
	private void initialize() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		lblNewLabel.setBounds(10, 10, 225, 38);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 57, 635, 2);
		frame.getContentPane().add(separator);
		
		tabelaMemoria = new JTable(dtm);
		String zeroBin = Conversoes.conversaoCompleta(0, 16);
		String zeroHex = Conversoes.binaryToHex(zeroBin);
		for(int i = 0; i<4096; i++) {
			String[] valueCols = {String.valueOf(i),
								String.valueOf(0),
								zeroBin,
								zeroHex};
			dtm.addRow(valueCols);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 612, 269);
		scrollPane.setViewportView(tabelaMemoria);
		frame.getContentPane().add(scrollPane);
	}
	
	public void update() {
		for(int i = 0; i<4096; i++) {
			int currentValue = Conversoes.binaryIntToDecimal(StaticObjects.getUc().memoriaPrincipal.get(i));
			if(currentValue != 0) {
				String binario = Conversoes.conversaoCompleta(currentValue, 16);
				dtm.setValueAt(binario, i, 1); // seta o binário
				dtm.setValueAt(currentValue, i, 2); // seta o decimal
				dtm.setValueAt(Conversoes.binaryToHex(binario), i, 3); // seta o hex
			}
			
		}
	}
}