package geUtilities.utilites;

public class Checksum {

	String gameID;
	String clientName;
	String nodeName;
	String checksum;		// Need one for each player
	int actionNumber;
	
	public Checksum (String aGameID, String aClientName, String aNodeName, String aChecksum, int aActionNumber) {
		setGameID (aGameID);
		setClientName (aClientName);
		setNodeName (aNodeName);
		setChecksum (aChecksum);
		setActionNumber (aActionNumber);
	}
	
	private void setGameID (String aGameID) {
		gameID = aGameID;
	}
	
	private void setClientName (String aClientName) {
		clientName = aClientName;
	}
	
	private void setNodeName (String aNodeName) {
		nodeName = aNodeName;
	}
	
	private void setChecksum (String aChecksum) {
		checksum = aChecksum;
	}
	
	private void setActionNumber (int aActionNumber) {
		actionNumber = aActionNumber;
	}

	public String getGameID () {
		return gameID;
	}
	
	public String getClientName () {
		return clientName;
	}
	
	public String getNodeName () {
		return nodeName;
	}
	
	public String getChecksum () {
		return checksum;
	}
	
	public int getActionNumber () {
		return actionNumber;
	}
	
	public void printInfo () {
		System.out.println ("ID: " + gameID + 
							" Client Name: " + clientName +
							" Action Number: " + actionNumber +
							" Node Name: " + nodeName +
							" Checksum: " + checksum);
	}
}
