package viking.logging;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public static ArrayList<ArrayList<Double>> Reader (String filename) {

        String line; // Empty String variable to read the CSV values to
        ArrayList<ArrayList<Double>> positionsArrayList = new ArrayList<>(); // ArrayList where positions coordinates are stored

        try {
            // Starts bufferedReader
            BufferedReader br = new BufferedReader(new FileReader(filename)); 

            // Read CSV top to bottom until there is a line that contains nothing
            while ((line = br.readLine()) != null) { 
                String[] values = line.split(",");
                
                // Converts string array into double array
                ArrayList<Double> coordinates = new ArrayList<Double>();

                // Iterates through String Array and converts position elements into doubles
                for (int i = 0; i < values.length; i++) {
                    coordinates.add(Double.parseDouble(values[i]));
                }
                
                // Nests coordinates arrayList into the main arrayList
                positionsArrayList.add(coordinates); 

            }

            // Closes bufferedReader instance
            br.close();

        }
        
        // Exceptions
        catch (FileNotFoundException e) {
            System.out.println("[CSVReader] The CSV file could not be found.");
            e.printStackTrace();
        }

        catch (IOException e) {
            System.out.println("[CSVReader] IO error.");
            e.printStackTrace();
        }

        return positionsArrayList;

    }

}















