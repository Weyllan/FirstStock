/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 *
 * @author valentin
 */

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class JCheckList{

    public ArrayList checkArray = new ArrayList();
    public Vector<CheckListItem> vect = new Vector();
    public boolean empty = false;
    public JList list;
    public JCheckList(ArrayList array) {

        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                vect.addElement(new CheckListItem(array.get(i).toString()));
            }
            this.list = new JList(this.vect);
            this.list.setCellRenderer(new CheckListRenderer());
            this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.list.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    JList list = (JList) event.getSource();
                    int index = list.locationToIndex(event.getPoint());// Get index of item
                    // clicked
                    CheckListItem item = (CheckListItem) list.getModel()
                            .getElementAt(index);
                    item.setSelected(!item.isSelected()); // Toggle selected state
                    list.repaint(list.getCellBounds(index, index));// Repaint cell
                }
            });
        } else {
            empty = true;
        }
    }
    
    public ArrayList getCheckArray(){
        for (int i = 0; i < vect.size(); i++) {
            if(vect.get(i).isSelected()){
                checkArray.add(vect.get(i).toString());
            }
        }
        return this.checkArray;
    }
}

class CheckListItem {

    private String label;
    private boolean isSelected = false;

    public CheckListItem(String label) {
        this.label = label;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return label;
    }
}

class CheckListRenderer extends JCheckBox implements ListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean hasFocus) {
        setEnabled(list.isEnabled());
        setSelected(((CheckListItem) value).isSelected());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(value.toString());
        return this;
    }
}
