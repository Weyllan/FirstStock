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
public class Product {
    private String name;
    private double price;
    private Product[] ingredients;
    private int quantity;
        
    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        Product[] empty = {};
        this.ingredients = empty;
        this.quantity = quantity;
    }
    
    public Product(String name, double price, Product[] ingredients, int quantity){
        this(name, price, quantity);
        this.ingredients = ingredients;
    }
    
    public double cost(){
        double f = getQuantity()* getPrice();
        return f;
    }
    
    public String getName(){        
        return name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public Product[] getIngredients(){
        return ingredients;
    }
    
    public int getQuantity(){
        return quantity;
    }
}
