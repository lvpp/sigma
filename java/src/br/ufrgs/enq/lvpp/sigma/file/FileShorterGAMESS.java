package br.ufrgs.enq.lvpp.sigma.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/**
 * File shorter for *.gout files
 * 
 * @author gbflores
 * @version 2.0.0
 */
public class FileShorterGAMESS {

	private String sourceFolder;
	private String destinyFolder;
	private String errorFolder;
	private double timeToExecute = 0;

	/**
	 * @param sourcePath
	 * @param destinyPath
	 * @param errorPath
	 */
	public FileShorterGAMESS(String sourcePath, String destinyPath, String errorPath) {

		this.sourceFolder = Util.folder(sourcePath);
		this.destinyFolder = Util.folder(destinyPath);
		this.errorFolder = Util.folder(errorPath);
		
		File destiny = new File(this.destinyFolder);
		if (!destiny.exists())
			destiny.mkdir();

		File error = new File(this.errorFolder);
		if (!error.exists())
			error.mkdir();

		File source = new File(this.sourceFolder);
		if (source.exists())
			for (String file : source.list())
				shorter(file);

		if (destiny.list().length == 0)
			destiny.list();

		if (error.list().length == 0)
			error.delete();

	}

	/**
	 * @return
	 */
	public double getCPUTimingInformation() {
		return timeToExecute;
	}

	/**
	 * @param fileName
	 *            the file that will be shorter
	 */
	private void shorter(String fileName) {
		double tempTime = 0;
		String tmp = "";

		try {
			File file = new File(sourceFolder + fileName);

			Scanner input = new Scanner(file);
			input.useLocale(Locale.US);

			boolean exitedGracefully = false;
			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("CPU timing information for all processes")) {
					input.nextLine();
					String s = input.nextLine();
					String s2[] = s.split("= ");
					tempTime = Double.parseDouble(s2[1]);
				}
				if (tmp.contains("exited gracefully")) {
					exitedGracefully = true;
					timeToExecute = +tempTime;
					break;
				}
			}

			input.close();

			PrintWriter writer = new PrintWriter(
					new FileWriter((exitedGracefully ? destinyFolder : errorFolder) + fileName));

			input = new Scanner(file);
			input.useLocale(Locale.US);

			String line = "";

			writer.println("----- GAMESS execution script -----");
			writer.println();

			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("*****")) {
					writer.println(tmp);
					while (!line.contains("*****")) {
						line = input.nextLine();
						writer.println(line);
					}
					writer.println();
					writer.flush();
					break;
				}
			}
			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("EXECUTION OF GAMESS")) {
					writer.println(tmp);
					while (!line.contains("INTERNUCLEAR")) {
						line = input.nextLine();
						writer.println(line);
					}
					writer.println();
					writer.println();
					writer.println();
					writer.flush();
					break;
				}
			}

			while (input.hasNext()) {
				if (input.next().equals("$CONTRL") && input.next().equals("OPTIONS")) {
					writer.println();
					writer.print("     $CONTRL OPTIONS");
					line = input.nextLine();
					while (!line.contains("BEGINNING GEOMETRY")) {
						writer.println(line);
						line = input.nextLine();
					}
					writer.flush();
					break;
				}
			}

			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("***** EQUILIBRIUM")) {
					writer.println("");
					writer.println(tmp);
					while (!line.contains("INTERNUCLEAR")) {
						line = input.nextLine();
						writer.println(line);
					}
					writer.println();
					writer.println();
					writer.flush();
					break;
				}
			}

			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("====")) {
					writer.println(tmp);
					line = input.nextLine();
					while (!line.contains("----- accounting info -----")) {
						writer.println(line);
						line = input.nextLine();
					}
					writer.flush();
					break;
				}
			}
			input.close();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/**
	 * for each file in this folder, will verify if gout ends without problems,
	 * if yes, will rewrite the file at "GAMESS_Output" folder;
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO review folders...
		String sourcePath = "testTransfer/gout";
		String destinyPath = "testTransfer/goutShort";
		String errorPath = "testTransfer/gout3";
		new FileShorterGAMESS(sourcePath, destinyPath, errorPath);

	}
}
