package mode;

import object.UseCaseObject;
import view.Canvas;

public class CreateUseCaseMode extends Mode {
	UseCaseObject obj;
	
	public CreateUseCaseMode(){
		
	}
	
	@Override
	public void onMouseRelease(Canvas canvas,int x,int y) {
		obj = new UseCaseObject(x,y);
		canvas.addObject(obj);
		canvas.repaint();
	}	
}
