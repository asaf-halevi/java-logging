package mdcexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MdcExample {

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(MdcExample.class);
        MDC.put("client", "Alex");

        logger.info("Deposited 100$");
        logger.warn("Wants to close account");

        MDC.put("client", "Freddie");

        logger.info("Asked for a loan");
    }
}
