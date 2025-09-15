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

public class MainWindow {
	
	private JFrame frame;
	private JTextArea txtCode;
	private JSpinner spinner;
	private JButton btnAplicar;
	private String[] title = new String[] {"Registrador", "Valor"};
	private String[] titlemp = new String[] {"Endereço", "Valor (binário)", "Valor (decimal)"};
	private DefaultTableModel dtm = new DefaultTableModel(new String[0][2], title);
	private DefaultTableModel dtmmp = new DefaultTableModel(new String[0][2], titlemp);
	JTable tableProdutos;
	private JLabel lblNewLabel_1_2;
	private JTextArea txtMicro;
	private WindowUpdater wu;
	private UnidadeControle uc;
	private JButton btnAplicar_1;
	private JScrollPane scrollMP;
	private JTable tableMP;
	private JScrollPane scrollAssembly;
	private JTextArea txtAssembly;
	private JButton btnPause;
	private JButton btnAplicar_1_1;
	private JLabel lblTempo;
	private JSpinner spnPausaPC;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		wu = new WindowUpdater(this, 1000);
		wu.memw = new MemoriaWindow();
		uc = new UnidadeControle();
		StaticObjects.setUc(uc);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Simulador MIC-1 - Por Cauã Bassin, Lucas Avelar, Bernardo Rebello, Vitor Lemos e Allan Gaetani");
		
		spinner = new JSpinner();
		spinner.setBounds(96, 260, 52, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblNewLabel = new JLabel("Pausa entre subciclos (em milissegundos):");
		lblNewLabel.setBounds(96, 235, 218, 14);
		frame.getContentPane().add(lblNewLabel);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int segundos = Integer.parseInt(spinner.getValue().toString());
				uc.setSleepInMillis(segundos);
				wu.setSleep(segundos/2);
			}
		});
		btnAplicar.setBounds(158, 259, 65, 23);
		frame.getContentPane().add(btnAplicar);
		
		JLabel lblNewLabel_1 = new JLabel("Compilado:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(161, 11, 143, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Registradores");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(324, 11, 137, 26);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(324, 40, 290, 184);
		tableProdutos = new JTable(dtm);
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
		scrollPane.setViewportView(tableProdutos);
		frame.getContentPane().add(scrollPane);
		
		lblNewLabel_1_2 = new JLabel("Microinstrução");
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(324, 235, 137, 26);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		txtMicro = new JTextArea();
		txtMicro.setWrapStyleWord(true);
		txtMicro.setLineWrap(true);
		txtMicro.setBounds(324, 260, 290, 53);
		frame.getContentPane().add(txtMicro);
		
		JButton btnGravarCdigoNa = new JButton("Gravar código na MP");
		btnGravarCdigoNa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] programa = txtCode.getText().split("\n");
				for(int i = 0; i<programa.length; i++) {
					uc.memoriaPrincipal.setEnderecoSlecionado(i);
					uc.memoriaPrincipal.write(Conversoes.stringToIntegerArray(programa[i]));
				}

			}
		});
		btnGravarCdigoNa.setBounds(10, 290, 212, 23);
		frame.getContentPane().add(btnGravarCdigoNa);
		
		btnAplicar_1 = new JButton("Iniciar");
		btnAplicar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uc.start();
				wu.start();
			}
		});
		btnAplicar_1.setBounds(231, 290, 72, 23);
		frame.getContentPane().add(btnAplicar_1);
		
		scrollMP = new JScrollPane();
		scrollMP.setBounds(10, 323, 604, 236);
		tableMP = new JTable(dtmmp);
		String[] data = new String[2];
		for(int i = 0; i<4096; i++) {
			data[0] = String.valueOf(i);;
			data[1] = "";
			dtmmp.addRow(data);
		}
		scrollMP.setViewportView(tableMP);
		frame.getContentPane().add(scrollMP);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(161, 40, 153, 185);
		frame.getContentPane().add(scrollPane_1);
		
		txtCode = new JTextArea();
		scrollPane_1.setViewportView(txtCode);
		txtCode.setWrapStyleWord(true);
		txtCode.setLineWrap(true);
		
		scrollAssembly = new JScrollPane();
		scrollAssembly.setBounds(10, 40, 143, 185);
		frame.getContentPane().add(scrollAssembly);
		
		txtAssembly = new JTextArea();
		txtAssembly.setWrapStyleWord(true);
		txtAssembly.setLineWrap(true);
		scrollAssembly.setViewportView(txtAssembly);
		
		JLabel lblNewLabel_1_3 = new JLabel("Assembly:");
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(10, 11, 153, 26);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JButton btnMontar = new JButton("Montar");
		btnMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Montador montador = new Montador(txtAssembly.getText(), currentJanela);
				montador.start();
			}
		});
		btnMontar.setBounds(10, 259, 72, 23);
		frame.getContentPane().add(btnMontar);
		
		btnPause = new JButton("Pausar");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(uc.isPause()) {
					uc.setPause(false);
					wu.setPause(false);
				}else {
					uc.setPause(true);
					wu.setPause(true);
				}
			}
		});
		btnPause.setBounds(232, 259, 72, 23);
		frame.getContentPane().add(btnPause);
		
		btnAplicar_1_1 = new JButton("Editar Valores");
		btnAplicar_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PokeMenu(uc);
			}
		});
		btnAplicar_1_1.setBounds(485, 570, 129, 23);
		frame.getContentPane().add(btnAplicar_1_1);
		
		lblTempo = new JLabel("Tempo de Execução: 0ms");
		lblTempo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblTempo.setBounds(10, 569, 372, 24);
		frame.getContentPane().add(lblTempo);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Pausar automaticamente quando PC:");
		lblNewLabel_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setBounds(10, 598, 264, 26);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		spnPausaPC = new JSpinner();
		spnPausaPC.setBounds(284, 603, 177, 20);
		frame.getContentPane().add(spnPausaPC);
		
		JButton btnAplicar_1_1_1 = new JButton("Aplicar");
		btnAplicar_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uc.setPcValueForPause(Integer.parseInt(spnPausaPC.getValue().toString()));
			}
		});
		btnAplicar_1_1_1.setBounds(485, 603, 129, 23);
		frame.getContentPane().add(btnAplicar_1_1_1);
	}
	
	public void update() {
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
		txtMicro.setText("Subciclo atual: "+WindowData.currentSub+"\nMicroinstrução atual: "+WindowData.microAtual);
		lblTempo.setText("Tempo de execução: "+WindowData.executionTime+"ms");
		for(int i = 0; i<4096; i++) {
			dtmmp.setValueAt(Conversoes.integerArrayToString(uc.memoriaPrincipal.get(i)), i, 1);
			dtmmp.setValueAt(Conversoes.binaryIntToDecimal(uc.memoriaPrincipal.get(i)), i, 2);
		}
		
	}
	public void setTxtCode(String code) {
		txtCode.setText(code);
	}
}
