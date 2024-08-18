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
	   try {
	        MessageDigest tMD = java.security.MessageDigest.getInstance (algorithm);
	        byte [] tBytes = tMD.digest (aMessage.getBytes ());
	        StringBuffer tStringBuffer = new StringBuffer ();
	        
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
