package view;

import javax.swing.JMenuBar;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	Canvas canvas;
	public MenuBar(Canvas canvas) {
		this.canvas = canvas;
		
		JMenu FileMenu = new JMenu("File");
		this.add(FileMenu);
		
		JMenu EditMenu = new JMenu("Edit");
		JMenuItem change_obj_name_mitem = new JMenuItem("Change Object Name");
		change_obj_name_mitem.addActionListener(new ChangeNameListener());
		EditMenu.add(change_obj_name_mitem);
		JMenuItem group_mitem = new JMenuItem("Group");
		group_mitem.addActionListener(new GroupListener());
		EditMenu.add(group_mitem);
		JMenuItem ungroup_mitem = new JMenuItem("Ungroup");
		ungroup_mitem.addActionListener(new UnGroupListener());
		EditMenu.add(ungroup_mitem);
		this.add(EditMenu);
	}
	
	class GroupListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.doGroup();
		}
	}
	
	class UnGroupListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.unGroup();
		}
	}
	
	class ChangeNameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(canvas.canChangeName()) {
				changeName(canvas.getSelectedObjectName());
			}
		}
	}
	private void changeName(String originalName) {
		JFrame inputTextFrame = new JFrame("Change Name");
		inputTextFrame.setSize(300, 100);
		inputTextFrame.getContentPane().setLayout(new GridLayout(0, 1));
		
		JPanel panel = null;
		panel = new JPanel();
		
		
		JTextField newName =  new JTextField(originalName);
		newName.setPreferredSize(new Dimension(200, 24));
		panel.add(newName);
		inputTextFrame.getContentPane().add(panel);
		
		panel = new JPanel();
		
		
		JButton confirm = new JButton("OK");
		JButton cancel = new JButton("Cancel");
		panel.add(confirm);
		panel.add(cancel);

		inputTextFrame.getContentPane().add(panel);
		inputTextFrame.setLocationRelativeTo(null);
		inputTextFrame.setVisible(true);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.changeName(newName.getText());
				inputTextFrame.dispose();
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputTextFrame.dispose();
			}
		});
		
		
	}
}
