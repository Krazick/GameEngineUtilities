package geUtilities;

import java.util.LinkedList;
import java.util.List;

import geUtilities.xml.ElementName;

public class Checksums {
	public static final ElementName EN_CHECKSUMS = new ElementName ("Checksums");
	public static final Checksums NO_CHECKSUMS = null;
	public static final int NOT_FOUND = -1;
	List<Checksum> checksums;

	public Checksums () {
		checksums = new LinkedList<Checksum> ();
	}

	public void add (Checksum aChecksum) {
		checksums.add (aChecksum);
	}
	
	public Checksum get (int aIndex) {
		return checksums.get (aIndex);
	}
	
	public Checksum getLast () {
		Checksum tLastChecksum;
		
		tLastChecksum = get (checksums.size () - 1);
		
		return tLastChecksum;
	}
	
	public int findIndexFor (int aActionNumber) {
		int tIndex;
		int tCount;
		int tFoundIndex;
		Checksum tChecksum;
		
		tFoundIndex = NOT_FOUND;
		if (!checksums.isEmpty ()) {
			tCount = size ();
			for (tIndex = 0; tIndex < tCount; tIndex++) {
				tChecksum = checksums.get (tFoundIndex);
				if (tChecksum.getActionNumber () == aActionNumber) {
					tFoundIndex = tIndex;
				}
			}
			
		}
		
		return tFoundIndex;
	}
	
	public void removeAtNumber (int aActionNumber) {
		int tIndex;
		
		tIndex = findIndexFor (aActionNumber);
		if (tIndex != NOT_FOUND) {
			remove (tIndex);
		}
	}
	
	public void remove (int aIndex) {
		checksums.remove (aIndex);
	}
	
	public int size () {
		return checksums.size ();
	}
	
	public String printChecksums () {
		String tAllDetails;
		
		tAllDetails = GUI.EMPTY_STRING;
		if (!checksums.isEmpty ()) {
			for (Checksum tChecksum : checksums) {
				tAllDetails = tChecksum.getAllDetails ();
			}
		}
		
		return tAllDetails;
	}
}
