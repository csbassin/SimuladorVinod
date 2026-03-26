package visao;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.UnidadeControle;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DiagramaWindow{

	private final JLabel lblNewLabel = new JLabel("Diagrama do Processador");
	private UnidadeControle uc = UnidadeControle.getUc();
	private DiagramaCanvas canvas;
	
	
	public DiagramaWindow() {
		initialize();
	}
	
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
		frame.setResizable(true);
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
		
		canvas = new DiagramaCanvas();
		canvas.setBounds(20, 68, 399, 296);
		frame.getContentPane().add(canvas);
		canvas.repaint();
	}
	
}