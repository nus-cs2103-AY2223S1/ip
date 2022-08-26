package duke;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    abstract void execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage) throws IOException, DukeEventEmptyException, DukeDeadlineEmptyException, DukeTodoEmptyException;
}
