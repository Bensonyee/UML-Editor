package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import mode.*;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar{
	Properties prop = new Properties();
    InputStream input = null;
    private ToolBarBtn selectedBtn = null;
    private String CONFIG_FILE = "config.properties";
    private Canvas canvas;
    
    ToolBar(Canvas canvas){
    	this.canvas = canvas;
    	this.loadProperty();
    	//System.out.print(prop.getProperty("icon.select"));
    	this.setLayout(new GridLayout(6,1,2,2));
    	this.setBackground(new Color(20,20,20));
    	this.setFloatable(false);
    	
    	ImageIcon selectIcon = new ImageIcon(new ImageIcon(prop.getProperty("icon.select")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ToolBarBtn selectBtn = new ToolBarBtn("select",selectIcon, new SelectMode());
		this.add(selectBtn);
		
		ImageIcon associationIcon = new ImageIcon(new ImageIcon(prop.getProperty("icon.association")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ToolBarBtn associationBtn = new ToolBarBtn("association",associationIcon, new CreateAssociationMode());
		this.add(associationBtn);
		
		ImageIcon generalizationIcon = new ImageIcon(new ImageIcon(prop.getProperty("icon.generalization")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ToolBarBtn generalizationBtn = new ToolBarBtn("generalization",generalizationIcon, new CreateGeneralizationMode());
		this.add(generalizationBtn);
		
		ImageIcon compositionIcon = new ImageIcon(new ImageIcon(prop.getProperty("icon.composition")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ToolBarBtn compositionBtn = new ToolBarBtn("composition",compositionIcon, new CreateCompositionMode());
		this.add(compositionBtn);
		
		ImageIcon classIcon = new ImageIcon(new ImageIcon(prop.getProperty("icon.class")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ToolBarBtn classBtn = new ToolBarBtn("class",classIcon, new CreateClassMode());
		this.add(classBtn);
		
		ImageIcon useCaseIcon = new ImageIcon(new ImageIcon(prop.getProperty("icon.use_case")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ToolBarBtn useCaseBtn = new ToolBarBtn("use case",useCaseIcon, new CreateUseCaseMode());
		this.add(useCaseBtn);
    }
    
    private void loadProperty() {
    	try {

            input = new FileInputStream(CONFIG_FILE);
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
	private class ToolBarBtn extends JButton{
		Mode btnMode;
		public ToolBarBtn(String ToolName, ImageIcon icon, Mode mode) {
			this.btnMode = mode;
			setToolTipText(ToolName);
			setIcon(icon);
			setFocusable(false);
			setPreferredSize(new Dimension(120,120));
			setBackground(new Color(255, 255, 255));
			setBorderPainted(false);
			setRolloverEnabled(true);
			addActionListener(new toolListener());
		}
		class toolListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedBtn != null)
					selectedBtn.setBackground(new Color(255,255,255));
				selectedBtn = (ToolBarBtn) e.getSource();
				selectedBtn.setBackground(new Color(100,100,100));
				canvas.cleanSelectedObj();
				canvas.repaint();
				canvas.setCurrentMode(btnMode);
			}
		}
	}
    
}
