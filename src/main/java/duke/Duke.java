package duke;

import java.io.FileNotFoundException;

/**
 * A Duke bot.
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage("data/nuke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load storage");
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    protected String getResponse(String input) {
        if (parser.parse(input)) {
            return parser.runCommand(this);
        } else {
            if (input.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else {
                return "Please enter a valid command:\n\n" + "mark\n"
                        + "unmark\n" + "list\n" + "todo\n" + "deadline\n" + "event";
            }
        }
    }

    /**
     * Starts the Duke bot.
     */
    public void run() {
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load storage");
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
