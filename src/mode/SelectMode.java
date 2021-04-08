package mode;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import object.BasicObject;
import object.Group;
import object.SelectableObject;
import object.SelectedArea;
import view.Canvas;

public class SelectMode extends Mode{
	
	private List<BasicObject> objects;
	private List<Group> groups;
	private SelectableObject selectedObj;
	private Point selectAreaStart = null;
	private int pressX, pressY;
	
	@Override
	public void onMousePress(Canvas canvas, int x, int y) {
		pressX = x;
		pressY = y;
		selectedObj = null;
		canvas.cleanSelectedObj();
		
		groups = canvas.getGroups();
		for (int i = groups.size() - 1; i >= 0; i--) {
			Group gp = groups.get(i);
			int idx = gp.inWhichArea(new Point(x,y)); 
			if (idx != -1) {
				selectedObj = gp;
				canvas.addSelectedObjs(gp);
				break;
			}
		}
		//if no group selected
		if(selectedObj == null) {
			objects = canvas.getObjects();
			for (int i = objects.size() - 1; i >= 0; i--) {
				BasicObject obj = objects.get(i);
				int idx = obj.inWhichArea(new Point(x,y)); 
				if (idx != -1) {
					selectedObj = obj;
					canvas.addSelectedObjs(obj);
					break;
				}
			}
		}
		if(selectedObj == null) {
			selectAreaStart = new Point(x, y);
		}
		canvas.repaint();
	}
	
	@Override
	public void onMouseDrag(Canvas canvas, int x, int y) {
		if(selectAreaStart != null) {
			SelectedArea selectedArea = new SelectedArea(selectAreaStart.x,selectAreaStart.y, x, y);
			canvas.setSelectedArea(selectedArea);
			canvas.repaint();
		}
		//move selected object
		else { 
			selectedObj.move(x - pressX, y - pressY);
			canvas.resetLines();
			pressX = x;
			pressY = y;
			canvas.repaint();
		}
	}
	
	@Override
	public void onMouseRelease(Canvas canvas, int x, int y) {
		if(this.selectAreaStart != null) {
			//find Group
			List<Group> selectedGroups = new ArrayList<Group>();
			for (int i = 0; i < groups.size(); i++) {
				Group gp = groups.get(i);
				if(gp.isInArea(selectAreaStart, new Point(x,y))) {
					canvas.addSelectedObjs(gp);
					selectedGroups.add(gp);
				}
			}
			//find BasicObj
			for (int i = 0; i < objects.size(); i++) {
				BasicObject obj = objects.get(i);
				if(obj.isInArea(selectAreaStart, new Point(x,y))) {
					if(!isInSelectedGroup(obj, selectedGroups)) {
						canvas.addSelectedObjs(obj);
					}
				}
			}
		}
		this.selectAreaStart = null;
		canvas.cleanSelectedArea();
		canvas.repaint();
	}
	
	private boolean isInSelectedGroup(SelectableObject obj, List<Group> selectedGroups) {
		boolean flag = false;
		for(int i = 0; i < selectedGroups.size(); i++) {
			Group gp = selectedGroups.get(i);
			if(gp.getMembers().contains(obj)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
