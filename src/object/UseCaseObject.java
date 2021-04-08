package object;

import java.awt.Graphics;
import java.awt.Point;

public class UseCaseObject extends BasicObject{
	public UseCaseObject(int x, int y) {
		this.width = 120;
		this.height = 90;
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
		g.drawOval(x1, y1, width, height);
		
		
		int stringWidth = g.getFontMetrics(font).stringWidth(this.name);
		double dx = (Math.abs(x1-x2) - stringWidth)/2;
		g.setFont(this.font);	
		g.drawString(this.name, x1 + (int)dx, y1 + 50);
	}
	@Override
	public void move(int dx,int dy) {
		resetPosition(new Point(x1+dx, y1+dy));
		this.initPorts();
	}
}
