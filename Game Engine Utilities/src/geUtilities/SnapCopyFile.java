package geUtilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SnapCopyFile {

	public SnapCopyFile (File aSourceFile, File aTargetDirectory, int aActionNumber) {
		String tSourceFileName;
		String tTargetDirName;
		String tTargetFileName;
		String tActionNumber;
		File tDestinationFile;
		Path tSourcePath;
		Path tDestinationPath;
		
        try {
        	if (exists (aSourceFile)) {
	        	tSourceFileName = aSourceFile.getName ();
	        	if (exists (aTargetDirectory)) {
		        	tTargetDirName = aTargetDirectory.getPath ();
		        	tActionNumber = "." + aActionNumber;
		        	tTargetFileName = tSourceFileName.replace (".save18xx.xml", 
		        						tActionNumber + ".save18xx.xml");
		           	tDestinationFile = new File (tTargetDirName + "/" + tTargetFileName);
		        	
		           	tSourcePath = aSourceFile.toPath ();
		           	tDestinationPath = tDestinationFile.toPath ();
		           	Files.copy (tSourcePath, tDestinationPath, StandardCopyOption.REPLACE_EXISTING);
	        	}
        	}
         } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public boolean exists (File aFile) {
		boolean tExists;
		
		tExists = true;
		if (aFile == null) {
			tExists = false;
		} else {
			tExists = aFile.exists ();
		}
		
		return tExists;
	}
}
