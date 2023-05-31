package basics;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ConsoleFormatter extends Formatter {
    @Override
    public String format(LogRecord logRecord) {
        return String.format("%s%n", logRecord.getMessage());
    }
}
