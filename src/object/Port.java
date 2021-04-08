package object;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Port extends Rectangle{
	
	private int r = 5;
	
	Port(int x, int y){
		resetPosition(x,y);
	}
	
	public void resetPosition(int x, int y) {
		int x1 = x - r;
		int y1 = y - r;
		int width = r * 2;
		int height = r * 2;
		this.setBounds(x1, y1, width, height);
	}
}
