package duke.command;

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
    public String execute(TaskList taskList) {
        Deadline deadline = new Deadline(description, dateTime);
        return taskList.add(deadline);
    }
}
