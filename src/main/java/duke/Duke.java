package duke;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Duke IP for CS2103T AY 2022/2023
 *
 * @author Yuvaraj Kumaresan
 */
public class Duke {

    private static TaskList taskList = new TaskList(new ArrayList<Task>());
    private static Storage storage = new Storage(taskList);
    private static Ui ui = new Ui(storage);
    private static Parser parser = new Parser(storage, ui);

    /**
     * Starts the Duke chatbot.
     *
     * @throws IOException
     */
    public static void run() throws IOException {
        storage.readFromFile();
        parser.parse();
    }

    /**
     * Main method.
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException, IOException {
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
        run();
    }
}

