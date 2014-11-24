/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTE.ui;

/**
 *
 * @author peng
 */
public class City {
    private String CityName;
    private String CityColor;
    private int Part;
    private int CityX;
    private int CityY;
    boolean hasSeaRoute;
    boolean hasAirport;
    
    public City(){
    CityName="";
    CityColor="";
    Part=0;
    CityX=0;
    CityY=0;
 
    }
    public City(String CityName, String CityColor,int Part,int CityX, int CityY){
    this.CityName=CityName;
    this.CityColor=CityColor;
    this.Part=Part;
    this.CityX=CityX;
    this.CityY=CityY;
    this.hasSeaRoute=hasSeaRoute;
    this.hasAirport=hasAirport;
    }

    public void setName(String text) {
       CityName=text;
    }

    public void setColor(String text) {
      CityColor=text;
    }

    public void setPart(int i) {
      Part=i;
    }

    public void setX(int X) {
      CityX=X;
    }

    public void setY(int Y) {
       CityY=Y;
    }
    public String getCityColor(){
    return CityColor;
    }
    public String getCityName(){
    return CityName;
    }
    
    public int getPart(){
    return Part;
    }
    
    public int getX(){
    return CityX;
    }
    public int getY(){
    return CityY;
    }
    
    @Override
    public String toString(){
    return "<" + CityName + "," + CityColor + "," + Part + "," + CityX + "," +CityY+">";
    }

   
}
