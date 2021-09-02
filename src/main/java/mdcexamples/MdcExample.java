package mdcexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MdcExample {
    private static final Logger logger = LoggerFactory.getLogger(MdcExample.class);

    public static void main(String[] args) throws Exception {
        helpCustomer("Roger");
        helpCustomer("Freddie");
    }

    private static void helpCustomer(String customerName) {
        MDC.put("client", customerName);
        logger.info("Deposited 100$");
        logger.info("Asked for a loan");
        logger.warn("Wants to close account");
    }
}
