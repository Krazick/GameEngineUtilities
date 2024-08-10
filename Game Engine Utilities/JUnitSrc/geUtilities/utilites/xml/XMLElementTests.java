package geUtilities.utilites.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import geUtilities.xml.AttributeName;
import geUtilities.xml.ElementName;
import geUtilities.xml.XMLDocument;
import geUtilities.xml.XMLElement;

class XMLElementTests {
	XMLElement xmlElement;
	XMLDocument xmlDocument;
	ElementName elementName;
	AttributeName attributeName1;
	AttributeName attributeName2;
	
	@BeforeEach
	void setUp () throws Exception {
		xmlDocument = new XMLDocument ();
		elementName = new ElementName ("TestElement");
		attributeName1 = new AttributeName ("testAttributeAlpha");
		attributeName2 = new AttributeName ("testAttributeBeta");
		
		xmlElement = xmlDocument.createElement (elementName);
		xmlElement.setAttribute (attributeName1, "value of an");
		xmlElement.setAttribute (attributeName2, "This is a test");
		xmlDocument.appendChild (xmlElement);
	}

	@Test
	@DisplayName ("Basic XML Element Tests")
	void basicXMLElementTests () {
		assertEquals ("<TestElement testAttributeAlpha=\"value of an\" testAttributeBeta=\"This is a test\"/>\n", 
						xmlDocument.toXMLString ());
		
		assertEquals ("<TestElement testAttributeAlpha=\"value of an\" testAttributeBeta=\"This is a test\"/>\n",
				xmlElement.toXMLString ());
		
		assertEquals ("64430fe783626feea8c5af77650761f1", xmlDocument.MD5 ());
		assertEquals ("64430fe783626feea8c5af77650761f1", xmlElement.MD5 ());
		
	}
}
