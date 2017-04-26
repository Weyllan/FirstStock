/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

/**
 *
 * @author kieffersarah
 */
public class Window extends JFrame {
    
    private int sizeX=600;
    private int sizeY=500;
    
    private ToolsBox toolsBox = new ToolsBox("Tools");
    public JMenuBar menuBar = new JMenuBar();
    private WorkSpace workSpace = new WorkSpace(this);
    private JDesktopPane desktop = new JDesktopPane();

    
    public Window(String str){
        super(str);
    }
    
    public void init(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(getSizeX(), getSizeY());
        this.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane = new JScrollPane(getToolsBox());
        this.add(scrollPane, BorderLayout.WEST);
        this.add(menuBar, BorderLayout.NORTH);
        this.add(getWorkSpace(), BorderLayout.CENTER);

        this.setVisible(true); 
    }
    
    public void addJMenu(Component comp){
        menuBar.add(comp);
    }
    
    /**
     * @return the sizeX
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * @param sizeX the sizeX to set
     */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    /**
     * @return the sizeY
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * @param sizeY the sizeY to set
     */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    /**
     * @return the toolsBox
     */
    public ToolsBox getToolsBox() {
        return toolsBox;
    }

    /**
     * @param toolsBox the toolsBox to set
     */
    public void setToolsBox(ToolsBox toolsBox) {
        this.toolsBox = toolsBox;
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
     * @param menuBar the menuBar to set
     */
    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }
}
