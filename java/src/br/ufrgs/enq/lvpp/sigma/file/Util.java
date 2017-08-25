package br.ufrgs.enq.lvpp.sigma.file;

import java.io.File;

public class Util {

	private static String separator = File.separator;
	
	public static String folder(String folder){
		return (folder.endsWith(separator) ? folder : folder + separator);
	}

}
