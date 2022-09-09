package ui;

import java.io.IOException;

import javafx.util.Pair;
import technical.Parser;

/**
 * Class to marry UI with Java application.
 * @author Nicholas Patrick
 */
public class Duke {
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    Pair<String, Boolean> getResponse(String input) throws IOException {
        return Parser.parseExecute(input);
    }
}
