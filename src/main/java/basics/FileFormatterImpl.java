package basics;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FileFormatterImpl extends Formatter {
    @Override
    public String format(LogRecord record) {
        return String.format("%s: [%s] - %s%n", record.getLoggerName(), record.getLevel(), record.getMessage());
    }
}
