package JTE.ui;

import application.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import JTE.file.JourneyThroughEuropeFileLoader;
import JTE.game.JourneyThroughEuropeGameData;
import JTE.game.JourneyThroughEuropeGameStateManager;
import application.Main.JTEPropertyType;
import java.awt.Canvas;
import java.awt.Panel;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JScrollPane;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author peng
 */
public class JourneyThroughEuropeUI extends Pane {

    public enum JTEUIState {

        SPLASH_SCREEN_STATE, START_GAME_STATE, PLAY_GAME_STATE, VIEW_ABOUT_STATE, LOAD_HISTORY_STATE,
        VIEW_AIR_MAP_STATE, AC14_MAP_STATE, DF14_MAP_STATE, AC58_MAP_STATE, DF58_MAP_STATE,
    }

    // mainStage
    private Stage primaryStage;

    // mainPane
    public BorderPane mainPane;
    private BorderPane hmPane;

    // SplashScreen
    private ImageView splashScreenImageView;
    private StackPane splashScreenPane;
    private Label splashScreenImageLabel;
    private Label flagScreenImageLael;

    // SplashScreenToolBar
    private HBox OptionToolbar;
    private Button StartButton;
    private Button LoadButton;
    private Button AboutButton;
    private Button QuitButton;

//Selection Screen 
    private ImageView selectScreenImageView;
    private Label selectScreenImageLabel;
    private SplitPane SelectionPane;//set to the senter of the border pane
    private BorderPane SelectionScreenPanel;// top for the combobox and go button
    private HBox SelectionToolbar;
    private Button GOButton;
    public ComboBox CB;

    //in the center of selectionscreen
    private RadioButton RBC;
    private RadioButton RBP;
    private TextField TF;
    boolean isComputer = false;
    boolean isPerson = false;

    //GamePane
    private ImageView MAPScreenImageView;
    private Label MAPScreenImageLabel;
    private BorderPane GamePanel;//borderpane for it 
    private JEditorPane GamePane;
    private JScrollPane GameScrollPane;
    public AnchorPane ap;
    public StackPane GameScreenPane;
    //GameScreenButtons
    private VBox GameToolOption;
    private Button AboutJTEButton;
    private Button GameHistoryButton;
    private Button AirMapButton;

    private Button AC14;//switch pane to ac14
    private Button DF14;//switch pane to df14
    private Button AC58;//switch pane to ac58
    private Button DF58;
    //private Button dice;
    public Button dice;
//buttons for the map

    //LoadPane
    private BorderPane LoadPanel;
    private JEditorPane LoadPane;
    //

    //AboutPane
    private JEditorPane AboutPane;
    private Button OKButton;
    private Pane workspace;

    private ImageView aboutScreenImageView;
    private StackPane aboutScreenPane;
    private Label aboutScreenImageLabel;
    //AIR PANE

    private ImageView AIRScreenImageView;
    private Label AIRScreenImageLabel;
    // Padding
    private Insets marginlessInsets;

    // Image path
    private String ImgPath = "file:img/";
    private String redImgPath = "file:img/red/";

    // mainPane weight && height
    private int paneWidth;
    private int paneHeigth;
    public Label PlayerImageLabel;
    boolean isFromSplashScreen = false;

    // THIS CLASS WILL HANDLE ALL ACTION EVENTS FOR THIS PROGRAM
    private JourneyThroughEuropeEventHandler eventHandler;
    private JourneyThroughEuropeErrorHandler errorHandler;
    private JourneyThroughEuropeDocumentManager docManager;
    private JourneyThroughEuropeFileLoader fl;
    JourneyThroughEuropeGameStateManager gsm;

    public JourneyThroughEuropeUI() {
        gsm = new JourneyThroughEuropeGameStateManager(this);
        eventHandler = new JourneyThroughEuropeEventHandler(this);
        errorHandler = new JourneyThroughEuropeErrorHandler(primaryStage);
        docManager = new JourneyThroughEuropeDocumentManager(this);
        fl = new JourneyThroughEuropeFileLoader(this);
        initMainPane();
        initSplashScreen();
    }

