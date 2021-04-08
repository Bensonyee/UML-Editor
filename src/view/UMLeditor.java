package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class UMLeditor extends JFrame{
	
	private MenuBar menubar;
	private ToolBar toolbar;
	private Canvas canvas;
	
	public UMLeditor(){
		this.setLayout(new BorderLayout());
		this.initCanvas();
		this.initMenuBar();
		this.initToolBar();
		this.initEditorFrame();
		
	}
	
	private void initEditorFrame() {
		//this.getContentPane().setLayout(null);
		this.setTitle("UML Editor");
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initCanvas() {
		canvas = new Canvas();
		this.add(canvas, BorderLayout.CENTER);
	}
	
	private void initMenuBar() {
		menubar = new MenuBar(canvas);
		this.setJMenuBar(menubar);
	}
	
	private void initToolBar() {
		toolbar = new ToolBar(canvas);
		this.add(toolbar, BorderLayout.WEST);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UMLeditor();
	}

}
