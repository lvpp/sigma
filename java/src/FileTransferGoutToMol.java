
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
 * @author gbflores
 * @version 1.0.0
 */
public class FileTransferGoutToMol {

	/**
	 * @param fileName the file that will be converted
	 */
	public FileTransferGoutToMol(String fileName) {
		String destString = "MOL_Output";
		
		File fileGout = new File(fileName);
		File fileMol = new File(fileName.replace(".gout", ".mol"));
		
		
		
		Scanner input;
		PrintWriter writer;

		try {

			input = new Scanner(fileGout);
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
					while (!name.contains("--------------------")) {
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

			String path = fileGout.getAbsolutePath().replace(fileGout.getName(), "");
			File destiny = new File(path + destString);
			if (!destiny.exists())
				destiny.mkdir();
			
			writer = new PrintWriter(new FileWriter(destiny + "/" + fileMol.getName()));
			
			writer.println(input.nextLine());
			writer.println(input.nextLine());
			writer.println(input.nextLine());
			writer.println(input.nextLine());
			for (int i = 0; i < atom.size(); i++) {
				writer.print(atom.get(i).x+atom.get(i).y+atom.get(i).z+" "+atom.get(i).name);
				input.next();
				input.next();
				input.next();
				input.next();
				writer.println(input.nextLine());
			}
			while(input.hasNextLine()){
				writer.println(input.nextLine());
			}
			
				
				
				input.close();
				writer.close();

		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Static class to store and format each atom 
	 * @author gbflores
	 *
	 */
	public static class Atom{
		public String name;
		public String x, y, z;
		DecimalFormat f = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
		{f.applyPattern("0.0000");}

		public Atom(String name, double x, double y, double z){
			this.name = name;
			this.x = ((x<0||x>=10) ? "   " : "    ")+f.format(x);
			this.y = ((y<0||y>=10) ? "   " : "    ")+f.format(y);
			this.z = ((z<0||z>=10) ? "   " : "    ")+f.format(z);
		}
	}

	
	public static void main(String[] args) {

		String path = "./";
		File dir;
		String[] files;
		dir = new File(path);
		files = dir.list();
		for (int i = 0; i < files.length; i++) {
			if (files[i].endsWith(".gout")) {
				try {
					new FileTransferGoutToMol(path + files[i]);
				} catch (Exception e) {
					System.err.println(files[i]);
				}
			}
		}

		System.out.println("END GRACEFULLY");
	}
}
