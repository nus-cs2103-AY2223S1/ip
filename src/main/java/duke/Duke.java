package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Scanner sc;
    private final Storage storage;
    private TaskList tasklist;
    private final Ui ui;
    private final static String PATH = "data/tasks.txt";

    /**
     * Constructor for Duke.
     * Loads if there are any existing tasks in storage.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(PATH);
        try {
            this.tasklist = new TaskList();
            storage.load();

        } catch (IOException e) {
            this.tasklist = new TaskList();
            ui.printError(e.getMessage());
        }
    }

    /**
     * Gets the response from the Duke bot.
     * @param input command.
     * @return the response from the Duke bot.
     */
    public String getResponse(String input) {
        try {
            Handler handler = new Handler(tasklist, ui);
            if (input.equals("bye")) {
                storage.save();
                return ui.bye();
            } else if (input.equals("list")) {
                storage.save();
                return ui.showList();
            } else if (input.startsWith("done")) {
                storage.save();
                return handler.handleMark(input);
            } else if (input.startsWith("todo")) {
                storage.save();
                return handler.handleToDo(input);
            } else if (input.startsWith("deadline")) {
                storage.save();
                return handler.handleDeadline(input);
            } else if (input.startsWith("event")) {
                storage.save();
                return handler.handleEvent(input);
            } else if (input.startsWith("delete")) {
                storage.save();
                return handler.handleDelete(input);
            } else if (input.startsWith("find")) {
                storage.save();
                return handler.handleFind(input);
            } else if (input.startsWith("edit")) {
                storage.save();
                return handler.handleEdit(input);
            } else {
                throw new DukeUnknownTaskException();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String greet() {
        return ui.greeting();
    }
}
