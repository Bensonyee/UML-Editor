package mode;

import object.CompositionLine;
import object.Line;

public class CreateCompositionMode extends CreateLineMode{
	public CreateCompositionMode(){
		
	}

	@Override
	public Line lineFactory(int x1, int y1, int x2, int y2) {
		return new CompositionLine(x1, y1, x2, y2);
	}
}
