package duke;

import java.util.ArrayList;

public class TodoCommand extends TaskCommand {
    @Override
    String addTaskToList(String fullCommand, ArrayList<Task> listOfTasks) {
        assert fullCommand.length() >= 4; 
        String name = fullCommand.substring(5);
        TaskList taskList = new TaskList(listOfTasks);
        Task t = new ToDo(name, false);
        return taskList.addToList(t);
    }

    @Override
    boolean isTaskEmpty(String fullCommand) {
        return fullCommand.length() == 4;
    }

    @Override
    void handleEmptyTask(String fullCommand) throws DukeTodoEmptyException {
        if (isTaskEmpty(fullCommand)) {
            throw new DukeTodoEmptyException();
        }
        assert fullCommand.length() > 4; 
    }
}
