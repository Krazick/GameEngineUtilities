package geUtilities.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import geUtilities.Checksum;
import geUtilities.GUI;

public class XMLDocument {
	public static final Document NO_DOCUMENT = null;
	public static final XMLDocument NO_XML_DOCUMENT = null;
	Document document;

	public XMLDocument (Document aDocument) {
		document = aDocument;
	}

	public XMLDocument () {
		DocumentBuilderFactory tDocBuilderFac;
		DocumentBuilder tDocBuilder;

		try {
			tDocBuilderFac = DocumentBuilderFactory.newInstance ();
			tDocBuilder = tDocBuilderFac.newDocumentBuilder ();
			document = tDocBuilder.newDocument ();
		} catch (ParserConfigurationException tException) {
			System.err.println ("Caught Exception " + tException);
			document = NO_DOCUMENT;
			tException.printStackTrace ();
		}
	}

	public XMLDocument (File aFile) {
		DocumentBuilderFactory tDocBuilderFac;
		DocumentBuilder tDocBuilder;

		try {
			tDocBuilderFac = DocumentBuilderFactory.newInstance ();
			tDocBuilder = tDocBuilderFac.newDocumentBuilder ();
			document = tDocBuilder.parse (aFile);
		} catch (Exception tException) {
			System.err.println ("Caught Exception " + tException);
			document = NO_DOCUMENT;
			tException.printStackTrace ();
		}
	}

	public XMLDocument (URL aURL) {
		DocumentBuilderFactory tDocBuilderFac;
		DocumentBuilder tDocBuilder;

		try {
			tDocBuilderFac = DocumentBuilderFactory.newInstance ();
			tDocBuilder = tDocBuilderFac.newDocumentBuilder ();
			document = tDocBuilder.parse (aURL.openStream ());
		} catch (Exception tException) {
			System.err.println ("Caught Exception " + tException);
			document = NO_DOCUMENT;
			tException.printStackTrace ();
		}
	}

	public XMLDocument (String aFileName) {
		DocumentBuilderFactory tDocBuilderFac;
		DocumentBuilder tDocBuilder;

		try {
			tDocBuilderFac = DocumentBuilderFactory.newInstance ();
			tDocBuilder = tDocBuilderFac.newDocumentBuilder ();
			document = tDocBuilder.parse (aFileName);
		} catch (Exception tException) {
			System.err.println ("Caught Exception " + tException);
			document = NO_DOCUMENT;
			tException.printStackTrace ();
		}
	}

	/**
	 * Clear the document of any Children from the Document.
	 *
	 */
	public void clearDocumentChildren () {
		if (validDocument ()) {
			while (document.hasChildNodes ()) {
				document.removeChild (document.getFirstChild ());
			}
		}
	}

	public XMLDocument parseXMLString (String aXMLString) {
		DocumentBuilderFactory tDocBuilderFac;
		DocumentBuilder tDocBuilder;

		try {
			tDocBuilderFac = DocumentBuilderFactory.newInstance ();
			tDocBuilder = tDocBuilderFac.newDocumentBuilder ();
			document = tDocBuilder.parse (new InputSource (new StringReader (aXMLString)));
		} catch (Exception tException) {
			System.err.println ("Caught Exception " + tException);
			document = NO_DOCUMENT;
			tException.printStackTrace ();
		}

		return this;
	}

	/**
	 * If this XML Document has an Action non-NULL Document Object, then it is Valid
	 *
	 * @return FALSE if the DOCUMENT Object is NO_DOCUMENT, otherwise True
	 *
	 */
	public boolean validDocument () {
		boolean tValidDocument;

		if (document == NO_DOCUMENT) {
			tValidDocument = false;
		} else {
			tValidDocument = true;
		}

		return tValidDocument;
	}

	/**
	 * Append the Element from the provided XMLElement to this Document.
	 *
	 * @param aXMLElement This XMLElement contains an Element
	 */
	public void appendChild (XMLElement aXMLElement) {
		if (validDocument ()) {
			if (aXMLElement != XMLElement.NO_XML_ELEMENT) {
				document.appendChild (aXMLElement.getElement ());
			}
		}
	}

