package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {

    protected TripleConsumer function;
    protected boolean isExit;

    /**
     * A command class to keep track of commands.
     *
     * @param function The consumer object that holds the instructions to be executed.
     * @param isExit Whether the command is "bye"
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
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        function.execute(tasks, ui, storage);
    }

    public boolean isExit() {
        return this.isExit;
    }
}
