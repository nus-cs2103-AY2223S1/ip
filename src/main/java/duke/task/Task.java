package duke.task;

import duke.Duke;
import duke.exception.DukeCommandFormatException;
import duke.exception.DukeTaskDateTimeMissingException;
import duke.exception.DukeTaskTitleMissingException;
import duke.util.CommandParser;

public abstract class Task {

    private static final String TODO_TASK_COMMAND_STRING = "todo";
    private static final String EVENT_TASK_COMMAND_STRING = "event";
    private static final String DEADLINE_TASK_COMMAND_STRING = "deadline";

    // TODO: Try to combine the string literals in line with duke.task.TaskType

    public static Task createFromCommand(String input)
            throws DukeCommandFormatException, DukeTaskTitleMissingException, DukeTaskDateTimeMissingException {
        String firstWord = CommandParser.getFirstWord(input);

        String taskTitle = "";
        String dateTime = "";
        switch (firstWord) {
        case (TODO_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(input);
            return new ToDoTask(taskTitle);

        case (EVENT_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(input);
            dateTime = CommandParser.getAtDate(input);
            return new EventTask(taskTitle, dateTime);

        case (DEADLINE_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(input);
            dateTime = CommandParser.getByDate(input);
            return new DeadlineTask(taskTitle, dateTime);

        default:
            return null;
        }
    }

    protected String taskTitle;
    protected boolean done;
    protected TaskType taskType;

    protected Task(String taskTitle, TaskType taskType) {
        this(taskTitle, false, taskType);
    }

    protected Task(String taskTitle, boolean done, TaskType taskType) {
        this.taskTitle = taskTitle;
        this.done = done;
        this.taskType = taskType;
    }

    public void markDone() {
        done = true;
    }

    public void markUndone() {
        done = false;
    }

    protected String getStringRepresentation(String label, String displayedText) {
        return "["
                + (done ? "X" : " ")
                + "] "
                + "["
                + label
                + "] "
                + displayedText;
    }

    public abstract String getFileRepresentation();

    protected String getFileRepresentation(String label) {
        return getFileRepresentation(label, null);
    }

    protected String getFileRepresentation(String label, String dateTime) {
        String output = label
                + " "
                + Duke.FILE_WRITING_DELIMITER
                + " "
                + (done ? "1" : "0")
                + " "
                + Duke.FILE_WRITING_DELIMITER
                + " "
                + taskTitle;
        if (dateTime != null) {
            output += " "
                    + Duke.FILE_WRITING_DELIMITER
                    + " "
                    + dateTime;
        }
        return output;
    }
}
