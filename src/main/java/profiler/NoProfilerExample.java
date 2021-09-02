package profiler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoProfilerExample {

    private static final int LIST_SIZE = 100000;
    private static final Logger logger = LoggerFactory.getLogger(NoProfilerExample.class.getName());

    public static void main(String[] args) {
        logger.info("client had made a request");
        final long startRunning = System.currentTimeMillis();

        List<Long> list = generateList();
        final long listGenerationProcess = System.currentTimeMillis() - startRunning;

        Collections.sort(list);
        final long listSortingProcess = System.currentTimeMillis() - (startRunning + listGenerationProcess);

        logger.info("finished");
        System.out.println("List generation process took " + listGenerationProcess + " millis");
        System.out.println("List sorting process took " + listSortingProcess + " millis");
        System.out.println("Total: " + (listGenerationProcess + listSortingProcess) + " millis");
    }

    private static List<Long> generateList() {
        List<Long> list = new ArrayList<>(LIST_SIZE);
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(Math.round((Math.random() * LIST_SIZE)));
        }
        return list;
    }
}
