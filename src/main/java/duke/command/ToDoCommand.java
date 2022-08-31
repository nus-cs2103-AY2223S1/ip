package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Command to add a todo task to the task list.
 */
public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private String description;

    /**
     * Constructor for the ToDoCommand Object.
     * @param description
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a todo task and add it into the task list and return a message
     * that the todo task has been added.
     * @param task The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The message that the event has been added and the number of current tasks.
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        ToDo currToDo = new ToDo(this.description);
        task.addTask(currToDo);
        return "Got it. I've added this task:\n " + currToDo.taskInfo()
                + "\nNow you have"  + task.getTaskSize() + " tasks in the list.";
    }

}
