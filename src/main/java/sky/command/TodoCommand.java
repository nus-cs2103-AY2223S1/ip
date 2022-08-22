package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.Ui;
import sky.exception.TextNoMeaningException;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TextNoMeaningException {
        try {
            String taskToDo = this.fullCommand.substring(5);
            Task task = new ToDo(taskToDo);
            taskList.addTask(task);
            // Add task into data file
            storage.append(task.toString());
            String s = "  Got it. I've added this task: \n" +
                    "    " + task +
                    "\n  Now you have " + taskList.size() +
                    (taskList.size() <= 1 ? " task in the list.": " tasks in the list.");
            ui.displayText(s);
            return s;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Are you new? Specify a task after typing todo.");
        }
        throw new TextNoMeaningException("  Error executing TodoCommand.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}