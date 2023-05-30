package basics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Motivation {

    private static final String RESOURCE_LIB = "src/main/resources/txtFiles/";
    private static final String INPUT_FILE = RESOURCE_LIB + "motivationInput.txt";
    private static final String OUTPUT_FILE = RESOURCE_LIB + "motivationOutput.txt";

    public Motivation() {
        super();
    }

    public static void main(String[] args) throws IOException {
        Motivation motivation = new Motivation();
        motivation.getNumbersAndSaveSum();
    }

    /**
     * get numbers from text file and save their sum into a different file with a
     * time stamp. If that file exists already - write the sum in a new line.
     */
    public void getNumbersAndSaveSum() throws IOException {
        List<Integer> numbers = getNumbersFromFile(INPUT_FILE);
        int sum = getSum(numbers);
        saveSumIntoFile(sum);
    }

    private List<Integer> getNumbersFromFile(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e1) {
            //Do nothing
        }

        StringBuilder allDataAsText = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                allDataAsText.append(line).append(" ");
            }
        } catch (IOException e) {
            //Do nothing
        } finally {
            if (br != null) {
                br.close();
            }
        }

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
        return sum;
    }

    private void saveSumIntoFile(int sum) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedWriter writer = null;
        File f = new File(OUTPUT_FILE);
        if (f.exists() && !f.isDirectory()) {
            List<Integer> numbers = getNumbersFromFile(OUTPUT_FILE);
            for (Integer num : numbers) {
                output.append(num).append("\n");
            }
        }

        output.append(sum);

        try {
            writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            writer.append(output.toString());
        } catch (IOException e) {
            //Do nothing
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
