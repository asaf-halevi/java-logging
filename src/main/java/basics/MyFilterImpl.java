package basics;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class MyFilterImpl implements Filter {

    @Override
    public boolean isLoggable(LogRecord logRecord) {
        return logRecord.getMessage().length() > 16;
    }
}
