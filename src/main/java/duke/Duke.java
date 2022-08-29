package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.parser.DukeParser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Main Duke class.
 */
public class Duke {

    private static final String FILE_PATH = "src/main/java/duke/data.txt";

    // String array used to store duke.tasks
    private static TaskList taskList = new TaskList();

    /**
     * The main function to run Duke.
     *
     * @param args command-line arguments (currently serves no functionality)
     */
    // Currently, the main function takes in user input and echoes it to the user
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello, I am Duke.");

        // Init variables to use
        Scanner sc = new Scanner(System.in);
        Storage st = new Storage(FILE_PATH);
        try {
            taskList = st.load();
        } catch (Exception e) {
            System.out.println("I found some previously entered data on your disk, but "
                    + "encountered the following error when trying to load it:\n"
                    + e.getMessage());
        }
        DukeParser parser = new DukeParser(taskList);
        System.out.println("What can I do for you? :)\n=================================");

        while (!parser.exitDuke()) {
            String input = sc.nextLine();
            parser.parseInput(input);
            try {
                parser.execute(st);
                continue;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("=================================");
                continue;
            }
        }
    }

}


