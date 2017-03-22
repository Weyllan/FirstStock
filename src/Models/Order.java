/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author louis
 */
public class Order {

    private static final int id = 0;
    private Product[] list;
    private Transaction transaction;

    public Order(Product[] list, Transaction transaction){
        this.list = list;
        this.transaction = transaction;
    }
    
    public double totalCost(){
        double f = 0;
        for(int i = 0 ; i < getList().length ;i++){
            f += getList()[i].getPrice() *  getList()[i].getQuantity();
        }
        return f;
    }
    
    public Product[] getList(){
        return list;
    }
    
    public Transaction getTransaction(){
        return transaction;
    }
    
    
}
