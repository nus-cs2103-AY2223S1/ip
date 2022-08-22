package jduke.commands;

import jduke.task.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String FORMAT = "deadline <description> /by <dd/mm/yyyy> <hhmm | optional>";

    private final Deadline deadline;
    public DeadlineCommand(String params) {
        String[] deadlineParams = params.split(" /by ", 2);
        this.deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
    }

    @Override
    public String execute() {
        this.tasklist.addDeadline(deadline);
        return String.format("|  added task:%n|    %s%n", this.deadline);
    }
}
