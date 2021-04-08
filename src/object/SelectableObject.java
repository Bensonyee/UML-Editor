package object;

import java.awt.Graphics;
import java.awt.Point;

public abstract class SelectableObject {
	protected int x1,x2,y1,y2;
	public abstract void drawSelectedHint(Graphics g);
	public abstract int inWhichArea(Point p);
	public abstract void move(int dx, int dy);
	
	public boolean isInArea(Point p1, Point p2) {
    	if(p1.x > p2.x && p1.y > p2.y) {
    		Point temp;
    		temp = p1;
    		p1 = p2;
    		p2 = temp;
    	}
    	if(p1.x < this.x1 && p1.y < this.y1 && p2.x > this.x2 && p2.y > this.y2) {
    		return true;
    	}
    	return false;
	}
}
