package visao;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.JPanel;

//TODO definir offsets em função da escala
public class DiagramaCanvas extends JPanel{
	private Map<String, Color> componentColors = new HashMap<>();
	private Map<String, Integer[]> componentPositions = new HashMap<>();
	private int scale = 3;
	
	public DiagramaCanvas() {
		this.setBackground(Color.DARK_GRAY); // pra ficar de mesma cor do fundo da janela
		componentColors.put("ULA", Color.WHITE); // componentes brancos por padrão
		componentColors.put("VDREGS", Color.WHITE); 
		componentColors.put("LATCHA", Color.WHITE);
		componentColors.put("LATCHB", Color.WHITE);
		componentColors.put("SHIFTER", Color.WHITE);
		
		componentPositions.put("VDREGS", new Integer[]{10, 10});
		componentPositions.put("ULA", new Integer[]{componentPositions.get("VDREGS")[0]+(10+15)*scale, componentPositions.get("VDREGS")[1]+(20+25)*scale});
		componentPositions.put("SHIFTER", new Integer[]{componentPositions.get("ULA")[0]+(5)*scale, componentPositions.get("ULA")[1]+(10+5)*scale});
		componentPositions.put("LATCHA", new Integer[]{componentPositions.get("ULA")[0], componentPositions.get("ULA")[1]-7*scale});
		componentPositions.put("LATCHB", new Integer[]{componentPositions.get("LATCHA")[0]+(6+5)*scale, componentPositions.get("LATCHA")[1]});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		desenhaULA(g);
		desenhaVdRegs(g);
		desenhaLatchA(g);
		desenhaLatchB(g);
		desenhaShifter(g);
	}
	
	public void desenhaShifter(Graphics g) {
		Integer[] pos = componentPositions.get("SHIFTER");
		g.setColor(componentColors.get("SHIFTER"));
		g.drawRect(pos[0], pos[1], 6*scale, 2*scale);
	}
	
	public void desenhaLatchA(Graphics g) {
		Integer[] pos = componentPositions.get("LATCHA");
		g.setColor(componentColors.get("LATCHA"));
		g.drawRect(pos[0], pos[1], 5*scale, 2*scale);
		
	}
	public void desenhaLatchB(Graphics g) {
		Integer[] pos = componentPositions.get("LATCHB");
		g.setColor(componentColors.get("LATCHB"));
		g.drawRect(pos[0], pos[1], 5*scale, 2*scale);
		
	}
	public void desenhaVdRegs(Graphics g) {
		Integer[] pos = componentPositions.get("VDREGS");
		g.setColor(componentColors.get("VDREGS"));
		g.drawRect(pos[0], pos[1], 15*scale, 25*scale);
		g.drawString("16", pos[0]+15, pos[1]+25);
		g.drawString("Regs", pos[0]+10, pos[1]+40);
	}
	public void desenhaULA(Graphics g) {
		Integer[] offset = componentPositions.get("ULA"); 
		int[] posX = new int[] {0+offset[0], 5*scale+offset[0], 8*scale+offset[0], 11*scale+offset[0], 16*scale+offset[0], 11*scale+offset[0], 5*scale+offset[0]};
		int[] posY = new int[] {0+offset[1], 0+offset[1], 5*scale+offset[1], 0+offset[1], 0+offset[1], 10*scale+offset[1], 10*scale+offset[1]};
		g.setColor(componentColors.get("ULA"));
		g.drawPolygon(posX, posY, 7);
		
		//g.drawString("ULA", offset[0] + 20, offset[1]);
		
	}
	
	
}