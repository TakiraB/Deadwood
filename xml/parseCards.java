import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

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

    public void readCardData(Document d) {
        Element root = d.getDocumentElement();
        NodeList cards = root.getElementsByTagName("card");

        for(int i = 0;i<cards.getLength();i++){
            Element cardList = (Element) cards.item(i);
            String cardName = cardList.getAttributes().getNamedItem("name").getNodeValue();
            System.out.println("Card Title: "+ cardName);
            String cardImage = cardList.getAttributes().getNamedItem("img").getNodeValue();
            System.out.println("Associated Image: "+ cardImage);
            String cardBudget = cardList.getAttributes().getNamedItem("budget").getNodeValue();
            System.out.println("Card Budget: "+ cardBudget);

            NodeList cardScenes = cardList.getElementsByTagName("scene");

            for(int j=0;j<cardScenes.getLength();j++){
                Element cardScene = (Element) cardScenes.item(j);
                String sceneNumber = cardScene.getAttributes().getNamedItem("number").getNodeValue();
                System.out.println("Scene Number: "+ sceneNumber);
                String sceneDescription = cardScene.getTextContent().trim();
                System.out.println("Scene Line: "+ sceneDescription);
            }

            NodeList parts = cardList.getElementsByTagName("part");

            for (int k=0;k<parts.getLength();k++){
                Element partList = (Element) parts.item(k);
                String partName = partList.getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("Part Name: "+ partName);
                String partLevel = partList.getAttributes().getNamedItem("level").getNodeValue();
                System.out.println("Part Level: "+ partLevel);

                NodeList cardSceneAreaList = partList.getElementsByTagName("area");

                for (int a=0;a<cardSceneAreaList.getLength();a++){
                    Element starredPartArea = (Element) cardSceneAreaList.item(a);
                    String x = starredPartArea.getAttribute("x");
                    System.out.println("This should be the x-value: "+x);
                    String y = starredPartArea.getAttribute("y");
                    System.out.println("This should be the y-value: "+y);
                    String h = starredPartArea.getAttribute("h");
                    System.out.println("This should be the h-value: "+h);
                    String w = starredPartArea.getAttribute("w");
                    System.out.println("This should be the w-value: "+w);
                }

                NodeList cardSceneLineList = partList.getElementsByTagName("line");

                for (int b=0;b<cardSceneLineList.getLength();b++){
                    Element starredLines = (Element) cardSceneLineList.item(b);
                    String starredLineText = starredLines.getTextContent().trim();
                    System.out.println("This should be the Starred Part Line: "+starredLineText);
                }
            }
        }
    }

    public static void main(String args[]){
        Document doc = null;
        parseCards parsing = new parseCards();
        try{
           doc = parsing.getDocFromFile("cards.xml");
           parsing.readCardData(doc);
        }catch (Exception e){
           System.out.println("Error = "+e);
        }
     }
}

