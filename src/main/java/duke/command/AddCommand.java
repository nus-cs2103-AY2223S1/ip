package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

public class AddCommand extends Command {

    public AddCommand(String info) {
        super(info);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task newTask;
        String[] splitInfo = getInfo().split(" ", 2);
        String[] splitDate;

        switch (splitInfo[0]) {
        case "todo":
            if (splitInfo[1].length() == 0) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            newTask = new Todo(TaskType.TODO, splitInfo[1], false);
            break;
        case "deadline":
            splitDate = splitInfo[1].split(" /by ", 2);
            if (splitDate[0].length() == 0) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (splitDate[1].length() == 0) {
                throw new DukeException("The date of a deadline cannot be empty.");
            }
            newTask = new Deadline(TaskType.DEADLINE, splitDate[0],
                    false, splitDate[1]);
            break;
        case "event":
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
            ui.showUnknownMessage();
            return;
        }

        taskList.add(newTask);
        ui.showAddMessage(newTask, taskList.getSize());
    }
}

