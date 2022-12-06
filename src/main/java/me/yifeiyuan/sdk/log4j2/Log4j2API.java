package me.yifeiyuan.sdk.log4j2;

import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2API {
    public static void main(String[] args) {

        Logger logger = LogManager.getLogger("Foo");

        System.out.println(logger);

        logger.warn("Log4j2API logger main");

        Logger loggerFoo2 = LogManager.getLogger("Foo");

        System.out.println(loggerFoo2);


        Logger logger1 = LogManager.getLogger(Log4j2API.class);

        logger1.warn("logger1 ");
        System.out.println(logger1);

    }
}
