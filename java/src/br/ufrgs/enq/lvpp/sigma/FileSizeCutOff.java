package br.ufrgs.enq.lvpp.sigma;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class FileSizeCutOff {

	private String originalPath;
	private String destinyPath;
	private int cutOff;

	public FileSizeCutOff(int cutOff, String originalPath, String destinyPath) {
		this.cutOff = cutOff;
		this.originalPath = originalPath;
		this.destinyPath = destinyPath;
	}

	public void transferFile(String fileName){
		try {
			File originalFile = new File(originalPath+fileName);
			Scanner input = new Scanner(originalFile);
			input.useLocale(Locale.US);
			input.nextLine();
			input.nextLine();
			input.nextLine();
			int nAtoms = input.nextInt();
			if(nAtoms>=cutOff){
				System.out.println(fileName);
				input.close();
				input = new Scanner(originalFile);
				PrintWriter writer = new PrintWriter(new FileWriter(destinyPath +fileName));
				while(input.hasNextLine()){
					writer.println(input.nextLine());
				}
				input.close();
				writer.close();
				originalFile.delete();

			}
			input.close();

		} catch (Exception e) {
			System.err.println("Bad file");
		}
	}

	public static void main(String[] args) {
		
		int cut = 30;
		String originalPath = "mol/std/";
		String destinyPath = "mol/big/";

		FileSizeCutOff cutOff = new FileSizeCutOff(cut, originalPath, destinyPath);

		File dir;
		String[] files;
		dir = new File(originalPath);
		files = dir.list();
		for (int i = 0; i < files.length; i++) {
			cutOff.transferFile(files[i]);
		}
	}

}
