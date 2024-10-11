package geUtilities.checksum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import checksum.Checksum;

class ChecksumTests {
	ChecksumTestFactory checksumTestFactory;
	Checksum checksum;
	
	@BeforeEach
	void setUp () throws Exception {
		checksumTestFactory = new ChecksumTestFactory ("2024-08-13-2010");
		checksum = checksumTestFactory.buildChecksum (1, 101);
		checksum.addClientChecksum (0, "fb1ed0c56294da977e869b9e49df239a");
	}

	@Test
	@DisplayName ("Checksum Detail Test")
	void checksumDetailTests () {
		String tDetail;
		
		tDetail = checksum.getAllDetails ();
		
		assertEquals (
			"ID: 2024-08-13-2010 Action Index: 1 Node Name: Game Client Name: Buster" +
					" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
					" Checksum [1] NO-CHECKSUM\n" +
					" Checksum [2] NO-CHECKSUM\n",
			tDetail);
		
		checksum.addClientChecksum (1, "fb1ed0c56294da977e869b9e49df239a");
		tDetail = checksum.getAllDetails ();
		assertEquals (
				"ID: 2024-08-13-2010 Action Index: 1 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [1] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [2] NO-CHECKSUM\n",
				tDetail);

	}

}
