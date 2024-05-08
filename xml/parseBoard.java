// Example Code for parsing XML file
// Dr. Moushumi Sharmin
// CSCI 345
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class parseBoard{

   
        // building a document from the XML file
        // returns a Document object after loading the book.xml file.
        public Document getDocFromFile(String filename)
        throws ParserConfigurationException{
        {     
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = null;
           
           try{
               doc = db.parse(filename);
           } catch (Exception ex){
               System.out.println("XML parse failure");
               ex.printStackTrace();
           }
           return doc;
        } // exception handling
        
        }  
        
        // reads data from board XML file
        public void readBoardData(Document d){
        
            // Gets the root AKA "board"
            Element root = d.getDocumentElement();
            
            NodeList sets = root.getElementsByTagName("set");
            
            for (int i=0; i<sets.getLength();i++){
                
                System.out.println("Printing information for set "+(i+1));
                
                //reads data from the nodes
                Node set = sets.item(i);
                String setName = set.getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("Set name = "+setName);
                
                //Im not entirely sure how to proceed with the rest
                                             
                NodeList neighbors = set.getChildNodes();
                
                for (int j=0; j< neighbors.getLength(); j++){
                    
                  Node sub = neighbors.item(j);
                
                  if("title".equals(sub.getNodeName())){
                     String bookLanguage = sub.getAttributes().getNamedItem("lang").getNodeValue();
                     System.out.println("Language = "+bookLanguage);
                     String title = sub.getTextContent();
                     System.out.println("Title = "+title);
                     
                  }
                  
                  else if("author".equals(sub.getNodeName())){
                     String authorName = sub.getTextContent();
                     System.out.println(" Author = "+authorName);
                     
                  }
                  else if("year".equals(sub.getNodeName())){
                     String yearVal = sub.getTextContent();
                     System.out.println(" Publication Year = "+yearVal);
                     
                  }
                  else if("price".equals(sub.getNodeName())){
                     String priceVal = sub.getTextContent();
                     System.out.println(" Price = "+priceVal);
                     
                  }
                                 
                
                } //for childnodes
                
                System.out.println("\n");
                
            }//for book nodes
        
        }// method
    
    



}//class
