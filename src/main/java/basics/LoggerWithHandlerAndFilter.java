package basics;

import java.io.IOException;
import java.util.logging.*;

public class LoggerWithHandlerAndFilter {
    private static final Logger logger = Logger.getLogger(LoggerWithHandlerAndFilter.class.getName());

    private static final String LOG_FILE = "logFile.log";

    public static void main(String[] args) throws IOException {
        //Adding a filter to the logger
        Filter filter = new MyFilterImpl();
        //logger.setFilter(filter);

        //Adding and removing a console handler
        Handler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);
        //logger.removeHandler(consoleHandler);

        //Adding a file handler
        Handler fileHandler = new FileHandler(LOG_FILE);
        logger.addHandler(fileHandler);

        //Setting handler's level
        fileHandler.setLevel(Level.WARNING);

        // Adding a filter to the handler
        fileHandler.setFilter(filter);

        //Adding a formatter to the handler
        MyFormatterImpl formatter = new MyFormatterImpl();
        fileHandler.setFormatter(formatter);

        //do not forward records to parent logger
        logger.setUseParentHandlers(false);

        logger.info("Some information");
        logger.warning("Warning! Warning! Warning!");
        logger.warning("too short");

        logger.info("\nAll of the logger's handlers: ");

        for (Handler handler : logger.getHandlers()) {
            final String message = String.format(" - %s, level: %s", handler.toString(), handler.getLevel().getName());
            logger.info(message);
        }
    }
}
