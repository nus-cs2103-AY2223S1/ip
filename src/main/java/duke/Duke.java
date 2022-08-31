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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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
            } else {
                throw new DukeUnknownTaskException();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }

    }


    /**
     * Run Duke programme
     *
     */
    public void run() {
        System.out.println(ui.greeting());
        boolean isExit = false;
        Handler handler = new Handler(tasklist, ui);
        while (!isExit) {
            try {
                String echo = ui.getInput();
                if (echo.equals("bye")) {
                    System.out.println(ui.bye());
                    isExit = true;
                } else if (echo.equals("list")) {
                    System.out.println(ui.showList());
                } else if (echo.startsWith("mark")) {
                    System.out.println(handler.handleMark(echo));
                } else if (echo.startsWith("unmark")) {
                    System.out.println(handler.handleUnmark(echo));
                } else if (echo.startsWith("todo")) {
                    System.out.println(handler.handleToDo(echo));
                } else if (echo.startsWith("deadline")) {
                    System.out.println(handler.handleDeadline(echo));
                } else if (echo.startsWith("event")) {
                    System.out.println(handler.handleEvent(echo));
                } else if (echo.startsWith("delete")) {
                    System.out.println(handler.handleDelete(echo));
                } else if (echo.startsWith("find")) {
                    System.out.println(handler.handleFind(echo));
                } else {
                    throw new DukeUnknownTaskException();
                }
                storage.save();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
