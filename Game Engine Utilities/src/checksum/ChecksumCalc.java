package checksum;

import java.lang.NullPointerException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import geUtilities.xml.XMLDocument;

public class ChecksumCalc {
	public static final boolean STRIP_WHITESPACE = true;
	public static final boolean DONT_STRIP_WHITESPACE = false;
	
	private String algorithm;
	
	public ChecksumCalc () {
		this ("MD5");
	}
	
	public ChecksumCalc (String aAlgorithm) {
		setAlgorithm (aAlgorithm);
	}
	
	private void setAlgorithm (String aAlgorithm) {
		algorithm = aAlgorithm;
	}

	public String stripWhitespaceFromXML (String aMessage) {
		String tTrimmedMessage;
		
		tTrimmedMessage = aMessage.replaceAll ("\r\n*", "\n").replaceAll ("    ", "");

		return tTrimmedMessage;
	}
	
	public String MD5 (XMLDocument aXMLDocument) {
		return MD5 (aXMLDocument.toXMLString (), STRIP_WHITESPACE);
	}
	
	public String MD5 (String aMessage) {
		return MD5 (aMessage, DONT_STRIP_WHITESPACE);
	}
	
	public String MD5 (String aMessage, boolean aStripWhitespace) {
		//
		MessageDigest tMD;
		StringBuffer tStringBuffer;
		String tTrimmedMessage;
		byte [] tBytes;
		
		try {
			if (aStripWhitespace) {
				tTrimmedMessage = stripWhitespaceFromXML (aMessage);
			} else {
				tTrimmedMessage = aMessage;
			}
			tMD = java.security.MessageDigest.getInstance (algorithm);
			tBytes = tMD.digest (tTrimmedMessage.getBytes ());
			tStringBuffer = new StringBuffer ();
	        
	        for (int i = 0; i < tBytes.length; ++i) {
	          tStringBuffer.append (Integer.toHexString ((tBytes [i] & 0xFF) | 0x100).substring (1, 3));
	        }
	        
	        return tStringBuffer.toString ();
	    } catch (NullPointerException eNull) {
	    	
	    } catch (NoSuchAlgorithmException eNSA) {
	    	
	    }
	   
	    return null;
	}
}
