package JTE.ui;

import application.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JEditorPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

import JTE.file.JourneyThroughEuropeFileLoader;
import JTE.game.JourneyThroughEuropeGameData;
import JTE.game.JourneyThroughEuropeGameStateManager;
import application.Main.JTEPropertyType;
import java.awt.Panel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.swing.JScrollPane;

/**
 *
 * @author peng
 */
public class JourneyThroughEuropeUI extends Pane {

    public enum JTEUIState {

        SPLASH_SCREEN_STATE, START_GAME_STATE, PLAY_GAME_STATE, VIEW_ABOUT_STATE, LOAD_HISTORY_STATE,
        VIEW_AIR_MAP_STATE,
    }

    // mainStage
    private Stage primaryStage;

    // mainPane
    private BorderPane mainPane;
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
    private ComboBox CB;

    //in the center of selectionscreen
    //private <Pane>playerPane;
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

    //GameScreenButtons
    private VBox GameToolOption;
    private Button AboutJTEButton;
    private Button GameHistoryButton;
    private Button AirMapButton;
    private Button die;
     //buttons for the map

//Cities
    private String CityName;
    private String Color;
    private int part;
    private int x;
    private int y;

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

    // mainPane weight && height
    private int paneWidth;
    private int paneHeigth;

    // THIS CLASS WILL HANDLE ALL ACTION EVENTS FOR THIS PROGRAM
    private JourneyThroughEuropeEventHandler eventHandler;
    private JourneyThroughEuropeErrorHandler errorHandler;
    private JourneyThroughEuropeDocumentManager docManager;

    JourneyThroughEuropeGameStateManager gsm;

    public JourneyThroughEuropeUI() {
        gsm = new JourneyThroughEuropeGameStateManager(this);

        eventHandler = new JourneyThroughEuropeEventHandler(this);
        errorHandler = new JourneyThroughEuropeErrorHandler(primaryStage);
        docManager = new JourneyThroughEuropeDocumentManager(this);
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
        props.addProperty(JTEPropertyType.INSETS, "5");
        String str = props.getProperty(JTEPropertyType.INSETS);
        System.out.println("11111111" + str);
   // splashScreenPane = new Pane();

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
       //stage.setScene(new Scene(splashScreenPane));
        //stage.show();

        initOptionToolbar();
        // add key listener

        mainPane.setCenter(splashScreenPane);
        mainPane.setBottom(OptionToolbar);

    }

    public void initJourneyThroughEuropeUI() {

        mainPane.setBottom(null);

        // GET THE UPDATED TITLE
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String title = props.getProperty(JTEPropertyType.GAME_TITLE_TEXT);
        primaryStage.setTitle(title);

        // THEN ADD ALL THE STUFF WE MIGHT NOW USE
        // initOptionToolbar();
        // OUR WORKSPACE WILL STORE EITHER THE GAME, STATS,
        // OR HELP UI AT ANY ONE TIME
        initWorkspace();
        initGameScreen();
        initLoadPane();
        initAboutPane();
        initSelectionScreenPane();

        // WE'LL START OUT WITH THE GAME SCREEN
        // changeWorkspace(JTEUIState.PLAY_GAME_STATE);
    }

    private void initOptionToolbar() {
        OptionToolbar = new HBox();
        OptionToolbar.setStyle("-fx-background-color: white");
        OptionToolbar.setSpacing(10.0);
        OptionToolbar.setPadding(marginlessInsets);
        OptionToolbar.setAlignment(Pos.CENTER);

        /*
         set the StartButton to the SplashScreen
         */
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
        Toolbar.getChildren().add(button);
        return button;

    }

    private void initWorkspace() {
        // THE WORKSPACE WILL GO IN THE CENTER OF THE WINDOW, UNDER THE NORTH
        // TOOLBAR
        workspace = new Pane();
        mainPane.setCenter(workspace);
        mainPane.getChildren().add(workspace);
        System.out.println("in the initWorkspace");
    }

