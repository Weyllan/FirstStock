package view;

import IA.myIA;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import plugginLoad.CashPlugin;
import java.awt.BorderLayout;
import java.io.File;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class CashMenu extends PluginStyle {

    private CashMenu cash = this;
    private JPanel pane = null;
    private JButton exporter = new JButton("Exporter");
    private JButton choix = new JButton("Choix");
    private JPanel bottom = new JPanel();

    public CashMenu(String name, WorkSpace workSpace, Window window) {
        super(name, workSpace, window);
        this.init();
    }

    public void init() {
        button.addActionListener(new CashMenu.EventAccess());
        myIA IA = new myIA();
        exporter.addActionListener(new CashMenu.EventExport());
        bottom.add(exporter);
        bottom.add(choix);
        this.setLayout(new BorderLayout(0,0));
        pane = IA.makePredictionTresorerie();
        this.add(pane, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH );
    }

    public class EventAccess implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent clic) {
            cash.loadMenu();
        }
    }
    
    public class EventExport implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent clic) {
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export du graphique de la trésorerie");   
            
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                System.out.println(fileToSave.getAbsolutePath());
                myIA IA = new myIA();
                IA.setPath(fileToSave.getAbsolutePath());
                
                IA.makePredictionTresorerie();
            }
        }
    }

    public void loadMenu() {        
        plugin.getWorkSpace().getPlugin().button.setEnabled(true);
        plugin.getWorkSpace().setPlugin(plugin);
        plugin.button.setEnabled(false);
        plugin.getWorkSpace().getContentPane().removeAll();
        plugin.add (bottom, BorderLayout.SOUTH);
        plugin.add(pane, BorderLayout.CENTER);
        plugin.getWorkSpace().setContentPane(plugin);
        plugin.getWorkSpace().getContentPane().validate();
        plugin.getWorkSpace().setTitle(plugin.name);
        plugin.setVisible(true);
        plugin.getToolsBox().pane.removeAll();
        
        System.out.println("view.Window.load : " + name);
        if (this.window.files.size() > 0) {
            this.window.pluginsLoader.setFiles(this.window.convertArrayListToArrayString(this.window.files));
            try {
                this.loadPlugins(this.window.pluginsLoader.loadAllCashPlugins());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        plugin.getToolsBox().pane.validate();

    }

    public void loadPlugins(CashPlugin[] cashplugins) {
        for (int index = 0; index < cashplugins.length; index++) {
            this.window.cashPlugins.add(cashplugins[index]);
            PluginStyle c = new PluginStyle(cashplugins[index].getLibelle(), workSpace, window);
        }
    }

    @Override
    public void addToTools() {
        this.getWindow().addJMenu(button);

    }
        
}
