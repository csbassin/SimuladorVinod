package visao;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.JPanel;

public class DiagramaCanvas extends JPanel{
	private Drawable drawable;
	
	public DiagramaCanvas(Drawable drawable) {
		this.drawable = drawable;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		desenhaULA(g, Color.blue, 10, 10, 10);
		drawable.draw(g);
	}
	
	
	public int[][] ulaVet = {
		{0, 0, 10, 0}, {10, 0, 15, 5}, {15, 5, 20, 0}, {20, 0, 30, 0},
		{30, 0, 22, 10}, {22, 10, 8, 10}, {8, 10, 0, 0}
	};
	public void desenhaULA(Graphics g, Color c, int x, int y, int scale) {
		g.setColor(c);
		for(int[] v : ulaVet) {
			g.drawLine((v[0] + x) * scale,
					(v[1] + y) * scale,
					(v[2] + x) * scale, 
					(v[3] + y)* scale);
		}
		
		g.drawString("ULA uwu", x + 20, y + 20);
		
	}
}