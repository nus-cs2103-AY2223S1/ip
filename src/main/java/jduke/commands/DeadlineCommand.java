package jduke.commands;

import jduke.task.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String FORMAT = "deadline <description> /by <dd/mm/yyyy> <hhmm | optional>";

    private final String description;
    private final String timing;
    public DeadlineCommand(String params) {
        String[] deadlineParams = params.split(" /by ", 2);
        this.description = deadlineParams[0];
        this.timing = deadlineParams[1];
    }

    @Override
    public String execute() {
        return this.tasklist.addDeadline(this.description, this.timing);
    }
}
