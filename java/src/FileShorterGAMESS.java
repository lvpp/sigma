
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/**
 * File shorter for *.gout files
 * @author gbflores
 * @version 1.0.2
 */
public class FileShorterGAMESS {

	/**
	 * @param fileName the file that will be shorter
	 */
	public FileShorterGAMESS(String fileName) {
		// destiny folder
		String destString = "GAMESS_Output";
		// destiny folder for files with problem (gout not complete)
		String destStringError = "problem";
		
		File file = new File(fileName);
		Scanner input;
		PrintWriter writer;
		
		// first read to verify if the gout end with no problem
		try {
			input = new Scanner(file);
			input.useLocale(Locale.US);

			String path = file.getAbsolutePath().replace(file.getName(), "");
			File destiny = new File(path + destString);
			if (!destiny.exists())
				destiny.mkdir();
			File destinyError = new File(path + destStringError);
			if (!destinyError.exists())
				destinyError.mkdir();

			String tmp = "";
			boolean exitedGracefully = false;
			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("exited gracefully")) {
					exitedGracefully = true;
					break;
				}
			}

			if (exitedGracefully) {
				writer = new PrintWriter(new FileWriter(destiny + "/" + file.getName()));
			} else {
				writer = new PrintWriter(new FileWriter(destinyError + "/" + file.getName()));
			}

			input.close();

			// second read to short only files without problems
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
			if (destinyError.list().length == 0)
				destinyError.delete();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**Time to complete the calculus of GAMESS
	 * @param fileName
	 * @return time to compute the gout
	 */
	public double getTime(String fileName) {
		File file = new File(fileName);
		Scanner input;
		double time = -1;
		boolean exitedGracefully = false;

		try {
			input = new Scanner(file);
			input.useLocale(Locale.US);

			String tmp = "";
			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("exited gracefully")) {
					exitedGracefully = true;
					break;
				}
			}

			input.close();
			input = new Scanner(file);
			input.useLocale(Locale.US);

			while (input.hasNext()) {
				tmp = input.nextLine();
				if (tmp.contains("CPU timing information for all processes")) {
					input.nextLine();
					String s = input.nextLine();
					String s2[] = s.split("= ");
					time = Double.parseDouble(s2[1]);
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return exitedGracefully ? time : -1;
	}

	/** for each file in this folder, will verify if gout ends without problems,
	 * if yes, will rewrite the file at "GAMESS_Output" folder;
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		String path = "./";
		File dir;
		String[] files;
		dir = new File(path);
		files = dir.list();
		for (int i = 0; i < files.length; i++) {
			if (files[i].endsWith(".gout")) {
				try {
					new FileShorterGAMESS(path + files[i]);
					System.out.println(files[i]);
				} catch (Exception e) {
					System.err.println(files[i]);
				}
			}
		}

		System.out.println("END GRACEFULLY");
	}
}
