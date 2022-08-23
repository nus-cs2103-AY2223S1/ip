import java.io.IOException;

public abstract class Command {

    /**
     * Runs the command based on the command type
     * @param taskList TaskList to update tasks data
     * @param storage Storage to save updates to TaskList
     * @throws DukeException Index out of bounds / Improper syntax errors
     * @throws IOException Input Errors
     */
    public abstract void run(TaskList taskList, Storage storage) throws DukeException, IOException;
}
