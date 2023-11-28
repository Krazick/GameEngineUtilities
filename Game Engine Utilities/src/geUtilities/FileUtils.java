package geUtilities;

import java.io.File;
import java.io.FileWriter;
//import java.io.IOException;
//import java.util.logging.Logger;

//import org.apache.logging.log4j.Logger;

public class FileUtils {
	public static final File NO_FILE = null;
	public static final FileUtils NO_FILE_UTILS = null;
	public static final FileWriter NO_FILE_WRITER = null;
//	private Logger logger;
//	private FileWriter fileWriter;
//	private File file;
	private String GE_extension;
	public String xml;

	public FileUtils (String aExtension) {
		GE_extension = aExtension;
		xml = aExtension + "xml";
	}

	/*
	 * Get the extension of a file.
	 */
	public String getExtension (File aFile) {
		String tExtension = GUI.NULL_STRING;
		String s = aFile.getName ();
		int i = s.lastIndexOf ('.' + GE_extension);

		if ((i > 0) && (i < s.length () - 1)) {
			tExtension = s.substring (i + 1).toLowerCase ();
		}

		return tExtension;
	}
//	
//	public FileUtils (Logger aLogger) {
//		setLogger (aLogger);
//	}
//	
//	public void setLogger (Logger aLogger) {
//		logger = aLogger;
//	}
//
//	public void setFile (File aFile) {
//		file = aFile;
//	}
//	
//	public void printInfo () {
//		if (file != NO_FILE) {
//			System.out.println ("File is NOT NULL");
//		} else {
//			System.out.println ("File is NULL");
//		}
//		if (fileWriter != NO_FILE_WRITER) {
//			System.out.println ("File Writer is NOT NULL");
//		} else {
//			System.out.println ("File Writer is NULL");
//		}
//		if (logger != null) {
//			System.out.println ("Logger is NOT NULL");
//		} else {
//			System.out.println ("Logger is NULL");
//		}
//	}
//	
//	public static void createDirectory (String aDirectoryName) {
//	    File tDirectory = new File (aDirectoryName);
//	    
//	    if (! tDirectory.exists ()){
//	    	tDirectory.mkdir ();
//	    }
//	}
//	
//	public boolean setupFileWriter () {
//		boolean tGoodFileWriter = false;
//		
//		fileWriter = NO_FILE_WRITER;
//		
//		if (file != NO_FILE) {
//			try {
//				fileWriter = new FileWriter (file, false); // Overwrite the file if it exists
//				tGoodFileWriter = true;
//			} catch (IOException tException) {
//				logger.error ("FileUtils problem creating FileWriter", tException);
//			}
//		}
//		
//		return tGoodFileWriter;
//	}
//	
//	public boolean fileIsSetup () {
//		boolean tFileIsSetup = false;
//		
//		if (file != NO_FILE) {
//			tFileIsSetup = true;
//		}
//		
//		return tFileIsSetup;
//	}
//	
//	public boolean fileWriterIsSetup () {
//		boolean tFileWriterIsSetup = false;
//		
//		if (fileWriter != NO_FILE_WRITER) {
//			tFileWriterIsSetup = true;
//		}
//		
//		return tFileWriterIsSetup;
//	}
//	
//	public void startXMLFileOutput () {
//		outputToFile ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//	}
//	
//	public void closeFile () {
//		if (fileWriterIsSetup ()) {
//			try {
//				fileWriter.close ();
//				fileWriter = NO_FILE_WRITER;
//			} catch (IOException tException) {
//				logger.error ("FileUtils problem Closing FileWriter", tException);
//			}
//		}
//	}
//	
//	public void outputToFile (String aDataString) {
//		if (fileWriterIsSetup ()) {
//			try {
//				fileWriter.write (aDataString + "\n");
//				fileWriter.flush ();
//			} catch (Exception tException) {
//				logger.error ("FileUtils problem Writing to FileWriter", tException);
//			}
//		}
//	}
//	
//	public XMLDocument loadXMLFile (File aSaveGame) {
//		XMLDocument tXMLDocument = XMLDocument.NO_XML_DOCUMENT;
//		
//		if (aSaveGame != NO_FILE) {
//			try {
//				tXMLDocument = new XMLDocument (aSaveGame);
//				if (! tXMLDocument.ValidDocument ()) {
//					logger.error ("XML Document for the Saved File did not load a Valid Document");
//				}
//			} catch (Exception tException) {
//				logger.error ("Oops, mucked up the XML AutoSaved File [" + aSaveGame.getName () + "].");
//				logger.error ("Exception Message [" + tException.getMessage () + "].", tException);
//			}
//		} else {
//			logger.error ("No File Object for XML AutoSaved Game");
//		}
//		
//		return tXMLDocument;
//	}
}
