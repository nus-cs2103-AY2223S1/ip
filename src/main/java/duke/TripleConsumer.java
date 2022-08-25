package duke;

public interface TripleConsumer {
    void execute(TaskList tasks, Ui ui, Storage storage);
}
