package geUtilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SnapCopyFile {

	public SnapCopyFile (File aSourceFile, File aTargetDirectory, int aActionNumber) {
		String tSourceDirName;
		String tSourceFileName;
		String tTargetDirName;
		String tTargetFileName;
		String tActionNumber;
		File tDestinationFile;
		Path tSourcePath;
		Path tDestinationPath;
		
        try {
        	tSourceDirName =  aSourceFile.getParent ();
        	tSourceFileName = aSourceFile.getName ();
        	
        	tTargetDirName = aTargetDirectory.getPath ();
        	tActionNumber = "." + aActionNumber;
        	tTargetFileName = tSourceFileName.replace (".save18xx.xml", 
        						tActionNumber + ".save18xx.xml");
        	System.out.println ("Source Dir [" + tSourceDirName + "]");
        	System.out.println ("Source File [" + tSourceFileName + "]");
        	System.out.println ("Destination Dir [" + tTargetDirName + "]");
           	System.out.println ("Destination File [" + tTargetFileName + "]");
           	tDestinationFile = new File (tTargetDirName + "/" + tTargetFileName);
        	
           	tSourcePath = aSourceFile.toPath ();
           	tDestinationPath = tDestinationFile.toPath ();
           	Files.copy (tSourcePath, tDestinationPath, StandardCopyOption.REPLACE_EXISTING);
           	            
            System.out.println("File copied successfully!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
