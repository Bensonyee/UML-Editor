package mode;

import java.awt.Point;
import java.util.List;

import object.Line;
import object.BasicObject;
import object.Port;
import view.Canvas;

public abstract class CreateLineMode extends Mode {
	private List<BasicObject> objects;
	private BasicObject startObj, endObj;
	private int startPortIdx = -1, endPortIdx = -1;
	
	public abstract Line lineFactory(int x1, int y1, int x2, int y2);
	
	@Override
	public void onMousePress(Canvas canvas,int x, int y) {
		this.objects = canvas.getObjects();
		for (int i = objects.size() - 1; i >= 0; i--) {
			BasicObject obj = objects.get(i);
			int idx = obj.inWhichArea(new Point(x,y)); 
			if (idx != -1) {
				startPortIdx = idx;
				startObj = obj;
				break;
			}
		}
	}
	
	@Override
	public void onMouseDrag(Canvas canvas, int x, int y) {
		if(startPortIdx != -1) {
			Port startPort = startObj.getPort(startPortIdx);
			Line l = lineFactory((int)startPort.getCenterX(), (int)startPort.getCenterY(),x,y);
			canvas.setDrawingLine(l);
			canvas.repaint();
		}
	}
	
	@Override
	public void onMouseRelease(Canvas canvas,int x, int y) {
		if(startPortIdx != -1) {
			for (int i = objects.size() - 1; i >= 0; i--) {
				BasicObject obj = objects.get(i);
				int idx = obj.inWhichArea(new Point(x,y)); 
				if (idx != -1) {
					endPortIdx = idx;
					endObj = obj;
					break;
				}
			}
			
			if(endPortIdx != -1 && startObj != endObj) {
				Port startPort = startObj.getPort(startPortIdx);
				Port endPort = endObj.getPort(endPortIdx);
				Line l = lineFactory((int)startPort.getCenterX(), (int)startPort.getCenterY(),(int)endPort.getCenterX(),(int)endPort.getCenterY());
				canvas.addLine(l);
				l.setStartPort(startObj, startPortIdx);
				l.setEndPort(endObj, endPortIdx);
			}
		}
		resetStatus();
		canvas.cleanDrawingLine();
		canvas.repaint();
	}
	
	private void resetStatus() {
		this.startObj = null;
		this.endObj = null;
		this.startPortIdx = -1;
		this.endPortIdx = -1;
	}
}