    public void SetStage(Stage stage) {
        primaryStage = stage;
    }

    public BorderPane GetMainPane() {
        return this.mainPane;
    }

    public JourneyThroughEuropeGameStateManager getGSM() {
        return gsm;
    }

    public JourneyThroughEuropeDocumentManager getDocManager() {
        return docManager;
    }

    public JourneyThroughEuropeErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public JEditorPane getAboutPane() {
        return AboutPane;
    }

    private void initMainPane() {
        marginlessInsets = new Insets(5, 5, 5, 5);
        mainPane = new BorderPane();

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        paneWidth = Integer.parseInt(props
                .getProperty(JTEPropertyType.WINDOW_WIDTH));
        paneHeigth = Integer.parseInt(props
                .getProperty(JTEPropertyType.WINDOW_HEIGHT));
        mainPane.resize(paneWidth, paneHeigth);
        mainPane.setPadding(marginlessInsets);

    }

    private void initSplashScreen() {

        // INIT THE SPLASH SCREEN CONTROLS
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String splashScreenImagePath = props
                .getProperty(JTEPropertyType.SPLASH_SCREEN_IMAGE_NAME);

        StackPane splashScreenPane = new StackPane();
        splashScreenPane.setPrefSize(827, 622);

        Image splashScreenImage = loadImage(splashScreenImagePath);
        splashScreenImageView = new ImageView(splashScreenImage);

        splashScreenImageLabel = new Label();
        splashScreenImageLabel.setGraphic(splashScreenImageView);
        splashScreenImageLabel.setLayoutX(-45);// move the label position to fix
        // the pane
        splashScreenPane.getChildren().add(splashScreenImageLabel);
        StackPane.setAlignment(splashScreenImageLabel, Pos.CENTER);
        initOptionToolbar();
        mainPane.setCenter(splashScreenPane);
        mainPane.setBottom(OptionToolbar);

    }

    public void initJourneyThroughEuropeUI() {

        mainPane.setBottom(null);
        // GET THE UPDATED TITLE
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String title = props.getProperty(JTEPropertyType.GAME_TITLE_TEXT);
        primaryStage.setTitle(title);

    }

