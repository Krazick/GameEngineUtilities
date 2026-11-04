package genericMap;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JLabel;

import geUtilities.xml.AttributeName;
import geUtilities.xml.LoadableXMLI;
import geUtilities.xml.XMLDocument;

public class Map extends JLabel implements Cloneable, LoadableXMLI {
	private static final long serialVersionUID = 1L;
	public static final AttributeName AN_INDEX = new AttributeName ("index");
	public static final AttributeName AN_START_COL = new AttributeName ("startCol");
	protected Cell cells [] [];

	public Map () {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTypeName () {
		return null;
	}

	@Override
	public void loadXML (XMLDocument aXMLDocument) throws IOException {
		
	}

	public void buildMapArray (int aCols, int aRows) {
		cells = new Cell [aRows] [aCols];
	}
	
	public void setCell (int aRow, int aCol, Cell aCell) {
		cells [aRow] [aCol] = aCell;
	}

	@Override
	public void paintComponent (Graphics aGraphics) {
		
	}

	public int getColCount (int aRowIndex) {
		int tColCount;
		
		if (cells == Cell.NO_CELLS) {
			tColCount = 0;
		} else {
			tColCount = cells [aRowIndex].length;
		}
		
		return tColCount;
	}

	public int getRowCount () {
		int tRowCount;
		
		if (cells == Cell.NO_CELLS) {
			tRowCount = 0;
		} else {
			tRowCount = cells.length;
		}
	
		return tRowCount;
	}

	public boolean inColRange (int aRow, int aCol) {
		boolean tInColRange;
		
		tInColRange = false;
		if (inRowRange (aRow)) {
			tInColRange = ((aCol >= 0) && (aCol < getColCount (aRow)));
		}
		
		return tInColRange;
	}

	public boolean inRowRange (int aRow) {
		boolean tInRowRange;
		
		tInRowRange = ((aRow >= 0) && (aRow < getRowCount ()));
		
		return tInRowRange;
	}

	public boolean inRowColRanges (int aRow, int aCol) {
		boolean tInRowColRanges;
	
		tInRowColRanges = false;
		if (inRowRange (aRow)) {
			if (inColRange (aRow, aCol)) {
				tInRowColRanges = true;
			}
		}
	
		return tInRowColRanges;
	}

	public void redrawMap () {
		revalidate ();
		repaint ();
	}

	public int getMaxColCount () {
		int tIndex;
		int tMaxColCount;
		int tColCount;
		int tRowCount;
	
		tMaxColCount = getColCount (0);
		if (tMaxColCount > 0) {
			tRowCount = getRowCount ();
			for (tIndex = 1; tIndex < tRowCount; tIndex++) {
				tColCount = getColCount (tIndex);
				if (tColCount > tMaxColCount) {
					tMaxColCount = tColCount;
				}
			}
		}
	
		return tMaxColCount;
	}

	public int getMaxRowCount () {
		return (getRowCount ());
	}

	public int getMaxX (int aHexRightEdgeDisplacement) {
		int tMaxRow;
		int tMaxCol;
		int tMaxX;
		int tMaxX1;
		int tMaxX2;
	
		if (cells == Cell.NO_CELLS) {
			tMaxX = 0;
		} else {
			tMaxRow = getMaxRowCount ();
			if (cells [tMaxRow - 1] [0] == Cell.NO_CELL) {
				tMaxX = 0;
			} else {
				if (cells [0] [0].getMapDirection ()) {
					tMaxCol = getMaxColCount ();
					tMaxX1 = cells [0] [tMaxCol - 1].getXCenter () + aHexRightEdgeDisplacement;
					tMaxX2 = cells [0] [tMaxCol - 2].getXCenter () + aHexRightEdgeDisplacement;
					if (tMaxX1 > tMaxX2) {
						tMaxX = tMaxX1;
					} else {
						tMaxX = tMaxX2;
					}
				} else {
					tMaxX = cells [tMaxRow - 1] [0].getXCenter () + aHexRightEdgeDisplacement;
				}
			}
		}
	
		return (tMaxX + 3);
	}

	public int getMaxY (int aHexBottomEdgeDisplacement) {
		int tMaxY;
		int tMaxRow;
		int tMaxCol;
	
		if (cells == Cell.NO_CELLS) {
			tMaxY = 0;
		} else {
			tMaxRow = getMaxRowCount ();
			tMaxCol = getMaxColCount ();
			if (cells [1] [tMaxCol - 1] == Cell.NO_CELL) {
				tMaxY = 0;
			} else {
				if (cells [0] [0].getMapDirection ()) {
					tMaxY = cells [0] [0].getYCenter () + aHexBottomEdgeDisplacement;
				} else {
					if (tMaxRow > 0) {
						tMaxY = cells [1] [tMaxCol - 1].getYCenter () + aHexBottomEdgeDisplacement;
					} else {
						tMaxY = cells [0] [tMaxCol - 1].getYCenter () + aHexBottomEdgeDisplacement;
					}
				}
			}
		}
	
		return (tMaxY + 3);
	}

}
