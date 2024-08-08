package geUtilities;

public class Checksum {

	public Checksum () {
		
	}
	
	public String MD5 (XMLDocument aXMLDocument) {
		return MD5 (aXMLDocument.toXMLString ());
	}
	
	public String MD5 (String aMessage) {
	   try {
	        java.security.MessageDigest tMD = java.security.MessageDigest.getInstance ("MD5");
	        byte [] tBytes = tMD.digest (aMessage.getBytes ());
	        StringBuffer tStringBuffer = new StringBuffer ();
	        
	        for (int i = 0; i < tBytes.length; ++i) {
	          tStringBuffer.append (Integer.toHexString ((tBytes [i] & 0xFF) | 0x100).substring (1, 3));
	        }
	        
	        return tStringBuffer.toString ();
	    } catch (java.lang.NullPointerException eNull) {
	    	
	    } catch (java.security.NoSuchAlgorithmException eNSA) {
	    	
	    }
	   
	    return null;
	}
}
