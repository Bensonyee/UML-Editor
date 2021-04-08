package object;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class GeneralizationLine extends Line {
	public GeneralizationLine(int x1, int y1, int x2, int y2) {		
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke sk= g2.getStroke();
		g2.setStroke(new BasicStroke(3));
		
		
		double h = 20; //arrow height
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double px = x2-h*dx/D;
		double py = y2-h*dy/D;
		
		double sin = Math.sin(Math.PI/4), cos = Math.cos(Math.PI/4);
		double a1x = x2 + (cos*(px-x2) - sin*(py-y2));
		double a1y = y2 + (sin*(px-x2) + cos*(py-y2));
		
		sin = Math.sin(-1*Math.PI/4);
		cos = Math.cos(-1*Math.PI/4);
		double a2x = x2 + (cos*(px-x2) - sin*(py-y2));
		double a2y = y2 + (sin*(px-x2) + cos*(py-y2));
		
		g.drawLine(x2, y2, (int)a1x, (int)a1y);
		g.drawLine(x2, y2, (int)a2x, (int)a2y);
		g.drawLine((int)a1x, (int)a1y, (int)a2x, (int)a2y);
		px = x2-0.7*h*dx/D;
		py = y2-0.7*h*dy/D;
		g.drawLine(x1, y1, (int)px, (int)py);
		
		g2.setStroke(sk);
		
	}
}
