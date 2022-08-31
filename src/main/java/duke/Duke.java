package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Entry point of the Duke Chat Bot application
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    /** Ui class that prints terminal output. */
    private Ui ui;

    /** The list of tasks. */
    private TaskList tasks;

    /** Constructor for Class Duke/ */
    public Duke() {
        this.ui = new Ui();
        try {
            this.tasks = Storage.load();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the program. */
    public void run () {
        this.ui.showWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(this.tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }
}
