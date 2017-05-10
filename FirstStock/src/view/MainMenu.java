package view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class MainMenu extends PluginStyle {
    
    private StockMenu stockMenu = null;
    private CashMenu cashMenu = null;
    private SaleMenu saleMenu = null;
    private JButton stock = new JButton("Stocks");
    private JButton cash = new JButton("Trésorerie");
    private JButton sale = new JButton("Ventes");
    private MainMenu main = this;
    
    public MainMenu(String name, WorkSpace workSpace, Window window){
        super(name, workSpace, window);
        this.cashMenu = new CashMenu("cash menu", workSpace, window);
        this.stockMenu = new StockMenu("stock menu", workSpace, window);
        this.saleMenu = new SaleMenu("sale menu", workSpace, window);
        this.init();
        this.initMainMenu();
        this.addElements();
        this.getWorkSpace().getContentPane().validate();
        this.setVisible(true);
    }
    
    
    public void init(){
        button.addActionListener(new EventAccess());
        this.getWorkSpace().setPlugin(this);
        button.setEnabled(false);
    }
    
    public class EventAccess implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            main.loadMenu();
        }
    }
    
    public void loadMenu(){
        plugin.addElements();
        plugin.getWorkSpace().getContentPane().removeAll();
        plugin.getWorkSpace().getPlugin().button.setEnabled(true);
        plugin.getWorkSpace().setPlugin(plugin);
        plugin.button.setEnabled(false);
        plugin.getWorkSpace().setContentPane(plugin);
        plugin.getWorkSpace().getContentPane().validate();
        plugin.getWorkSpace().setTitle(plugin.name);
        plugin.setVisible(true);
        plugin.getToolsBox().pane.removeAll();
        plugin.getToolsBox().validate();
        System.out.println("view.Window.load : " + name);
    }
    
    
    @Override
    public void addToTools(){ 
        this.getWindow().addJMenu(button);
    }
    
    public void initMainMenu(){
        stock.addActionListener(new EventStock());
        cash.addActionListener(new EventCash());
        sale.addActionListener(new EventSale());
    }

    /*
    * Chargement du menu des Stocks
    */
    public class EventStock implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            stockMenu.loadMenu();
        }
    }
    
    /*
    * Chargement du menu Trésorerie
    */
    public class EventCash implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            cashMenu.loadMenu();
        }
    }
    
    /*
    * Chargement du menu Ventes
    */
    public class EventSale implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            saleMenu.loadMenu();
        }
    }
    
    @Override
    public void addElements(){
        plugin.addItem(plugin, stock, 0, 0, 1, 1,GridBagConstraints.CENTER);
        plugin.addItem(plugin, cash, 1, 0, 1, 1,GridBagConstraints.CENTER);
        plugin.addItem(plugin, sale, 2, 0, 1, 1,GridBagConstraints.CENTER);
    }
}

