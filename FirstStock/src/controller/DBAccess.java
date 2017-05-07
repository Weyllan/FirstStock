/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
/**
 *
 * @author valentin
 */
/* REMEMBER VAL : CHANGE NECESSARY WITH DELETE AND UPDATE, AND SET CASH  */

public class DBAccess {
    
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PWD;
    
    public DBAccess(String url, String username, String mdp){
        this.DB_URL = url;
        this.DB_USER = username;
        this.DB_PWD = mdp;
    }
    
    /*  Getters sur la BDD  */
    
    /*  Select All Products Name */
    public ArrayList selectAllProduct() throws SQLException{
        ArrayList myProducts = new ArrayList();
        Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT product_name FROM product_type";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String productName = rs.getString("product_name");
                myProducts.add(productName);
                System.out.println(productName);          
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return myProducts;
    }
    
    /* Select all raws names */
    public ArrayList selectAllRaws() throws SQLException{
        ArrayList myProducts = new ArrayList();
        Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT raw_name FROM raw_type";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String rawName = rs.getString("raw_name");
                myProducts.add(rawName);
                System.out.println(rawName);          
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return myProducts;
    }
    
    
    /*  Return Stock of ONE Product, wich is specified  */
    public int selectAProductStock(String product_type) throws SQLException {
        int mystock = -1;
	Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT product_stock FROM product_type WHERE product_name LIKE '"+product_type+"'";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            rs.next();
            String productStock = rs.getString("product_stock");
            mystock = Integer.parseInt(productStock);
            System.out.println("product stock : "+product_type+":" + productStock);
            return mystock;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return mystock;
    }
    
