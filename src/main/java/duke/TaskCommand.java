package duke;

import java.util.ArrayList;

public abstract class TaskCommand extends Command {
    @Override
    String execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        try {
            handleEmptyTask(fullCommand);
            return addTaskToList(fullCommand, listOfTasks);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    abstract void handleEmptyTask(String fullCommand) throws DukeException;
    abstract String addTaskToList(String fullCommand, ArrayList<Task> listOfTasks);
    abstract boolean isTaskEmpty(String fullCommand);
}
