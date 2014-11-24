/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTE.ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JEditorPane;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import application.Main.JTEPropertyType;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;
import JTE.file.JourneyThroughEuropeFileLoader;
import JTE.game.JourneyThroughEuropeGameStateManager;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.xml.stream.XMLStreamException;

public class JourneyThroughEuropeEventHandler {

    private JourneyThroughEuropeUI ui;
    private JourneyThroughEuropeFileLoader fl;
    private Button RollingDice;
    private Insets marginlessInsets;
    /**
     * Constructor that simply saves the ui for later.
     *
     * @param initUI
     */
    public JourneyThroughEuropeEventHandler(JourneyThroughEuropeUI initUI) {
        ui = initUI;
       
    }

    /**
     * This method responds to when the user click on the buttons on the
     * SplashScreen
     *
     * @param uiState The ui state, or screen, that the user wishes to switch
     * to.
     */
    public void respondToOKRequest() {
        JEditorPane helpPage = ui.getAboutPane();
        //   ui.loadPage(helpPage, JTEPropertyType.ABOUT_FILE_NAME);
    }

    public void respondToSwitchScreenRequest(JourneyThroughEuropeUI.JTEUIState uiState) {
        ui.changeWorkspace(uiState);
    }

    public void respondToExitRequest(Stage primaryStage) {
        System.exit(0);

    }

    public void respondToRollDiceRequest() {
        int diceNum = (int)(Math.random()*6);
        System.out.println(diceNum);
        
         
       changeDiceImage(diceNum);
       

        
    }
    public void changeDiceImage(int NumOnDice){
        switch (NumOnDice) {
                case 0:
                    ui.dice.setText("");
                   SetDiceGraphic(0);
                    break;
                case 1:
                    ui.dice.setText("");
                    SetDiceGraphic(1);
                    break;
                case 2:
                  ui.dice.setText("");
                   SetDiceGraphic(2);
                    break;
                case 3:
                   ui.dice.setText("");
                    SetDiceGraphic(3);
                    break;
                case 4:
                    ui.dice.setText("");
                    SetDiceGraphic(4);
                    break;
                    case 5:
                    ui.dice.setText("");
                    SetDiceGraphic(5);
                    break;
                default:

            }
    }
    public void SetDiceGraphic(int x){
    PropertiesManager props=PropertiesManager.getPropertiesManager();
    ArrayList<String>DiceGraph = props.getPropertyOptionsList(JTEPropertyType.ROLLING_DICE_IMAGE_NAME);
    String DiceGraphName = DiceGraph.get(x);
    Image DiceImage;
        DiceImage = ui.loadImage(DiceGraphName);
    ImageView DiceImageView = new ImageView(DiceImage);
    
    ui.dice.setGraphic(DiceImageView);
    ui.dice.setStyle("-fx-background-color:transparent");
    
   
    
    }

    public void respondToRadioButtonSelection(Stage primaryStage) {
        System.out.println("is a radio button");
    }

   public  void respondToSwitchMapRequest(JourneyThroughEuropeUI.JTEUIState jteuiState)  {
      switch (jteuiState){
       case AC14_MAP_STATE:
              SetMapGraphic(JTEPropertyType.GAME_MAP_AC14_IMAGE_NAME);
                    break;
       case DF14_MAP_STATE:
              SetMapGraphic(JTEPropertyType.GAME_MAP_DF14_IMAGE_NAME);
           break;
       case AC58_MAP_STATE:
              SetMapGraphic(JTEPropertyType.GAME_MAP_AC58_IMAGE_NAME);
           break;
       case DF58_MAP_STATE:
              SetMapGraphic(JTEPropertyType.GAME_MAP_DF58_IMAGE_NAME);
           break;
                default:
      }
    }
   public void SetMapGraphic(JTEPropertyType jtePT) {
       JourneyThroughEuropeFileLoader fl = new JourneyThroughEuropeFileLoader(ui);
       System.out.println(jtePT);
         PropertiesManager props = PropertiesManager.getPropertiesManager();
        String MAPScreenPath = props
                .getProperty(jtePT);
         
       //fl.loadCSVFile();
        StackPane MapScreen = new StackPane();
        //MapScreen.setPrefSize(600, 800);
        AnchorPane aps =  new AnchorPane();
        Image newMAPScreenImage = ui.loadImage(MAPScreenPath);
        ImageView newMAPScreenImageView = new ImageView(newMAPScreenImage);
        
       
        MapScreen.getChildren().addAll(newMAPScreenImageView,aps);
        StackPane.setAlignment(newMAPScreenImageView, Pos.CENTER);
      
        ui.mainPane.setCenter(MapScreen);
        
        aps.setOnMouseClicked((MouseEvent me) -> {
          respondToClick(me);
       });
        
   }

    void respondToClick(MouseEvent me) {
        JourneyThroughEuropeFileLoader fl = new JourneyThroughEuropeFileLoader(ui);
        int x =(int)me.getX();
        int y =(int)me.getY();
        //fl.loadCSVFile();
        System.out.println("x: "+x+"y: "+y);
      
    }
}
