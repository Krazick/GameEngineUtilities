/*
 * Game Engine Utilities (geUtilities) Package that are used for various Game Engines.
 * This package will not have any game specific classes or methods.
 */
package geUtilities.xml;

import geUtilities.GUI;

/**
 * The Class NoSpaceString, that will only allow a String without Spaces, or null
 */
public class NoSpaceString {
	
	/** The Constant NULL_STRING. */
	public static final String NULL_STRING = GUI.NULL_STRING;
	
	/** The string. */
	String string;

	/**
	 * Instantiates a new no space string.
	 */
	public NoSpaceString () {
		setString (NULL_STRING);
	}

	/**
	 * Instantiates a new no space string. If length Zero, save a NULL String.
	 * If string has one (or more) spaces, it will store as a NULL String.
	 *
	 * @param aString the a string
	 */
	public NoSpaceString (String aString) {
		if (aString == NULL_STRING) {
			setString (aString);
		} else {
			if (aString.length () == 0) {
				setString (NULL_STRING);
			} else if (aString.indexOf (" ") >= 0) {
				setString (NULL_STRING);
			} else {
				setString (aString);
			}
		}
	}

	/**
	 * Checks for value.
	 *
	 * @return true, if successful
	 */
	public boolean hasValue () {
		return (string != NULL_STRING);
	}

	/**
	 * Equals.
	 *
	 * @param aString the a string
	 * @return true, if successful
	 */
	public boolean equals (String aString) {
		boolean tEquals;

		if (hasValue ()) {
			if (aString == NULL_STRING) {
				tEquals = false;
			} else {
				tEquals = string.equals (aString);
			}
		} else {
			tEquals = false;
		}

		return tEquals;
	}

	/**
	 * Sets the string.
	 *
	 * @param aString the new string
	 */
	protected void setString (String aString) {
		string = aString;
	}

	/**
	 * Gets the string.
	 *
	 * @return the string
	 */
	public String getString () {
		return string;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString () {
		return getString ();
	}
}
