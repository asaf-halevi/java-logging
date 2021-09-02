package lesson1basics;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerWithHandlerAndFilter {
    private static final Logger logger = Logger.getLogger(LoggerWithHandlerAndFilter.class.getName());

    private static final String RESOURCE_LIB = "resources/txtFiles/";
    private static final String LOG_FILE = RESOURCE_LIB + "logFile.log";

    public static void main(String[] args) throws IOException {
        //Adding a filter to the logger
//        Filter filter = new MyFilterImpl();
//        logger.setFilter(filter);

        //Adding and removing a console handler
//        Handler consoleHandler = new ConsoleHandler();
//        logger.addHandler(consoleHandler);
//        logger.removeHandler(consoleHandler);

        //Adding a file handler
//        Handler fileHandler = new FileHandler(LOG_FILE);
//        logger.addHandler(fileHandler);

        //Setting handler's level
//        fileHandler.setLevel(Level.WARNING);
        // Adding a filter to the handler
//        fileHandler.setFilter(filter);

        //Adding a formatter to the handler
//        MyFormatterImpl formatter = new MyFormatterImpl();
//        fileHandler.setFormatter(formatter);

        logger.info("Some information");
        logger.warning("Warning! Warning! Warning!");

        System.out.println("\nAll of the logger's handlers: ");
        for (Handler h : logger.getHandlers()) {
            System.out.println(h.toString() + ", level: " + h.getLevel().getName());
        }
    }
}
