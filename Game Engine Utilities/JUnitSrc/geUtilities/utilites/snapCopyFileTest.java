package geUtilities.utilites;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geUtilities.SnapCopyFile;

class snapCopyFileTest {

	@BeforeEach
	void setUp () throws Exception {
	}

	@Test
	void test () {
		SnapCopyFile tSnapCopyFile;
		String tSource = "/Users/marksmith/eclipse-workspace/18XX-Game-Engine/autoSaves/network";
		String tSourceFileName = "1830.2026-11-10-1451.Jeff.save18xx.xml";
		String tDestination = "/Users/marksmith/eclipse-workspace/18XX-Game-Engine/autoSaves/network/snaps";
		File tSourceDir;
		File tDestinationDir;
		int tActionNumber;
		
		tSourceDir = new File (tSource + "/" + tSourceFileName);
		tDestinationDir = new File (tDestination);
		tActionNumber = 101;
		tSnapCopyFile = new SnapCopyFile (tSourceDir, tDestinationDir, tActionNumber);
	}

}
