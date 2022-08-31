package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.task.Task;
import sky.task.ToDo;

import java.io.IOException;

/**
 * The TodoCommand class deals with adding a todo task into taskList.
 */
public class TodoCommand extends Command {
    private String fullCommand;

    public TodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TextNoMeaningException, IOException {
        try {
            String taskToDo = this.fullCommand.substring(5);
            Task task = new ToDo(taskToDo);
            taskList.addTask(task);
            // Add task into data file
            storage.append(task.toString());
            String s = "Got it. I've added this task: \n" +
                    "    " + task +
                    "\nNow you have " + taskList.getSize() +
                    (taskList.getSize() <= 1 ? " task in the list.": " tasks in the list.");
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("Are you new? Specify a task after typing todo.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}