package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.EmptyTodoException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * A class to represent the todo command.
 */
public class TodoCommand extends Command {
    public static final String COMMAND = "todo";
    private String desc = "";

    /**
     * Constructs a new TodoCommand instance.
     *
     * @param desc the description of the command.
     * @throws DukeException If desc is an empty String.
     */
    public TodoCommand(String desc) throws DukeException {
        //No Description given
        if (desc.equals("")) {
            throw new EmptyTodoException();
        }
        this.desc = desc;
    }

    /**
     * Adds a new ToDo instance to the list of tasks
     *     and prints the new ToDo instance.
     *
     * @param taskList the list of tasks.
     * @return the response message.
     * @throws DukeException If there is a problem saving the tasks.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) throws DukeException {
        ToDo task = new ToDo(desc);
        taskList.add(task);
        Storage.saveTasks(taskList);
        return Ui.getTaskAddedMessage(task, taskList.getSize());
    }
}
