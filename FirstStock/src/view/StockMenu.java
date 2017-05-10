package view;

import IA.myIA;
import controller.DBAccess;
import firststock.FirstStock;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import plugginLoad.StockPlugin;

/**
 *
 * @author kieffersarah
 */
public class StockMenu extends PluginStyle implements ActionListener {

    private final FirstStock appli = new FirstStock();
    private final DBAccess dbAccess = new DBAccess(appli.getUrl(), appli.getUsername(), appli.getPwd());

    private StockMenu stock = this;
    private JPanel pane = null;

    private JButton exporter = new JButton("Exporter");
    private JButton choix = new JButton("Choix");
    private JComboBox productList;
    private JButton selectProduct, cancel;
    String newProduct;
    private JPanel bottom = new JPanel();
    JDialog chooseProduct;
    myIA IA = new myIA();

    public StockMenu(String name, WorkSpace workSpace, Window window) {
        super(name, workSpace, window);
        this.init();
    }

    public void init() {
        button.addActionListener(new EventAccess());
        exporter.addActionListener(new StockMenu.EventExport());
        choix.addActionListener(this);
        bottom.add(exporter);
        bottom.add(choix);
//newProduct
        this.setLayout(new BorderLayout(0, 0));
        pane = IA.makePredictionStock(newProduct);
        this.add(pane, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        

    }

    public class EventAccess implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent clic) {
            stock.loadMenu();
        }
    }

    public class EventExport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent clic) {
            /*
            JFrame parentFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export du graphique des stocks");   
            
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                System.out.println(fileToSave.getAbsolutePath());
                myIA IA = new myIA();                
                //IA.setFile(fileToSave);
                IA.makePredictionStock(newProduct);
                
            }
             */
            myIA IA = new myIA();
            File file = new File("stock.jpeg");
            IA.setFile(file);
            IA.makePredictionStock(newProduct);
        }
    }

    public void personnalProducts() {
        try {
            ArrayList myExistProducts = this.dbAccess.selectAllRawsStocks();
            Vector myProducts = new Vector();

            for (int i = 0; i < myExistProducts.size(); i++) {
                myProducts.addElement(myExistProducts.get(i).toString());
            }

            chooseProduct = new JDialog();
            chooseProduct.setTitle("Sélectionnez le produit");
            chooseProduct.setSize(300, 100);
            chooseProduct.setLocationRelativeTo(null);
            chooseProduct.setResizable(false);

            productList = new JComboBox(myProducts);

            JPanel control = new JPanel();
            this.selectProduct = new JButton("Confirmer");
            this.selectProduct.addActionListener(this);
            this.cancel = new JButton("Annuler");
            this.cancel.addActionListener(this);

            control.add(this.cancel);
            control.add(this.selectProduct);

            chooseProduct.add(productList, BorderLayout.CENTER);
            chooseProduct.add(control, BorderLayout.SOUTH);
            chooseProduct.setVisible(true);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadMenu() {
            plugin.getWorkSpace().getPlugin().button.setEnabled(true);
        plugin.getWorkSpace().setPlugin(plugin);
        plugin.button.setEnabled(false);
        plugin.getWorkSpace().getContentPane().removeAll();
        plugin.add(bottom, BorderLayout.SOUTH);
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
                this.loadPlugins(this.window.pluginsLoader.loadAllStockPlugins());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        plugin.getToolsBox().pane.validate();
    }

    public void loadPlugins(StockPlugin[] stockplugins) {

        for (int index = 0; index < stockplugins.length; index++) {
            this.window.stockPlugins.add(stockplugins[index]);
            PluginStyle c = new PluginStyle(stockplugins[index].getLibelle(), workSpace, window);
        }
    }

    @Override
    public void addToTools() {
        this.getWindow().addJMenu(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.choix) {
            personnalProducts();
        }

        if (e.getSource() == this.cancel) {
            chooseProduct.dispose();
        }

        if (e.getSource() == this.selectProduct) {
            chooseProduct.dispose();
            newProduct = productList.getSelectedItem().toString();
            this.pane = null;
            this.pane = IA.makePredictionStock(newProduct);
        }
    }

}
