package JTE.file;

import JTE.ui.City;
import java.io.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.Main.JTEPropertyType;
import properties_manager.PropertiesManager;
import JTE.ui.JourneyThroughEuropeUI;
import JTE.ui.NeighbourCity;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

//import javax.swing.text.Document;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author peng
 */
public class JourneyThroughEuropeFileLoader {

    private JourneyThroughEuropeUI ui;
    private String FilePath = "data/";
    private String CName="";
    private ArrayList<String> land;
    private ArrayList<String> sea;

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
                

            
                //System.out.println(a+","+b+","+p+","+ CityX+","+CityY);

                City city = new City(a, b, p, CityX, CityY);
                
                cities.add(city);
               

            }

        } catch (Exception e) {
            System.out.print(e);
        }

        //    System.out.println(cities);
        return cities;
    }
    public ArrayList<NeighbourCity>loadNeighbourCity(){
    ArrayList<NeighbourCity> neighbours = new ArrayList();
    
    
        String fileName = "cities.xml";

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        fileName = props.getProperty(JTEPropertyType.DATA_PATH) + fileName;
    
    
    try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(fileName));
			Node root = doc.getElementsByTagName("routes").item(0);
			NodeList cardlist = root.getChildNodes();
                        
                         NeighbourCity nc;
                        
			for (int i = 0; i < cardlist.getLength(); i++) {
				Node cardNode = cardlist.item(i);
				if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
					NodeList cardAttrs = cardNode.getChildNodes();
					// one card
					for (int j = 0; j < cardAttrs.getLength(); j++) {
						if (cardAttrs.item(j).getNodeType() == Node.ELEMENT_NODE) {
							Node theNode = cardAttrs.item(j);
							switch (theNode.getNodeName()) {
                                                            
							case "name":
                                                              CName =theNode.getTextContent();
                                                            
								//System.out.println("City name: "
								//		+ theNode.getTextContent());
                                                                //nc.setName(CName);
                                                                        break;
							case "land":
								NodeList landList = theNode.getChildNodes();
                                                                String lands = "";
                                                                land = new ArrayList();
								for (int k = 0; k < landList.getLength(); k++) {
									if (landList.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                                                          //  ArrayList<String> land = new ArrayList();
                                                                          
                                                                            lands = landList.item(k).getTextContent();
                                                                        land.add(lands);
                                                                          
                                                                            //land.add(landList.item(k).getTextContent());
                                                                          
										//System.out.println("Land neighbour: "
										//		+ landList.item(k)
										//				.getTextContent());
									}
								}
                                                                 //nc.setLand(land);
                                                                //System.out.println("Array of land: "+land.toString());
								break;
							case "sea":
								NodeList seaList = theNode.getChildNodes();
                                                                String seas="";
                                                                sea = new ArrayList();								
                                                                for (int k = 0; k < seaList.getLength(); k++) {
									if (seaList.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                                                            //ArrayList<String> sea = new ArrayList();
                                                                           seas=seaList.item(k).getTextContent();
                                                                           // sea.add(seaList.item(k).getTextContent());
                                                                          
										//System.out.println("Sea neighbour: "
										//		+ seaList.item(k)
										//				.getTextContent());
									sea.add(seas);
                                                                        
                                                                        }
								}
                                                                //nc.setSea(sea);
                                                               // System.out.println("Array of sea: "+sea.toString());
								break;
                                                           
                                                               
                                                             
							}
                                                        nc = new NeighbourCity(CName, land, sea);
                                                     //System.out.println(" "+nc.toString());
						}
					
                                         //System.out.println(" "+nc.toString());
                                                
                                       
                                        }
                                    // neighbours.add(nc);
                             //   System.out.println(neighbours.toString());   
				}
                                nc = new NeighbourCity(CName, land, sea);
                               // System.out.println("test: " + nc.toString());
                                
                                neighbours.add(nc);
                                
                               // System.out.println(neighbours.toString()); 
			}
                        

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
    
    
    
    return  neighbours;
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
