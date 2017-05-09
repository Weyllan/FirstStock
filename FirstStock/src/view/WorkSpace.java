package view;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;


public class WorkSpace extends JInternalFrame{

    private MainMenu mainMenu = null;
    public Window window = null;
    private PluginStyle plugin = null;

    public WorkSpace(Window window){
        super("main Menu");
        this.window = window;
        this.setLayout(new BorderLayout(0, 0));
        init();
        this.add(mainMenu, BorderLayout.CENTER);
        this.setVisible(true);
        
    }
    
    public void init(){
        mainMenu = new MainMenu("main menu", this, window);
    }
    
    public void setPlugin(PluginStyle plugin){
        this.plugin= plugin;
    }
    
    public PluginStyle getPlugin(){
        return plugin;
    }
}
