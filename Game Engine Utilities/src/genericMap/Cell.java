package genericMap;

import geUtilities.GUI;
import geUtilities.xml.AttributeName;
import geUtilities.xml.ElementName;

public class Cell implements Cloneable {
	public static final AttributeName AN_MAP_CELL_ID = new AttributeName ("mapCellID");
	public static final ElementName EN_MAP_CELL = new ElementName ("MapCell");
	public static final String DIRECTION_NS = "NS";
	public static final String DIRECTION_EW = "EW";
	public static final String NO_ID = GUI.EMPTY_STRING;
	public static final Cell NO_MAP_CELL = null;
	public static final Cell NO_CELL = null;
	public static final Cell [] [] NO_CELLS = null;
	public static final String NO_DIRECTION = GUI.NULL_STRING;
	public static final Cell NO_DESTINATION = null;
	public static boolean mapDirection;
	public static void setMapDirection (boolean aMapDirection) {
		mapDirection = aMapDirection;
	}

	protected String id = "A1";
	protected int XCenter;
	protected int YCenter;
	protected boolean selected;
	OffsetCoord offsetCoordinates;

	public Cell ()  {
		
		
	}

	@Override
	public Cell clone () throws CloneNotSupportedException {
		Cell tClone;
		
		tClone = (Cell) super.clone ();
		
		return tClone;
	}

	public void setXY (int aXCenter, int aYCenter) {
		XCenter = aXCenter;
		YCenter = aYCenter;
	}

	public void setSelected (boolean aSelected) {
		selected = aSelected;
	}
	
	public boolean isSelected () {
		return selected;
	}

	public void toggleSelected () {
		selected = !selected;
	}

//	@Override
//	public String toString () {
//		return id;
//	}

	public void setID (String aID) {
		id = aID;
	}

	public String getID () {
		return id;
	}

	public String getCellID () {
		return id;
	}

	public boolean forID (String aID) {
		return (aID.equals (id));
	}

	public boolean sameID (Cell aCell) {
		boolean tSameID;
	
		tSameID = false;
		if (aCell != NO_CELL) {
			if (id.equals (aCell.getID ())) {
				tSameID = true;
			}
		}
	
		return tSameID;
	}

	public boolean getMapDirection () {
		return mapDirection;
	}

	public int getXCenter () {
		return XCenter;
	}

	public int getYCenter () {
		return YCenter;
	}

	public void printlog () {
		System.out.println ("Cell " + id);		// PRINTLOG
	}

	public void setMapDirection (String aMapDirection) {
		if (aMapDirection == null) {
			setMapDirection (false);
		} else {
			if (aMapDirection.equals (DIRECTION_NS)) {
				setMapDirection (false);
			} else {
				if (aMapDirection.equals (DIRECTION_EW)) {
					setMapDirection (true);
				} else {
					setMapDirection (false);
				}
			}
		}
	}

	public void setOffsetCoordinates (int aColIndex, int aRowIndex) {
		offsetCoordinates = new OffsetCoord (aColIndex, aRowIndex);
	}

	public OffsetCoord getOffsetCoordinates () {
		return offsetCoordinates;
	}

	public int getDistanceTo (Cell aCell) {
		int tDistance;
		OffsetCoord tOffsetCoord;
		
		tOffsetCoord = aCell.getOffsetCoordinates ();
		tDistance = offsetCoordinates.getDistanceTo (OffsetCoord.ODD, tOffsetCoord);
	
		return tDistance;
	}
}
