package infrastructure;

import org.testng.Reporter;

public class Logger {
    private static Logger instance = null;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);

    private Logger() {
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void info(final String message) {
        logger.info(message);
        Reporter.log(message + "<br>");
    }

    public void error(final String message) {
        logger.error(message);
        Reporter.log(message + "<br>");
    }
}
