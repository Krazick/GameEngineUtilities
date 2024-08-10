package geUtilities.utilites;

import java.util.LinkedList;
import java.util.List;

public class Checksums {
	public static final Checksums NO_CHECKSUMS = null;
	List<Checksum> checksums;

	public Checksums () {
		checksums = new LinkedList<Checksum> ();
	}

	public void add (Checksum aChecksum) {
		checksums.add (aChecksum);
	}
	
	public Checksum getAt (int aIndex) {
		return checksums.get (aIndex);
	}
	
	public int size () {
		return checksums.size ();
	}
}