    /* Return all stocks of all products */
    public Hashtable selectAllProductsStocks() throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        Hashtable ht = new Hashtable();
	String selectTableSQL = "SELECT * from product_type";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String productName = rs.getString("product_name");
                String productStock = rs.getString("product_stock");
		System.out.println("product name : " + productName);
                System.out.println("product stock : " + productStock);
                ht.put(productName, Integer.parseInt(productStock));             
            }
            return ht;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return ht;
    }
    
    /*  Return Stock of ONE Raw, wich is specified  */
    public int selectARawStock(String raw_type) throws SQLException {
        int mystock = -1;
	Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT raw_stock FROM raw_type WHERE raw_name LIKE '"+raw_type+"'";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            rs.next();
            String rawStock = rs.getString("raw_stock");
            mystock = Integer.parseInt(rawStock);
            System.out.println("raw stock : " + rawStock);
            return mystock;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return mystock;
    }
    
    /*  Return Price of ONE Product, wich is specified  */
    public double selectAProductPrice(String product_type) throws SQLException {
        double mystock = -1;
	Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT price FROM product_type WHERE product_name LIKE '"+product_type+"'";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            rs.next();
            String productPrice = rs.getString("price");
            mystock = Double.parseDouble(productPrice);
            System.out.println("product price : " + productPrice);
            return mystock;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return mystock;
    }
    
    /*  Return Price of ONE Raw, wich is specified  */
    public double selectARawPrice(String raw_type) throws SQLException {
        double mystock = -1;
	Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT price_buy FROM raw_type WHERE raw_name LIKE '"+raw_type+"'";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            rs.next();
            String rawPrice = rs.getString("price_buy");
            mystock = Double.parseDouble(rawPrice);
            System.out.println("raw price : " + rawPrice);
            return mystock;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return mystock;
    }
    
    /* Return all stocks of all raws */
    public Hashtable selectAllRawsStocks() throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        Hashtable ht = new Hashtable();
	String selectTableSQL = "SELECT * from raw_type";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String rawName = rs.getString("raw_name");
                String rawStock = rs.getString("raw_stock");
		System.out.println("raw name : " + rawName);
                System.out.println("raw stock : " + rawStock);
                ht.put(rawName, Integer.parseInt(rawStock)); 
            }
            return ht;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return ht;
    }
    
    
    /* Return all stocks of all products */
    public ArrayList selectAllNecessaruRaw(String product_name) throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        ArrayList ht = new ArrayList();
	String selectTableSQL = "SELECT `raw_id` FROM `product_raw` WHERE `product_id` LIKE '"+product_name+"' ";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String rawName = rs.getString("raw_id");
		System.out.println("raw id : " + rawName);
                ht.add(rawName);
            }
            return ht;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return ht;
    }
    
    /* Return all stocks of all products */
    public int selectNecessaruRawQuantity(String product_name, String raw_name) throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        int myQuantity = 0;
	String selectTableSQL = "SELECT `quantity` FROM `product_raw` WHERE product_id = '"+product_name+"' AND raw_id = '"+raw_name+"' ";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String quantity = rs.getString("quantity");
                myQuantity = Integer.parseInt(quantity);
		System.out.println("raw id : " + myQuantity);
            }
            return myQuantity;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return myQuantity;
    }
      
    /* Return all stocks of all products */
    public Hashtable selectAllNecessaru(String product_name) throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        Hashtable ht = new Hashtable();
	String selectTableSQL = "SELECT `raw_id`,`quantity` FROM `product_raw` WHERE `product_id` LIKE '"+product_name+"' ";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String rawName = rs.getString("raw_id");
                String quantity = rs.getString("quantity");
		System.out.println("raw id : " + rawName);
                System.out.println("quantity: " + quantity);
                ht.put(rawName, Integer.parseInt(quantity)); 
            }
            return ht;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return ht;
    }
    
    /* Return Entreprise cash */
    public double getMyCash() throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        double mymonney = -1;
	String selectTableSQL = "SELECT quantity_cash from cash";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String mycash = rs.getString("quantity_cash");
                System.out.println("quantity : " + mycash);
                mymonney = Integer.parseInt(mycash);
                return mymonney;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return mymonney;
    }
    
    /*  Return all commands about one product specified  */
    public Hashtable getCmdForProduct(String product_name) throws SQLException{
        Hashtable ht = new Hashtable();
	Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT date,quantity_sold FROM commande WHERE product_name LIKE '"+product_name+"'";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String date = rs.getString("date");
                String stockSold = rs.getString("quantity_sold");
		System.out.println("date : " + date);
                System.out.println("Stock sold : " + stockSold);
                ht.put(date, Integer.parseInt(stockSold)); 
            }
            return ht;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return ht;
    }
    
    public Hashtable getBuyForRaw(String raw_name) throws SQLException{
        Hashtable ht = new Hashtable();
	Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT date_buy,quantity_buy FROM buy WHERE raw_name LIKE '"+raw_name+"'";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String date = rs.getString("date_buy");
                String stockBuy = rs.getString("quantity_buy");
		System.out.println("date : " + date);
                System.out.println("Stock buy : " + stockBuy);
                ht.put(date, Integer.parseInt(stockBuy)); 
            }
            return ht;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return ht;
    }
    
    
    /*  Add a Product_Type to BDD */
    public void setProduct(String product_name, double price, int quantity) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "INSERT INTO `product_type` (`product_name`, `price`, `product_stock`) VALUES ('"+product_name+"', '"+price+"', '"+quantity+"')";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }
    
    /*  Add a Raw_Type to BDD */
    public void setRaw(String raw_name, double price, int quantity) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "INSERT INTO `raw_type` (`raw_name`, `price_buy`, `raw_stock`) VALUES ('"+raw_name+"', '"+price+"', '"+quantity+"')";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }   
    
    /*  Add a link between a raw and a product */
    public void setProductRaw(String product_name, String raw_name, int quantity) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "INSERT INTO `product_raw` (`product_id`, `raw_id`, `quantity`) VALUES ('"+product_name+"', '"+raw_name+"', '"+quantity+"')";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }  
    
    /*  Add a buy to BDD */
    public void setBuy(String date_buy, int quantity_buy, String raw_name) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "INSERT INTO `buy` ( `date_buy`, `quantity_buy`, `raw_name`) VALUES ( '"+date_buy+"', '"+quantity_buy+"','"+raw_name+"' )";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }   
    
    /*  Add a Commande to BDD */
    public void setCmd(String date_cmd, int quantity_sold, String product_name) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "INSERT INTO `commande` ( `date`, `quantity_sold`, `product_name`) VALUES ( '"+date_cmd+"', '"+quantity_sold+"','"+product_name+"' )";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }   
    
    /*  Add a Production Order to BDD */
    public void setProductionOrder(String production_date, int quantity, String product_name) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "INSERT INTO `production_order` ( `production_date`, `quantity`, `product_name`) VALUES ( '"+production_date+"', '"+quantity+"','"+product_name+"' )";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }   
    
    /*  Add a Use Raw Production to BDD */
    public void setUseRaw(String product_name,String raw_name,String date) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "INSERT INTO `use_raw` ( `product_name`, `raw_name`, `date`) VALUES ( '"+product_name+"', '"+raw_name+"','"+date+"' )";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }
    
    public void setMyCash(double newCash) throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        double mymonney = -1;
	String selectTableSQL = "UPDATE `cash` SET `quantity_cash` = '"+newCash+"' ";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }
    

    /*  Modify product Price */
    public void modifyProductPrice(String product_name, double price) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "UPDATE `product_type` SET `price` = '"+price+"' WHERE `product_type`.`product_name` = '"+product_name+"';";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }
    
    /*  Modify product stock */
    public void modifyProductStock(String product_name, int stock) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "UPDATE `product_type` SET `product_stock` = '"+stock+"' WHERE `product_type`.`product_name` = '"+product_name+"';";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }
    
    /*  Modify Raw Price */
    public void modifyRawPrice(String raw_name, double price) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "UPDATE `raw_type` SET `price_buy` = '"+price+"' WHERE `raw_type`.`raw_name` = '"+raw_name+"';";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }
    
    /*  Modify raw stock */
    public void modifyRawStock(String raw_name, int stock) throws SQLException{
	Connection dbConnection = null;
	Statement statement = null; 
        String selectTableSQL = "UPDATE `raw_type` SET `raw_stock` = '"+stock+"' WHERE `raw_type`.`raw_name` = '"+raw_name+"';";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(selectTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
    }
    
    
    
    
    
    /*  Function wich return a connection  */
    private static Connection getDBConnection() {
	Connection dbConnection = null;
	try {
            Class.forName("com.mysql.jdbc.Driver");  
	} catch (Exception e) {
            System.out.println(e.getMessage());
	}      
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            System.out.println("Connection Succesfull");
            return dbConnection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
	}        
        return dbConnection;
    }
    
}


