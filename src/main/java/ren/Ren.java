package ren;

/**
 * Ren is a Task Manager program that helps a user keep track of and manage their tasks.
 */
public class Ren {
    /** Stores the list of tasks added by the user. */
    private final TaskList tasks;

    private final Ui ui = new Ui();

    /**
     * Constructor for a Ren bot.
     */
    public Ren() {
        tasks = new TaskList(new Storage("data/list.txt"));
    }

    /**
     * Runs the Ren program until the user exits with the 'bye' command.
     */
    public void run() {
        ui.greet();
        String cmd = "";
        while (!cmd.equals("bye")) {
            cmd = ui.readCommand();

            try {
                ui.speak(Parser.parseCommand(cmd, this.tasks));
            } catch (RenException e) {
                ui.speak(e.toString());
            }
        }
    }

    /**
     * Driver method for Ren.
     */
    public static void main(String[] args) {
        new Ren().run();
    }

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
