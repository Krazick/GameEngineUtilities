package checksum;

import java.lang.NullPointerException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import geUtilities.xml.XMLDocument;

public class ChecksumCalc {
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

	public String MD5 (XMLDocument aXMLDocument) {
		return MD5 (aXMLDocument.toXMLString ());
	}
	
	public String MD5 (String aMessage) {
		//
		MessageDigest tMD;
		StringBuffer tStringBuffer;
		String tTrimmedMessage;
		byte [] tBytes;
		
		try {
			tTrimmedMessage = aMessage.replace ("\r\n", "\n").replace ("    ", "").replace ("\n\n", "\n");
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
