package br.ufrgs.enq.lvpp.sigma.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * File shorter for *.gout files
 * 
 * @author gbflores
 * @version 2.0.0
 */
public class FileTransferGoutToMol {

	private String goutFolder;
	private String molSourceFolder;
	private String molDestinyFolder;

	public FileTransferGoutToMol(String goutFolder, String molSourceFolder, String molDestinyFolder) {
		this.goutFolder = Util.folder(goutFolder);
		this.molSourceFolder = Util.folder(molSourceFolder);
		this.molDestinyFolder = Util.folder(molDestinyFolder);
		
		File source = new File(this.goutFolder);
		File destiny = new File(this.molDestinyFolder);
		if (!destiny.exists())
			destiny.mkdir();
		
		if (source.exists())
			for (String file : source.list())
				transferGoutToMol(file);
		
		if (destiny.list().length==0)
			destiny.delete();
		
	}

	/**
	 * @param fileName
	 *            the file that will be converted
	 */
	public void transferGoutToMol(String fileName) {

		File fileGout = new File(goutFolder + fileName);
		String fileNameMol = new FileFinder(fileName.replace(".gout", ".mol"), molSourceFolder).getFileLocation();
		File fileMol = new File(fileNameMol);

		try {

			Scanner input = new Scanner(fileGout);
			input.useLocale(Locale.US);
			String tmp = "";

			ArrayList<Atom> atom = new ArrayList<Atom>();
			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("***** EQUILIBRIUM")) {
					input.nextLine();
					input.nextLine();
					input.nextLine();
					input.nextLine();
					String name = input.next();
					while (!name.contains("INTERNUCLEAR")) {
						input.next();
						double x = input.nextDouble();
						double y = input.nextDouble();
						double z = input.nextDouble();
						atom.add(new Atom(name, x, y, z));
						name = input.next();
					}
					break;
				}
			}

			input.close();

			input = new Scanner(fileMol);
			input.useLocale(Locale.US);

			PrintWriter writer = new PrintWriter(new FileWriter(molDestinyFolder + fileMol.getName()));

			writer.println(input.nextLine());
			writer.println(input.nextLine());
			writer.println(input.nextLine());
			writer.println(input.nextLine());
			for (int i = 0; i < atom.size(); i++) {
				writer.print(atom.get(i).x + atom.get(i).y + atom.get(i).z + " " + atom.get(i).name);
				input.next();
				input.next();
				input.next();
				input.next();
				writer.println(input.nextLine());
			}
			while (input.hasNextLine()) {
				writer.println(input.nextLine());
			}

			input.close();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Static class to store and format each atom
	 * 
	 * @author gbflores
	 *
	 */
	public static class Atom {
		public String name;
		public String x, y, z;
		DecimalFormat f = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
		{
			f.applyPattern("0.0000");
		}

		public Atom(String name, double x, double y, double z) {
			this.name = name;
			this.x = ((x < 0 || x >= 10) ? "   " : "    ") + f.format(x);
			this.y = ((y < 0 || y >= 10) ? "   " : "    ") + f.format(y);
			this.z = ((z < 0 || z >= 10) ? "   " : "    ") + f.format(z);
		}
	}
//	1-HEXENE.gout
//	CYCLOHEXANE.gout
//	N-HEXANE.gout

	public static void main(String[] args) {
		String goutFolder = "testTransfer/gout";
		String molSourceFolder = "testTransfer/molsource";
		String molDestinyFolder = "testTransfer/mol2";
		
		new FileTransferGoutToMol(goutFolder, molSourceFolder, molDestinyFolder);

	}
}
