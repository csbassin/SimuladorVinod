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
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	private JButton btnReset;
	private JSpinner spnPausaPC;
	private JLabel lblNewLabel_1_2;
	private JButton btnAplicar_1_1;
	private JPanel panePasso;
	private JCheckBox checkPasso;
	private JButton btnPassarCiclo;
	
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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "O simulador deve ser fechado pela janela \"Programa\".");
			}
		});
		frame.setTitle("Inicie, pause ou resete o processador");
		frame.setBounds(0, 410, 459, 413);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		lblNewLabel.setBounds(10, 10, 290, 38);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 57, 423, 2);
		frame.getContentPane().add(separator);
		
		btnPause = new JButton("");
		btnPause.setBounds(20, 65, 96, 96);
		btnPause.setEnabled(false);
		btnPause.setContentAreaFilled(false);
		btnPause.setToolTipText("Pausar");
		btnPause.setBackground(Color.GRAY);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uc.setPause(true);
				btnPause.setEnabled(false);
				btnPlay.setEnabled(true);
			}
		});
		btnPause.setBorder(new LineBorder(Color.WHITE, 3));
		btnPause.setIcon(new ImageIcon(ControlesWindow.class.getResource("/assets/PauseIcon.png")));
		frame.getContentPane().add(btnPause);
		
		btnPlay = new JButton("");
		btnPlay.setBounds(122, 65, 96, 96);
		btnPlay.setBackground(Color.GRAY);
		btnPlay.setToolTipText("Continuar");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!uc.isAlive()) {
					btnPlay.setEnabled(false);
					btnPause.setEnabled(true);
					btnStop.setEnabled(true);
					btnReset.setEnabled(true);
					uc.start();
					wu.start();
				}else {
					uc.setPause(false);
					btnPlay.setEnabled(false);
					btnPause.setEnabled(true);
					
				}
				
			}
		});
		btnPlay.setIcon(new ImageIcon(ControlesWindow.class.getResource("/assets/PlayIcon.png")));
		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorder(new LineBorder(Color.WHITE, 3));
		frame.getContentPane().add(btnPlay);
		
		btnStop = new JButton("");
		btnStop.setBounds(224, 65, 96, 96);
		btnStop.setEnabled(false);
		btnStop.setBackground(Color.GRAY);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wu.setStop(true);
				uc.setStop(true);
			}
		});
		btnStop.setIcon(new ImageIcon(ControlesWindow.class.getResource("/assets/StopIcon.png")));
		btnStop.setContentAreaFilled(false);
		btnStop.setBorder(new LineBorder(Color.WHITE, 3));
		frame.getContentPane().add(btnStop);
		
		lblNewLabel_1 = new JLabel("Pausa entre subciclos (em milissegundos):");
		lblNewLabel_1.setBounds(20, 167, 259, 20);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_1);
		
		spinner = new JSpinner();
		spinner.setBounds(20, 193, 227, 26);
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(spinner);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.setBounds(253, 193, 98, 25);
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int segundos = Integer.parseInt(spinner.getValue().toString());
				uc.setSleepInMillis(segundos);
				wu.setSleep(segundos/2);
			}
		});
		btnAplicar.setForeground(Color.WHITE);
		btnAplicar.setBorder(new LineBorder(Color.WHITE, 2));
		btnAplicar.setContentAreaFilled(false);
		btnAplicar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(btnAplicar);
		
		btnReset = new JButton("");
		btnReset.setBounds(326, 65, 96, 96);
		btnReset.setEnabled(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uc.setPause(false);
				uc.setResetar(true);
				btnStop.setEnabled(false);
				btnPlay.setEnabled(true);
				btnPause.setEnabled(false);
			}
		});
		btnReset.setIcon(new ImageIcon(ControlesWindow.class.getResource("/assets/ResetIcon.png")));
		btnReset.setContentAreaFilled(false);
		btnReset.setBorder(new LineBorder(Color.WHITE, 3));
		btnReset.setBackground(Color.GRAY);
		frame.getContentPane().add(btnReset);
		
		spnPausaPC = new JSpinner();
		spnPausaPC.setBounds(253, 229, 98, 26);
		spnPausaPC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(spnPausaPC);
		
		lblNewLabel_1_2 = new JLabel("Pausar automaticamente quando PC:");
		lblNewLabel_1_2.setBounds(20, 232, 227, 20);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_1_2);
		
		btnAplicar_1_1 = new JButton("Aplicar");
		btnAplicar_1_1.setBounds(358, 229, 65, 26);
		btnAplicar_1_1.setForeground(Color.WHITE);
		btnAplicar_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAplicar_1_1.setContentAreaFilled(false);
		btnAplicar_1_1.setBorder(new LineBorder(Color.WHITE, 2));
		frame.getContentPane().add(btnAplicar_1_1);
		
		panePasso = new JPanel();
		panePasso.setBackground(Color.DARK_GRAY);
		panePasso.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2), "Modo Passo-a-Passo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panePasso.setBounds(20, 263, 402, 100);
		frame.getContentPane().add(panePasso);
		panePasso.setLayout(null);
		
		checkPasso = new JCheckBox("Executar em Modo Passo-a-Passo");
		checkPasso.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					uc.setModoPassoAPasso(true);
					btnPassarCiclo.setEnabled(true);
				}else {
					uc.setModoPassoAPasso(false);
					btnPassarCiclo.setEnabled(false);
				}
			}
		});
		checkPasso.setForeground(Color.WHITE);
		checkPasso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		checkPasso.setBackground(Color.DARK_GRAY);
		checkPasso.setBounds(6, 17, 379, 23);
		panePasso.add(checkPasso);
		
		btnPassarCiclo = new JButton("Passar um ciclo");
		btnPassarCiclo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uc.setPause(false);
			}
		});
		btnPassarCiclo.setEnabled(false);
		btnPassarCiclo.setForeground(Color.WHITE);
		btnPassarCiclo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnPassarCiclo.setContentAreaFilled(false);
		btnPassarCiclo.setBorder(new LineBorder(Color.WHITE, 2));
		btnPassarCiclo.setBounds(10, 47, 382, 30);
		panePasso.add(btnPassarCiclo);
	}
}