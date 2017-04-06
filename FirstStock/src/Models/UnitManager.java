/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 *
 * @author louis
 */
public class UnitManager {
    private String fileName;
    private Map<String, Integer> units;
    
    public UnitManager(String f){
        fileName = f;
        units = new LinkedHashMap<>();
        this.read();
    }
    
    public void clear(){
        this.units.clear();
        this.write();
    }
    
    public void add(String s, Integer i){
        units.put(s,i);
    }
    
    public void write(){
        // Ecriture
        ObjectOutputStream dataOut;
        try{
            dataOut = new ObjectOutputStream(
                        new BufferedOutputStream(
                            new FileOutputStream(
                                new File(this.fileName))));
            // Sauvegarde des unités
            dataOut.writeObject(units);
            // Fermeture du flux
            dataOut.close();
        } catch (FileNotFoundException e) {
            // Si le fichier est inexistant
            e.printStackTrace();
        } catch (IOException e) {
            // Si la lecture est impossible
          e.printStackTrace();
        }
    }
    
    public void read(){
        try{
            // Affichage
            ObjectInputStream dataIn;

            dataIn = new ObjectInputStream(
                        new BufferedInputStream(
                          new FileInputStream(
                            new File(this.fileName))));
            
            // Lecture des données
            try{
                // Récupération du nombre de scores à lire               
                units = (Map<String, Integer>)dataIn.readObject();
            }
            catch (ClassNotFoundException e){
                // Si Il n'y a pas de class à récupérer
                e.printStackTrace();
            }
            // Fermeture du flux
            dataIn.close();        
        }
         catch (FileNotFoundException e) {
            // Si le fichier est inexistant
            e.printStackTrace();
        } catch (IOException e) {
            // Si la lecture est impossible
            e.printStackTrace();
        }
    }
    
    public Map<String, Integer> getUnits() {
        return units;
    }
    
    public Unit getOneUnit(String uni){
        Unit unit = null;
        try{
            if(units.get(uni) == null) throw new Exception();
            unit = new Unit(uni, units.get(uni));
        }
        catch(Exception e){
            System.out.println("Error can't find unit");
        }
        return unit;
    }
    
    @Override
    public String toString(){
        Set<Entry<String, Integer>> setUnits = units.entrySet();
        Iterator<Entry<String, Integer>> it = setUnits.iterator();
        String str = "";
        while(it.hasNext()){
            Entry<String, Integer> e = it.next();
            str += "Unit : "+e.getKey()+" with 10^"+e.getValue()+"\n";
        }
        return str;
    }
}
