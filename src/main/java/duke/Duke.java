package duke;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Duke IP for CS2103T AY 2022/2023
 *
 * @author Yuvaraj Kumaresan
 */
public class Duke {

    /**
     * Tasklist used.
     */
    private static TaskList taskList = new TaskList(new ArrayList<Task>());

    /**
     * Storage used.
     */
    private static Storage storage = new Storage(taskList);

    /**
     * UI used.
     */
    private static Ui ui = new Ui(storage);

    /**
     * Parser used.
     */
    static Parser parser = new Parser(storage, ui);

    /**
     * Gets the response from the parser and ui processors.
     */
    String getResponse(String input) throws IOException {

        return parser.parse(input);
    }


    /**
     * Main method.
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) {
        String divider = "---------------------------------------------------" +
                "-------------------------------------------------------------";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(divider);
        System.out.println("Hello I am \n" + logo + "\nType help to see user guide.\n\nWhat can I do for you?");
        System.out.println(divider);
    }
}

