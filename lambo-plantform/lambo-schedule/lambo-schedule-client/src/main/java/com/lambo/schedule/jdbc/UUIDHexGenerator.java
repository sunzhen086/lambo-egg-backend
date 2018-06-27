//$Id: UUIDHexGenerator.java 2343 2004-09-14 01:36:55 +0000 
package com.lambo.schedule.jdbc;


/**
 * <b>uuid.hex </b> <br>
 * <br>
 * A <tt>UUIDGenerator</tt> that returns a string of length 32, This string
 * will consist of only hex digits. Optionally, the string may be generated with
 * seperators between each component of the UUID.
 * 
 * @see UUIDStringGenerator
 * @author Gavin King
 */

public class UUIDHexGenerator extends UUIDGenerator {
	private static UUIDHexGenerator instance;
	private UUIDHexGenerator(){
		
	}
	
	public static UUIDHexGenerator getInstance(){
		if(instance==null){
			instance=new UUIDHexGenerator();
		}
		return instance;
	}
	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}
	public String generate() {
		StringBuffer sb = new StringBuffer(36);
		sb.append(format(getIP())).append(getSeparator()).append(
				format(getJVM())).append(getSeparator()).append(
				format(getHiTime())).append(getSeparator()).append(
				format(getLoTime())).append(getSeparator()).append(
				format(getCount()));
		return sb.toString();
	}
	public static void main(String[] args) {
		UUIDHexGenerator gen = UUIDHexGenerator.getInstance();
		//gen.setSeparator(":");
		for (int i = 0; i < 10; i++) {
			System.out.println(gen.generate());
		}
	}
}
