package main;

import java.util.logging.Logger;

public class Main {
    private static Logger logger;

    public static void main(String[] args) {
        if (!setupLogger()) utils.UtilsForAll.exitFromProgram();
    }

    private static boolean setupLogger() {
        if (!utils.UtilsForAll.setMyLoggerConfiguration()) {
            System.out.println("Ошибка доступа к настройкам логгера");
            return false;
        }

        logger = Logger.getLogger(utils.UtilsForAll.getMainClass().getName());
        logger.info("Запуск программы");
        return true;
    }
}
