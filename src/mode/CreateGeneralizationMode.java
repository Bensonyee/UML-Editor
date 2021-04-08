package mode;

import object.GeneralizationLine;
import object.Line;

public class CreateGeneralizationMode extends CreateLineMode{
	public CreateGeneralizationMode() {
		
	}

	@Override
	public Line lineFactory(int x1, int y1, int x2, int y2) {
		return new GeneralizationLine(x1,y1,x2,y2);
	}
	
}
