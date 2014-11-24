package JTE.file;

import JTE.ui.City;
import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.Main.JTEPropertyType;
import properties_manager.PropertiesManager;
import JTE.ui.JourneyThroughEuropeUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

//import javax.swing.text.Document;
import java.util.Scanner;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author peng
 */
public class JourneyThroughEuropeFileLoader {

    private JourneyThroughEuropeUI ui;
    private String FilePath = "data/";

    public JourneyThroughEuropeFileLoader(JourneyThroughEuropeUI initUI) {
        ui = initUI;
    }

    // Document doc =  builder.parse(new File());
    // set up an array for the value of city
    // static String[][]csvToArray;
//public static void 

    public ArrayList<City> loadCSVFile() {
        ArrayList<City> cities = new ArrayList();

        String fileName = "cities.csv";

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        fileName = props.getProperty(JTEPropertyType.DATA_PATH) + fileName;

        try {

            Scanner fromFile = new Scanner(new BufferedReader(new FileReader(fileName)));
            int CityX=0;
            int CityY=0;
            //String b[]=null;
            while (fromFile.hasNextLine()) {

                String Input = fromFile.nextLine();
                String[] c = Input.split("\t");
                //System.out.println(c[0]);

                // c[0] cityName  c[1] cityColor  c[2] Part    c[3] CityX    c[4] CityY
                String a = c[0];
                String b = c[1];
                int p = Integer.parseInt(c[2]);
                int x = Integer.parseInt(c[3]);
                int y = Integer.parseInt(c[4]);
                switch (p){
                    case 1:
                        CityX =(int) ((x * 517)/2010);
                        CityY =(int) ((y * 660)/ 2569);
                        break;
                    case 2:
                         CityX = (int)((x * 517) / 1903);
                         CityY = (int)((y * 660) / 2585);
                        break;
                    case 3:
                         CityX = (int)((x * 517) / 1985);
                        CityY = (int)((y * 660) / 2585);
                        break;
                    case 4:
                         CityX = (int)((x * 517) / 1972);
                        CityY = (int)((y * 660) / 2583);
                        break;
                    default:
                
                }
                

            
                System.out.println(a+","+b+","+p+","+ CityX+","+CityY);

                City city = new City(a, b, p, CityX, CityY);
                
                cities.add(city);
               

            }

        } catch (Exception e) {
            System.out.print(e);
        }

        //    System.out.println(cities);
        return cities;
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
