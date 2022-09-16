package duke;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javafx.application.Application;

/**
 * Main class that calls the launching of the Main class
 */
public class Duke {

    public Duke() {
        try {
            Storage.load();
        } catch (FileNotFoundException e) {
            System.out.println("no file yet");
        }
    }

    public static void main(String[] args) {
        Application.launch(Launcher.class,args);

    }


    public String getResponse(String input) {

        return Parser.startParse(input);
    }
}
