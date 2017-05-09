package view;

import IA.myIA;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author kieffersarah
 */
public class SaleMenu extends PluginStyle implements ActionListener {

    JDialog chooseProduct;
    private SaleMenu sale = this;
    private JPanel pane = null;
    private JButton exporter = new JButton("Exporter");
    private JButton choix = new JButton("Choix");
    private JComboBox productList; 
    private JButton selectProduct, cancel;
    String newProduct;

    public SaleMenu(String name, WorkSpace workSpace, Window window) {
        super(name, workSpace, window);
        this.init();
    }

    public void init() {
        button.addActionListener(new SaleMenu.EventAccess());
        myIA IA = new myIA();

        exporter.addActionListener(new SaleMenu.EventExport());

        this.setLayout(new BorderLayout(0,0));
        pane = IA.makePredictionVente("ordinateur");
        this.add(pane, BorderLayout.CENTER);

    }

    public class EventAccess implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent clic) {
            sale.loadMenu();
        }
    }
    public class EventExport implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent clic) {
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export du graphique des ventes");   
            
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.cancel) {
            chooseProduct.dispose();
        }

        if (e.getSource() == this.selectProduct) {
            chooseProduct.dispose();
            newProduct = productList.getSelectedItem().toString();
        }
    }

    public void loadMenu() {
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
        /*if (this.window.files.size() > 0) {
            this.window.pluginsLoader.setFiles(this.window.convertArrayListToArrayString(this.window.files));
            try {
                this.loadPlugins(this.window.pluginsLoader.loadAllSalePlugins());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        plugin.getToolsBox().pane.validate();

    }

    /*public void loadPlugins(SalePlugin[] saleplugins) {
        for (int index = 0; index < saleplugins.length; index++) {
            this.window.salePlugins.add(saleplugins[index]);
            PluginStyle c = new PluginStyle(saleplugins[index].getLibelle(), workSpace, window);
        }
    }*/
    @Override
    public void addToTools() {
        this.getWindow().addJMenu(button);
    }
}
