package duke;

public class Command {

    protected TripleConsumer function;
    protected boolean isExit;

    /**
     * A command class to keep track of commands.
     *
     * @param function
     * @param isExit
     */
    public Command(TripleConsumer function, boolean isExit) {
        this.function = function;
        this.isExit = isExit;
    }

    public Command(TripleConsumer function) {
        this(function, false);
    }

    /**
     * Execute the command
     *
     * @param tasks
     * @param ui
     * @param storage
     */

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        function.execute(tasks, ui, storage);
    }

    public boolean isExit() {
        return this.isExit;
    }
}
