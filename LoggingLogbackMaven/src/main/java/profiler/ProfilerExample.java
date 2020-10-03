package profiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

public class ProfilerExample {

    private static final int LIST_SIZE = 1000;
    private static final Logger logger = LoggerFactory.getLogger(ProfilerExample.class.getName());

    public static void main(String[] args) {
        logger.info("client has made a request");
        Profiler myProfiler = new Profiler("myProfiler");

        myProfiler.start("List generation process");
        List<Long> list = generateList();

        myProfiler.start("List sorting process");
        Collections.sort(list);

        myProfiler.stop().print();
        logger.info("finished");
    }

    private static List<Long> generateList() {
        List<Long> list = new ArrayList<Long>(LIST_SIZE);
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(Math.round((Math.random() * LIST_SIZE)));
        }
        return list;
    }
}
