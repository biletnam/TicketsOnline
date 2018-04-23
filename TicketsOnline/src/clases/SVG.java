package clases;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class SVG {
	private String fileName = null;
	private File file = null;
	private ArrayList<Seat> seats = null;
	private ArrayList<NonNumbered> nonNumbereds = null;
	private DocumentBuilderFactory dbf = null;
	private DocumentBuilder db = null;
	private Document doc = null;
	
	public static void main(String[] args) {
		ArrayList<Seat> arr = new ArrayList<Seat>();
		arr.add(new Seat("1", "1", "1", "100.00", "1", "A", "1", "0", "10", "1"));
		
		ArrayList<NonNumbered> arrNon = new ArrayList<NonNumbered>();
		arrNon.add(new NonNumbered("1", "1", "1", "100.00", "1", "A", "1", "0", "10", "1"));
		
		
		new SVG("C:\\Users\\Hp Pro\\Desktop\\1_1.svg", arr, arrNon).getXML();
	}
	
	public SVG(final String fileName_, final ArrayList<Seat> seats_, final ArrayList<NonNumbered> nonNumbereds_){
		fileName = fileName_;
		seats = seats_;
		nonNumbereds = nonNumbereds_;
	}
	
	private String getString() {
	    try {
	        StringWriter sw = new StringWriter();
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        transformer.transform(new DOMSource(doc), new StreamResult(sw));

	        return sw.toString();
	    } catch (Exception ex) {
	        throw new RuntimeException("Error converting to String", ex);
	    }
	}

	public String getXML() {
		readXML();
		modifyStructure();
		return getString();
	}
	
	private boolean modifyStructure() {
		String id = null;
		NodeList circles = null;
		NodeList rects = null;
		Node circle = null;
		Node rect = null;
		boolean res = true;
		Seat seat = null;
		NonNumbered nonNumbered = null;
		Element title = null;
		
		try {
			circles = doc.getElementsByTagName("circle");
			
			for (int j = 0; j < seats.size(); j++) {
				seat = seats.get(j);
				circle = circles.item(j);
				
				if (circle != null) {
				
					if (!seat.getStatus().equals("0")) {
						((Element) circle).setAttribute("style", "fill:#999999ff;stroke:#000000;stroke-width:0.4445;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1");
					}else {
						((Element) circle).setAttribute("id", seat.getId());
						((Element) circle).setAttribute("code", seat.getNumber());
						((Element) circle).setAttribute("style", "fill:#2697e5;stroke:#000000;stroke-width:0.4445;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1");
						((Element) circle).setAttribute("cost", Double.toString(seat.getCost()));
						((Element) circle).setAttribute("commision", Double.toString(seat.getCommision()));
						((Element) circle).setAttribute("subTotal", Double.toString(seat.getSubTotal()));
						((Element) circle).setAttribute("row", seat.getRow());
						((Element) circle).setAttribute("number", seat.getNumber());
						((Element) circle).setAttribute("index", seat.getIndex());
						((Element) circle).setAttribute("sectionId", seat.getSection());
						((Element) circle).setAttribute("eventId", seat.getEventId());
						((Element) circle).setAttribute("location", seat.getLocation());
						((Element) circle).setAttribute("onclick", "window.parent.actionSeat(this);");
						title = doc.createElement("title");
						title.appendChild(doc.createTextNode("Sección: " + seat.getSection() + "\nLocalidad: " + seat.getLocation() + "\nAsiento: " + seat.getNumber() + "\nPrecio: $" + seat.getCost() + "\nIndex: " + seat.getIndex()));
						circle.appendChild(title);
					}
				}
				
			}
			
			rects = doc.getElementsByTagName("path");
			
			for (int j = 0; j < nonNumbereds.size(); j++) {
				nonNumbered = nonNumbereds.get(j);
				
				for (int k = 0; k < rects.getLength(); k++) {
					rect = rects.item(k);
					
//					System.err.println(((Element) rect).getAttribute("id").replace('_', ' ') + " = " + nonNumbered.getLocation());
					
					if (((Element) rect).getAttribute("id").replace('_', ' ').equalsIgnoreCase(nonNumbered.getLocation())) {
						((Element) rect).setAttribute("id", nonNumbered.getId());
						((Element) rect).setAttribute("code", nonNumbered.getNumber());
						((Element) rect).setAttribute("style", "fill:#2697e5;stroke:#000000;stroke-width:0.4445;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1");
						((Element) rect).setAttribute("cost", Double.toString(nonNumbered.getCost()));
						((Element) rect).setAttribute("commision", Double.toString(nonNumbered.getCommision()));
						((Element) rect).setAttribute("subTotal", Double.toString(nonNumbered.getSubTotal()));
						((Element) rect).setAttribute("row", nonNumbered.getRow());
						((Element) rect).setAttribute("number", nonNumbered.getNumber());
						((Element) rect).setAttribute("index", nonNumbered.getIndex());
						((Element) rect).setAttribute("sectionId", nonNumbered.getSection());
						((Element) rect).setAttribute("eventId", nonNumbered.getEventId());
						((Element) rect).setAttribute("location", nonNumbered.getLocation());
						((Element) rect).setAttribute("onclick", "window.parent.actionSeat2(this);");
						title = doc.createElement("title");
						title.appendChild(doc.createTextNode("Sección: " + nonNumbered.getSection() + "\nLocalidad: " + nonNumbered.getLocation() + "\nAsiento: " + nonNumbered.getNumber() + "\nPrecio: $" + nonNumbered.getCost() + "\nIndex: " + nonNumbered.getIndex()));
						rect.appendChild(title);
					}
				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
			res = false;
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		
		return res;
	}

	private boolean readXML(){
		boolean res = true;
		
		try {
			file = new File(fileName);
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			doc = db.parse(file);
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			res = false;
		}
			
		return res;
	}
}
