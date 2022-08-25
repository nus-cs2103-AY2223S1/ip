import java.io.FileWriter;
import java.io.IOException;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        System.out.println("Bye. Hope to see you again soon!");
        storage.writeAll(tasks);
    }
}
