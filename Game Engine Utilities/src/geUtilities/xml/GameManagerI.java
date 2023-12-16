package geUtilities.xml;

import java.awt.Image;

import geUtilities.GameFrameConfig;

public interface GameManagerI {
	public static final String NO_GAME_NAME = "<NONE>";
	public static final GameEngineManager NO_GAME_MANAGER = null;

	public String getActiveGameName ();

	public String createFrameTitle (String aBaseTitle);

	public Image getIconImage ();

	public GameFrameConfig getGameFrameConfig ();
}
