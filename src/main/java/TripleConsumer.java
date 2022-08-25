import java.util.ArrayList;

public interface TripleConsumer {
    void execute(TaskList tasks, Ui ui, Storage storage);
}
