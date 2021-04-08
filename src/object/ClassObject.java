package object;

import java.awt.Graphics;
import java.awt.Point;

public class ClassObject extends BasicObject {
	
	
	public ClassObject(int x, int y){
		this.width = 100;
		this.height = 120;
		resetPosition(new Point(x,y));
		this.initPorts();
	}
	
	private void resetPosition(Point p) {
		this.x1 = p.x;
		this.y1 = p.y;
		this.x2 = p.x + width;
		this.y2 = p.y + height;
	}
	
	public void draw(Graphics g) {
		g.drawRect(x1, y1, width, height);
		
		int portion = height / 3;
		g.drawLine(x1, y1 + portion, x2, y1 + portion);
		g.drawLine(x1, y1 + portion * 2, x2, y1 + portion * 2);
		
		
		int stringWidth = g.getFontMetrics(font).stringWidth(name);
		double dx = (Math.abs(x1-x2) - stringWidth)/2;
		g.setFont(this.font);	
		g.drawString(this.name, x1 + (int)dx, y1 + 25);
	}

	@Override
	public void move(int dx,int dy) {
		resetPosition(new Point(x1+dx, y1+dy));
		this.initPorts();
	}
	

}
