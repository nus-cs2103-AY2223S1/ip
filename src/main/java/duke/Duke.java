package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Constructor for Duke.
     * Loads if there are any existing tasks in storage.
     *
     * @param path filepath
     */
    public Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            this.tasklist = new TaskList();
            storage.load();

        } catch (IOException e) {
            this.tasklist = new TaskList();
            ui.printError(e.getMessage());
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
        new Duke("data/tasks.txt").run();
    }
}
