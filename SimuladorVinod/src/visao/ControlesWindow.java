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
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class ControlesWindow{

	private final JLabel lblNewLabel = new JLabel("Controles da Simulação");
	private JButton btnPause;
	private JButton btnPlay;
	private JButton btnStop;
	private UnidadeControle uc = UnidadeControle.getUc();
	private WindowUpdater wu;
	private JLabel lblNewLabel_1;
	private JSpinner spinner;
	private JButton btnAplicar;
	
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
	
	
	public ControlesWindow(WindowUpdater wu) {
		this.wu = wu;
		initialize();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		JFrame frame = new JFrame();
		frame.setTitle("Inicie, pause ou resete o processador");
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
		
		btnPause = new JButton("");
		btnPause.setContentAreaFilled(false);
		btnPause.setToolTipText("Pausar");
		btnPause.setBackground(Color.DARK_GRAY);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uc.setPause(true);
				btnPause.setEnabled(false);
				btnPlay.setEnabled(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnPause, 6, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.WEST, btnPause, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnPause, 102, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.EAST, btnPause, 116, SpringLayout.WEST, frame.getContentPane());
		btnPause.setBorder(new LineBorder(Color.WHITE, 3));
		btnPause.setIcon(new ImageIcon(ControlesWindow.class.getResource("/assets/PauseIcon.png")));
		frame.getContentPane().add(btnPause);
		
		btnPlay = new JButton("");
		btnPlay.setToolTipText("Continuar");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!uc.isAlive()) {
					uc.start();
					wu.start();
				}else {
					uc.setPause(false);
					btnPlay.setEnabled(false);
					btnPause.setEnabled(true);
				}
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnPlay, 6, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.WEST, btnPlay, 6, SpringLayout.EAST, btnPause);
		springLayout.putConstraint(SpringLayout.SOUTH, btnPlay, 0, SpringLayout.SOUTH, btnPause);
		springLayout.putConstraint(SpringLayout.EAST, btnPlay, 102, SpringLayout.EAST, btnPause);
		btnPlay.setIcon(new ImageIcon(ControlesWindow.class.getResource("/assets/PlayIcon.png")));
		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorder(new LineBorder(Color.WHITE, 3));
		frame.getContentPane().add(btnPlay);
		
		btnStop = new JButton("");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wu.setStop(true);
				uc.setStop(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnStop, 6, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.WEST, btnStop, 6, SpringLayout.EAST, btnPlay);
		springLayout.putConstraint(SpringLayout.SOUTH, btnStop, 0, SpringLayout.SOUTH, btnPause);
		springLayout.putConstraint(SpringLayout.EAST, btnStop, 102, SpringLayout.EAST, btnPlay);
		btnStop.setIcon(new ImageIcon(ControlesWindow.class.getResource("/assets/StopIcon.png")));
		btnStop.setContentAreaFilled(false);
		btnStop.setBorder(new LineBorder(Color.WHITE, 3));
		frame.getContentPane().add(btnStop);
		
		lblNewLabel_1 = new JLabel("Pausa entre subciclos (em milissegundos):");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.SOUTH, separator);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 6, SpringLayout.EAST, btnStop);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -20, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_1);
		
		spinner = new JSpinner();
		springLayout.putConstraint(SpringLayout.NORTH, spinner, 6, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, spinner, 6, SpringLayout.EAST, btnStop);
		springLayout.putConstraint(SpringLayout.SOUTH, spinner, -245, SpringLayout.SOUTH, frame.getContentPane());
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(spinner);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int segundos = Integer.parseInt(spinner.getValue().toString());
				uc.setSleepInMillis(segundos);
				wu.setSleep(segundos/2);
			}
		});
		btnAplicar.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.EAST, spinner, -6, SpringLayout.WEST, btnAplicar);
		springLayout.putConstraint(SpringLayout.EAST, btnAplicar, 504, SpringLayout.WEST, frame.getContentPane());
		btnAplicar.setBorder(new LineBorder(Color.WHITE, 2));
		btnAplicar.setContentAreaFilled(false);
		btnAplicar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.WEST, btnAplicar, 434, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnAplicar, 6, SpringLayout.SOUTH, lblNewLabel_1);
		frame.getContentPane().add(btnAplicar);
	}
	
	
	public void update() {
		
	}
}