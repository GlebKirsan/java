import java.util.logging.*;

public class Main {
    private static void configureLogging() {
        Logger.getLogger("org.stepic.java.logging.ClassA").setLevel(Level.ALL);
        Logger.getLogger("org.stepic.java.logging.ClassB").setLevel(Level.WARNING);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new XMLFormatter());
        handler.setLevel(Level.ALL);
        Logger otherLoggers = Logger.getLogger("org.stepic.java");
        otherLoggers.setLevel(Level.ALL);
        otherLoggers.setUseParentHandlers(false);
        otherLoggers.addHandler(handler);
    }

    public static void main(String[] args) {

    }
}