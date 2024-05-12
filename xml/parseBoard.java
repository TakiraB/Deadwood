import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class parseBoard {

    public Document getDocFromFile(String filename)
            throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = null;

        try {
            doc = db.parse(new File(filename));
        } catch (Exception ex) {
            System.out.println("XML parse failure");
            ex.printStackTrace();
        }
        return doc;
    }

    public void readBoardData(Document d) {
        // Get the root element of the document <board>
        Element root = d.getDocumentElement();

        // create a Nodelist of sets by searching for the tag name
        NodeList sets = root.getElementsByTagName("set");

        // Start of the Set loop
        // Iterate through each "chunk" of sets all the way up to 10 sets (length is how many sets)
        for (int i = 0; i < sets.getLength(); i++) {
            // Making sure we have all the sets accounted for (10)
            System.out.println("Printing information for set " + (i + 1));
            // Each element is an individual set
            Element set = (Element) sets.item(i);
            // Get the name associated with set by specifically getting "name" attribute, node value stored in setName
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();
            // Printing for testing purposes
            System.out.println("Set name = " + setName);

            // Trying to get the neighbors of each set
            // Create a NodeList of all the <neighbors> tag for each set
            NodeList neighborList = set.getElementsByTagName("neighbors");

            // Start of the Set Neighbors loop
            for (int j=0; j<neighborList.getLength(); j++){
                Element neighbors = (Element) neighborList.item(j);
                NodeList neighborNameList = neighbors.getElementsByTagName("neighbor");

                for(int k=0; k<neighborNameList.getLength(); k++){
                    Element singleNeighbor = (Element) neighborNameList.item(k);
                    String singleNeighborName = singleNeighbor.getAttributes().getNamedItem("name").getNodeValue();
                    System.out.println("Neighbors: "+ singleNeighborName);
                }

            }
            // Get the area
            NodeList setArea = set.getElementsByTagName("area");
            Element areas = (Element) setArea.item(0);
            String x1 = areas.getAttribute("x");
            System.out.println("This should be the x-value: "+x1);
            String y1 = areas.getAttribute("y");
            System.out.println("This should be the y-value: "+y1);
            String h1 = areas.getAttribute("h");
            System.out.println("This should be the h-value: "+h1);
            String w1 = areas.getAttribute("w");
            System.out.println("This should be the w-value: "+w1);

            NodeList takes = set.getElementsByTagName("takes");

            for (int a=0;a<takes.getLength(); a++){
                Element setTakes = (Element) takes.item(a);
                NodeList takesList = setTakes.getElementsByTagName("take");

                for (int b=0;b<takesList.getLength(); b++){
                    Element singleTake = (Element) takesList.item(b);
                    String takeNumber = singleTake.getAttributes().getNamedItem("number").getNodeValue();
                    System.out.println("Take Numbers: "+ takeNumber);
                    NodeList takeArea = singleTake.getElementsByTagName("area");

                    for(int c=0;c<takeArea.getLength(); c++){
                        Element takeAreas = (Element) takeArea.item(c);
                        String x2 = takeAreas.getAttribute("x");
                        System.out.println("This should be the x-value (takes): "+x2);
                        String y2 = takeAreas.getAttribute("y");
                        System.out.println("This should be the y-value (takes): "+y2);
                        String h2 = takeAreas.getAttribute("h");
                        System.out.println("This should be the h-value (takes): "+h2);
                        String w2 = takeAreas.getAttribute("w");
                        System.out.println("This should be the w-value (takes): "+w2);
                    }
                }
            }

            NodeList parts = set.getElementsByTagName("parts");

            for(int m=0;m<parts.getLength();m++){
                Element partElements = (Element) parts.item(m);
                NodeList singleParts = partElements.getElementsByTagName("part");

                for(int n=0;n<singleParts.getLength();n++){
                    Element individualPart = (Element) singleParts.item(n);
                    String partName = individualPart.getAttributes().getNamedItem("name").getNodeValue();
                    String partLevel = individualPart.getAttributes().getNamedItem("level").getNodeValue();
                    System.out.println("This is the part: " + partName + " This is the level: " + partLevel);
                    
                    NodeList partAreaList = individualPart.getElementsByTagName("area");

                    for(int o=0;o<partAreaList.getLength();o++){
                        Element partArea = (Element) partAreaList.item(o);
                        String x3 = partArea.getAttribute("x");
                        System.out.println("This should be the x-value (takes): "+x3);
                        String y3 = partArea.getAttribute("y");
                        System.out.println("This should be the y-value (takes): "+y3);
                        String h3 = partArea.getAttribute("h");
                        System.out.println("This should be the h-value (takes): "+h3);
                        String w3 = partArea.getAttribute("w");
                        System.out.println("This should be the w-value (takes): "+w3);
                    }

                    NodeList lineList = individualPart.getElementsByTagName("line");

                    for(int p=0;p<lineList.getLength();p++){
                        Element partLine = (Element)lineList.item(p);
                        String line = partLine.getTextContent();
                        System.out.println("This is the line for this part: "+line);
                    }
                }
            }
        }

        NodeList trailer = root.getElementsByTagName("trailer");

        for(int q=0;q<trailer.getLength();q++){
            Element trailerElement = (Element)trailer.item(q);
            NodeList trailerNeighborList = trailerElement.getElementsByTagName("neighbors");

            for (int r=0;r<trailerNeighborList.getLength();r++){
                Element trailerNeighborElement = (Element)trailerNeighborList.item(q);
                NodeList trailerNeighbor = trailerNeighborElement.getElementsByTagName("neighbor");

                for(int s=0;s<trailerNeighbor.getLength();s++){
                    Element singleTrailerNeighborElement = (Element)trailerNeighbor.item(s);
                    String singleTrailerNeighborName = singleTrailerNeighborElement.getAttributes().getNamedItem("name").getNodeValue();
                    System.out.println("Trailer Neighbors: "+ singleTrailerNeighborName);
                }
            }

            NodeList trailerArea = trailerElement.getElementsByTagName("area");
            Element trailerAreaDims = (Element) trailerArea.item(0);
            String x4 = trailerAreaDims.getAttribute("x");
            System.out.println("This should be the x-value of Trailer: "+x4);
            String y4 = trailerAreaDims.getAttribute("y");
            System.out.println("This should be the y-value of Trailer: "+y4);
            String h4 = trailerAreaDims.getAttribute("h");
            System.out.println("This should be the h-value of Trailer: "+h4);
            String w4 = trailerAreaDims.getAttribute("w");
            System.out.println("This should be the w-value of Trailer: "+w4);
        }

        NodeList office = root.getElementsByTagName("office");

        for(int e=0;e<office.getLength();e++){
            Element officeElement = (Element)office.item(e);
            NodeList officeNeighborList = officeElement.getElementsByTagName("neighbors");

            for (int f=0;f<officeNeighborList.getLength();f++){
                Element officeNeighborElement = (Element)officeNeighborList.item(f);
                NodeList officeNeighbor = officeNeighborElement.getElementsByTagName("neighbor");

                for(int g=0;g<officeNeighbor.getLength();g++){
                    Element singleOfficeNeighborElement = (Element)officeNeighbor.item(g);
                    String singleOfficeNeighborName = singleOfficeNeighborElement.getAttributes().getNamedItem("name").getNodeValue();
                    System.out.println("Office Neighbors: "+ singleOfficeNeighborName);
                }
            }

            NodeList officeArea = officeElement.getElementsByTagName("area");
            Element officeAreaDims = (Element) officeArea.item(0);
            String x5 = officeAreaDims.getAttribute("x");
            System.out.println("This should be the x-value of Office: "+x5);
            String y5 = officeAreaDims.getAttribute("y");
            System.out.println("This should be the y-value of Office: "+y5);
            String h5 = officeAreaDims.getAttribute("h");
            System.out.println("This should be the h-value of Office: "+h5);
            String w5 = officeAreaDims.getAttribute("w");
            System.out.println("This should be the w-value of Office: "+w5);

            NodeList officeUpgradeList = officeElement.getElementsByTagName("upgrades");

            for(int t=0;t<officeUpgradeList.getLength();t++){
                Element officeUpgradeElement = (Element) officeUpgradeList.item(t);
                NodeList singleOfficeUpgradeList = officeUpgradeElement.getElementsByTagName("upgrade");

                for (int u=0;u<singleOfficeUpgradeList.getLength();u++){
                    Element singleOfficeUpgradElement = (Element) singleOfficeUpgradeList.item(u);
                    String singleOfficeUpgradeLevel = singleOfficeUpgradElement.getAttributes().getNamedItem("level").getNodeValue();
                    System.out.println("Upgrade level: "+singleOfficeUpgradeLevel);
                    String singleOfficeUpgradeCurrency = singleOfficeUpgradElement.getAttributes().getNamedItem("currency").getNodeValue();
                    System.out.println("Currency Needed: "+singleOfficeUpgradeCurrency);
                    String singleOfficeUpgradeAmount = singleOfficeUpgradElement.getAttributes().getNamedItem("amt").getNodeValue();
                    System.out.println("Amount of Currency: "+singleOfficeUpgradeAmount);

                    NodeList officeUpgradeArea = singleOfficeUpgradElement.getElementsByTagName("area");

                    for(int v=0;v<officeUpgradeArea.getLength();v++){
                        Element officeUpgradeAreaDims = (Element) officeUpgradeArea.item(v);
                        String x6 = officeUpgradeAreaDims.getAttribute("x");
                        System.out.println("This should be the x-value of Office Upgrade: "+x6);
                        String y6 = officeUpgradeAreaDims.getAttribute("y");
                        System.out.println("This should be the y-value of Office Upgrade: "+y6);
                        String h6 = officeUpgradeAreaDims.getAttribute("h");
                        System.out.println("This should be the h-value of Office Upgrade: "+h6);
                        String w6 = officeUpgradeAreaDims.getAttribute("w");
                        System.out.println("This should be the w-value of Office Upgrade: "+w6);
                    }
                }
            }
        }
}

public static void main(String args[]){
    Document doc = null;
    parseBoard parsing = new parseBoard();
    try{
       doc = parsing.getDocFromFile("board.xml");
       parsing.readBoardData(doc);
    }catch (Exception e){
       System.out.println("Error = "+e);
    }
 }
}
