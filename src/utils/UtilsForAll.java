package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

import static utils.ConstantForAll.*;

public class UtilsForAll {

    public static Class getMainClass() {
        return main.Main.class;
    }

    public static void exitFromProgram() {
        System.exit(0);
    }

    public static boolean setMyLoggerConfiguration() {
        try {
            InputStream inputStream = getMainClass().getResourceAsStream(LOGGER_PARAM);
            if (inputStream != null) {
                LogManager.getLogManager().readConfiguration(inputStream);
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean setLoggerConsoleHandler(Logger logger) {
        //удалить все хэндлерсы для логгера
        Handler[] handlers = logger.getHandlers();
        for (Handler handler : handlers) {
            logger.removeHandler(handler);
        }
        //добавить новый
        ConsoleHandler fh;
        try {
            fh = new ConsoleHandler();
            fh.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format("%s: %s%n",
                            fh.getLevel().getLocalizedName(),
                            record.getMessage()
                    );
                }
            });
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
            return false;
        }
        return true;
    }
}
