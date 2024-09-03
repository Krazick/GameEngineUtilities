package geUtilities;

import geUtilities.xml.XMLNode;

public interface ParsingRoutineI {
	public default Object getObject () {
		return "Default Parsing Routine Object";
	}

	/* Support Callback Functions when a XML Node Name matches a specified value */
	public default void foundItemMatchKey1 (XMLNode aChildNode) {

	}
}
