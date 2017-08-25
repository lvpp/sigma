package br.ufrgs.enq.lvpp.sigma.file;

import java.io.File;
import java.io.FileFilter;

public class FileFinder{

	private String fileToFind;
	private String fileLocation;
	private boolean stop = false;
	
	public FileFinder(String fileToFind, String startFolder) {
		this.fileToFind = fileToFind;
		find(startFolder);
	}

	private void find(String folder){
		if (stop){return;}

		File file = new File(folder);
		if(file.isDirectory()){
			File[] folderList = file.listFiles();
			for (File d :folderList) {
				String split[] = d.toString().split(File.separator);
				if(split[split.length-1].equalsIgnoreCase(fileToFind)){
					fileLocation = d.toString();
					stop = true;
					return;
				}
				if (d.isDirectory())
					find(d.toString());
			}
		}
	}
	
	public String getFileLocation(){
		return fileLocation;
	}

	
	public static void main(String[] args) {
		FileFinder f = new FileFinder("benzene.mol", "testeF1");
		System.out.println(f.fileLocation);

	}

}
