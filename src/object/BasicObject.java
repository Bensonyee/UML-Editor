package object;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public abstract class BasicObject extends SelectableObject{
    protected int width, height;
    protected String name = "Default Name";
    protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);
    private Port[] ports = new Port[4];
    
    
    BasicObject() {
    	
    }
    
    public abstract void draw(Graphics g);
    
    public void showPorts(Graphics g) {
    	for(int i = 0; i < ports.length; i++) {
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String s) {
    	name = s;
    }
    
    @Override
    public void drawSelectedHint(Graphics g) {
    	showPorts(g);
    }
    
    public void initPorts() {
    	ports[0] = new Port((x1+x2)/2,y1);
    	ports[1] = new Port(x2,(y1+y2)/2);
    	ports[2] = new Port((x1+x2)/2,y2);
    	ports[3] = new Port(x1,(y1+y2)/2);
    	
    }
    
    public void resetPorts() {
    	ports[0].resetPosition((x1+x2)/2,y1);
    	ports[1].resetPosition(x2,(y1+y2)/2);
    	ports[2].resetPosition((x1+x2)/2,y2);
    	ports[3].resetPosition(x1,(y1+y2)/2);
    }
    
    public Port getPort(int i) {
    	return this.ports[i];
    }
    
    
    @Override
    public int inWhichArea(Point p) {
		Point center = new Point();
		center.x = (x1 + x2) / 2;
		center.y = (y1 + y2) / 2;
		Point[] points = { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) };
		
		for (int i = 0; i < 4; i++) {
			Polygon t = new Polygon();
			t.addPoint(points[i].x, points[i].y);
			t.addPoint(points[(i + 1) % 4].x, points[(i + 1) % 4].y);
			t.addPoint(center.x, center.y);
		
			if (t.contains(p)) {
				return i;
			}
		}
		return -1;
    	
    }
    
}
