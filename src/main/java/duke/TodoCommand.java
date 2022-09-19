package duke;

import java.util.ArrayList;
/**
 * Represents a command which adds a todo to the list of task.
 */
public class TodoCommand extends TaskCommand {
    /**
     * Adds the task to the list.
     * @param fullCommand Full command that the user inputs which adds a todo task.
     * @param listOfTasks List that stores the tasks.
     * @return A string that will be outputted to the screen when user add a task to the list.
     */
    @Override
    String addTaskToList(String fullCommand, ArrayList<Task> listOfTasks) {
        assert fullCommand.length() >= 4; 
        String name = fullCommand.substring(5);
        TaskList taskList = new TaskList(listOfTasks);
        Task t = new ToDo(name, false);
        return taskList.addToList(t);
    }

    /**
     * Checks if the task is empty.
     * @param fullCommand Full command that the user inputs.
     * @return Status of whether a task is empty.
     */
    @Override
    boolean isTaskEmpty(String fullCommand) {
        return fullCommand.length() == 4;
    }

    /**
     * Handles an exception when user input an empty task.
     * @param fullCommand Full command that the user inputs.
     * @throws DukeTodoEmptyException
     */
    @Override
    void handleEmptyTask(String fullCommand) throws DukeTodoEmptyException {
        if (isTaskEmpty(fullCommand)) {
            throw new DukeTodoEmptyException();
        }
        assert fullCommand.length() > 4; 
    }
}
