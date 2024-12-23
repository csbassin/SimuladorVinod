package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import main.UnidadeControle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JButton btnParar;
	private JTextArea textArea;
	private JSpinner spinner;
	private JButton btnAplicar;

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
		frame.setBounds(100, 100, 450, 319);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(32, 48, 373, 184);
		frame.getContentPane().add(textArea);
		
		btnParar = new JButton("Parar");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wu.interrupt();
				uc.interrupt();
			}
		});
		btnParar.setBounds(324, 243, 81, 23);
		frame.getContentPane().add(btnParar);
		
		spinner = new JSpinner();
		spinner.setBounds(187, 246, 52, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblNewLabel = new JLabel("Pausa no loop (em segundos):");
		lblNewLabel.setBounds(32, 249, 145, 14);
		frame.getContentPane().add(lblNewLabel);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int segundos = Integer.parseInt(spinner.getValue().toString())*1000;
				uc.setSleepInMillis(segundos);
			}
		});
		btnAplicar.setBounds(249, 243, 65, 23);
		frame.getContentPane().add(btnAplicar);
		
		wu.start();
		uc.start();
	}
	public void update() {
		textArea.setText(WindowData.txtData);
	}
}
