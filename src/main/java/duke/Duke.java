package duke;

import java.time.format.DateTimeParseException;

import java.io.IOException;

/**
 * The main class to run Duke.
 */
public class Duke {

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke (String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.parser = new Parser(this.storage, this.tasks);
        this.ui = new Ui(this.parser);
        try {
           this.parser.load();
            System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" );
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Date and Time format error: check input or file, " + e.getMessage());
        } catch (DukeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void run() {
        this.ui.run();
    }

    public static void main(String[] args)  {
        new Duke("data/tasks.txt").run();
    }
}
