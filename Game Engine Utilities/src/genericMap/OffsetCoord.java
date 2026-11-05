package genericMap;

public class OffsetCoord {
	public final int col;
	public final int row;
	static public int EVEN = 1;
	static public int ODD = -1;
	
	public OffsetCoord (int aCol, int aRow) {
        col = aCol;
        row = aRow;
    }
 
    static public OffsetCoord qoffsetFromCube (int aOffset, Hex aHex) {
        int col = aHex.q;
        int row = aHex.r + (int) ((aHex.q + aOffset * (aHex.q & 1)) / 2);
        
        if ((aOffset != OffsetCoord.EVEN) && (aOffset != OffsetCoord.ODD)) {
            throw new IllegalArgumentException("offset must be EVEN (+1) or ODD (-1)");
        }
        return new OffsetCoord(col, row);
    }

    static public Hex qoffsetToCube (int aOffset, OffsetCoord aHex) {
        int q = aHex.col;
        int r = aHex.row - (int) ((aHex.col + aOffset * (aHex.col & 1)) / 2);
        int s = -q - r;
        
        if ((aOffset != OffsetCoord.EVEN) && (aOffset != OffsetCoord.ODD)) {
            throw new IllegalArgumentException("offset must be EVEN (+1) or ODD (-1)");
        }
        return new Hex(q, r, s);
    }

    static public OffsetCoord roffsetFromCube (int aOoffset, Hex aHex) {
        int col = aHex.q + (int)((aHex.r + aOoffset * (aHex.r & 1)) / 2);
        int row = aHex.r;
 
       	if ((aOoffset != OffsetCoord.EVEN) && (aOoffset != OffsetCoord.ODD)) {
            throw new IllegalArgumentException("offset must be EVEN (+1) or ODD (-1)");
        }
        return new OffsetCoord(col, row);
    }

    static public Hex roffsetToCube (int offset, OffsetCoord aHex) {
        int q = aHex.col - (int) ((aHex.row + offset * (aHex.row & 1)) / 2);
        int r = aHex.row;
        int s = -q - r;
        
        if ((offset != OffsetCoord.EVEN) && (offset != OffsetCoord.ODD)) {
            throw new IllegalArgumentException ("offset must be EVEN (+1) or ODD (-1)");
        }
        return new Hex (q, r, s);
    }
    
	public String getCoordinates () {
		String tString;
		
		tString = "(" + col + "," + row + ")";
		
		return tString;
	}
	
	public int getDistanceTo (int aEvenOdd, OffsetCoord aOffsetCoord) throws IllegalArgumentException {
		Hex tHex1;
		Hex tHex2;
		int tDistance;
		
		tHex1 = OffsetCoord.roffsetToCube (aEvenOdd, this);
		tHex2 = OffsetCoord.roffsetToCube (aEvenOdd, aOffsetCoord);

		tDistance = tHex1.distance (tHex2);
		
		return tDistance;
	}
}
