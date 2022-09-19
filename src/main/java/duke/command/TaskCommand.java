package duke.command;

import duke.Storage;
import duke.task.Deadline;
import duke.task.DoAfter;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Class which manages Tasks.
 */
public class TaskCommand extends Command {

    private String taskType;
    private String description;
    private Task task;

    /**
     * Creates an instance of a task command.
     * @param taskType type of task
     * @param description description of task
     */
    public TaskCommand(String taskType, String description) {
        if (taskType == "todo") {
            this.taskType = taskType;
            this.description = description;
            task = new Todo(description);
        } else if (taskType == "deadline") {
            this.taskType = taskType;
            this.description = description;
            task = new Deadline(description);
        } else if (taskType == "event") {
            this.taskType = taskType;
            this.description = description;
            task = new Event(description);
        } else if (taskType == "doafter") {
            this.taskType = taskType;
            this.description = description;
            task = new DoAfter(description);
        }
        assert false : "Wrong task type";
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (description.equals("") || description.split(" ").length == 0) {
            return "You've given me an invalid task description!";
        } else {
            try {
                tasks.add(task);
                storage.add(taskType, description);
                String taskS = "tasks";
                if (tasks.listSize() == 1) {
                    taskS = "task";
                }
                return "I've added this task to the list:\n  " + task.getTask()
                        + "\nYou have a total of " + tasks.listSize() + " " + taskS + " in the list.";
            } catch (NullPointerException e) {
                tasks.delete(task);
                storage.delete(taskType, description);
                return "You've given me an invalid task description!";
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
