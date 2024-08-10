package geUtilities.utilites;

public class Checksum {

	String gameID;
	String nodeName;
	String checksum;
	int actionNumber;
	
	public Checksum (String aGameID, String aNodeName, String aChecksum, int aActionNumber) {
		setGameID (aGameID);
		setNodeName (aNodeName);
		setChecksum (aChecksum);
		setActionNumber (aActionNumber);
	}
	
	private void setGameID (String aGameID) {
		gameID = aGameID;
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
	
	public String getNodeName () {
		return nodeName;
	}
	
	public String getChecksum () {
		return checksum;
	}
	
	public int getActionNumber () {
		return actionNumber;
	}
}
