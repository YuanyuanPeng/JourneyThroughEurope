/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTE.ui;

import java.util.ArrayList;

/**
 *
 * @author peng
 */
public class NeighbourCity {
    private String CityName;
    private ArrayList <String> land;
    private ArrayList<String> sea;
    

    
    public NeighbourCity(String CityName, ArrayList<String> land, ArrayList<String>sea){
        
    this.CityName=CityName;
    this.land=land;
    this.sea=sea;
    
    }
    public void setName(String name){
    this.CityName=name;
    }
    public String getName(){
    return CityName;
    }
    public void setSea(ArrayList<String> sea){
        this.sea = sea;
    
    }
    public void setLand(ArrayList<String> land){
        this.land=land;
    }
    
    public ArrayList<String> getSea(){
    return sea;
    }
    
    public ArrayList<String>getLand(){
    return land;
    }
    
    @Override
    public String toString(){
    return "<" + CityName + ", this is land" + land + ", this is sea" + sea+">";
    }
    

}
