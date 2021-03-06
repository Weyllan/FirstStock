package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import plugginLoad.CashPlugin;
import plugginLoad.PluginsLoader;
import plugginLoad.StockPlugin;


public class Window extends JFrame implements ActionListener{
    
    private int sizeX=600;
    private int sizeY=500;
    
    private ToolsBox toolsBox = new ToolsBox("Tools");
    public JMenuBar menuBar = new JMenuBar();
    private WorkSpace workSpace = null;
    
    private JMenu fileMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem loadMenuItem;
    
    public PluginsLoader pluginsLoader;
    public ArrayList files = new ArrayList();
    public ArrayList stockPlugins = new ArrayList();
    public ArrayList cashPlugins = new ArrayList();

    
    public Window(String str){
        super(str);
        this.pluginsLoader = new PluginsLoader();
    }
    
    public void init(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(getSizeX(), getSizeY());
        this.setLayout(new BorderLayout(0, 0));
        this.add(toolsBox, BorderLayout.PAGE_START);
        this.setJMenuBar(menuBar);
        
        ImageIcon icon = new ImageIcon("fav.png");
        this.setIconImage(icon.getImage());
        
        this.fileMenu = new JMenu();
        this.exitMenuItem = new JMenuItem();
        this.loadMenuItem = new JMenuItem();
        
        //fileMenu
	this.fileMenu.setText("Fichier");
	this.fileMenu.add(this.loadMenuItem);
	this.fileMenu.addSeparator();
	this.fileMenu.add(this.exitMenuItem);
        //exitMenuItem
	this.exitMenuItem.setText("Fermer");
	this.exitMenuItem.addActionListener(this);
		
	//loadMenuItem
	this.loadMenuItem.setText("Charger un plugin");
	this.loadMenuItem.addActionListener(this);
        
        menuBar.add(fileMenu);
        
        this.workSpace = new WorkSpace(this);
        this.add(getWorkSpace(), BorderLayout.CENTER);
        
        Dimension dim = new Dimension(500,300);
        this.setMinimumSize(dim);

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
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // Quitte l'application
        if (arg0.getSource() == this.exitMenuItem) {
            System.exit(0);
        } else {
            // Charge les fichier jar
            if (arg0.getSource() == this.loadMenuItem) {
                JFileChooser f = new JFileChooser();
                if (f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    this.files.add(f.getSelectedFile().getAbsolutePath());
                }
            // Si ce n'est pas l'un des deux précédents, c'est un plugin
            } else {
                this.ActionFromPlugins(arg0);
            }
        }
    }
    
    public String[] convertArrayListToArrayString(ArrayList list) {
        String[] tmp = new String[list.size()];

        for (int index = 0; index < tmp.length; index++) {
            tmp[index] = (String) list.get(index);
        }

        return tmp;
    }
    
    /*  Fonction de détection de l'action d'un plugin, en comparant le nom du bouton avec les libelle de plugin  */
    private void ActionFromPlugins(ActionEvent e) {
        for (int index = 0; index < this.stockPlugins.size(); index++) {
            if (e.getActionCommand().equals(((StockPlugin) this.stockPlugins.get(index)).getLibelle())) {
                ((StockPlugin) this.stockPlugins.get(index)).actionOnStock();
                return;
            }
        }

        for (int index = 0; index < this.cashPlugins.size(); index++) {
            if (e.getActionCommand().equals(((CashPlugin) this.cashPlugins.get(index)).getLibelle())) {
                ((CashPlugin) this.cashPlugins.get(index)).actionOnCash();
                return;
            }
        }

    }
        
}
