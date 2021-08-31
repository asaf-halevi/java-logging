package lesson1basics;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatterImpl extends Formatter {
    @Override
    public String format(LogRecord record) {
        return String.format("%s: [%s] - %s%n", record.getLoggerName(), record.getLevel(), record.getMessage());
    }
}
