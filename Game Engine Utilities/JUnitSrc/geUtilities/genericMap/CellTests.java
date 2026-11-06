package geUtilities.genericMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import genericMap.Cell;

class CellTests {
	Cell cell;
	CellTestFactory cellTestFactory;

	@BeforeEach
	void setUp () throws Exception {
		cellTestFactory = new CellTestFactory ();
		cell = cellTestFactory.buildCell ("N11");
	}

	@Test
	@DisplayName ("Test cell ID functionalities")
	void cellIDFunctionalTests () {
		Cell tSecondCell;
		
		tSecondCell = Cell.NO_CELL;
		assertFalse (cell.sameID (tSecondCell));
		
		tSecondCell = cellTestFactory.buildCell ("A3");
		assertFalse (cell.sameID (tSecondCell));

		tSecondCell = cellTestFactory.buildCell ("N11");
		assertTrue (cell.sameID (tSecondCell));

		assertTrue (cell.sameID (cell));
		
		assertEquals ("N11", tSecondCell.getCellID ());
		assertEquals ("N11", tSecondCell.getID ());
	
		assertTrue (tSecondCell.forID ("N11"));
		assertFalse (tSecondCell.forID ("A3"));
	}
	
	@Test
	@DisplayName ("Test Selected functionality")
	void cellSelectedTests () {
		assertFalse (cell.isSelected ());
		
		cell.setSelected (true);
		assertTrue (cell.isSelected ());

		cell.toggleSelected ();
		assertFalse (cell.isSelected ());
		
		cell.toggleSelected ();
		assertTrue (cell.isSelected ());
	}

	@Test
	@DisplayName ("Test XCenter and YCenter")
	void cellXCenterYCenterTests () {
		assertEquals (100, cell.getXCenter ());
		assertEquals (100, cell.getYCenter ());
		
		cell.setXY (200, 500);
		assertNotEquals (100, cell.getXCenter ());
		assertNotEquals (100, cell.getYCenter ());
		assertEquals (200, cell.getXCenter ());
		assertEquals (500, cell.getYCenter ());
	}
	
	@Test
	@DisplayName ("Test Opposite Side Calculation")
	void cellOppositeSideTest () {
		int tSide;
		int tOppositeSide;
		
		tSide = 0;
		tOppositeSide = cell.oppositeSide (tSide);
		assertEquals (3, tOppositeSide);

		tSide = 1;
		tOppositeSide = cell.oppositeSide (tSide);
		assertEquals (4, tOppositeSide);
		
		tSide = 2;
		tOppositeSide = cell.oppositeSide (tSide);
		assertEquals (5, tOppositeSide);
		
		tSide = 3;
		tOppositeSide = cell.oppositeSide (tSide);
		assertEquals (0, tOppositeSide);
		
		tSide = 4;
		tOppositeSide = cell.oppositeSide (tSide);
		assertEquals (1, tOppositeSide);
		
		tSide = 5;
		tOppositeSide = cell.oppositeSide (tSide);
		assertEquals (2, tOppositeSide);

	}
}
