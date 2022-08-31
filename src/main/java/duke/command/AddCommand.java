package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/** Represents the command to add task that inherits from Command. */
public class AddCommand extends Command {
    private final String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String command;
        String message = "";
        command = userInput.split(" ")[0];

        Task task;
        if (userInput.split(" ", 2).length == 1) {
            if (command.equals(Duke.Keyword.TODO.getKeyword())) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else if (command.equals(Duke.Keyword.EVENT.getKeyword())) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            } else if (command.equals(Duke.Keyword.DEADLINE.getKeyword())) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        String taskDetails = userInput.split(" ", 2)[1];

        if (command.equals(Duke.Keyword.TODO.getKeyword())) {
            task = new Todo(taskDetails);
            taskList.addTask(task);
        } else if (command.equals(Duke.Keyword.EVENT.getKeyword())) {
            task = new Event(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
            taskList.addTask(task);
        } else if (command.equals(Duke.Keyword.DEADLINE.getKeyword())) {
            task = new Deadline(taskDetails.split("/")[0], taskDetails.split("/")[1].split(" ", 2)[1]);
            taskList.addTask(task);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        message += "Got it. I've added this task:\n";
        message += "\t" + task.toString() + "\n";
        message += "Now you have " + taskList.getTaskListSize() + " tasks in the list.\n";

        ui.printMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
