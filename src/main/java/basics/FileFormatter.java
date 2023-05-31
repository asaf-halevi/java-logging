package basics;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FileFormatter extends Formatter {
    @Override
    public String format(LogRecord logRecord) {
        return String.format("%s: [%s] - %s%n", logRecord.getLoggerName(), logRecord.getLevel(), logRecord.getMessage());
    }
}
