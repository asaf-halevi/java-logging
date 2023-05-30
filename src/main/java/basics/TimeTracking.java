package basics;

import java.io.*;
import java.util.logging.Logger;

public class TimeTracking {

    private static final Logger logger = Logger.getLogger(TimeTracking.class.getName());

    private static final String RESOURCE_LIB = "src/main/resources/txtFiles/";
    private static final String INPUT_FILE = RESOURCE_LIB + "timeTrackingInfo.txt";

    public TimeTracking() {
        super();
    }

    public static void main(String[] args) throws IOException {
        TimeTracking timeTracking = new TimeTracking();
        long startTime = System.currentTimeMillis();
        timeTracking.someTimeConsumingFunction(INPUT_FILE);
        long elapsedTimeForReading = System.currentTimeMillis() - startTime;
        logger.info("elapsed time = " + elapsedTimeForReading + " millis");
    }

    private String someTimeConsumingFunction(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            logger.info("reading from file " + fileName);
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

        logger.info("information from file " + allDataAsText);

        return allDataAsText.toString();
    }
}
