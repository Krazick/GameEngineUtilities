package genericMap;

import java.awt.Graphics;

import geUtilities.GUI;
import geUtilities.xml.AttributeName;
import geUtilities.xml.ElementName;

public class Cell implements Cloneable {
	public static final AttributeName AN_MAP_CELL_ID = new AttributeName ("mapCellID");
	public static final ElementName EN_MAP_CELL = new ElementName ("MapCell");
	
	// The AN_MAP_CELL_ID and EN_MAP_CELL are maintained so that don't have to update all the Map XML Files
	// to use the new Attribute Names, and Element Names.
	
	// The NO_MAP_CELL value is null, but lots of code use this.
	public static final Cell NO_MAP_CELL = null;
	public static final Cell NO_CELL = null;
	public static final Cell [] [] NO_CELLS = null;
	public static final String DIRECTION_NS = "NS";
	public static final String DIRECTION_EW = "EW";
	public static final String NO_ID = GUI.EMPTY_STRING;
	public static final String NO_DIRECTION = GUI.NULL_STRING;
	public static final Cell NO_DESTINATION = null;
	public static final int NO_LOCATION = -1;		// This should be Location.NO_LOCATION, but it is not
													// a Generic Class "yet". 
	public static boolean mapDirection;
	public static void setMapDirection (boolean aMapDirection) {
		mapDirection = aMapDirection;
	}

	OffsetCoord offsetCoordinates;
	protected String id = "A1";
	protected int XCenter;
	protected int YCenter;
	protected int countOfSides = 6;
	protected boolean selected;
	protected Cell neighbors [];

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

	public void printlog () {
		System.out.println ("Cell " + id);		// PRINTLOG
	}

	public void printNeighbors () {
		String tCoordinates;
		OffsetCoord tOffsetCoord;
		
		if (offsetCoordinates == null) {
			tCoordinates = "NONE SET";
		} else {
			tCoordinates = offsetCoordinates.getCoordinates ();
		}
		System.out.println ("Cell " + id + " " + tCoordinates + " Neighbors: ");
		for (int tIndex = 0; tIndex < countOfSides; tIndex++) {
			tOffsetCoord = neighbors [tIndex].getOffsetCoordinates ();
			if (tOffsetCoord == null) {
				tCoordinates = "NONE SET";
			} else {
				tCoordinates = tOffsetCoord.getCoordinates ();
			}
			System.out.println (tIndex + ": " + neighbors [tIndex].getID () + " " + tCoordinates);
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

	public void paintComponent (Graphics aGraphics) {

	}

	public boolean isNeighbor (Cell tPossibleNeighbor) {
		boolean tIsNeighbor;
	
		tIsNeighbor = false;
		if (tPossibleNeighbor != NO_CELL) {
			for (Cell tNeighbor : neighbors) {
				if (tNeighbor == tPossibleNeighbor) {
					tIsNeighbor = true;
				}
			}
		}
	
		return tIsNeighbor;
	}

	/**
	 * Get the Cell that is the Neighbor to for this Cell on the specified side.
	 *
	 * @param aSide int value of the Location (must be between 0 and 5)
	 *
	 * @return NO_CELL if not a valid side, otherwise the neighbor Cell.
	 * Also note, if the Cell has no neighbor on that side, it also returns NO_CELL
	 *
	 */
	public Cell getNeighbor (int aSide) {
		Cell tNeighbor;
	
		if ((aSide < 0) || (aSide >= countOfSides)) {
			tNeighbor = NO_CELL;
		} else {
			tNeighbor = neighbors [aSide];
		}
	
		return tNeighbor;
	}

	public void setNeighbor (int aSide, Cell aNeighbor) {
		if (neighbors [aSide] == NO_CELL) {
			neighbors [aSide] = aNeighbor;
			aNeighbor.setNeighbor (oppositeSide (aSide), this);
		}
	}

	private int oppositeSide (int aSide) {
		int tOppositeSide;
		int tHalfCount;
		
		tHalfCount = countOfSides/2;
		tOppositeSide = (aSide + tHalfCount) % countOfSides;
		
		return tOppositeSide;
	}
	
	/**
	 * Find the side on the Neighbor Cell that connects to this Cell.
	 *
	 * @param aNeighborCell Find this Neighbor, if valid and return the
	 * Location of the side. Otherwise return NO_LOCATION;
	 *
	 * @return NO_LOCATION (-1) if the Neighbor is not Found for this Cell
	 */
	public int getSideFromNeighbor (Cell aNeighborMapCell) {
		int tSideFromNeighbor;
		int tSideIndex;
		
		tSideFromNeighbor = NO_LOCATION;
		for (tSideIndex = 0; tSideIndex < 6; tSideIndex++) {
			if (neighbors [tSideIndex] == aNeighborMapCell) {
				tSideFromNeighbor = (tSideIndex + 3) % 6;
			}
		}
	
		return tSideFromNeighbor;
	}
}
