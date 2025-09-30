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
import javax.swing.JOptionPane;

import main.UnidadeControle;
import modelo.MemoriaPrincipal;
import util.Conversoes;
import util.GetRegistrador;
import util.Montador;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

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
	private JScrollPane scrollPane;
	private JLabel lblStatus;
	private JLabel lblTempoExec;
	
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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "O simulador deve ser fechado pela janela \"Programa\".");
			}
		});
		frame.setMinimumSize(new Dimension(670, 516));
		frame.setTitle("Endereços em azul são parte da pilha. Endereço em cinza é para onde o PC aponta no momento.");
		frame.setBounds(0, 0, 670, 516);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		
		scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -324, SpringLayout.EAST, separator);
		tableRegisters = new JTable(dtm);
		tableRegisters.setCellSelectionEnabled(false);
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
		
		
		JLabel lblRegs = new JLabel("Registradores");
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblRegs);
		springLayout.putConstraint(SpringLayout.WEST, lblRegs, 20, SpringLayout.WEST, frame.getContentPane());
		lblRegs.setForeground(new Color(255, 255, 255));
		springLayout.putConstraint(SpringLayout.NORTH, lblRegs, 6, SpringLayout.SOUTH, separator);
		lblRegs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(lblRegs);
		
		txtMicro = new JTextArea();
		txtMicro.setWrapStyleWord(true);
		txtMicro.setLineWrap(true);
		txtMicro.setEditable(false);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Histórico de Microinstruções");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1, 10, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_1_1, -20, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblRegs, -10, SpringLayout.WEST, lblNewLabel_1_1_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1, 6, SpringLayout.SOUTH, separator);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JScrollPane scrollMicro = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollMicro, 6, SpringLayout.SOUTH, lblNewLabel_1_1_1);
		springLayout.putConstraint(SpringLayout.WEST, scrollMicro, 10, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollMicro, 0, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, scrollMicro, -20, SpringLayout.EAST, frame.getContentPane());
		scrollMicro.setViewportView(txtMicro);
		frame.getContentPane().add(scrollMicro);
		
		lblStatus = new JLabel("A simulação ainda não começou");
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -132, SpringLayout.NORTH, lblStatus);
		springLayout.putConstraint(SpringLayout.WEST, lblStatus, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblStatus, -334, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblStatus, -40, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblStatus, -20, SpringLayout.SOUTH, frame.getContentPane());
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(lblStatus);
		
		lblTempoExec = new JLabel("Tempo de execução: 0ms");
		springLayout.putConstraint(SpringLayout.NORTH, lblTempoExec, -25, SpringLayout.NORTH, lblStatus);
		springLayout.putConstraint(SpringLayout.WEST, lblTempoExec, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTempoExec, -5, SpringLayout.NORTH, lblStatus);
		springLayout.putConstraint(SpringLayout.EAST, lblTempoExec, 0, SpringLayout.EAST, lblNewLabel_1_1_1);
		lblTempoExec.setForeground(Color.WHITE);
		lblTempoExec.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(lblTempoExec);
	}
	
	
	public void update() {
		lblTempoExec.setText("Tempo de execução: "+WindowData.executionTime+"ms");
		lblStatus.setText(WindowData.statusSimulacao);
		dtm.setValueAt(WindowData.pc, 0, 1);
		dtm.setValueAt(WindowData.ac, 1, 1);
		dtm.setValueAt(WindowData.sp, 2, 1);
		dtm.setValueAt(WindowData.ir, 3, 1);
		dtm.setValueAt(WindowData.tir, 4, 1);
		dtm.setValueAt(WindowData.a, 5, 1);
		dtm.setValueAt(WindowData.b, 6, 1);
		dtm.setValueAt(WindowData.c, 7, 1);
		dtm.setValueAt(WindowData.d, 8, 1);
		dtm.setValueAt(WindowData.e, 9, 1);
		dtm.setValueAt(WindowData.f, 10, 1);
		
		if(txtMicro.getText().length()>50000) { // limpa se tiver mais de 50 mil caracteres pra não comer muita memória
			txtMicro.setText("");
		}
		String mant = txtMicro.getText(); 
		if(!(mant.substring(mant.lastIndexOf("\n")+1).equals(WindowData.microAtual))) {
			txtMicro.setText(txtMicro.getText()+"\n\n"+WindowData.microAtual);
		}
	}
}