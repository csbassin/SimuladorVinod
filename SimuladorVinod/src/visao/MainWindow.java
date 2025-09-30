package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import main.UnidadeControle;
import util.Conversoes;
import util.Montador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

public class MainWindow {
	
	private JFrame frame;
	private JTextArea txtCode;
	private String[] title = new String[] {"Registrador", "Valor"};
	private DefaultTableModel dtm = new DefaultTableModel(new String[0][2], title);
	JTable tableRegisters;
	private WindowUpdater wu;
	private UnidadeControle uc;
	private JScrollPane scrollMP;
	private JTable tableMP;
	private JScrollPane scrollAssembly;
	private JTextArea txtAssembly;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// chamar o construtor por qualquer outro lugar dá merda e eu não sei o porquê
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainWindow currentJanela = this;
		MemoriaWindow mw = new MemoriaWindow();
		wu = WindowUpdater.getWu(this, mw, new ProcessadorWindow(), 1000);
		new ControlesWindow(wu);
		uc = UnidadeControle.getUc();
		
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(395, 375));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 395, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Simulador MIC-1 - Por Cauã Bassin, Lucas Avelar, Bernardo Rebello, Vitor Lemos e Allan Gaetani");
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel_1 = new JLabel("Compilado:");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 323, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnGravarCdigoNa = new JButton("Gravar código na MP");
		btnGravarCdigoNa.setBorder(new LineBorder(Color.WHITE, 2));
		btnGravarCdigoNa.setContentAreaFilled(false);
		btnGravarCdigoNa.setForeground(Color.WHITE);
		btnGravarCdigoNa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.WEST, btnGravarCdigoNa, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnGravarCdigoNa, -20, SpringLayout.EAST, frame.getContentPane());
		btnGravarCdigoNa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] programa = txtCode.getText().split("\n");
				for(int i = 0; i<programa.length; i++) {
					uc.memoriaPrincipal.setEnderecoSlecionado(i);
					uc.memoriaPrincipal.write(Conversoes.stringToIntegerArray(programa[i]));
				}
				mw.update();
			}
		});
		frame.getContentPane().add(btnGravarCdigoNa);
	
		
		JScrollPane scrollPane_1 = new JScrollPane();
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, scrollPane_1);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane_1, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane_1, 78, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane_1, 263, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane_1, -20, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(scrollPane_1);
		
		txtCode = new JTextArea();
		scrollPane_1.setViewportView(txtCode);
		txtCode.setWrapStyleWord(true);
		txtCode.setLineWrap(true);
		
		scrollAssembly = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollAssembly, 78, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollAssembly, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollAssembly, 263, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollAssembly, 170, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(scrollAssembly);
		
		txtAssembly = new JTextArea();
		txtAssembly.setWrapStyleWord(true);
		txtAssembly.setLineWrap(true);
		scrollAssembly.setViewportView(txtAssembly);
		
		JLabel lblNewLabel_1_3 = new JLabel("Assembly:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, lblNewLabel_1_3);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_3, 52, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_3, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1_3, 78, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_3, 163, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JButton btnMontar = new JButton("Montar");
		btnMontar.setForeground(Color.WHITE);
		btnMontar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnMontar.setContentAreaFilled(false);
		btnMontar.setBorder(new LineBorder(Color.WHITE, 2));
		springLayout.putConstraint(SpringLayout.WEST, btnMontar, 20, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnGravarCdigoNa, 6, SpringLayout.SOUTH, btnMontar);
		springLayout.putConstraint(SpringLayout.NORTH, btnMontar, 6, SpringLayout.SOUTH, scrollPane_1);
		springLayout.putConstraint(SpringLayout.EAST, btnMontar, -20, SpringLayout.EAST, frame.getContentPane());
		btnMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Montador montador = new Montador(txtAssembly.getText(), currentJanela);
				montador.start();
			}
		});
		frame.getContentPane().add(btnMontar);
		
		JLabel lblPrograma = new JLabel("Programa");
		springLayout.putConstraint(SpringLayout.WEST, lblPrograma, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblPrograma, -6, SpringLayout.NORTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, lblPrograma, 300, SpringLayout.WEST, frame.getContentPane());
		lblPrograma.setForeground(Color.WHITE);
		lblPrograma.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		frame.getContentPane().add(lblPrograma);
		
		JSeparator separator = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator, 47, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, separator, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(separator);
	}
	
	public void update() {

	}
	public void setTxtCode(String code) {
		txtCode.setText(code);
	}
}