	/**
	 * If this XMLDocument has a Valid Document Element (ie non-NULL), find if the Document has
	 * any children, then return TRUE. If the Document is NULL, or the Document has NO Children,
	 * then return FALSE.
	 *
	 * @return True if there are one (or more) Children of the document.
	 *
	 */
	public boolean hasChildNodes () {
		boolean tHasChildNodes;

		if (validDocument ()) {
			tHasChildNodes = document.hasChildNodes ();
		} else {
			tHasChildNodes = false;
		}

		return tHasChildNodes;
	}

	/**
	 * Create an XMLElement with the provided Element Name.
	 * If the EntityName is NULL, or the String in the Entity Name is NULL this
	 * will return a NO_XML_ELEMENT
	 *
	 * @param aElementName The Element Name to be created. No Attributes or children will be attached
	 *
	 * @return If a Valid Entity
	 */
	public XMLElement createElement (ElementName aElementName) {
		XMLElement tXMLElement;

		if (aElementName != ElementName.NO_ELEMENT_NAME) {
			if (aElementName.validElementName ()) {
				tXMLElement = new XMLElement (document.createElement (aElementName.getString ()));
			} else {
				tXMLElement = XMLElement.NO_XML_ELEMENT;
			}
		} else {
			tXMLElement = XMLElement.NO_XML_ELEMENT;
		}

		return tXMLElement;
	}

	public Document getDocument () {
		return document;
	}

	public Element getDocumentElement (Document aDocument) {
		Element tElement;
		
		tElement = aDocument.getDocumentElement ();
		
		return tElement;
	}
	
	public XMLNode getDocumentNode () {
		XMLNode tXMLNode;
		Element tElement;

		if (validDocument ()) {
			tElement = getDocumentElement (document);
			tXMLNode = new XMLNode (tElement);
		} else {
			tXMLNode = XMLNode.NO_NODE;
		}

		return tXMLNode;
	}
	
	public XMLNode getDocumentNode (Document aDocument) {
		XMLNode tXMLNode;
		Element tElement;

		if (validDocument ()) {
			tElement = getDocumentElement (aDocument);
			tXMLNode = new XMLNode (tElement);
		} else {
			tXMLNode = XMLNode.NO_NODE;
		}

		return tXMLNode;
	}
	
	public XMLNode getDocumentNodeFromElement () {
		return new XMLNode (document.getDocumentElement ());
	}

	public XMLElement getDocumentElement () {
		XMLElement tXMLElement;

		if (validDocument ()) {
			tXMLElement = new XMLElement (document.getDocumentElement ());
		} else {
			tXMLElement = XMLElement.NO_XML_ELEMENT;
		}

		return tXMLElement;
	}

	public DOMSource getDOMSource () {
		return new DOMSource (document);
	}

	public void outputXML (File aFile) {
		String tXMLString = GUI.EMPTY_STRING;

		try {
			FileWriter tFWout = new FileWriter (aFile);
			tXMLString = toXMLString ();

			tFWout.write ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			tFWout.write (tXMLString);
			tFWout.close ();
		} catch (Exception tException) {
			System.err.println (tException);
			tException.printStackTrace ();
		}
	}

	public String toXMLString () {
		String tXMLString = GUI.EMPTY_STRING;

		try {
			// set up a transformer
			TransformerFactory tTransformerFactory = TransformerFactory.newInstance ();
			Transformer tTransformer = tTransformerFactory.newTransformer ();
			tTransformer.setOutputProperty (OutputKeys.OMIT_XML_DECLARATION, "yes");
			tTransformer.setOutputProperty (OutputKeys.INDENT, "yes");

			// create string from XML tree
			StringWriter tStringWriter = new StringWriter ();
			StreamResult tStreamResult = new StreamResult (tStringWriter);
			DOMSource tDOMSource = getDOMSource ();
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
