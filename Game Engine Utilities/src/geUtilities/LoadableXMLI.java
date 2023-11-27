package geUtilities;

//
//LoadableXMLI.java
//Game_18XX
//
//Created by Mark Smith on 11/28/07.
//Copyright 2007 __MyCompanyName__. All rights reserved.
//

import java.io.IOException;

public interface LoadableXMLI extends ParsingRoutineI {
	/* Returns the name of the Loadable Object for Error Messages */
	public String getTypeName ();
	
	/* Loads from a XML Document the object, throws one IOException */
	public void loadXML (XMLDocument aXMLDocument) throws IOException;
}