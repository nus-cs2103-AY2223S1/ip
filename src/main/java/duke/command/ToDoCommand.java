package duke.command;

import duke.logic.TaskList;
import duke.exception.IllegalDescriptionException;
import duke.task.ToDo;

/**
 * ToDoCommand is a command for Duke to remember a todo.
 *
 * @author totsukatomofumi
 */
public class ToDoCommand extends Command {
    /** Task list the command has to add a todo to. */
    private TaskList taskList;

    /** Description of the todo. */
    private String description;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param description the description of the todo.
     * @throws IllegalDescriptionException If no description is specified, including just whitespaces.
     */
    public ToDoCommand(TaskList taskList, String description) throws IllegalDescriptionException {
        this.taskList = taskList;
        //double check
        if (description.length() > 0) {
            this.description = description;
        } else {
            throw new IllegalDescriptionException("No description specified.");
        }
    }

    /**
     * Adds new todo to the list of tasks.
     */
    @Override
    public void run() {
        taskList.add(new ToDo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }
}
