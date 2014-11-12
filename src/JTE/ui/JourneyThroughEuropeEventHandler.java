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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class JourneyThroughEuropeEventHandler {

    private JourneyThroughEuropeUI ui;
    private JourneyThroughEuropeFileLoader fl;
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

    public void respondToRollDieRequest() {
        System.out.println("Roll");
       // fl.loadCSVtoArrayandPrint();
        
    }

    public void respondToRadioButtonSelection(Stage primaryStage) {
        System.out.println("is a radio button");
    }
}
