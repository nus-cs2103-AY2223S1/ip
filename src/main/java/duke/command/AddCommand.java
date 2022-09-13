package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Command to add task.
 */
public class AddCommand extends Command {

    /**
     * Constructor for AddCommand.
     *
     * @param info Type of command
     */
    public AddCommand(String info) {
        super(info);
    }

    /**
     * Executes the add command. Add task into the TaskList.
     *
     * @param ui Ui used to show add messages
     * @param taskList TaskList to execute add operation
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        assert(ui != null && taskList != null);
        Task newTask;
        String[] commands = getInfo().split(" ", 2);
        switch (commands[0]) {
        case "todo":
            newTask = getTodoTask(commands);
            break;
        case "deadline":
            newTask = getDeadlineTask(commands);
            break;
        case "event":
            newTask = getEventTask(commands);
            break;
        default:
            return new InvalidCommand(commands).execute(ui, taskList, storage);
        }
        taskList.addTask(newTask);
        storage.writeFile(taskList);
        return ui.showAddMessage(newTask, taskList.getSize());
    }

    /**
     * @param commands Information of todo
     * @return A new todo task
     * @throws DukeException If there is invalid commands or arguments
     */
    private Task getTodoTask(String[] commands) throws DukeException {
        Task newTask;
        if (commands.length < 2 || commands[1].length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        newTask = new Todo(TaskType.TODO, commands[1], false);
        return newTask;
    }

    /**
     * @param commands Information of deadline
     * @return A new deadline task
     * @throws DukeException If there is invalid commands or arguments
     */
    private Task getDeadlineTask(String[] commands) throws DukeException {
        String[] splitDate;
        Task newTask;
        if (commands.length < 2 || commands[1].length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        splitDate = commands[1].split(" /by ", 2);
        if (splitDate.length < 2 || splitDate[0].length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (splitDate.length < 2 | splitDate[1].length() == 0) {
            throw new DukeException("The date of a deadline cannot be empty.");
        }

        newTask = new Deadline(TaskType.DEADLINE, splitDate[0],
                false, splitDate[1]);
        return newTask;
    }

    /**
     * @param commands Information of event
     * @return A new event task
     * @throws DukeException If there is invalid commands or arguments
     */
    private Task getEventTask(String[] commands) throws DukeException {
        String[] splitDate;
        Task newTask;
        if (commands.length < 2 || commands[1].length() == 0) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        splitDate = commands[1].split(" /at ", 2);
        if (splitDate[0].length() == 0) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (splitDate.length < 2 | splitDate[1].length() == 0) {
            throw new DukeException("The date of an event cannot be empty.");
        }

        newTask = new Event(TaskType.EVENT, splitDate[0],
                false, splitDate[1]);
        return newTask;
    }
}

