package geUtilities.xml;

import java.awt.Point;

import org.apache.logging.log4j.Logger;

import geUtilities.FileUtils;

public interface NetworkGameSupport {
	public final String NO_NAME = "";
	public static final String NO_GAME_ID = "NOID";
	public static final String EMPTY_GAME_ID = "";
	public final static int WAIT_TIME = 10;

	public void updatePlayerCountLabel ();
	public void addNetworkPlayer (String aPlayerName);
	public void removeNetworkPlayer (String aPlayerName);
	public void removeAllNetworkPlayers ();
	public void handleGameActivity (String aGameActivity);
	public String getPlayersInOrder ();
	public void randomizePlayerOrder ();
	public void initiateNetworkGame ();
	public boolean gameStarted ();
	public Point getOffsetGEFrame ();
	public String getClientUserName ();
	public void parseNetworkSavedGames (String aResponse);
	public void loadAutoSavedGame (String aAutoSaveFileName);
	public void updateDisconnectButton ();
	public FileUtils getFileUtils ();
	
	public int getSelectedGameIndex ();
	public void setSelectedGameIndex (int aGameIndex);

	public void addNewFrame (XMLFrame aJGameClient);

	public Logger getLogger ();
	public String getXMLBaseDirectory ();
	public XMLElement getGameVariantsXMLElement ();
	
	public void setGameID (String aGameID);
	public String getGameID ();
	public void resetGameID (String aGameID);

	public String getGEVersion ();
	public String getEnvironmentVersionInfo ();
}
