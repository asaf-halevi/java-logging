package lesson2slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class LoggingWithLogback {

    private static final Logger logger = LoggerFactory.getLogger(LoggingWithLogback.class.getName());

    private static final String RESOURCE_LIB = "src/main/resources/txtFiles/";
    private static final String INPUT_FILE = RESOURCE_LIB + "motivationInput.txt";
    private static final String OUTPUT_FILE = RESOURCE_LIB + "motivationOutput.txt";

    public static void main(String[] args) throws IOException {
        LoggingWithLogback motivationWithLog = new LoggingWithLogback();
        // for (int i = 0; i < 10; i++) { //activate to demonstrate rolling policies
        motivationWithLog.getNumbersAndSaveSum();
        // }
        //        motivationWithLog.checkTimeConsumed();
    }

    /**
     * get numbers from text file and save their sum into a different file with a
     * time stamp. If that file exists already - write the sum in a new line.
     */
    public void getNumbersAndSaveSum() throws IOException {
        logger.info("getNumbersAndSaveSum started");
        List<Integer> numbers = getNumbersFromFile(INPUT_FILE);
        int sum = getSum(numbers);
        saveSumIntoFile(sum);
        logger.info("getNumbersAndSaveSum finished - saved sum {}", sum);
    }

    private List<Integer> getNumbersFromFile(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            logger.debug("reading from file {}", fileName);
        } catch (FileNotFoundException e) {
            logger.debug("file not found: {} -> {}", fileName, e.getMessage());
            throw e;
        }

        StringBuilder allDataAsText = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                allDataAsText.append(line).append(" ");
            }
        } catch (IOException e) {
            logger.warn("failed reading from file: {}  -> {}", fileName, e.getMessage());
            throw e;
        } finally {
            br.close();
        }

        logger.debug("information from file {}", allDataAsText);

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
        logger.debug("sum is: {}", sum);
        return sum;
    }

    private void saveSumIntoFile(int sum) throws IOException {
        StringBuilder output = new StringBuilder();
        BufferedWriter writer = null;
        File f = new File(OUTPUT_FILE);
        if (f.exists() && !f.isDirectory()) {
            logger.debug("output file already exists");
            List<Integer> numbers = getNumbersFromFile(OUTPUT_FILE);
            for (Integer num : numbers) {
                output.append(num).append("\n");
            }
        }

        output.append(sum);

        try {
            writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            logger.trace("writing to output file the following data: {}", output);
            writer.append(output.toString());
        } catch (IOException e) {
            logger.warn("failed writing to file: {} -> {}", OUTPUT_FILE, e.getMessage(), e);
            throw e;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void checkTimeConsumed() {
        final int limit = 10_000_000;
        Profiler myProfiler = new Profiler("myProfiler");

        myProfiler.start("String Concatenation");
        for (int i = 0; i < limit; i++) {
            logger.trace("result = " + Math.random());
        }

        myProfiler.start("Parameterized log");
        for (int i = 0; i < limit; i++) {
            logger.trace("result = {}", Math.random());
        }

        myProfiler.start("check level");
        for (int i = 0; i < limit; i++) {
            if (logger.isTraceEnabled()) {
                final double random = Math.random();
                logger.trace("result = " + random);
            }
        }

        myProfiler.stop().print();
    }

    private void badExample(String id, Stream streamBytes, Optional workOrdersRequests) {
        //        logger.debug("Get streams getProvStreamFiles finished called with id={}, workOrderItemsCount={}, returningBytes={}",
        //                id, workOrdersRequests.get().getWorkItemsCount(), Optional.ofNullable(streamBytes).map(a -> a.length).orElse(null));
    }
}
