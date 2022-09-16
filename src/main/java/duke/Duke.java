package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
//testing
public class Duke {
    private static final List<Task> ListofMessages  = new ArrayList<>();

    /**
     * Runs Parser and the entire code
     * @param args
     * @throws DukeException
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws DukeException, IOException, ParseException {
        Parser.Parser(ListofMessages);

    }
}
