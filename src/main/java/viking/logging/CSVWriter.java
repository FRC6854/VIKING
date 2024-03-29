package viking.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVWriter {

	// Global object variables
	FileWriter fw;
	BufferedWriter bw;
	PrintWriter pw;

	// Class constructor with filename as a parameter
	public CSVWriter(String filename) {

		// Opens file in overwrite mode and intiializes PrintWriter
		try {
			fw = new FileWriter(filename, false);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
		}

		catch (IOException e) {
			System.out.println("[CSVWriter] Could not open the requested CSV file.");
			e.printStackTrace();
		}
	}

	
	/** 
	 * Writes new data to the bottom of the CSV 
	 * 
	 * @param row
	 */
	public void append(ArrayList<Double> row) {
		
		// Iterates through all the contents of the row arrayList and writes it onto the same row in the CSV.
		for (int i = 0; i < row.size(); i++) {

			// If at the end of the row arrayList, do not add an extra comma at the end to conclude the row           
			if (i == row.size() - 1) {
				pw.print(Double.toString(row.get(i)));
			}
			else {
				pw.print(Double.toString(row.get(i)) + ",");
			}
		}

		// Moves writer to write to the next row in the CSV
		pw.print("\n");
	}

   
	/**
	 * Method to flush the contents of the CSV file to buffer
	 * 
	 */
	public void flush() {
		pw.flush();
	}

	
	/**
	 * Method to call both append and flush methods
	 * 
	 * @param row
	 */
	public void appendAndFlush(ArrayList<Double> row) {
		append(row);
		flush();
	}


	/**
	 * Method to close PrintWriter
	 * 
	 */
	public void close() {
		pw.close();
	}
	
}
