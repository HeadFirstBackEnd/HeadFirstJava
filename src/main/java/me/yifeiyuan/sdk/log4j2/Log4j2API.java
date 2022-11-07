package me.yifeiyuan.sdk.log4j2;

import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2API {
    public static void main(String[] args) {

        Logger logger = LogManager.getLogger("Foo");

        logger.debug("Log4j2API logger main");
        Logger logger1 = LogManager.getLogger(Log4j2API.class);
    }
}
