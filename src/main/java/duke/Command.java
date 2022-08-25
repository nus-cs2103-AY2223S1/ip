package duke;

public class Command {

    protected TripleConsumer function;
    protected boolean isExit;

    public Command(TripleConsumer function, boolean isExit) {
        this.function = function;
        this.isExit = isExit;
    }

    public Command(TripleConsumer function) {
        this(function, false);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        function.execute(tasks, ui, storage);
    }

    public boolean isExit() {
        return this.isExit;
    }
}
