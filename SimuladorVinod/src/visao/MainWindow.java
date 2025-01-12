package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JLabel;
import main.UnidadeControle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;

public class MainWindow {

	private JFrame frame;
	private JButton btnParar;
	private JTextArea txtCode;
	private JSpinner spinner;
	private JButton btnAplicar;
	private String[] title = new String[] {"Registrador", "Valor"};
	private DefaultTableModel dtm = new DefaultTableModel(new String[0][2], title);
	JTable tableProdutos;
	private JLabel lblNewLabel_1_2;
	private JTextArea txtMicro;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		WindowUpdater wu = new WindowUpdater(this);
		UnidadeControle uc = new UnidadeControle();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCode = new JTextArea();
		txtCode.setWrapStyleWord(true);
		txtCode.setLineWrap(true);
		txtCode.setBounds(10, 40, 304, 184);
		frame.getContentPane().add(txtCode);
		
		btnParar = new JButton("Parar");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wu.interrupt();
				uc.interrupt();
			}
		});
		btnParar.setBounds(147, 259, 81, 23);
		frame.getContentPane().add(btnParar);
		
		spinner = new JSpinner();
		spinner.setBounds(10, 260, 52, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblNewLabel = new JLabel("Pausa entre subciclos(em segundos):");
		lblNewLabel.setBounds(10, 235, 218, 14);
		frame.getContentPane().add(lblNewLabel);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int segundos = Integer.parseInt(spinner.getValue().toString())*1000;
				uc.setSleepInMillis(segundos);
			}
		});
		btnAplicar.setBounds(72, 259, 65, 23);
		frame.getContentPane().add(btnAplicar);
		
		JLabel lblNewLabel_1 = new JLabel("Código");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 11, 74, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Registradores");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(324, 11, 137, 26);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(324, 40, 290, 184);
		tableProdutos = new JTable(dtm);
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
		
		wu.start();
		uc.start();
	}
	public void update() {
		dtm.setRowCount(0);
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
		txtMicro.setText("Subciclo atual: "+WindowData.currentSub+"\nMicroinnstrução atual: "+WindowData.microAtual);
		
	}
}
