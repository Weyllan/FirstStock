package view;

import IA.myIA;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import plugginLoad.StockPlugin;


public class StockMenu extends PluginStyle{
    
    private StockMenu stock = this;
    private JPanel pane = null;
    
    public StockMenu(String name, WorkSpace workSpace, Window window){
        super(name, workSpace, window);
        this.init();
    }
    
    public void init(){
        button.addActionListener(new EventAccess());
        myIA IA = new myIA();
        this.setLayout(new BorderLayout(0,0));
        pane = IA.makePredictionStock("clavier");
        this.add(pane, BorderLayout.CENTER);
    }
    
    public class EventAccess implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            stock.loadMenu();
        }
    }
    public class EventExport implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent clic) {
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export du graphique des stocks");   
            
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                // fileToSave.getAbsolutePath() renvoit un String indiquant le chemin
                /* 
                    Création d'un graph similaire et l'imprimer
                */
                
            }
        }
    }
    
    public void loadMenu(){
        plugin.getWorkSpace().getContentPane().removeAll();
        plugin.getWorkSpace().getPlugin().button.setEnabled(true);
        plugin.getWorkSpace().setPlugin(plugin);
        plugin.button.setEnabled(false);
        plugin.add(pane, BorderLayout.CENTER);
        plugin.getWorkSpace().setContentPane(plugin);
        plugin.getWorkSpace().getContentPane().validate();
        plugin.getWorkSpace().setTitle(plugin.name);
        plugin.setVisible(true);
        plugin.getToolsBox().pane.removeAll();
        
        System.out.println("view.Window.load : " + name);
        if(this.window.files.size() > 0){
            this.window.pluginsLoader.setFiles(this.window.convertArrayListToArrayString(this.window.files));
            try {
                this.loadPlugins(this.window.pluginsLoader.loadAllStockPlugins());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        plugin.getToolsBox().pane.validate();
    }
    
    public void loadPlugins(StockPlugin[] stockplugins){

        for (int index = 0; index < stockplugins.length; index++) {
            this.window.stockPlugins.add(stockplugins[index]);
            PluginStyle c = new PluginStyle(stockplugins[index].getLibelle(), workSpace, window);
        }
    }
    
    @Override
    public void addToTools(){ 
        this.getWindow().addJMenu(button);
    }
    
    
}
