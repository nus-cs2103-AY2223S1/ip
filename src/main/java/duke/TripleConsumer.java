package duke;

public interface TripleConsumer {
    /**
     * Interface for our command.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    void execute(TaskList tasks, Ui ui, Storage storage);
}
