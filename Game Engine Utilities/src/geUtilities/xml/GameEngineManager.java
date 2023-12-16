package geUtilities.xml;

import java.awt.Image;

import geUtilities.GameFrameConfig;

public abstract class GameEngineManager implements GameManagerI {

	public GameEngineManager () {
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract String getActiveGameName ();

	@Override
	public abstract String createFrameTitle (String aBaseTitle);

	@Override
	public abstract Image getIconImage ();

	@Override
	public abstract GameFrameConfig getGameFrameConfig ();

}
