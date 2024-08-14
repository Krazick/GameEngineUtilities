package geUtilities.utilites;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import geUtilities.Checksum;

class ChecksumTests {
	Checksum checksum;
	
	@BeforeEach
	void setUp () throws Exception {
		checksum = new Checksum ("2024-08-13-2010", "Game", "Buster", 3, 101);
		checksum.addClientChecksum (0, "fb1ed0c56294da977e869b9e49df239a");

	}

	@Test
	@DisplayName ("Checksum Detail Test")
	void checksumDetailTests () {
		String tDetail;
		
		tDetail = checksum.getAllDetails ();
		
		assertEquals (
			"ID: 2024-08-13-2010 Action Number: 101 Node Name: Game Client Name: Buster Checksum [0] fb1ed0c56294da977e869b9e49df239a\n",
			tDetail);
		
		checksum.addClientChecksum (1, "fb1ed0c56294da977e869b9e49df239a");
		tDetail = checksum.getAllDetails ();
		assertEquals (
				"ID: 2024-08-13-2010 Action Number: 101 Node Name: Game Client Name: Buster Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
				"ID: 2024-08-13-2010 Action Number: 101 Node Name: Game Client Name: Buster Checksum [1] fb1ed0c56294da977e869b9e49df239a\n",
				tDetail);

	}

}
