
// // Example Code for parsing XML file
// // Dr. Moushumi Sharmin
// // CSCI 345


// <!-- Structure of XML file -->
// <!--<board>
//     <set>
//         <neighbors>
//         </neigbors>
//         <area>
//         <takes>
//         </takes>
//         <parts>
//             <part>
//             <area>
//             <line></line>
//         </parts>
//     </set>
//     <trailer>
//         <neighbors>
//         </neighbors>
//         <area>
//     </trailer>
//     <office>
//         <neighbors>
//         </neighbors>
//         <area>
//         <upgrades>
//             <upgrade>
//             <area>
//             </upgrade>
//         </upgrades>
//     </office>
//     </board>-->

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

            // Start of the Neighbors loop
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
            NodeList setAreas = set.getElementsByTagName("area");
            for (int b=0; b<setAreas.getLength();b++){
                Element areas = (Element) setAreas.item(b);
                String x = areas.getAttribute("x");
                System.out.println("This should be the x-value: "+x);
                String y = areas.getAttribute("y");
                System.out.println("This should be the y-value: "+y);
                String h = areas.getAttribute("h");
                System.out.println("This should be the h-value: "+h);
                String w = areas.getAttribute("w");
                System.out.println("This should be the w-value: "+w);
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
