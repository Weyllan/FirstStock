/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Charts.*;
import IA.myIA;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import plugginLoad.CashPlugin;
import controller.DBAccess;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

/**
 *
 * @author kieffersarah
 */
public class CashMenu extends PluginStyle {

    private CashMenu cash = this;
    private JButton exporter = new JButton("Exporter");
    private JButton choix = new JButton("Choix");

    public CashMenu(String name, WorkSpace workSpace, Window window) {
        super(name, workSpace, window);
        this.init();
        
    }

    public void init() {
        button.addActionListener(new EventAccess());
        myIA IA = new myIA();

        JPanel bottom = new JPanel();
        bottom.add(exporter);
        bottom.add(choix);
        this.setLayout(new BorderLayout(0,0));
                this.add(bottom, BorderLayout.SOUTH );
        this.add(IA.makePredictionStock("clavier"), BorderLayout.CENTER);
        


    }

    public class EventAccess implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent clic) {
            cash.loadMenu();
        }
    }

    public void loadMenu() {
        plugin.getWorkSpace().getContentPane().removeAll();
        //myIA IA = new myIA();
        //cash.setContentPane(IA.makePrediction());
        //plugin.addItem(plugin, IA.makePrediction(), 1, 0, 50, 50,GridBagConstraints.CENTER);
        //plugin.add(IA.makePrediction());
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
