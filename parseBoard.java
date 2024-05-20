// Parsing the Board
// There are 10 sets
// Each set has:
// Neighbors
// Off-card Roles
// Each Off-Card Role has:
// Line
// Area (dont need this yet)
// Trailer has:
// Neighbors
// Office has:
// Upgrades (with currency )

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
// import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
// import java.util.HashMap;
// import java.util.Map;

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

    public Board readBoardData(Document d) {
        // Get the root element of the document <board>
        Element root = d.getDocumentElement();
        NodeList sets = root.getElementsByTagName("set");
        Board newBoard = new Board();

        // Start of looping through each set element
        for (int i = 0; i < sets.getLength(); i++) {

            // Each element is an individual set
            // We loop through each child tag and grab attributes as we go, declaring them
            // to be used in Objects later on
            Element set = (Element) sets.item(i);
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();
            RoomWithScene newRoom = new RoomWithScene(setName);

            // Start of the Set Neighbors loop
            // Create a NodeList of all the <neighbors> tag for each set
            NodeList neighborList = set.getElementsByTagName("neighbors");
            for (int j = 0; j < neighborList.getLength(); j++) {
                Element neighbors = (Element) neighborList.item(j);
                NodeList neighborNameList = neighbors.getElementsByTagName("neighbor");

                // Grabbing individual neighbor names for each set
                for (int k = 0; k < neighborNameList.getLength(); k++) {
                    Element singleNeighbor = (Element) neighborNameList.item(k);
                    String singleNeighborName = singleNeighbor.getAttributes().getNamedItem("name").getNodeValue();

                    // the neighbors are set to title case to make sure that you can travel to them
                    // and for consistency
                    String[] words = singleNeighborName.toLowerCase().split(" ");
                    StringBuilder capitalized = new StringBuilder();
                    for (String word : words) {
                        if (word.length() > 0) {
                            capitalized.append(Character.toUpperCase(word.charAt(0)))
                                    .append(word.substring(1))
                                    .append(" ");
                        }
                    }
                    singleNeighborName = capitalized.toString().trim();
                    newRoom.setAdjacentNeighbors(singleNeighborName);
                }

            }
            // TODO: Store Areas for each set
            NodeList setArea = set.getElementsByTagName("area");
            Element areas = (Element) setArea.item(0);
            String x1 = areas.getAttribute("x");
            String y1 = areas.getAttribute("y");
            String h1 = areas.getAttribute("h");
            String w1 = areas.getAttribute("w");

            // Loop through and parsing/storing Takes (how many takes for successful scene)
            NodeList takes = set.getElementsByTagName("takes");
            for (int a = 0; a < takes.getLength(); a++) {
                Element setTakes = (Element) takes.item(a);
                NodeList takesList = setTakes.getElementsByTagName("take");
                // Get the take numbers
                for (int b = 0; b < takesList.getLength(); b++) {
                    Element singleTake = (Element) takesList.item(b);
                    int takeNumber = Integer.parseInt(singleTake.getAttributes().getNamedItem("number").getNodeValue());
                    NodeList takeArea = singleTake.getElementsByTagName("area");
                    Takes newTake = new Takes(takeNumber);
                    newRoom.addTakesForScene(newTake);

                    // TODO: Store Areas for each take
                    for (int c = 0; c < takeArea.getLength(); c++) {
                        Element takeAreas = (Element) takeArea.item(c);
                        String x2 = takeAreas.getAttribute("x");
                        String y2 = takeAreas.getAttribute("y");
                        String h2 = takeAreas.getAttribute("h");
                        String w2 = takeAreas.getAttribute("w");
                    }
                }
            }

            // Start the loop through the parts (AKA off-card roles at each set)
            NodeList parts = set.getElementsByTagName("parts");
            for (int m = 0; m < parts.getLength(); m++) {
                Element partElements = (Element) parts.item(m);
                NodeList singleParts = partElements.getElementsByTagName("part");
                // Grab individual off-card role, including name and level
                for (int n = 0; n < singleParts.getLength(); n++) {
                    Element individualPart = (Element) singleParts.item(n);
                    String partName = individualPart.getAttributes().getNamedItem("name").getNodeValue();
                    int partLevel = Integer
                            .parseInt(individualPart.getAttributes().getNamedItem("level").getNodeValue());

                    // TODO: Store Area for each off-card role
                    NodeList partAreaList = individualPart.getElementsByTagName("area");
                    for (int o = 0; o < partAreaList.getLength(); o++) {
                        Element partArea = (Element) partAreaList.item(o);
                        String x3 = partArea.getAttribute("x");
                        String y3 = partArea.getAttribute("y");
                        String h3 = partArea.getAttribute("h");
                        String w3 = partArea.getAttribute("w");
                    }

                    // initialize empty string since I can't create Role object - too deep in the
                    // loop
                    String line = "";

                    // Loop through the lines or script for each off-card role
                    NodeList lineList = individualPart.getElementsByTagName("line");
                    for (int p = 0; p < lineList.getLength(); p++) {
                        Element partLine = (Element) lineList.item(p);
                        line = partLine.getTextContent();
                    }

                    // Create off-card roles associated with each set on the map
                    Role newRole = new Role(partName, line, false, partLevel);
                    newRoom.addRole(newRole);
                }
            }
            // Add rooms to the board layout
            newBoard.addRoomToBoard(newRoom);
        }

        // Done with grabbing all the set info, now grabbing Trailer and Neighbors to
        // add
        NodeList trailer = root.getElementsByTagName("trailer");
        for (int q = 0; q < trailer.getLength(); q++) {
            Element trailerElement = (Element) trailer.item(q);
            String trailerName = "Trailer";

            // Not creating RoomWithScene
            Room newTrailerRoom = new Room(trailerName);

            // Start looping through Neighbors for Trailer
            NodeList trailerNeighborList = trailerElement.getElementsByTagName("neighbors");
            for (int r = 0; r < trailerNeighborList.getLength(); r++) {
                Element trailerNeighborElement = (Element) trailerNeighborList.item(q);
                NodeList trailerNeighbor = trailerNeighborElement.getElementsByTagName("neighbor");

                for (int s = 0; s < trailerNeighbor.getLength(); s++) {
                    Element singleTrailerNeighborElement = (Element) trailerNeighbor.item(s);
                    String singleTrailerNeighborName = singleTrailerNeighborElement.getAttributes().getNamedItem("name")
                            .getNodeValue();

                    // Set Neighbors for Trailer
                    newTrailerRoom.setAdjacentNeighbors(singleTrailerNeighborName);
                }
            }

            // TODO: store Trailer area dims
            NodeList trailerArea = trailerElement.getElementsByTagName("area");
            Element trailerAreaDims = (Element) trailerArea.item(0);
            String x4 = trailerAreaDims.getAttribute("x");
            String y4 = trailerAreaDims.getAttribute("y");
            String h4 = trailerAreaDims.getAttribute("h");
            String w4 = trailerAreaDims.getAttribute("w");

            // Add Trailer to the Board
            newBoard.addRoomToBoard(newTrailerRoom);
        }

        // Starting the office loop
        NodeList office = root.getElementsByTagName("office");
        for (int e = 0; e < office.getLength(); e++) {
            Element officeElement = (Element) office.item(e);
            String officeName = "Casting Office";
            NodeList officeNeighborList = officeElement.getElementsByTagName("neighbors");

            // Not creating RoomWithScene
            CastingOffice newCastingOfficeRoom = new CastingOffice(officeName);

            // Grabbing office neighbors
            for (int f = 0; f < officeNeighborList.getLength(); f++) {
                Element officeNeighborElement = (Element) officeNeighborList.item(f);
                NodeList officeNeighbor = officeNeighborElement.getElementsByTagName("neighbor");
                for (int g = 0; g < officeNeighbor.getLength(); g++) {
                    Element singleOfficeNeighborElement = (Element) officeNeighbor.item(g);
                    String singleOfficeNeighborName = singleOfficeNeighborElement.getAttributes().getNamedItem("name")
                            .getNodeValue();

                    // Add the neighbors for Casting Office
                    newCastingOfficeRoom.setAdjacentNeighbors(singleOfficeNeighborName);
                }
            }

            // TODO: Store Office area dims
            NodeList officeArea = officeElement.getElementsByTagName("area");
            Element officeAreaDims = (Element) officeArea.item(0);
            String x5 = officeAreaDims.getAttribute("x");
            String y5 = officeAreaDims.getAttribute("y");
            String h5 = officeAreaDims.getAttribute("h");
            String w5 = officeAreaDims.getAttribute("w");

            // Loop through individual upgrade choices, grabbing level, currency type and
            // upgrade amount
            NodeList officeUpgradeList = officeElement.getElementsByTagName("upgrades");
            for (int t = 0; t < officeUpgradeList.getLength(); t++) {
                Element officeUpgradeElement = (Element) officeUpgradeList.item(t);
                NodeList singleOfficeUpgradeList = officeUpgradeElement.getElementsByTagName("upgrade");

                for (int u = 0; u < singleOfficeUpgradeList.getLength(); u++) {
                    Element singleOfficeUpgradeElement = (Element) singleOfficeUpgradeList.item(u);
                    int singleOfficeUpgradeLevel = Integer
                            .parseInt(singleOfficeUpgradeElement.getAttributes().getNamedItem("level").getNodeValue());
                    String singleOfficeUpgradeCurrency = singleOfficeUpgradeElement.getAttributes()
                            .getNamedItem("currency").getNodeValue();
                    int singleOfficeUpgradeAmount = Integer
                            .parseInt(singleOfficeUpgradeElement.getAttributes().getNamedItem("amt").getNodeValue());

                    // Store information in Upgrades class
                    Upgrades newUpgradeChoice = new Upgrades(singleOfficeUpgradeLevel, singleOfficeUpgradeCurrency,
                            singleOfficeUpgradeAmount);
                    newCastingOfficeRoom.addUpgradeChoice(newUpgradeChoice);

                    NodeList officeUpgradeArea = singleOfficeUpgradeElement.getElementsByTagName("area");
                    for (int v = 0; v < officeUpgradeArea.getLength(); v++) {
                        Element officeUpgradeAreaDims = (Element) officeUpgradeArea.item(v);

                        // TODO: Store Office Upgrade area dims
                        String x6 = officeUpgradeAreaDims.getAttribute("x");
                        String y6 = officeUpgradeAreaDims.getAttribute("y");
                        String h6 = officeUpgradeAreaDims.getAttribute("h");
                        String w6 = officeUpgradeAreaDims.getAttribute("w");
                    }
                }
            }
            // Add Casting Office to the board
            newBoard.addRoomToBoard(newCastingOfficeRoom);
        }
        return newBoard;
    }

    public static void main(String args[]) {
        Document doc = null;
        parseBoard parsing = new parseBoard();
        try {
            doc = parsing.getDocFromFile("board.xml");
            parsing.readBoardData(doc);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
    }
}