    private void initAboutPane() {

        System.out.println("aboutpane");

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String aboutScreenImagePath = props
                .getProperty(JTEPropertyType.ABOUT_SCREEN_IMAGE_NAME);
        props.addProperty(JTEPropertyType.INSETS, "7");
        String str = props.getProperty(JTEPropertyType.INSETS);
        System.out.println("11111111" + str);
   // splashScreenPane = new Pane();

        StackPane aboutScreenPane = new StackPane();
        aboutScreenPane.setPrefSize(827, 622);

        Image aboutScreenImage = loadImage(aboutScreenImagePath);
        aboutScreenImageView = new ImageView(aboutScreenImage);

        aboutScreenImageLabel = new Label();
        aboutScreenImageLabel.setGraphic(aboutScreenImageView);
        aboutScreenImageLabel.setLayoutX(-45);// move the label position to fix
        // the pane
        aboutScreenPane.getChildren().add(aboutScreenImageLabel);
        StackPane.setAlignment(aboutScreenImageLabel, Pos.CENTER);

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

        OKButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.SPLASH_SCREEN_STATE);
            }

        });

        AboutHyperlinkListener hhl = new AboutHyperlinkListener(this);
    }

    private void initSelectionScreenPane() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String selectScreenImagePath = props
                .getProperty(JTEPropertyType.SELECT_SCREEN_IMAGE_NAME);
        props.addProperty(JTEPropertyType.INSETS, "6");
        String str = props.getProperty(JTEPropertyType.INSETS);
        System.out.println("11111111" + str);

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
        // setTooltip(homeButton, jtePropertyType.OK_TOOLTIP);
        GOButton.setPadding(marginlessInsets);
        // to the button in the toolbar
        HBox selectToolbar = new HBox();
   // AboutPanel=new BorderPane();

        final ComboBox CB = new ComboBox();
        CB.getItems().addAll(2, 3, 4, 5, 6);
        CB.setValue("Select Player #");

        selectToolbar.getChildren().addAll(CB, GOButton);

        selectToolbar.setStyle("-fx-background-color:white");

        GridPane playerSelectionGridPane = new GridPane();

        playerSelectionGridPane.setHgap(10);

        playerSelectionGridPane.setVgap(10);

        playerSelectionGridPane.setPadding(new Insets(0, 10, 0, 10));

        final TextField tex = new TextField();
        tex.setPrefWidth(50);
        tex.setPrefHeight(10);

        ArrayList<String> flagImages = props
                .getPropertyOptionsList(JTEPropertyType.FLAG_IMG_NAMES);

        String flagImageName = flagImages.get(0);
        Image flagImage = loadImage(flagImageName);
        ImageView flagImageView = new ImageView(flagImage);
        flagScreenImageLael = new Label();
        flagScreenImageLael.setGraphic(flagImageView);
       
         String flagImageName1 = flagImages.get(1);
        Image flagImage1 = loadImage(flagImageName1);
        ImageView flagImageView1 = new ImageView(flagImage1);
        Label flagScreenImageLael1 = new Label();
        flagScreenImageLael1.setGraphic(flagImageView1);
        
       
        
        StackPane pPane = new StackPane();
        RBC = new RadioButton("Computer");
        RBP = new RadioButton("Player");
        TF = new TextField("Player" + 1);

        RadioButton RBC1 = new RadioButton("Computer");
        RadioButton RBP1 = new RadioButton("Player");
        TextField TF1 = new TextField("Player" + 2);
        
       
        int playernum = 2;

        TF.setId("Player" + playernum);
        pPane.getChildren().addAll(playerSelectionGridPane);
        // playerSelectionGridPane.add(flagScreenImageLael,0,2);
        playerSelectionGridPane.add(flagScreenImageLael, 0, 3);
        playerSelectionGridPane.add(flagScreenImageLael1, 4,3);
       
        // playerSelectionGridPane.add(flagScreenImageLael,0,4);
        playerSelectionGridPane.add(RBC, 1, 2);
        playerSelectionGridPane.add(RBC1, 5, 2);
    
        
        playerSelectionGridPane.add(RBP, 1, 3);
        playerSelectionGridPane.add(RBP1, 5, 3);
     
         
        playerSelectionGridPane.add(TF, 1, 4);
        playerSelectionGridPane.add(TF1,5, 4);
        
  //flagScreenImageLael,RBC,RBP,TF

        RBC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isComputer = true;
                RBP.setDisable(true);
                eventHandler.respondToRadioButtonSelection(primaryStage);
            }

        });
        RBP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isPerson = true;
                RBC.setDisable(true);
                eventHandler.respondToRadioButtonSelection(primaryStage);
            }

        });
         RBC1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isComputer = true;
                RBP1.setDisable(true);
                eventHandler.respondToRadioButtonSelection(primaryStage);
            }

        });
        RBP1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isPerson = true;
                RBC1.setDisable(true);
                eventHandler.respondToRadioButtonSelection(primaryStage);
            }

        });

        mainPane.setTop(selectToolbar);
        mainPane.setCenter(pPane);

        GOButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.PLAY_GAME_STATE);
            }

        });

    }

    JEditorPane gamePane;

    private void initGameScreen() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String MAPScreenImagePath = props
                .getProperty(JTEPropertyType.GAME_MAP_AC14_IMAGE_NAME);
        props.addProperty(JTEPropertyType.INSETS, "8");
        String str = props.getProperty(JTEPropertyType.INSETS);
        System.out.println("11111111" + str);

        StackPane GameScreenPane = new StackPane();
        GameScreenPane.setPrefSize(600, 800);

        Image MAPScreenImage = loadImage(MAPScreenImagePath);
        MAPScreenImageView = new ImageView(MAPScreenImage);

        MAPScreenImageLabel = new Label();
        MAPScreenImageLabel.setGraphic(MAPScreenImageView);
        MAPScreenImageLabel.setLayoutX(-45);// move the label position to fix
        // the pane
        GameScreenPane.getChildren().add(MAPScreenImageLabel);
        StackPane.setAlignment(MAPScreenImageLabel, Pos.CENTER);

         //Make the tool bar which will return to the main pain 
        //  PropertiesManager props = PropertiesManager.getPropertiesManager();
        String AboutJTEImgName = props.getProperty(JTEPropertyType.ABOUT_JTE_IMG_NAME);
        Image AboutJTEImg = loadImage(AboutJTEImgName);
        ImageView AboutJTEImgIcon = new ImageView(AboutJTEImg);

        AboutJTEButton = new Button();
        AboutJTEButton.setGraphic(AboutJTEImgIcon);
        AboutJTEButton.setPadding(marginlessInsets);

        String HistoryImgName = props.getProperty(JTEPropertyType.GAME_HISTORY_IMAGE_NAME);
        Image HistoryImg = loadImage(HistoryImgName);
        ImageView HistoryImgIcon = new ImageView(HistoryImg);
        GameHistoryButton = new Button();
        GameHistoryButton.setGraphic(HistoryImgIcon);
        GameHistoryButton.setPadding(marginlessInsets);

        String airImgName = props.getProperty(JTEPropertyType.AIR_MAP_IMG_NAME);
        Image airImg = loadImage(airImgName);
        ImageView airImgIcon = new ImageView(airImg);
        AirMapButton = new Button();
        AirMapButton.setGraphic(airImgIcon);
        AirMapButton.setPadding(marginlessInsets);

        String dieImgName = props.getProperty(JTEPropertyType.DIE_IMAGE_NAME);
        Image dieImg = loadImage(dieImgName);
        ImageView dieImgIcon = new ImageView(dieImg);
        die = new Button();
        die.setGraphic(dieImgIcon);
        die.setPadding(marginlessInsets);

        // to the button in the toolbar
        VBox selectToolbar = new VBox();

        selectToolbar.getChildren().addAll(die, AirMapButton, AboutJTEButton, GameHistoryButton);

        selectToolbar.setStyle("-fx-background-color:white");

        mainPane.setRight(selectToolbar);
        mainPane.setCenter(GameScreenPane);

        AboutJTEButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.VIEW_ABOUT_STATE);
            }

        });
        AirMapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.VIEW_AIR_MAP_STATE);
            }

        });
        GameHistoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToSwitchScreenRequest(JTEUIState.LOAD_HISTORY_STATE);
            }

        });
        die.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventHandler.respondToRollDieRequest();
            }

        });
    }

    private void initLoadPane() {
        System.out.println("aboutpane");

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String loadScreenImagePath = props
                .getProperty(JTEPropertyType.ABOUT_SCREEN_IMAGE_NAME);
        props.addProperty(JTEPropertyType.INSETS, "7");
        String str = props.getProperty(JTEPropertyType.INSETS);
        System.out.println("11111111" + str);
   // splashScreenPane = new Pane();

        StackPane loadScreenPane = new StackPane();

        String OKImgName = props.getProperty(JTEPropertyType.OK_IMG_NAME);
        Image OKImg = loadImage(OKImgName);
        ImageView OKImgIcon = new ImageView(OKImg);
        Button OKButton1 = new Button();
        OKButton1.setGraphic(OKImgIcon);
        // setTooltip(homeButton, jtePropertyType.OK_TOOLTIP);
        OKButton1.setPadding(marginlessInsets);
        // to the button in the toolbar
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
   // splashScreenPane = new Pane();

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
        // to the button in the toolbar
        VBox aboutToolbar = new VBox();
   // AboutPanel=new BorderPane();

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
                // mainPane.setCenter(SelectionScreenPanel);
                initSelectionScreenPane();
                break;
            case PLAY_GAME_STATE:
                mainPane.getChildren().clear();
                //  mainPane.setCenter(GamePanel);
                initGameScreen();
                break;
            case VIEW_ABOUT_STATE:
                //System.out.println("test");
                mainPane.getChildren().clear();
                // mainPane.setCenter(AboutPanel);
                initAboutPane();
                break;
            case LOAD_HISTORY_STATE:
                mainPane.getChildren().clear();
                initLoadPane();
                break;
            default:
        }
    }

    public void City(String CityName, String Color, int part, int x, int y) {
        this.CityName = CityName;
        this.Color = Color;
        this.part = part;
        this.x = x;
        this.y = y;
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
    public void loadRemoteHelpPage(URL link) {
        try {
            // PUT THE WEB PAGE IN THE HELP PANE
            Document doc = AboutPane.getDocument();
            doc.putProperty(Document.StreamDescriptionProperty, null);
            AboutPane.setPage(link);
        } catch (IOException ioe) {
            errorHandler.processError(JTEPropertyType.INVALID_URL_ERROR_TEXT);
        }
    }

    public Image loadImage(String imageName) {
        Image img = new Image(ImgPath + imageName);
        return img;
    }

}