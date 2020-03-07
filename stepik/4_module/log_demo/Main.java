import java.util.Arrays;
import java.util.logging.*;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());


    public static void main(String[] args) {
        LOGGER.log(Level.FINE, "Started with {0}", Arrays.toString(args));

        try {
            randomlyFailingAlgorithm();
        } catch (IllegalStateException e) {
            LOGGER.log(Level.SEVERE, "Exception caught", e);
            System.exit(2);
        }

        LOGGER.log(Level.FINE, "Finished successfully");
    }

    private static void randomlyFailingAlgorithm() {
        double randomNumber = Math.random();
        LOGGER.log(Level.FINE,"Generated random number: {0}", randomNumber);
        if (randomNumber < 0.5) {
            throw new IllegalStateException("Invalid fase of the Moon");
        }
    }
}