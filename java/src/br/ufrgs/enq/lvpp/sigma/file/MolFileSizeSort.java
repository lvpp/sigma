package br.ufrgs.enq.lvpp.sigma.file;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/**
 * A class to sort a folder of MOL files by size of molecules
 * 
 * @author gbflores
 * @version 1.0.0
 */
public class MolFileSizeSort {

	private String sourceFolder;
	private String destinyFolder;
	private int cutOff;

	/**
	 * @param cutOff
	 *            Number of atoms of molecules to be moved
	 * @param sourceFolder
	 *            Original path of the MOL files
	 * @param destinyFolder
	 *            Destiny path of the MOL files which contains equal or more
	 *            atoms than {@code cutOff}
	 */
	public MolFileSizeSort(int cutOff, String sourceFolder, String destinyFolder) {

		this.cutOff = cutOff;
		this.sourceFolder = Util.folder(sourceFolder);
		this.destinyFolder = Util.folder(destinyFolder);
				
		File source = new File(this.sourceFolder);
		File destiny = new File(this.destinyFolder);
		if (!destiny.exists())
			destiny.mkdir();

		if (source.exists())
			for (String file : source.list())
				transferFile(file);

		if (destiny.list().length == 0)
			destiny.delete();

	}

	/**
	 * @param fileName
	 *            File to be moved if number of atoms is bigger or equal the
	 *            {@code cutOff}
	 */
	private void transferFile(String fileName) {
		if (!fileName.endsWith(".mol"))
			return;

		try {
			File sourceFile = new File(sourceFolder + fileName);
			Scanner input = new Scanner(sourceFile);
			input.useLocale(Locale.US);
			input.nextLine();
			input.nextLine();
			input.nextLine();
			int nAtoms = input.nextInt();
			input.close();
			if (nAtoms >= cutOff) {

				input = new Scanner(sourceFile);
				PrintWriter writer = new PrintWriter(new FileWriter(destinyFolder + fileName));

				while (input.hasNextLine())
					writer.println(input.nextLine());

				input.close();
				writer.close();
				sourceFile.delete();

			}
		} catch (Exception e) {
			System.err.println("Bad file");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int cut = 30;
		String originalPath = "mol/std";
		String destinyPath = "mol/big";

		new MolFileSizeSort(cut, originalPath, destinyPath);

	}

}
