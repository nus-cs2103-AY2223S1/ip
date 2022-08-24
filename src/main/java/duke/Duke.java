package duke;

import java.io.IOException;

import duke.command.Command;

/**
 * The main entity for the Duke program.
 * @author Nephelite
 * @version 0.1
 */
public class Duke {
    /**
     * The Storage object used to load any previous tasks Duke was tracking in a previous session.
     */
    private final Storage storage;
    /**
     * The TaskList object that Duke uses to track all tasks the user inputs as commands.
     */
    private TaskList tasks;
    /**
     * The Ui object that allows Duke to communicate with the user.
     */
    private final Ui ui;

    /**
     * Constructor for a Duke object
     * @since 0.1
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showDukeException(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The method to start Duke's services.
     * @since 0.1
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.inputCommand(fullCommand, tasks, ui);
                c.execute(storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeException(e.getMessage());
            }
        }
        ui.finalGoodbye();
    }

    /**
     * Creates a Duke instance and runs it.
     * @param args
     * @since 0.1
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
