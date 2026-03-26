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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SpringLayout;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Canvas;

public class DiagramaWindow implements Drawable{

	private final JLabel lblNewLabel = new JLabel("Diagrama do Processador");
	private UnidadeControle uc = UnidadeControle.getUc();
	private DiagramaCanvas canvas;
	
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
	
	
	public DiagramaWindow() {
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
		frame.setTitle("Observe a movimentação dos dados no processador");
		frame.setBounds(680, 410, 459, 413);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		lblNewLabel.setBounds(10, 10, 354, 38);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 57, 423, 2);
		frame.getContentPane().add(separator);
		
		canvas = new DiagramaCanvas(this);
		canvas.setBounds(20, 68, 399, 296);
		frame.getContentPane().add(canvas);
		canvas.repaint();
	}
	private void desenhaCanvas(Graphics g) {
		
	}


	@Override
	public void draw(Graphics g) {
		desenhaCanvas(g);
	}
}