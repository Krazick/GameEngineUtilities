package geUtilities.checksum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import checksum.Checksum;
import checksum.Checksums;

class ChecksumsTests {
	ChecksumTestFactory checksumTestFactory;
	Checksums checksums;
	Checksum checksum1;
	Checksum checksum2;
	Checksum checksum3;
	
	@BeforeEach
	void setUp () throws Exception {
		
		checksumTestFactory = new ChecksumTestFactory ("2024-08-14-1600");
		checksums = checksumTestFactory.buildChecksums ();
		checksum1 = checksumTestFactory.buildChecksum (101);
		checksum2 = checksumTestFactory.buildChecksum (102);
		checksum3 = checksumTestFactory.buildChecksum (103);
	}

	@Test
	@DisplayName ("List of Checksums Tests")
	void checksumsTests () {
		Checksum tChecksumAlpha;
		Checksum tChecksumBeta;
		
		assertEquals ("", checksums.getDetailAllChecksums ());
		
		checksums.add (checksum1);
		assertEquals (
				"ID: 2024-08-14-1600 Action Index: 101 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [1] NO-CHECKSUM\n" +
						" Checksum [2] NO-CHECKSUM\n",
						checksums.getDetailAllChecksums ());
		
		tChecksumAlpha = checksums.get (0);
		assertEquals (checksum1, tChecksumAlpha);
		
		tChecksumAlpha.addClientChecksum (1, "fb1ed0c56294da977e869b9e49df239a");
		assertEquals (
				"ID: 2024-08-14-1600 Action Index: 101 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [1] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [2] NO-CHECKSUM\n",
						checksums.getDetailAllChecksums ());
		tChecksumAlpha.addClientChecksum (2, "fb1ed0c56294da977e869b9e49df239a");
		assertNotEquals (
				"ID: 2024-08-14-1600 Action Index: 101 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [1] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [2] NO-CHECKSUM\n",
						checksums.getDetailAllChecksums ());
		assertEquals (
				"ID: 2024-08-14-1600 Action Index: 101 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [1] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [2] fb1ed0c56294da977e869b9e49df239a\n",
						checksums.getDetailAllChecksums ());
		
		tChecksumBeta = checksums.get (1);
		assertNull (tChecksumBeta);

		tChecksumBeta = checksums.getLast ();
		assertEquals (tChecksumAlpha, tChecksumBeta);
		
		checksums.add (checksum2);
		tChecksumBeta = checksums.getLast ();
		assertEquals (checksum2, tChecksumBeta);
		assertNotEquals (tChecksumAlpha, tChecksumBeta);
		
		assertEquals (2, checksums.size ());
		
		tChecksumBeta.addClientChecksum (2, "fb1ed0c56294da977e869bxe49df239a");
		tChecksumBeta.addClientChecksum (0, "fb1ed0c56294da977e869bxe49df239a");

		assertEquals (
				"ID: 2024-08-14-1600 Action Index: 101 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [1] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [2] fb1ed0c56294da977e869b9e49df239a\n" +
				"ID: 2024-08-14-1600 Action Index: 102 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869bxe49df239a\n" +
						" Checksum [1] NO-CHECKSUM\n" +
						" Checksum [2] fb1ed0c56294da977e869bxe49df239a\n",

						checksums.getDetailAllChecksums ());

		checksums.add (checksum3);
		assertEquals (3, checksums.size ());
		
		assertEquals (
				"ID: 2024-08-14-1600 Action Index: 101 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [1] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [2] fb1ed0c56294da977e869b9e49df239a\n" +
				"ID: 2024-08-14-1600 Action Index: 102 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869bxe49df239a\n" +
						" Checksum [1] NO-CHECKSUM\n" +
						" Checksum [2] fb1ed0c56294da977e869bxe49df239a\n" +
				"ID: 2024-08-14-1600 Action Index: 103 Node Name: Game Client Name: Buster" +
						" Checksum [0] fb1ed0c56294da977e869b9e49df239a\n" +
						" Checksum [1] NO-CHECKSUM\n" +
						" Checksum [2] NO-CHECKSUM\n",

						checksums.getDetailAllChecksums ());
		
		assertEquals (1, checksums.findIndexFor (102));
		assertEquals (Checksums.NOT_FOUND, checksums.findIndexFor (404));
		
		checksums.removeAtNumber (404);
		assertEquals (3, checksums.size ());
		checksums.removeAtNumber (101);
		assertEquals (2, checksums.size ());
		
		checksums.remove (1);
		checksums.remove (0);
		assertEquals (Checksums.NOT_FOUND, checksums.findIndexFor (404));
		
		assertEquals (Checksum.NO_CHECKSUM, checksums.getLast ());
		assertEquals (Checksum.NO_CHECKSUM, checksums.get (5));
	}

}
