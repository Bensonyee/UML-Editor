package object;

import java.awt.Color;
import java.awt.Graphics;

public class SelectedArea {
	private int x1, x2, y1, y2;
	public SelectedArea(int x1, int y1, int x2, int y2){
		if (x1 > x2 && y1 > y2) {
			int temp;
			temp = x1;
			x1 = x2;
			x2 = temp;
			temp = y1;
			y1 = y2;
			y2 = temp;
		}
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void draw(Graphics g) {
		Color temp = g.getColor();
		g.setColor(new Color(20,20,120,120));
		g.fillRect(x1, y1, x2-x1, y2-y1);
		g.setColor(temp);
	}
}