    private void initOptionToolbar() {
        OptionToolbar = new HBox();
        OptionToolbar.setStyle("-fx-background-color: white");
        OptionToolbar.setSpacing(10.0);
        OptionToolbar.setPadding(marginlessInsets);
        OptionToolbar.setAlignment(Pos.CENTER);
       //set the StartButton to the SplashScreen

        StartButton = initToolbarButton(OptionToolbar, JTEPropertyType.START_IMG_NAME);
        //setTooltip(StartButton,JTEPropertyType.START_TOOLTIP);
        StartButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.START_GAME_STATE);
            }

        });
        /*
         set the LoadButton to SplashScreen
         */
        LoadButton = initToolbarButton(OptionToolbar, JTEPropertyType.LOAD_IMG_NAME);
        //setTooltip(StartButton,JTEPropertyType.LOAD_TOOLTIP);
        LoadButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.LOAD_HISTORY_STATE);
            }

        });

        /*
         set the AboutButton to SplashScreen
         */
        AboutButton = initToolbarButton(OptionToolbar, JTEPropertyType.ABOUT_IMG_NAME);
        //setTooltip(StartButton,JTEPropertyType.ABOUT_TOOLTIP);
        AboutButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                eventHandler.respondToSwitchScreenRequest(JTEUIState.VIEW_ABOUT_STATE);
            }

        });

        /*
         set the QuitButton to SplashScreen
         */
        QuitButton = initToolbarButton(OptionToolbar, JTEPropertyType.QUIT_IMG_NAME);
        //setTooltip(StartButton,JTEPropertyType.QUIT_TOOLTIP);
        QuitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("exit the pane");
                eventHandler.respondToExitRequest(primaryStage);
            }

        });
        mainPane.setBottom(OptionToolbar);
    }

    private Button initToolbarButton(HBox Toolbar, JTEPropertyType prop) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgName = props.getProperty(prop);
        //Load image
        Image image = loadImage(imgName);
        ImageView imageIcon = new ImageView(image);
        //Make the button
        Button button = new Button();
        button.setGraphic(imageIcon);
        button.setStyle("-fx-background-color:transparent");
        Toolbar.getChildren().add(button);
        return button;
    }

    private void initAboutPane() {
        System.out.println("aboutpane");
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String aboutScreenImagePath = props
                .getProperty(JTEPropertyType.ABOUT_SCREEN_IMAGE_NAME);
        // splashScreenPane = new Pane();
        StackPane aboutScreenPane = new StackPane();
        aboutScreenPane.setPrefSize(827, 622);
        Image aboutScreenImage = loadImage(aboutScreenImagePath);
        aboutScreenImageView = new ImageView(aboutScreenImage);

        aboutScreenPane.getChildren().add(aboutScreenImageView);
        StackPane.setAlignment(aboutScreenImageView, Pos.CENTER);
        //Make the tool bar which will return to the main pain 
        //  PropertiesManager props = PropertiesManager.getPropertiesManager();
        String OKImgName = props.getProperty(JTEPropertyType.OK_IMG_NAME);
        Image OKImg = loadImage(OKImgName);
        ImageView OKImgIcon = new ImageView(OKImg);

        OKButton = new Button();
        OKButton.setGraphic(OKImgIcon);
        // setTooltip(homeButton, jtePropertyType.OK_TOOLTIP);
        OKButton.setPadding(marginlessInsets);
        // to the button in the toolbar
        HBox aboutToolbar = new HBox();
   // AboutPanel=new BorderPane();

        // AboutPanel.setCenter(AboutPane);
        aboutToolbar.getChildren().add(OKButton);
        aboutToolbar.setStyle("-fx-background-color:white");

        mainPane.setBottom(aboutToolbar);
        mainPane.setCenter(aboutScreenPane);
        if (isFromSplashScreen = true) {
            OKButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    eventHandler.respondToSwitchScreenRequest(JTEUIState.SPLASH_SCREEN_STATE);
                }

            });
        } else {
            OKButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    eventHandler.respondToSwitchScreenRequest(JTEUIState.PLAY_GAME_STATE);
                }

            });
        }
    }

    private void initSelectionScreenPane() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String selectScreenImagePath = props
                .getProperty(JTEPropertyType.SELECT_SCREEN_IMAGE_NAME);
        SelectionPane = new SplitPane();

        StackPane selectScreenPane = new StackPane();
        selectScreenPane.setPrefSize(827, 622);

        Image selectScreenImage = loadImage(selectScreenImagePath);
        selectScreenImageView = new ImageView(selectScreenImage);

        selectScreenImageLabel = new Label();
        selectScreenImageLabel.setGraphic(selectScreenImageView);
        selectScreenImageLabel.setLayoutX(-45);// move the label position to fix
        // the pane
        selectScreenPane.getChildren().add(selectScreenImageLabel);
        StackPane.setAlignment(selectScreenImageLabel, Pos.CENTER);

        //Make the tool bar which will return to the main pain 
        //  PropertiesManager props = PropertiesManager.getPropertiesManager();
        String GOImgName = props.getProperty(JTEPropertyType.GO_IMG_NAME);
        Image GOImg = loadImage(GOImgName);
        ImageView GOImgIcon = new ImageView(GOImg);

        GOButton = new Button();
        GOButton.setGraphic(GOImgIcon);
        GOButton.setStyle("-fx-background-color:transparent");
        // setTooltip(homeButton, jtePropertyType.OK_TOOLTIP);
        GOButton.setPadding(marginlessInsets);
        // to the button in the toolbar
        HBox selectToolbar = new HBox();
        // AboutPanel=new BorderPane();

        CB = new ComboBox();
        CB.getItems().addAll(2, 3, 4, 5, 6);
        CB.setValue(" 2 ");

        CB.setOnAction((event) -> {
            Object player = CB.getSelectionModel().getSelectedItem();
            String NumOfPlayer = player.toString();
            switch (NumOfPlayer) {
                case "2":
                    initSeletOptions(2);
                    break;
                case "3":
                    initSeletOptions(3);
                    break;
                case "4":
                    initSeletOptions(4);
                    break;
                case "5":
                    initSeletOptions(5);
                    break;
                case "6":
                    initSeletOptions(6);
                    break;
                default:

            }

        });

        selectToolbar.getChildren().addAll(CB, GOButton);
        selectToolbar.setStyle("-fx-background-color:white");
        mainPane.setTop(selectToolbar);
        GOButton.setOnAction((ActionEvent event) -> {
            eventHandler.respondToSwitchScreenRequest(JTEUIState.PLAY_GAME_STATE);
            Object player = CB.getSelectionModel().getSelectedItem();
            String NumOfPlayer = player.toString();
            switch (NumOfPlayer) {
                case "2":
                    eventHandler.respondToPalayRequest(2);
                    break;
                case "3":
                    eventHandler.respondToPalayRequest(3);
                    break;
                case "4":
                    eventHandler.respondToPalayRequest(4);
                    break;
                case "5":
                    eventHandler.respondToPalayRequest(5);
                    break;
                case "6":
                    eventHandler.respondToPalayRequest(6);
                    break;
                default:

            }

        });
    }

    private void initSeletOptions(int n) {
        GridPane playerSelectionGridPane = new GridPane();
        for (int i = 0; i < n; i++) {
            System.out.println("Print out " + i + "Options");
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            ArrayList<String> flagImages = props
                    .getPropertyOptionsList(JTEPropertyType.FLAG_IMG_NAMES);

            String flagImageName = flagImages.get(i);
            Image flagImage = loadImage(flagImageName);
            ImageView flagImageView = new ImageView(flagImage);
            flagScreenImageLael = new Label();
            flagScreenImageLael.setGraphic(flagImageView);

            int num = i + 1;
            VBox opVBox = new VBox();
            RBC = new RadioButton("Computer");
            RBP = new RadioButton("Player");
            TF = new TextField("Player" + num);

            RBC.setOnAction((ActionEvent event) -> {
                isComputer = true;
                // RBP.setDisable(true);
                eventHandler.respondToRadioButtonSelection(primaryStage);
            });
            RBP.setOnAction((ActionEvent event) -> {
                isPerson = true;
                //  RBC.setDisable(true);
                eventHandler.respondToRadioButtonSelection(primaryStage);
            });
            opVBox.getChildren().addAll(flagScreenImageLael, RBC, RBP, TF);
            if (n < 4) {
                playerSelectionGridPane.add(opVBox, i, 0);
            } else {
                playerSelectionGridPane.add(opVBox, i, 4);
            }
        }
        mainPane.setCenter(playerSelectionGridPane);

    }
    JEditorPane gamePane;

    private void initGameScreen() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String MAPScreenImagePath = props
                .getProperty(JTEPropertyType.GAME_MAP_AC14_IMAGE_NAME);
        fl.loadNeighbourCity();
        GameScreenPane = new StackPane();
        GameScreenPane.setPrefSize(517, 660);

        Image MAPScreenImage = loadImage(MAPScreenImagePath);
        MAPScreenImageView = new ImageView(MAPScreenImage);
        MAPScreenImageView.setFitWidth(517.0);
        MAPScreenImageView.setFitHeight(660.0);
        ap = new AnchorPane();
        GameScreenPane.getChildren().add(ap);
        ap.setMaxHeight(660.0);
        ap.setMaxWidth(517.0);

        ap.toFront();
        GameScreenPane.getChildren().add(MAPScreenImageView);

       //ap.getChildren().add(GameScreenPane);
        // GameScreenPane.setAlignment(MAPScreenImageView,Pos.CENTER);
        GameScreenPane.setAlignment(ap, Pos.CENTER);

        // ap.toFront();
        //Make the tool bar which will return to the main pain 
        //  PropertiesManager props = PropertiesManager.getPropertiesManager();
        String AboutJTEImgName = props.getProperty(JTEPropertyType.ABOUT_JTE_IMG_NAME);
        Image AboutJTEImg = loadImage(AboutJTEImgName);
        ImageView AboutJTEImgIcon = new ImageView(AboutJTEImg);

        AboutJTEButton = new Button();
        AboutJTEButton.setGraphic(AboutJTEImgIcon);
        AboutJTEButton.setPadding(marginlessInsets);
        AboutJTEButton.setStyle("-fx-background-color:transparent");

        String HistoryImgName = props.getProperty(JTEPropertyType.GAME_HISTORY_IMAGE_NAME);
        Image HistoryImg = loadImage(HistoryImgName);
        ImageView HistoryImgIcon = new ImageView(HistoryImg);
        GameHistoryButton = new Button();
        GameHistoryButton.setGraphic(HistoryImgIcon);
        GameHistoryButton.setPadding(marginlessInsets);
        GameHistoryButton.setStyle("-fx-background-color:transparent");

        String airImgName = props.getProperty(JTEPropertyType.AIR_MAP_IMG_NAME);
        Image airImg = loadImage(airImgName);
        ImageView airImgIcon = new ImageView(airImg);
        AirMapButton = new Button();
        AirMapButton.setGraphic(airImgIcon);
        AirMapButton.setPadding(marginlessInsets);
        AirMapButton.setStyle("-fx-background-color:transparent");

        dice = new Button(" Click to Roll the Dice");

        GridPane GridMap = new GridPane();

        AC14 = new Button("AC14");//switch pane to ac14
        DF14 = new Button("DF14");//switch pane to df14
        AC58 = new Button("AC58");//switch pane to ac58
        DF58 = new Button("DF58");//switch pane to df58

        //playerSelectionGridPane.add(opVBox, i, 0);
        GridMap.add(AC14, 0, 0);
        GridMap.add(DF14, 1, 0);
        GridMap.add(AC58, 0, 1);
        GridMap.add(DF58, 1, 1);

        VBox selectToolbar = new VBox();
        selectToolbar.getChildren().addAll(dice, GridMap, AirMapButton, AboutJTEButton, GameHistoryButton);
        selectToolbar.setStyle("-fx-background-color:white");

      //  StackPane cardPane =  new StackPane();
        //Button card = new Button("I am a card dont click me!");
        //card.setStyle("-fx-background-color: transparent");
        //cardPane.getChildren().add(card);
        //mainPane.setLeft(cardPane);
        mainPane.setRight(selectToolbar);
        mainPane.setCenter(GameScreenPane);
        //mainPane.setCenter(ap);

        // mainPane.setLeft(selectToolbar);
        AC14.setOnAction((ActionEvent event) -> {
            eventHandler.respondToSwitchMapRequest(JTEUIState.AC14_MAP_STATE);
        });
        DF14.setOnAction((ActionEvent event) -> {
            eventHandler.respondToSwitchMapRequest(JTEUIState.DF14_MAP_STATE);
        });
        AC58.setOnAction((ActionEvent event) -> {
            eventHandler.respondToSwitchMapRequest(JTEUIState.AC58_MAP_STATE);
        });
        DF58.setOnAction((ActionEvent event) -> {
            eventHandler.respondToSwitchMapRequest(JTEUIState.DF58_MAP_STATE);
        });

        AboutJTEButton.setOnAction((ActionEvent event) -> {
            eventHandler.respondToSwitchScreenRequest(JTEUIState.VIEW_ABOUT_STATE);
        });
        AirMapButton.setOnAction((ActionEvent event) -> {
            eventHandler.respondToSwitchScreenRequest(JTEUIState.VIEW_AIR_MAP_STATE);
        });
        GameHistoryButton.setOnAction((ActionEvent event) -> {
            eventHandler.respondToSwitchScreenRequest(JTEUIState.LOAD_HISTORY_STATE);
        });
        dice.setOnAction((ActionEvent event) -> {
            eventHandler.respondToRollDiceRequest();
        });
        //MAPScreenImageView.
        ap.setOnMouseClicked((MouseEvent t) -> {
            eventHandler.respondToClick(t);
        });

    }

    private void initLoadPane() {
        System.out.println("aboutpane");

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String loadScreenImagePath = props
                .getProperty(JTEPropertyType.ABOUT_SCREEN_IMAGE_NAME);

        StackPane loadScreenPane = new StackPane();

        String OKImgName = props.getProperty(JTEPropertyType.OK_IMG_NAME);
        Image OKImg = loadImage(OKImgName);
        ImageView OKImgIcon = new ImageView(OKImg);
        Button OKButton1 = new Button();
        OKButton1.setGraphic(OKImgIcon);
        OKButton1.setPadding(marginlessInsets);
        HBox loadToolbar = new HBox();
   // AboutPanel=new BorderPane();

        // AboutPanel.setCenter(AboutPane);
        loadToolbar.getChildren().add(OKButton1);
        loadToolbar.setStyle("-fx-background-color:white");
        mainPane.setBottom(loadToolbar);
        //mainPane.setCenter(aboutScreenPane);
        OKButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.SPLASH_SCREEN_STATE);
            }

        });
    }

    private void initAIRPane() {

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String AIRScreenImagePath = props
                .getProperty(JTEPropertyType.AIR_MAP_IMAGE_NAME);
        props.addProperty(JTEPropertyType.INSETS, "9");
        String str = props.getProperty(JTEPropertyType.INSETS);
        System.out.println("11111111" + str);
        StackPane AIRScreenPane = new StackPane();
        AIRScreenPane.setPrefSize(827, 622);

        Image AIRScreenImage = loadImage(AIRScreenImagePath);
        AIRScreenImageView = new ImageView(AIRScreenImage);

        AIRScreenImageLabel = new Label();
        AIRScreenImageLabel.setGraphic(AIRScreenImageView);
        AIRScreenImageLabel.setLayoutX(-45);// move the label position to fix
        // the pane
        AIRScreenPane.getChildren().add(AIRScreenImageLabel);
        StackPane.setAlignment(AIRScreenImageLabel, Pos.CENTER);
        //Make the tool bar which will return to the main pain 
        //  PropertiesManager props = PropertiesManager.getPropertiesManager();
        String OKImgName = props.getProperty(JTEPropertyType.OK_IMG_NAME);
        Image OKImg = loadImage(OKImgName);
        ImageView OKImgIcon = new ImageView(OKImg);

        OKButton = new Button();
        OKButton.setGraphic(OKImgIcon);
        // setTooltip(homeButton, jtePropertyType.OK_TOOLTIP);
        OKButton.setPadding(marginlessInsets);
        OKButton.setStyle("-fx-background-color:transparent");
        // to the button in the toolbar
        VBox aboutToolbar = new VBox();
        // AboutPanel.setCenter(AboutPane);
        aboutToolbar.getChildren().add(OKButton);
        aboutToolbar.setStyle("-fx-background-color:white");

        mainPane.setRight(aboutToolbar);
        mainPane.setCenter(AIRScreenPane);

        OKButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.PLAY_GAME_STATE);
            }
        });
    }

    public void changeWorkspace(JTEUIState uiScreen) {
        switch (uiScreen) {
            case VIEW_AIR_MAP_STATE:
                mainPane.getChildren().clear();
                initAIRPane();
                break;
            case SPLASH_SCREEN_STATE:
                mainPane.getChildren().clear();
                initSplashScreen();
                break;
            case START_GAME_STATE:
                System.out.println("test");
                mainPane.getChildren().clear();
                initSelectionScreenPane();
                break;
            case PLAY_GAME_STATE:
                mainPane.getChildren().clear();
                initGameScreen();
                break;
            case VIEW_ABOUT_STATE:
                mainPane.getChildren().clear();
                initAboutPane();
                break;
            case LOAD_HISTORY_STATE:
                mainPane.getChildren().clear();
                initLoadPane();
                break;

            default:
        }
    }

    public void loadPage(JEditorPane AboutPane, JTEPropertyType fileProperty) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String fileName = props.getProperty(fileProperty);
        try {
            // LOAD THE HTML INTO THE EDITOR PANE
            String fileHTML = JourneyThroughEuropeFileLoader.loadTextFile(fileName);
            AboutPane.setText(fileHTML);
        } catch (Exception e) {
            errorHandler.processError(JTEPropertyType.INVALID_URL_ERROR_TEXT);
        }
    }

    /**
     * This method loads the link Web Page into the Help Screen's editor pane.
     *
     * @param link The Web Page to load.
     */
    public Image loadImage(String imageName) {
        Image img = new Image(ImgPath + imageName);
        return img;
    }

    public void PlayGame(int Nplayer) {

        ArrayList<City> PlayerCity = new ArrayList();
        ArrayList<City> PlayerCityPart1 = new ArrayList();
        ArrayList<City> PlayerCityPart2 = new ArrayList();
        ArrayList<City> PlayerCityPart3 = new ArrayList();
        ArrayList<City> PlayerCityPart4 = new ArrayList();

        ArrayList<City> newCity = fl.loadCSVFile();
       // fl.loadNeighbourCity();

        //StackPane cardPane =  new StackPane();
        //       GridPane cardPane = new GridPane();
        AnchorPane cardPane = new AnchorPane();
        cardPane.setPrefSize(300, 660.0);

        mainPane.setLeft(cardPane);

        for (int city = 0; city < 178; city++) {
            Random rand = new Random();
            int value = rand.nextInt(178);
         //   System.out.println(value);
            City a = newCity.get(value);
            if (a.getPart() == 1) {
               // System.out.println(a.toString());
                PlayerCityPart1.add(a);
            }
            if (a.getPart() == 2) {
             //   System.out.println(a.toString());
                PlayerCityPart2.add(a);
            }
            if (a.getPart() == 3) {
             //   System.out.println(a.toString());
                PlayerCityPart3.add(a);
            } else {
                PlayerCityPart4.add(a);
            }

        }

        for (int i = 0; i < Nplayer; i++) {
            int Ncities = PlayerCityPart1.size();

            Random rand = new Random();
            int nc = rand.nextInt(Ncities);

            City playerC = PlayerCityPart1.get(nc);
            System.out.println(playerC.toString());
            PlayerCity.add(playerC);

        }

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> PlayerImages = props
                .getPropertyOptionsList(JTEPropertyType.PLAYER_IMG_NAMES);
        ArrayList<String> RedCardsImages = props.getPropertyOptionsList(JTEPropertyType.RED_CARDS_IMG_NAME);
        ArrayList<String> GreCardsImages = props.getPropertyOptionsList(JTEPropertyType.GRE_CARDS_IMG_NAME);
        ArrayList<String> YelCardsImages = props.getPropertyOptionsList(JTEPropertyType.YEL_CARDS_IMG_NAME);

        //Nplayer
        for (int h = 0; h < Nplayer; h++) {
            String PlayerImageName = PlayerImages.get(h);
            Image PlayerImage = loadImage(PlayerImageName);
            ImageView PlayerImageView = new ImageView(PlayerImage);
            PlayerImageLabel = new Label();
            PlayerImageLabel.setGraphic(PlayerImageView);

            Random rand = new Random();
            int a = rand.nextInt(59);
            String cardsImageName1 = RedCardsImages.get(a);
            Random randb = new Random();
            int b = randb.nextInt(58);
            String cardsImageName2 = GreCardsImages.get(b);
            Random randc = new Random();
            int c = randc.nextInt(58);
            String cardsImageName3 = YelCardsImages.get(c);

            Image cardsImage1 = loadImage(cardsImageName1);
            Image cardsImage2 = loadImage(cardsImageName2);
            Image cardsImage3 = loadImage(cardsImageName3);

            ImageView cardsImageView1 = new ImageView(cardsImage1);
            ImageView cardsImageView2 = new ImageView(cardsImage2);
            ImageView cardsImageView3 = new ImageView(cardsImage3);

            cardsImageView1.setFitWidth(225);
            cardsImageView1.setFitHeight(320);

            cardsImageView2.setFitWidth(225);
            cardsImageView2.setFitHeight(320);

            cardsImageView3.setFitWidth(225);
            cardsImageView3.setFitHeight(320);

            Label cardsImageLabel1 = new Label();
            cardsImageLabel1.setGraphic(cardsImageView1);
            Label cardsImageLabel2 = new Label();
            cardsImageLabel2.setGraphic(cardsImageView2);
            Label cardsImageLabel3 = new Label();
            cardsImageLabel3.setGraphic(cardsImageView3);

            cardsImageLabel1.relocate(37.5, 0.0);
            cardsImageLabel2.relocate(37.5, 60.0);
            cardsImageLabel3.relocate(37.5, 120.0);

            AnchorPane one = new AnchorPane();
            AnchorPane two = new AnchorPane();
            AnchorPane three = new AnchorPane();
            one.getChildren().add(cardsImageLabel1);
            two.getChildren().add(cardsImageLabel2);
            three.getChildren().add(cardsImageLabel3);

            cardPane.getChildren().addAll(one, two, three);

            City playerc = PlayerCity.get(h);
            double e = (playerc.getX() - 9.5);
            double d = (playerc.getY() - 51);
            PlayerImageLabel.relocate(e, d);

            ap.getChildren().add(PlayerImageLabel);
            ap.toFront();
           //GameScreenPane.getChildren().add(PlayerImageLabel);
            //ap.setLayoutX(23.0);
       
            ap.setOnMouseClicked((MouseEvent t) -> {
           
               
              ArrayList <Integer> coor = new ArrayList();
               coor =eventHandler.respondToClick(t);
                 System.out.println("coor: "+coor.toString());
             int newX  =  coor.get(0);
            int newY  =  coor.get(1);
                PlayerImageLabel.relocate(newX-9.5, newY-51);
                System.out.println("Im a new label");
                ap.getChildren().clear();
        ap.getChildren().add(PlayerImageLabel);

       ap.toFront();

            });
            
            
            ap.setOnMousePressed((MouseEvent t) -> {
                System.out.println("Pressed");
            

            });
            ap.setOnMouseReleased((MouseEvent t)-> {
                System.out.println("Released");
              ArrayList <Integer> coor = new ArrayList();
               coor =eventHandler.respondToClick(t);
                 System.out.println("coor: "+coor.toString());
             int newX  =  coor.get(0);
            int newY  =  coor.get(1);
                PlayerImageLabel.relocate(newX-9.5, newY-51);
                System.out.println("Im a new label");
                ap.getChildren().clear();
        ap.getChildren().add(PlayerImageLabel);

       ap.toFront();
            
            
            });
            
            

        } //FOR LOOP CLOSE HERE

    }

    public void Move(double x, double y) {
        PlayerImageLabel.relocate(x, y);
       

    }

}
