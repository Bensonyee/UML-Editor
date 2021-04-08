package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Group extends SelectableObject{
    private List<SelectableObject> objs = new ArrayList<SelectableObject>();
    private int width, height;
    
    public Group(List<SelectableObject> members){
    	this.x1 = members.get(0).x1;
    	this.x2 = members.get(0).x2;
    	this.y1 = members.get(0).y1;
    	this.y2 = members.get(0).y2;
    	for(int i = 0; i < members.size(); i++) {
    		SelectableObject obj = members.get(i);
    		objs.add(obj);
    		this.x1 = Math.min(this.x1, obj.x1);
    		this.y1 = Math.min(this.y1, obj.y1);
    		this.x2 = Math.max(this.x2, obj.x2);
    		this.y2 = Math.max(this.y2, obj.y2);
    	}
    	this.width = x2-x1;
    	this.height = y2-y1;
    }
    
	@Override
	public void drawSelectedHint(Graphics g) {
		int margin = 5;
		Color tempColor = g.getColor();
		g.setColor(new Color(30,150,30,50));
		g.fillRect(x1-margin, y1-margin, x2-x1+margin*2, y2-y1+margin*2);
		g.setColor(tempColor);
		for(int i = 0; i < objs.size(); i++) {
			objs.get(i).drawSelectedHint(g);
		}
	}
	
	@Override
	public int inWhichArea(Point p){
		if(x1 <= p.x && y1 <= p.y && x2 >= p.x && y2 >= p.y) {
			return 1;
		}
		return -1;
	}
    
	public List<SelectableObject> getMembers() {
		return this.objs;
	}
	
	private void resetPosition(Point p) {
		x1 = p.x;
		x2 = x1 + width;
		y1 = p.y;
		y2 = y1 + height;
	}

	@Override
	public void move(int dx,int dy) {
		resetPosition(new Point(x1+dx, y1+dy));
		for(int i = 0; i < objs.size(); i++) {
			objs.get(i).move(dx, dy);
		}
	}


}
