package JTE.ui;

import properties_manager.PropertiesManager;
import application.Main.JTEPropertyType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author peng
 */
public class JourneyThroughEuropeErrorHandler {
private Stage ui;

    public JourneyThroughEuropeErrorHandler(Stage initUI) {
     ui=initUI;
    }
    public void processError(JTEPropertyType errorType){
    //Get feedback text
        PropertiesManager props=PropertiesManager.getPropertiesManager();
        String errorFeedbackText=props.getProperty(errorType);
        
        String errorTitle=props.getProperty(JTEPropertyType.ERROR_DIALOG_TITLE_TEXT);
        // POP OPEN A DIALOG TO DISPLAY TO THE USER
        //JOptionPane.showMessageDialog(window, errorFeedbackText, errorTitle, JOptionPane.ERROR_MESSAGE);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(ui);
        dialogStage.setTitle(errorTitle);
        VBox vbox = new VBox();
        vbox.setSpacing(10.0);
        Label errLabel = new Label(errorFeedbackText);
        Button errButton = new Button("confirm");
        vbox.getChildren().addAll(errLabel, errButton);

        Scene scene = new Scene(vbox, 50, 30);
        dialogStage.setScene(scene);
        dialogStage.show();
        
     
    
    
    }

}
