package badloggerpackage;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BadLogger {

    private static final Logger logger = LoggerFactory.getLogger(BadLogger.class.getName());

    public static void main(String[] args) throws IOException {
        BadLogger badLogger = new BadLogger();
        badLogger.calc(5, 7);
        badLogger.calc(50, 3);
        badLogger.calc(17, 2);
    }

    public BadLogger() {
        super();
    }

    public void calc(int a, int b) throws IOException {
        logger.debug("calc started");
        long power = getPower(a, b);
        long counter = getCounter(a) + getCounter(b);
        long devider = getDevider(a, b);
        System.out.println("result is " + power + counter + devider);
        logger.debug("calc finished");
    }

    private long getPower(int a, int b) {
        long result = (long) Math.pow(a + 1, b - 1);
        return result;
    }

    private long getCounter(int num) {
        long result = 0;
        for (int i = 1; i <= num; i++) {
            logger.info("i=" + i);
            result += i;
        }
        return result;
    }

    private long getDevider(int a, int b) {
        long result = a / b;
        return result;
    }
}
