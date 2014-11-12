package JTE.file;

import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.Main.JTEPropertyType;
import properties_manager.PropertiesManager;
import JTE.ui.JourneyThroughEuropeUI;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.text.Element;
import org.w3c.dom.Document;
//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Scanner;

/**
 *
 * @author peng
 */
public class JourneyThroughEuropeFileLoader {

    private JourneyThroughEuropeUI ui;
    private String FilePath = "data/";
    static String[][] AllCities;

    public JourneyThroughEuropeFileLoader(JourneyThroughEuropeUI initUI) {
        ui = initUI;
    }
    // Document doc =  builder.parse(new File());
    // set up an array for the value of city
    // static String[][]csvToArray;

    public static void loadCSVtoArrayandPrint() {
        //csvToArray=new String[180][5];
        // AllCities=new String [179][5];
        Scanner fromFile = null;
       // int rowc=0;
        //int row=0;
        //int colc=0;
        // int col=0;

        String InputLine = "";
        //String fileLocation;
        String fileName = "cities.txt";

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        fileName = props.getProperty(JTEPropertyType.DATA_PATH) + fileName;

        try {
            fromFile = new Scanner(new BufferedReader(new FileReader(fileName)));

            while (fromFile.hasNextLine()) {
                InputLine = fromFile.nextLine();

                String[] CityInfo = InputLine.split(" ");
                //Print all the city out
                System.out.println(Arrays.toString(CityInfo));

        //Loop to city's name color part x and y coordinate 
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String loadTextFile(String fileName) throws IOException {

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        fileName = props.getProperty(JTEPropertyType.DATA_PATH) + fileName;

        // WE'LL ADD ALL THE CONTENTS OF THE TEXT FILE TO THIS STRING
        String textToReturn = "";

        // OPEN A STREAM TO READ THE TEXT FILE
        FileReader fr = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fr);

        // READ THE FILE, ONE LINE OF TEXT AT A TIME
        String inputLine = reader.readLine();
        while (inputLine != null) {
            // APPEND EACH LINE TO THE STRING
            textToReturn += inputLine + "\n";

            // READ THE NEXT LINE
            inputLine = reader.readLine();

        }

        // RETURN THE TEXT
        return textToReturn;
    }

}
