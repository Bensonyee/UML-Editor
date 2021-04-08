package mode;

import view.Canvas;
import object.ClassObject;

public class CreateClassMode extends Mode{
	ClassObject obj;
	
	public CreateClassMode(){
		
	}
	
	@Override
	public void onMouseRelease(Canvas canvas,int x,int y) {
		obj = new ClassObject(x,y);
		canvas.addObject(obj);
		canvas.repaint();
	}

}
