package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



import mode.Mode;
import object.BasicObject;
import object.Group;
import object.Line;
import object.SelectableObject;
import object.SelectedArea;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private Mode currentMode = null;
	private List<BasicObject> objects = new ArrayList<BasicObject>();
	private List<Group> groups = new ArrayList<Group>();
	private List<Line> lines = new ArrayList<Line>();
	private Line drawingLine = null;
	private List<SelectableObject> selectedObjs = new ArrayList<SelectableObject>();
	private SelectedArea selectedArea = null;
	
	public MouseAdapter CanvasMouseAdapter;
	Canvas(){
		this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(new Color(255,255,255));
        CanvasMouseAdapter = (new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentMode == null)
                    return;
                onMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {  
                if (currentMode == null)
                    return;
                onMouseReleased(e);
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
            	if (currentMode == null)
                    return;
                onMouseDragged(e);
            }
        });
        this.addMouseListener(CanvasMouseAdapter);
        this.addMouseMotionListener(CanvasMouseAdapter);
	}

	
	private void onMousePressed(MouseEvent e) {
		currentMode.onMousePress(this, e.getX(), e.getY());
	}
	
	private void onMouseReleased(MouseEvent e) {
		currentMode.onMouseRelease(this,e.getX(),e.getY());
	}
	
	private void onMouseDragged(MouseEvent e) {
		currentMode.onMouseDrag(this, e.getX(), e.getY());
	}
	
	public void setCurrentMode(Mode mode) {
		this.currentMode = mode;
	}
	
	public void addObject(BasicObject obj) {
		this.objects.add(obj);
	}
	public void setDrawingLine(Line l) {
		this.drawingLine = l;
	}
	public void setSelectedArea(SelectedArea s) {
		this.selectedArea = s;
	}
	
	public void cleanSelectedArea() {
		this.selectedArea = null;
	}
	
	public List<BasicObject> getObjects(){
		return this.objects;
	}
	
	public List<Group> getGroups() {
		return this.groups;
	}
	
	public void cleanDrawingLine() {
		this.drawingLine = null;
	}
	
	public void addLine(Line l) {
		this.lines.add(l);
	}
	
	public void addSelectedObjs(SelectableObject obj) {
		this.selectedObjs.add(obj);
	}
	
	public List<SelectableObject> getSelectedObjects(){
		return this.selectedObjs;
	}
	
	public void cleanSelectedObj() {
		this.selectedObjs.clear();
	}
	
	
	
	public void doGroup() {
		if(this.selectedObjs.size() >= 2) {
			Group gp = new Group(selectedObjs);
			groups.add(gp);
			this.addSelectedObjs(gp);
			this.repaint();
		}
	}
	
	public boolean canChangeName(){
		if(selectedObjs.size() == 1) {
			if(objects.contains(selectedObjs.get(0))) {
				return true;
			}
		}
		return false;
	}
	
	public String getSelectedObjectName(){
		if(canChangeName()) {
			BasicObject obj = (BasicObject)selectedObjs.get(0);
			return obj.getName();
		}
		return null;
	}
	
	public void changeName(String s) {
		if(canChangeName()) {
			BasicObject obj = (BasicObject)selectedObjs.get(0);
			obj.setName(s);
			this.repaint();
		}
	}
	
	public void unGroup() {
		if(this.selectedObjs.size() == 1) {
			//if it's indeed a fucking group
			if(this.groups.remove(this.selectedObjs.get(0))){
				Group removedGP =  (Group)this.selectedObjs.get(0);
				List<SelectableObject> child = removedGP.getMembers();
				cleanSelectedObj();
				for(int i = 0; i < child.size(); i++) {
					this.addSelectedObjs(child.get(i));
				}
				this.repaint();
			}

		}
	}
	
	public void resetLines() {
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).updatePosition();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension dim = getSize();
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, dim.width, dim.height);
		
		Color paintingColor = new Color(20,20,120);
		g.setColor(paintingColor);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		
		
		for (int i = 0; i < objects.size(); i++) {
			BasicObject obj = objects.get(i);
			obj.draw(g);
			//obj.showPorts(g);
		}
		for (int i = 0; i < lines.size(); i++) {
			Line line = lines.get(i);
			line.draw(g);
		}
		if(selectedObjs.size() > 0) {
			for(int i = 0; i < selectedObjs.size(); i++) {
				selectedObjs.get(i).drawSelectedHint(g);
			}
		}
		
		if(drawingLine != null) {
			drawingLine.draw(g);
		}
		
		if(selectedArea != null) {
			selectedArea.draw(g);
		}
	}
}
