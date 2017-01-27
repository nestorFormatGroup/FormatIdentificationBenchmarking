package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import de.schlichtherle.io.FileReader;

public class TxtExtraction_MultiFormatTools {

	public static String examinedFile;
	static PrintWriter csvsummary;
	static String SEPARATOR = ";";

	public static void main(String args[]) throws IOException {
		
		// these file paths are hard coded, if needed can create a little GUI for path-pasting as well

		examinedFile = "C://Users//Friese Yvonne//FileforWindows//bin//neuerTest.txt";

		String outputCsv = "C://Users//Friese Yvonne//FileforWindows//bin//" + "nestorTestFiles" + ".csv";
		
		csvsummary = new PrintWriter(new FileWriter(outputCsv));

		csvsummary.println("Filename" + SEPARATOR + "Siegfried" + SEPARATOR + "TRId" + SEPARATOR + "FILE");

		List<String> linesLog = new ArrayList<String>();
		String filename;
		String siegfriedFindings;
		String TRIdFindings;
		String fileFindings;

		File fexaminedFile = new File(examinedFile);

		FileReader input = new FileReader(fexaminedFile.getPath());
		BufferedReader buffRd = new BufferedReader(input);
		String str;
		StringBuffer buff = new StringBuffer();

		while ((str = buffRd.readLine()) != null) {
			linesLog.add(str);
		}

		buffRd.close();

		for (int j = 0; j < linesLog.size(); j++) {
			buff.append(linesLog.get(j) + "\n");

			filename = null;
			siegfriedFindings = null;
			TRIdFindings = null;
			fileFindings = null;

			if (linesLog.get(j).contains("filename : ")) {
				filename = getFileName(linesLog.get(j));
				csvsummary.print(filename + SEPARATOR);
			}

			if (linesLog.get(j).contains("    id      : ")) {
				siegfriedFindings = linesLog.get(j).replace("    id      : ", " ");
				csvsummary.print(siegfriedFindings + SEPARATOR);
			}

			if (linesLog.get(j).startsWith("Collecting")) {

				if (linesLog.get(j + 1).isEmpty()) {
					TRIdFindings = linesLog.get(j + 2).replace("Warning: file seems to be plain ", "");
				}

				else {
					TRIdFindings = linesLog.get(j + 1);
				}

				csvsummary.print(TRIdFindings + SEPARATOR);
			}

			// test if it's in C: or D:
			if (linesLog.get(j).startsWith("D:")) {
				fileFindings = getFileFindings(linesLog.get(j));
				csvsummary.print(fileFindings);
				csvsummary.print("\n");    
			}

		}

		csvsummary.close();

	}

	private static String getFileName(String filename) {
		String[] parts = filename.split(Pattern.quote("\\"));
		filename = parts[parts.length - 1]; // filename including extension
		System.out.println(filename);
		return filename;
	}

	private static String getFileFindings(String filename) {
		String[] parts = filename.split(Pattern.quote(";"));
		filename = parts[parts.length - 1];
		System.out.println(filename);
		return filename;
	}
}
