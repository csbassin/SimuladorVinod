package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JLabel;
import main.UnidadeControle;
import modelo.MemoriaPrincipal;
import util.Conversoes;
import util.GetRegistrador;
import util.Montador;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SpringLayout;
import javax.swing.JSeparator;

public class ProcessadorWindow{
	
	static class CustomTableModel extends DefaultTableModel{
	
		public CustomTableModel(String[] colunas, int i) {
			super(colunas, i);
		}

		public void paintRowsAfter(int row) {
			fireTableRowsUpdated(row, 4095);
		}
		public void paintRow(int row) {
			fireTableRowsUpdated(row, row);
		}
		public void resetBefore(int row) {
			if(row>0) {
				fireTableRowsUpdated(row-1, row);
			}
		}
		
		public Color getColor(int row) {
			return null;
		}
	}
	
	static class CustomTableCellRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        CustomTableModel model = (CustomTableModel) table.getModel();
	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        Color bg = model.getColor(row);
	        c.setBackground(bg);
	        if(bg.equals(Color.BLUE)||bg.equals(Color.GRAY)) {
	        	c.setForeground(Color.WHITE);
	        }else {
	        	c.setForeground(Color.BLACK);
	        }
	        return c;
	    }
	}
	
	private String[] colunas = {"Registrador", "Valor"};
	private CustomTableModel dtm = new CustomTableModel(colunas, 0);
	private final JLabel lblNewLabel = new JLabel("Processador");
	private JTable tableRegisters;
	private JTextArea txtMicro;
	
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
	
	
	public ProcessadorWindow() {
		initialize();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		
		JFrame frame = new JFrame();
		frame.setTitle("Endereços em azul são parte da pilha. Endereço em cinza é para onde o PC aponta no momento.");
		frame.setBounds(0, 0, 670, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator, 57, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, separator, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -324, SpringLayout.EAST, separator);
		tableRegisters = new JTable(dtm);
		dtm.addRow(new String[]{"PC", WindowData.pc});
		dtm.addRow(new String[]{"AC", WindowData.ac});
		dtm.addRow(new String[]{"SP", WindowData.sp});
		dtm.addRow(new String[]{"IR", WindowData.ir});
		dtm.addRow(new String[]{"TIR", WindowData.tir});
		dtm.addRow(new String[]{"A", WindowData.a});
		dtm.addRow(new String[]{"B", WindowData.b});
		dtm.addRow(new String[]{"C", WindowData.c});
		dtm.addRow(new String[]{"D", WindowData.d});
		dtm.addRow(new String[]{"E", WindowData.e});
		dtm.addRow(new String[]{"F", WindowData.f});
		//tableProdutos.setBounds(scrollPane.getBounds());
		scrollPane.setViewportView(tableRegisters);
		frame.getContentPane().add(scrollPane);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Registradores");
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblNewLabel_1_1);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_1, 0, SpringLayout.EAST, scrollPane);
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1, 6, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_1, 20, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtMicro = new JTextArea();
		txtMicro.setWrapStyleWord(true);
		txtMicro.setLineWrap(true);
		txtMicro.setEditable(false);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Histórico de Microinstruções");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1, 6, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1, 332, SpringLayout.WEST, txtMicro);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JScrollPane scrollMicro = new JScrollPane();
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_1_1, 0, SpringLayout.EAST, scrollMicro);
		scrollMicro.setViewportView(txtMicro);
		springLayout.putConstraint(SpringLayout.NORTH, scrollMicro, 34, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.WEST, scrollMicro, 10, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollMicro, -20, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollMicro, -20, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(scrollMicro);
	}
	
	
	public void update() {
		if(txtMicro.getText().length()>50000) { // limpa se tiver mais de 50 mil caracteres pra não comer muita memória
			txtMicro.setText("");
		}
		String mant = txtMicro.getText(); 
		if(!(mant.substring(mant.lastIndexOf("\n")+1).equals(WindowData.microAtual))) {
			txtMicro.setText(txtMicro.getText()+"\n\n"+WindowData.microAtual);
		}
	}
}