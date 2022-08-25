package duke.task;

import duke.exception.DukeCommandFormatException;
import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeTaskDateTimeMissingException;
import duke.exception.DukeTaskTitleMissingException;
import duke.util.CommandParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected static final String OUTPUT_DATE_TIME_FORMAT = "yyyy/MM/dd T HH:mm:ss";
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT);

    private static final String TODO_TASK_COMMAND_STRING = "todo";
    private static final String EVENT_TASK_COMMAND_STRING = "event";
    private static final String DEADLINE_TASK_COMMAND_STRING = "deadline";

    // TODO: Try to combine the string literals in line with duke.task.TaskType

    public static Task createFromCommand(String command)
            throws DukeCommandFormatException, DukeTaskTitleMissingException, DukeTaskDateTimeMissingException,
            DukeDateTimeFormatException {
        String firstWord = CommandParser.getFirstWord(command);

        String taskTitle = "";
        LocalDateTime dateTime;
        switch (firstWord) {
        case (TODO_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(command);
            return new ToDoTask(taskTitle);

        case (EVENT_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(command);
            dateTime = CommandParser.getAtDate(command);
            return new EventTask(taskTitle, dateTime);

        case (DEADLINE_TASK_COMMAND_STRING):
            taskTitle = CommandParser.getTaskTitle(command);
            dateTime = CommandParser.getByDate(command);
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
}
