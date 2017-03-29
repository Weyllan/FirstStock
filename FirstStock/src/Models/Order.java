/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
/**
 *
 * @author louis
 */
public class Order {

    private static int counter = 0;
    private final int id;
    private Product[] list;
    private Transaction transaction;
    
    // Format date
    public Date date;
    public final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public Order(Product[] list, Transaction transaction){
        this.list = list;
        this.transaction = transaction;
        Order.counter++;
        this.id = Order.counter;
        
        // Création date
        this.date = new Date();
       
    }
    
    public void printDate(){
         System.out.println(dateFormat.format(date));
    }
    
    public double totalCost(){
        double f = 0;
        for (Product list : getList()) {
            f += list.getPrice() * list.getQuantity();
        }
        return f;
    }
    
    public Product[] getList(){
        return list;
    }
    
    public int getId(){
        return id;
    }
    
    public Transaction getTransaction(){
        return transaction;
    }

    /**
     * setDate
     * @param str
     * @throws ParseException 
     */
    public void setDate(String str) throws ParseException{
        try {
            Date newDate = this.dateFormat.parse(str);
            this.setDate(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * setDate
     * @param newDate 
     */
    public void setDate(Date newDate){
        this.date = newDate;
    }
}
