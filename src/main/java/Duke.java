/**
 * Duke is a Task Manager program that helps a user keep track of and manage their tasks.
 */
public class Duke {
    /** Stores the list of tasks added by the user. */
    private final TaskList tasks;

    private final Ui ui = new Ui();

    /**
     * Constructor for a Duke bot.
     */
    public Duke() {
        tasks = new TaskList(new Storage("data/list.txt"));
    }

    /**
     * Runs the Duke program until the user exits with the 'bye' command.
     */
    public void run() {
        ui.greet();
        String cmd = "";
        while (!cmd.equals("bye")) {
            cmd = ui.readCommand();

            try {
                ui.speak(Parser.parseCommand(cmd, this.tasks));
            } catch (DukeException e) {
                ui.speak(e.toString());
            }
        }
    }

    /**
     * Driver method for Duke.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
