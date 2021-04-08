package mode;

import object.AssociationLine;
import object.Line;

public class CreateAssociationMode extends CreateLineMode{

	
	public CreateAssociationMode(){
		
	}

	@Override
	public Line lineFactory(int x1, int y1, int x2, int y2) {
		return new AssociationLine(x1, y1, x2, y2);
	}
	
	
}
