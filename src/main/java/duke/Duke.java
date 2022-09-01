package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javafx.application.Application;

/**
 * Main class that calls the parsing of the user input.
 */
public class Duke {
    public static void main(String[] args) {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter("data/duke.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try {
            fwOb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Application.launch(Main.class,args);
    }

    public String getResponse(String input) {
        return Parser.startParse(input);
    }
}
