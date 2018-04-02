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
	private DocumentBuilderFactory dbf = null;
	private DocumentBuilder db = null;
	private Document doc = null;
	
	public static void main(String[] args) {
		ArrayList<Seat> arr = new ArrayList<Seat>();
		
		System.out.println(new SVG("C:\\Users\\Hp Pro\\Desktop\\1_1.svg", arr).getXML());
	}
	
	public SVG(final String fileName_, final ArrayList<Seat> seats_){
		fileName = fileName_;
		seats = seats_;
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
		Node circle = null;
		NamedNodeMap attr = null;
		boolean res = true;
		Seat seat = null;
		Element title = null;
		
		try {
			circles = doc.getElementsByTagName("ellipse");
			
			for (int j = 0; j < seats.size(); j++) {
				seat = seats.get(j);
				circle = circles.item(j);
				attr = circle.getAttributes();
				
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
					title.appendChild(doc.createTextNode("Seccion: " + seat.getSection() + "\nLocalidad: " + seat.getLocation() + "\nAsiento: " + seat.getNumber() + "\nPrecio: $" + seat.getCost() + "\nIndex: " + seat.getIndex()));
					circle.appendChild(title);
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
