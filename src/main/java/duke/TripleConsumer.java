package duke;

public interface TripleConsumer {
    /**
     * Interface for our command.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    void execute(TaskList tasks, Ui ui, Storage storage);
}
