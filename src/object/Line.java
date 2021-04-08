package object;

import java.awt.Graphics;

public abstract class Line {
	protected int x1, x2;
    protected int y1, y2;
    protected BasicObject startObj, endObj;
    protected int startPortNum, endPortNum;
    public abstract void draw(Graphics g);
    
    public void setStartPort(BasicObject obj, int p) {
    	startObj = obj;
    	startPortNum = p;
    }
    
    public void setEndPort(BasicObject obj, int p) {
    	endObj = obj;
    	endPortNum = p;
    }
    
    public void updatePosition() {
    	x1 = (int)startObj.getPort(startPortNum).getCenterX();
    	y1 = (int)startObj.getPort(startPortNum).getCenterY();
    	x2 = (int)endObj.getPort(endPortNum).getCenterX();
    	y2 = (int)endObj.getPort(endPortNum).getCenterY();
    }
    
}
