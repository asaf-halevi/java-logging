package lesson1basics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotivationWithLog {

    private static final Logger logger = Logger.getLogger(MotivationWithLog.class.getName());

    private static final String RESOURCE_LIB = "resources/txtFiles/";
    private static final String INPUT_FILE = RESOURCE_LIB + "motivationInput.txt";
    private static final String OUTPUT_FILE = RESOURCE_LIB + "motivationOutput.txt";

    public MotivationWithLog() {
        super();
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) throws IOException {
        MotivationWithLog motivationWithLog = new MotivationWithLog();
        motivationWithLog.getNumbersAndSaveSum();
    }

    /**
     * get numbers from text file and save their sum into a different file with a
     * time stamp. If that file exists already - write the sum in a new line.
     *
     * @throws IOException
     */
    public void getNumbersAndSaveSum() throws IOException {
        logger.info("getNumbersAndSaveSum started");
        List<Integer> numbers = getNumbersFromFile(INPUT_FILE);
        int sum = getSum(numbers);
        saveSumIntoFile(sum);
        logger.info("getNumbersAndSaveSum finished - saved sum " + sum);
    }

    private List<Integer> getNumbersFromFile(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            logger.fine("reading from file " + fileName);
        } catch (FileNotFoundException e) {
            logger.warning("file not found: " + fileName + " -> " + e.getMessage());
            throw e;
        }

        StringBuilder allDataAsText = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                allDataAsText.append(line).append(" ");
            }
        } catch (IOException e) {
            logger.warning("failed reading from file: " + fileName + " -> " + e.getMessage());
            throw e;
        } finally {
            br.close();
        }

        logger.fine("information from file " + allDataAsText);

        StringTokenizer stringTokenizer = new StringTokenizer(allDataAsText.toString());

        List<Integer> numbers = new ArrayList<>();
        while (stringTokenizer.hasMoreElements()) {
            numbers.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        return numbers;
    }

    private int getSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        logger.fine("sum is: " + sum);
        return sum;
    }

    private void saveSumIntoFile(int sum) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedWriter writer = null;
        File f = new File(OUTPUT_FILE);
        if (f.exists() && !f.isDirectory()) {
            logger.fine("output file already exists");
            List<Integer> numbers = getNumbersFromFile(OUTPUT_FILE);
            for (Integer num : numbers) {
                output.append(num).append("\n");
            }
        }

        output.append(sum);

        try {
            writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            logger.fine("writing to output file the following data: " + output);
            writer.append(output.toString());
        } catch (IOException e) {
            logger.warning("failed writing to file: " + OUTPUT_FILE + " -> " + e.getMessage());
            throw e;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
