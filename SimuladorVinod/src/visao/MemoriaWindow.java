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

public class MemoriaWindow{
	
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
			if(row>=currentSp) {
				return Color.BLUE;
			}else if(row == currentPc) {
				return Color.GRAY;
			}
			return Color.WHITE;
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
	
	private String[] colunas = {"Endereço", "Valor Binário", "Valor Decimal", "Valor Hexadecimal"};
	private CustomTableModel dtm = new CustomTableModel(colunas, 0);
	private JTable tabelaMemoria;
	private final JLabel lblNewLabel = new JLabel("Memória Principal");
	private final GetRegistrador gr = GetRegistrador.getGr();
	public static int currentSp = 4096;
	public static int currentPc = -1;
	public static int lastPc = -1;
	private JScrollPane scrollPane;
	
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
		
		tabelaMemoria = new JTable(dtm);
		tabelaMemoria.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		String zeroBin = Conversoes.conversaoCompleta(0, 16);
		String zeroHex = Conversoes.binaryToHex(zeroBin);
		for(int i = 0; i<4096; i++) {
			String[] valueCols = {String.valueOf(i),
								zeroBin,
								String.valueOf(0),
								zeroHex};
			dtm.addRow(valueCols);
		}
		tabelaMemoria.setCellSelectionEnabled(false);
		scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 70, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -20, SpringLayout.EAST, frame.getContentPane());
		scrollPane.setViewportView(tabelaMemoria);
		frame.getContentPane().add(scrollPane);
	}
	
	
	public void update() {
		lastPc = currentPc;
		for(int i = 0; i<4096; i++) {
			int currentValue = Conversoes.binaryIntToDecimal(UnidadeControle.getUc().memoriaPrincipal.get(i));
			if(currentValue != 0) {
				String binario = Conversoes.conversaoCompleta(currentValue, 16);
				dtm.setValueAt(binario, i, 1); // seta o binário
				dtm.setValueAt(currentValue, i, 2); // seta o decimal
				dtm.setValueAt(Conversoes.binaryToHex(binario), i, 3); // seta o hex	
			}
		}
		currentSp = Conversoes.binaryIntToDecimal(gr.get(2).getRegistrador());
		currentPc = Conversoes.binaryIntToDecimal(gr.get(0).getRegistrador());
		if(currentSp <= 4095) {
			dtm.paintRowsAfter(currentSp);
		}
		if(lastPc!=currentPc) {
			dtm.paintRow(lastPc);
		}
		dtm.paintRow(currentPc);
		//dtm.resetBefore(currentPc);
	}
}