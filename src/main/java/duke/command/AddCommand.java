package duke.command;

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
     * Execute the add command. Add task into the TaskList.
     *
     * @param ui Ui used to show add messages
     * @param taskList TaskList to execute add operation
     * @throws DukeException If there is invalid commands or arguments
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task newTask;
        String[] splitInfo = getInfo().split(" ", 2);
        String[] splitDate;

        try {
            switch (splitInfo[0]) {
            case "todo":
                if (splitInfo.length < 2 || splitInfo[1].length() == 0) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }

                newTask = new Todo(TaskType.TODO, splitInfo[1], false);
                break;
            case "deadline":
                if (splitInfo.length < 2 || splitInfo[1].length() == 0) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }

                splitDate = splitInfo[1].split(" /by ", 2);

                if (splitDate.length < 2 || splitDate[0].length() == 0) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else if (splitDate[1].length() == 0) {
                    throw new DukeException("The date of a deadline cannot be empty.");
                }

                newTask = new Deadline(TaskType.DEADLINE, splitDate[0],
                        false, splitDate[1]);
                break;
            case "event":
                if (splitInfo.length < 2 || splitInfo[1].length() == 0) {
                    throw new DukeException("The description of an event cannot be empty.");
                }

                splitDate = splitInfo[1].split(" /at ", 2);

                if (splitDate[0].length() == 0) {
                    throw new DukeException("The description of an event cannot be empty.");
                } else if (splitDate[1].length() == 0) {
                    throw new DukeException("The date of an event cannot be empty.");
                }

                newTask = new Event(TaskType.EVENT, splitDate[0],
                        false, splitDate[1]);
                break;
            default:
                return ui.showUnknownMessage();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }

        taskList.addTask(newTask);
        return ui.showAddMessage(newTask, taskList.getSize());
    }
}

