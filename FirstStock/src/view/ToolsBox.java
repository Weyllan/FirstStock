package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;



public class ToolsBox extends JToolBar{
    public JPanel pane = new JPanel();
    public JScrollPane scroll = null;
    
    public ToolsBox(String str){
        super(str);
        init();
    }
    
    public void init(){        
        scroll = new JScrollPane(pane);
        pane.setLayout(new GridBagLayout());
        this.add(scroll);
        this.addPropertyChangeListener((java.beans.PropertyChangeEvent evt) -> {
            String propName = evt.getPropertyName();
            if ("orientation".equals(propName)) {
                Integer oldValue = (Integer) evt.getOldValue();
                Integer newValue = (Integer) evt.getNewValue();
                if (newValue.intValue() == JToolBar.HORIZONTAL) {
                    System.out.println("horizontal orientation");
                    for(int i=0; i<pane.getComponentCount();i++){
                        changeItem(pane, pane.getComponent(0),i,0,1,1,GridBagConstraints.CENTER);
                    }
                } else {
                    System.out.println("vertical orientation");
                    for(int i=0; i<pane.getComponentCount();i++){
                        changeItem(pane, pane.getComponent(0),0,i,1,1,GridBagConstraints.CENTER);
                        
                    }
                }
            }
        });
        
        pane.setVisible(true);
        this.setFloatable(true);
    }
        
    public static void changeItem(JPanel t, Component c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        t.add(c, gc);
    }
    
    public void addItem(JPanel t, Component c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        t.add(c, gc);
    }

}
