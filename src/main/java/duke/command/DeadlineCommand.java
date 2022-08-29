package duke.command;

import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand implements Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a deadline task. Parameters: DESCRIPTION DATETIME. Example: " + COMMAND_WORD
            + " return book /by 2022-08-22";

    private String description;
    private String dateTime;

    public DeadlineCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Deadline deadline = new Deadline(description, dateTime);
        taskList.add(deadline);
        ui.printMessage(ui.wrapMessage("Got it. I've added this task:", deadline.toString(), taskList));
    }
}
