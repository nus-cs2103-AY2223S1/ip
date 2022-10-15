package duke;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javafx.application.Application;

/**
 * Represents the Duke application.
 */
public class Duke {

    /**
     * Constructs Duke object.
     */
    public Duke() {
        try {
            Storage.load();
        } catch (FileNotFoundException e) {
            System.out.println("no file yet");
        }
    }

    /**
     * Entry point of Duke application.
     */
    public static void main(String[] args) {
        Application.launch(Launcher.class,args);
    }


    /**
     * Returns string output for user's input in the CLI.
     *
     * @return String output for user's response.
     */
    public String getResponse(String input) {
        return Parser.startParse(input);
    }
}
