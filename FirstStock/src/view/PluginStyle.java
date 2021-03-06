package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class PluginStyle extends JPanel{
    protected WorkSpace workSpace = null;
    protected Window window = null;
    protected String name;
    protected PluginStyle plugin = this;
    protected JButton button;
    
    public PluginStyle(String name, WorkSpace workSpace, Window window){
        this.workSpace = workSpace;
        this.window = window;
        this.name = name;
        this.button = new JButton(name);
        this.button.addActionListener(this.window);
        this.button.setToolTipText(name);
        this.setLayout(new GridBagLayout());
        this.addToTools();
    }
    
    
    public void addElements(){
        
    }
    
    public void addToTools(){
        int i = this.getToolsBox().pane.getComponentCount();
        if(this.getToolsBox().getOrientation()==JToolBar.HORIZONTAL){
            this.getToolsBox().addItem(this.getToolsBox().pane, button, i, 0, 1, 1, GridBagConstraints.CENTER);
        }else{
            this.getToolsBox().addItem(this.getToolsBox().pane, button, 0, i, 1, 1, GridBagConstraints.CENTER);
        }
    }

    /**
     * @return the workSpace
     */
    public WorkSpace getWorkSpace() {
        return workSpace;
    }

    /**
     * @param workSpace the workSpace to set
     */
    public void setWorkSpace(WorkSpace workSpace) {
        this.workSpace = workSpace;
    }

    /**
     * @return the window
     */
    public Window getWindow() {
        return window;
    }

    /**
     * @param window the window to set
     */
    public void setWindow(Window window) {
        this.window = window;
    }
    
    public ToolsBox getToolsBox(){        
        return this.getWindow().getToolsBox();
    }
    
    public void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
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
        p.add(c, gc);
    }
    

}
