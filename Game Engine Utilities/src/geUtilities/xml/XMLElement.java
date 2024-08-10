package geUtilities.xml;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import geUtilities.AttributeName;
import geUtilities.Checksum;
import geUtilities.ElementName;
import geUtilities.GUI;

public class XMLElement {
	public static XMLElement NO_XML_ELEMENT = null;
	public static Element NO_ELEMENT = null;
	Element element;

	public XMLElement (Element aElement) {
		setElement (aElement);
	}

	public XMLElement (Node aNode) {
		Element tElement;

		tElement = (Element) aNode;
		setElement (tElement);
	}

	public void appendChild (XMLElement aXMLElement) {
		if (validElement ()) {
			element.appendChild (aXMLElement.getElement ());
		}
	}

	public void setElement (Element aElement) {
		element = aElement;
	}

	public Element getElement () {
		return element;
	}

	public boolean validElement () {
		return element != NO_ELEMENT;
	}

	public boolean hasChildNodes () {
		Node tNode;
		boolean tHasChildNodes;

		tHasChildNodes = false;
		if (validElement ()) {
			tNode = element;
			tHasChildNodes = tNode.hasChildNodes ();
		}

		return tHasChildNodes;
	}

	public NodeList getChildNodes () {
		return element.getChildNodes ();
	}

	public void setAttribute (AttributeName aAttributeName, String aValue) {
		if (validElement ()) {
			element.setAttribute (aAttributeName.getString (), aValue);
		}
	}

	public void setAttribute (AttributeName aAttributeName, int aValue) {
		setAttribute (aAttributeName, Integer.valueOf (aValue).toString ());
	}

	public void setAttribute (AttributeName aAttributeName, long aValue) {
		setAttribute (aAttributeName, Long.valueOf (aValue).toString ());
	}

	public void setAttribute (AttributeName aAttributeName, boolean aValue) {
		setAttribute (aAttributeName, Boolean.valueOf (aValue).toString ());
	}

	public XMLElement getElement (ElementName aElementName) {
		XMLElement tFoundElement;
		NodeList tNodeList;
		int tElementIndex;
		int tElementCount;
		Element tElement;
		String tNodeName;
		String tElementName;

		tFoundElement = NO_XML_ELEMENT;
		if (element.hasChildNodes ()) {
			tNodeList = element.getChildNodes ();
			tElementCount = tNodeList.getLength ();
			tElementName = aElementName.getString ();
			for (tElementIndex = 0; tElementIndex < tElementCount; tElementIndex++) {
				tElement = (Element) tNodeList.item (tElementIndex);
				tNodeName = tElement.getNodeName ();
				if (tElementName.equals (tNodeName)) {
					tFoundElement = new XMLElement (tElement);
				}
			}
		}

		return tFoundElement;
	}
	
	public String toXMLString () {
		String tXMLString = GUI.EMPTY_STRING;

		try {
			TransformerFactory tTransformerFactory = TransformerFactory.newInstance ();
			Transformer tTransformer = tTransformerFactory.newTransformer ();
			tTransformer.setOutputProperty (OutputKeys.OMIT_XML_DECLARATION, "yes");
			tTransformer.setOutputProperty (OutputKeys.INDENT, "yes");
		
			// create string from XML tree
			StringWriter tStringWriter = new StringWriter ();
			StreamResult tStreamResult = new StreamResult (tStringWriter);
			DOMSource tDOMSource = new DOMSource (element);
			
			tTransformer.transform (tDOMSource, tStreamResult);
			tXMLString = tStringWriter.toString ();
		} catch (Exception tException) {
			System.err.println (tException);
			tException.printStackTrace ();
		}
		return tXMLString;
	}

	public String MD5 () {
		Checksum tChecksum;
		String tMD5Sum;
		
		tChecksum = new Checksum ();
		tMD5Sum = tChecksum.MD5 (toXMLString ());
		
		return tMD5Sum;
	}

}
