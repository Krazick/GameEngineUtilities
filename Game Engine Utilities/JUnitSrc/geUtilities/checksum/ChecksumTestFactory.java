package geUtilities.checksum;

import org.mockito.Mockito;

import checksum.Checksum;
import checksum.Checksums;

public class ChecksumTestFactory {
	String gameID;
	
	public ChecksumTestFactory (String aGameID) {
		gameID = aGameID;
	}

	public Checksum buildChecksum (int aActionIndex, int aActionNumber) {
		Checksum tChecksum;
		
		tChecksum = new Checksum (gameID, "Game", "Buster", 3, aActionIndex, aActionNumber);
		tChecksum.addClientChecksum (0, "fb1ed0c56294da977e869b9e49df239a");

		return tChecksum;
	}
	
	public Checksum buildChecksum (String aNodeName, String aClientName, int aPlayerIndex, int aActionIndex, int aActionNumber) {
		Checksum tChecksum;
		
		tChecksum = new Checksum (gameID, aNodeName, aClientName, aPlayerIndex, aActionIndex, aActionNumber);
		tChecksum.addClientChecksum (0, "fb1ed0c56294da977e869b9e49df239a");

		return tChecksum;
	}

	public Checksum buildChecksumMock () {
		Checksum mChecksum;

		mChecksum = Mockito.mock (Checksum.class);
		Mockito.when (mChecksum.getNodeName ()).thenReturn ("Game");

		return mChecksum;
	}
	
	// ------------------ Build Checksums
	public Checksums buildChecksums () {
		Checksums tChecksums;
		
		tChecksums = new Checksums ();

		return tChecksums;
	}

	public Checksums buildChecksumsMock () {
		Checksums mChecksums;

		mChecksums = Mockito.mock (Checksums.class);
		Mockito.when (mChecksums.size ()).thenReturn (3);

		return mChecksums;
	}

}
