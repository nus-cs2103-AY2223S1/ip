package sky.command;

import java.io.IOException;

import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.storage.History;
import sky.storage.Storage;
import sky.task.Task;
import sky.task.ToDo;

/**
 * The TodoCommand class deals with adding a todo task into taskList.
 */
public class TodoCommand extends Command {
    private String fullCommand;

    public TodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, History history)
            throws TextNoMeaningException, IOException {
        try {
            String taskToDo = this.fullCommand.substring(5);
            Task task = new ToDo(taskToDo);
            taskList.addTask(task);
            // Add task into data file
            history.addHistoryInTime(taskList);
            storage.append(task.toString());
            return generateResponse(task, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("Are you new? Specify a task after typing todo.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String generateResponse(Task task, TaskList taskList) {
        return "Got it. I've added this task: \n"
                + "    " + task
                + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() <= 1 ? " task in the list." : " tasks in the list.");
    }
}
