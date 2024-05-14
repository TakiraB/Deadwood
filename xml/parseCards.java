// Parsing Cards
// SCENECARD CLASS
// Each Card has a:
// Name
// Associated image file
// Budget (in millions)
// Scene (and number)
// ROLE CLASS
// List of Parts, which has:
// Rank
// Associated area
// Line for each part


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class parseCards {
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

    public ArrayList<SceneCard> readCardData(Document d) {
        Element root = d.getDocumentElement();
        NodeList cards = root.getElementsByTagName("card");

        ArrayList<SceneCard> sceneCardArrayList = new ArrayList<>();

        for(int i = 0;i<cards.getLength();i++){
            Element cardList = (Element) cards.item(i);
            String cardName = cardList.getAttributes().getNamedItem("name").getNodeValue();
            String cardImage = cardList.getAttributes().getNamedItem("img").getNodeValue();
            int cardBudget = Integer.parseInt(cardList.getAttributes().getNamedItem("budget").getNodeValue());

            NodeList cardScenes = cardList.getElementsByTagName("scene");

            int sceneNumber=0;
            String sceneDescription="";

            // ArrayList<Role> starredRolesArray = new ArrayList<>();

            for(int j=0;j<cardScenes.getLength();j++){
                Element cardScene = (Element) cardScenes.item(j);
                // String sceneNumber = cardScene.getAttributes().getNamedItem("number").getNodeValue();
                // String sceneDescription = cardScene.getTextContent().trim();
                sceneNumber = Integer.parseInt(cardScene.getAttributes().getNamedItem("number").getNodeValue());
                sceneDescription = cardScene.getTextContent().trim();
            }

            ArrayList<Role> starredRolesArray = new ArrayList<>();

            NodeList parts = cardList.getElementsByTagName("part");

            for (int k=0;k<parts.getLength();k++){
                Element partList = (Element) parts.item(k);
                String partName = partList.getAttributes().getNamedItem("name").getNodeValue();
                // String partLevel = partList.getAttributes().getNamedItem("level").getNodeValue();
                int partLevel = Integer.parseInt(partList.getAttributes().getNamedItem("level").getNodeValue());

                NodeList cardSceneAreaList = partList.getElementsByTagName("area");

                for (int a=0;a<cardSceneAreaList.getLength();a++){
                    Element starredPartArea = (Element) cardSceneAreaList.item(a);
                    String x = starredPartArea.getAttribute("x");
                    String y = starredPartArea.getAttribute("y");
                    String h = starredPartArea.getAttribute("h");
                    String w = starredPartArea.getAttribute("w");
                }

                NodeList cardSceneLineList = partList.getElementsByTagName("line");

                for (int b=0;b<cardSceneLineList.getLength();b++){
                    Element starredLines = (Element) cardSceneLineList.item(b);
                    String starredLineText = starredLines.getTextContent().trim();

                    Role newRole = new Role(partName, starredLineText, true, partLevel);
                    starredRolesArray.add(newRole);
                }
                
            }

            // public class SceneCard {
            //     private String name;
            //     private int budget;
            //     private List<Role> roles;
            //     private int sceneNumber;
            //     private String description;
            //     private String imagePlaceholder;
            
            //     public SceneCard(String name, int budget, List<Role> roles, int sceneNumber, String description, String imagePlaceholder) {
            //         this.name = name;
            //         this.budget = budget;
            //         this.roles = roles;
            //         this.sceneNumber = sceneNumber;
            //         this.description = description;
            //         this.imagePlaceholder = imagePlaceholder;
            //     }

            SceneCard newSceneCard = new SceneCard(cardName, cardBudget, starredRolesArray, sceneNumber, sceneDescription, cardImage);
            sceneCardArrayList.add(newSceneCard);
        }
        return sceneCardArrayList;
    }

    public static void main(String args[]){
        Document doc = null;
        parseCards parsing = new parseCards();
        try{
            doc = parsing.getDocFromFile("cards.xml");
            ArrayList<SceneCard> cards = parsing.readCardData(doc);
        //    parsing.readCardData(doc);
        }catch (Exception e){
           System.out.println("Error = "+e);
        }
     }
}

