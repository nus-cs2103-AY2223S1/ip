package jude;

import java.io.IOException;

//@@author cheeheng-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart1.html with minor modifications
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    private static final String CONSOLE_MODE_FLAG = "console-test";
    private static final String DEFAULT_CONSOLE_DATA_STORAGE_PATH = "text-ui-test/data/tasks.txt";

    // Solution below adapted from https://github.com/nus-cs2103-AY2223S1/forum/issues/129
    /**
     * Launches the GUI unless the first argument is console-test.
     * If the first argument is console-test, then the application runs in console mode.
     * If the first argument is console-test and a second argument is provided, the second
     * argument will determine the file to save to in the output, otherwise the default of
     * /text-ui-test/data/tasks.txt will be used to facilitate regression testing
     *
     * @param args Determines whether to run in GUI mode or test in console-test mode.
     * @throws IOException When system I/O fails.
     */
    public static void main(String[] args) throws IOException {
        assert(args != null);
        if (args.length >= 1 && args[0].equalsIgnoreCase(CONSOLE_MODE_FLAG)) {
            // The directory has been chosen to facilitate regression testing.
            if (args.length == 1 || args[1].isBlank()) {
                new Main(DEFAULT_CONSOLE_DATA_STORAGE_PATH).runConsole();
            } else {
                new Main(args[1].trim()).runConsole();
            }
        } else {
            Application.launch(Main.class, args);
        }
    }
}

//@@author