package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    public DeadlineCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    @Override
    public String action() throws DukeException {
        if (this.inputArr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] descriptionDate = this.inputArr[1].split(" /by ", 2);
        if (descriptionDate.length < 2) {
            throw new DukeException("The date of a deadline cannot be empty.");
        }
        String task = descriptionDate[0];
        String date = descriptionDate[1];
        Deadline event = new Deadline(task, date);
        this.taskList.addTask(event);
        return ("Got it. I've added this task: " + "\n"
                + event + "\n"
                + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
    }
}