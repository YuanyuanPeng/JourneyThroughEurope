/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTE.ui;

import JTE.file.XMLParser;
import application.Main;
import java.io.FileReader;
import properties_manager.PropertiesManager;

/**
 *
 * @author peng
 */
public class Card {
    
    private JourneyThroughEuropeUI ui;
    private XMLParser paser;
    private String cityNameOnCard;
    private String color;

    private int ExtraMove; // move on card

    /*
     public Card(JourneyThroughEuropeUI initUI){
    ui=initUI;
    }
    }
    */
  
    public Card(String cityNameOnCard, String color, int ExtraMove) {
        this.cityNameOnCard = cityNameOnCard;
        this.color = color;
        this.ExtraMove = ExtraMove;
        
    }
    public void readCard(){
     
    
    
    }
}
