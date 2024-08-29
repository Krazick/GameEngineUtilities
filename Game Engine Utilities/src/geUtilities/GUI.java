package geUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import swingTweaks.KButton;

// TODO: Auto-generated Javadoc
/**
 * The Interface GUI.
 */
public interface GUI {
	
	/** The Constant NO_TOOL_TIP. */
	public static final String NO_TOOL_TIP = "";
	
	/** The Constant NULL_STRING. */
	public static final String NULL_STRING = null;
	
	/** The Constant EMPTY_STRING. */
	public static final String EMPTY_STRING = "";
	
	/** The Constant NO_PANEL. */
	public static final JPanel NO_PANEL = null;
	
	/** The Constant NO_LABEL. */
	public static final JLabel NO_LABEL = null;
	
	/** The Constant NO_BUTTON. */
	public static final KButton NO_BUTTON = null;
	
	/** The Constant NO_CHECK_BOX. */
	public static final JCheckBox NO_CHECK_BOX = null;
	
	/** The Constant NO_COMBO_BOX. */
	public static final JComboBox<String> NO_COMBO_BOX = null;
	
	/** The Constant NO_ITEM_LISTENER. */
	public static final ItemListener NO_ITEM_LISTENER = null;
	
	/** The Constant NO_JCOMPONENT. */
	public static final JComponent NO_JCOMPONENT = null;
	
	/** The Constant NO_BUTTON_GROUP. */
	public static final ButtonGroup NO_BUTTON_GROUP = null;
	
	/** The Constant NO_SCROLL_PANE. */
	public static final JScrollPane NO_SCROLL_PANE = null;
	
	/** The Constant NO_COLOR. */
	public static final Color NO_COLOR = null;
	
	/** The Constant SPLIT. */
	public static final String SPLIT = ";";
	
	/** The Constant COMMA -- divider. */
	public static final String COMMA = ",";
	public static final String COMMA_SPACE = ", ";

	/** The Constant NEWLINE. */
	public static final String NEWLINE = "\n";
	
	/** The Constant defaultColor. */
	public static final Color defaultColor = UIManager.getColor ("Panel.background");
	
	/**
	 * Gets the number of displays.
	 *
	 * @return the number of displays
	 */
	public static int getNumberOfDisplays () {
		int tNumberOfDisplays;
		GraphicsEnvironment tLocalGraphicsEnvironment;
		
		tLocalGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment ();
		tNumberOfDisplays = tLocalGraphicsEnvironment.getScreenDevices ().length;
		
		return tNumberOfDisplays;
	}
	
	/**
	 * Gets the default screen size.
	 *
	 * @return the default screen size
	 */
	public static Dimension getDefaultScreenSize () {
		GraphicsDevice tGraphicsDevice;
		Dimension tDimension;
		
		tGraphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment ().getDefaultScreenDevice ();
		tDimension = getSizeOfDevice (tGraphicsDevice);
		
		return tDimension;
	}
	
	/**
	 * Gets the screen size.
	 *
	 * @param aDeviceIndex the a device index
	 * @return the screen size
	 */
	public static Dimension getScreenSize (int aDeviceIndex) {
		GraphicsDevice tGraphicsDevice;
		GraphicsDevice [] tGraphicsDevices;
		Dimension tDimension;
		
		tGraphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment ().getScreenDevices ();
		if ((aDeviceIndex > 0) && (aDeviceIndex < tGraphicsDevices.length)) {
			tGraphicsDevice = tGraphicsDevices [aDeviceIndex];
			tDimension = getSizeOfDevice (tGraphicsDevice);
		} else {
			tDimension = null;
		}
		
		return tDimension;
	}

	/**
	 * Gets the size of device.
	 *
	 * @param tGraphicsDevice the t graphics device
	 * @return the size of device
	 */
	public static Dimension getSizeOfDevice (GraphicsDevice tGraphicsDevice) {
		Dimension tDimension;
		int tWidth;
		int tHeight;
		
		tWidth = tGraphicsDevice.getDisplayMode ().getWidth ();
		tHeight = tGraphicsDevice.getDisplayMode ().getHeight ();
		tDimension = new Dimension (tWidth, tHeight);
		
		return tDimension;
	}
	
	/**
	 * Make transparent.
	 *
	 * @param aSource the a source
	 * @param aAlpha the a alpha
	 * @return the color
	 */
	public static Color makeTransparent(Color aSource, int aAlpha) {
		Color tTransparent;
		
		tTransparent = new Color(aSource.getRed(), aSource.getGreen(), aSource.getBlue(), aAlpha);
		
		return tTransparent;
	}
	
	/**
	 * Go boom.
	 */
	// Use as a Utility Function to Throw an Exception and Print a Stack Trace
	public default void goBoom () {
		int boomVariable;
		
		boomVariable = 1;
		try {
			boomVariable = 5/0;
			System.out.println ("Boom Variable is " + boomVariable);
		} catch (Throwable e) {
			System.err.println ("Generated an Exception, to generate the STACK TRACE");
			e.printStackTrace ();
		}
	}

	/**
	 * Format date time.
	 *
	 * @param aLabel the a label
	 * @param aDateTime the a date time
	 * @return the string
	 */
	public static String formatDateTime (String aLabel, long aDateTime) {
		Date tDateTime;
		String tFormatDateTime;
		
		tDateTime = new Date (aDateTime);
		tFormatDateTime = formatDateTime (aLabel, tDateTime);
		
		return tFormatDateTime;
	}
	
	/**
	 * Format date time.
	 *
	 * @param aLabel the a label
	 * @param aDateTime the a date time
	 * @return the string
	 */
	public static String formatDateTime (String aLabel, Date aDateTime) {
		String tFormatDateTime;
		SimpleDateFormat tSimpleFormat;
		
		tSimpleFormat = new SimpleDateFormat ("|hh:mm:ss|SSS");
		tFormatDateTime = aLabel + " " + tSimpleFormat.format (aDateTime);
		
		return tFormatDateTime;
	}
}