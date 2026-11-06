package geUtilities.genericMap;

import org.mockito.Mockito;

import genericMap.Cell;
import genericMap.Map;

public class CellTestFactory {

	public CellTestFactory () {
		
	}

	public Cell buildMapCell () {
		Cell tMapCell;

		tMapCell = buildCell ("T1");

		return tMapCell;
	}

	public Cell buildCell (String aID) {
		Cell tMapCell;

		tMapCell = buildCell (aID, 100, 100);

		return tMapCell;
	}

//	public Cell buildMapCell (XMLNode aChildNode, int [] aTerrainCost, int [] aTerrainType, String aChildName) {
//		
//		Cell tCell;
//		Map mMap;
//		String tMapDirection;
//		
//		mHexMap = buildMapMock ();
//		tMapDirection = "EW";
//		tCell = new Cell (mHexMap, tMapDirection);
//		
//		return tCell;
//	}
	
	public Cell buildCell (String aID, int aXc, int aYc) {
		Cell tCell;

		tCell = new Cell (aXc, aYc);
		tCell.setID (aID);

		return tCell;
	}
	
	public Cell buildCellMock (String aID) {
		Cell mMapCell;
		
		mMapCell = Mockito.mock (Cell.class);
		Mockito.when (mMapCell.getID ()).thenReturn (aID);
		
		return mMapCell;
	}
	
	public Cell buildCellMock (String aID, int aXCenter, int aYCenter) {
		Cell mCell;
		
		mCell = Mockito.mock (Cell.class);
		Mockito.when (mCell.getID ()).thenReturn (aID);
		Mockito.when (mCell.getXCenter ()).thenReturn (aXCenter);
		Mockito.when (mCell.getYCenter ()).thenReturn (aYCenter);
		
		return mCell;
	}

	public Map buildMap () {
		Map tMap;
		
		tMap = new Map ();
		
		return tMap;
	}
	
	public Map buildMapMock () {
		Map mMap;
		
		mMap = Mockito.mock (Map.class);
		
		return mMap;
	}
}
