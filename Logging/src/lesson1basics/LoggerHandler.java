package lesson1basics;

import java.util.logging.ConsoleHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerHandler {
	private static final Logger logger = Logger.getLogger(LoggerHandler.class.getName());

	public LoggerHandler() {
		super();
	}
	
	public static void main(String[] args) {
		Handler handler = new ConsoleHandler();

		logger.addHandler(handler);

		logger.removeHandler(handler);

		logger.info("example");
		
		Filter filter = new MyFilterImpl();

		logger.setFilter(filter);
		
		Handler[] handlers = logger.getHandlers();
		
		for (Handler h : handlers) {
			logger.info(h.getLevel().getName());
		}
	}
}
