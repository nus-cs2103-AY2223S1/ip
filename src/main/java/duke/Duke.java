package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.task.TaskList;

/**
 * Driver class of Duke.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a new Duke to talk to.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.taskList = storage.load();
        } catch (DukeException e) {
            ui.showErrorMessage(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs Duke until it is stopped.
     */
    public void run() {
        this.start();
        this.loop();
        this.stop();
    }

    /**
     * Reads the user input and executes it, until the user issues the exit command.
     */
    public void loop() {
        while (true) {
            try {
                Command command = Parser.parseInput(this.ui.getUserInput(), this.taskList);

                if (command instanceof ExitCommand) {
                    return;
                }

                this.ui.echo(command.execute());
                this.storage.save(this.taskList);
            } catch (DukeException e) {
                this.ui.showErrorMessage(e);
            }
        }
    }

    /**
     * Starts Duke by displaying a welcome message.
     */
    public void start() {
        this.ui.showWelcomeMessage();
    }

    /**
     * Stops Duke after displaying a goodbye message.
     */
    public void stop() {
        this.ui.showGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Runs Duke from here.
     *
     * @param args Ignore.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
