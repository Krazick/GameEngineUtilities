package geUtilities;

import geUtilities.xml.AttributeName;
import geUtilities.xml.ElementName;
import geUtilities.xml.XMLDocument;
import geUtilities.xml.XMLElement;
import geUtilities.xml.XMLSaveGameI;

public class Checksum implements XMLSaveGameI {
	public static final AttributeName AN_GAME_ID = new AttributeName ("gameID");
	public static final AttributeName AN_NODE_NAME = new AttributeName ("nodeName");
	public static final AttributeName AN_ACTION_NUMBER = new AttributeName ("actionNumber");
	public static final AttributeName AN_CLIENT_NAME = new AttributeName ("clientName");
	public static final AttributeName AN_CHECKSUM = new AttributeName ("checksum");
	public static final AttributeName AN_PLAYER_INDEX = new AttributeName ("playerIndex");
	public static final ElementName EN_CHECKSUM = new ElementName ("Checksum");

	String gameID;
	String clientName;
	String nodeName;
	String [] checksums;
	int actionNumber;
	
	public Checksum (String aGameID, String aNodeName, String aClientName, int aPlayerCount, int aActionNumber) {
		setGameID (aGameID);
		setNodeName (aNodeName);
		setClientName (aClientName);
		checksums = new String [aPlayerCount];
		setActionNumber (aActionNumber);
	}
	
	private void setClientName (String aClientName) {
		clientName = aClientName;
	}
	
	private void setGameID (String aGameID) {
		gameID = aGameID;
	}
	
	private void setNodeName (String aNodeName) {
		nodeName = aNodeName;
	}
	
	private void setActionNumber (int aActionNumber) {
		actionNumber = aActionNumber;
	}

	public String getClientName () {
		return clientName;
	}
	
	public String getGameID () {
		return gameID;
	}
	
	public String getNodeName () {
		return nodeName;
	}
	
	public int getActionNumber () {
		return actionNumber;
	}
	
	public void addClientChecksum (int aPlayerIndex, String aChecksum) {
		checksums [aPlayerIndex] = aChecksum;
	}
	
	public String getChecksum (int aPlayerIndex) {
		return checksums [aPlayerIndex];
	}
	
	public String getAllDetails () {
		String tDetail;
		
		tDetail = GUI.EMPTY_STRING;
		for (int tPlayerIndex = 0; tPlayerIndex < checksums.length; tPlayerIndex++) {
			if (checksums [tPlayerIndex] != GUI.NULL_STRING) {
				tDetail += getDetailFor (tPlayerIndex);
			}
		}
		
		return tDetail;
	}
	
	public String getDetailFor (int aPlayerIndex) {
		String tDetail;
		
		tDetail = "ID: " + gameID + 
					" Action Number: " + actionNumber +
					" Node Name: " + nodeName +
					" Client Name: " + getClientName () +
					" Checksum [" + aPlayerIndex + "] " + getChecksum (aPlayerIndex) + "\n";
		
		return tDetail;
	}
	
	@Override
	public XMLElement addElements (XMLDocument aXMLDocument, ElementName aEN_Type) {
		XMLElement tXMLElement;

		tXMLElement = aXMLDocument.createElement (aEN_Type);
		tXMLElement.setAttribute (AN_GAME_ID, gameID);
		tXMLElement.setAttribute (AN_NODE_NAME, nodeName);
		tXMLElement.setAttribute (AN_CLIENT_NAME, clientName);
		tXMLElement.setAttribute (AN_ACTION_NUMBER, actionNumber);
		
		for (int tPlayerIndex = 0; tPlayerIndex < checksums.length; tPlayerIndex++) {
			if (checksums [tPlayerIndex] != GUI.NULL_STRING) {
				tXMLElement.setAttribute  (AN_PLAYER_INDEX, tPlayerIndex);
				tXMLElement.setAttribute (AN_CHECKSUM, checksums [tPlayerIndex]); 
			}
		}
		
		return tXMLElement;
	}
}


