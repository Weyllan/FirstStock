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
public class Product{
    private String name;
    private double price;
    private Product[] ingredients;
    private int quantity;
        
    public Product(String name, double price, int quantity) throws Exception {
        this.name = name;
        try{
            if(price < 0 || quantity < 1)
               throw new Exception();
            this.price = price;
            this.quantity = quantity;
        }
        catch(Exception e){
           System.err.printf("Error on price/quantity number");
        }
        Product[] empty = {};
        this.ingredients = empty;
    }
    
    public Product(String name, double price, Product[] ingredients, int quantity) throws Exception{
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
