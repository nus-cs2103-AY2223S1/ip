package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Add Tasks into TaskList depending on the relevant command given.
 */
public class AddCommand extends Command {
    public static final boolean IS_EXIT = false;
    private final String fullCommand;

    /**
     * Constructs a AddCommand instance with the provided whole fullCommand input by the user.
     *
     * @param fullCommand fullCommand to be split into command and the task provided.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Split the fullCommand into command, task description, date and time,
     * then passes the variables to create corresponding Command.
     *
     * @param taskList the TaskList to be added with new Task.
     * @param ui unused for AddCommand.
     * @param storage the Storage to write new Task into file.
     * @throws DukeException if the task description is an empty String or null or .
     * the date or time given is in the wrong format.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] parts = fullCommand.split(" ", 0);
            String command = parts[0];
            if (parts.length == 1) {
                throw new DukeException("OOPS!!! The description of a task cannot be empty.\n");
            }
            String taskName = "";
            String dateString = "";
            String timeString = "";
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].charAt(0) != '/') {
                    taskName += parts[i] + " ";
                } else {
                    dateString = parts[i + 1];
                    timeString = parts[i + 2];
                    break;
                }
            }

            LocalDate date = null;
            if (!dateString.equals("")) {
                date = validateDateString(dateString);
            }

            LocalTime time = null;
            if (!timeString.equals("")) {
                time = validateTimeString(timeString);
            }

            Task task = null;
            switch (command) {
            case "todo":
                task = new ToDo(taskName, date, time);
                break;
            case "deadline":
                task = new Deadline(taskName, date, time);
                break;
            case "event":
                task = new Event(taskName, date, time);
                break;
            }
            taskList.addTask(task);

            String list = "";
            for (Task t : taskList.getList()) {
                list += t.toString();
            }
            storage.write(list);

            assert task != null : "Task is null.";
            return "Got it. I've added this task:\n" + ui.beautyWrapTask(task)
                    + "\nNow you have " + taskList.getSize() + " tasks in the list.\n";

        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date & time format. Please follow the format of date "
                    + "as \"YYYY-MM-DD\" and time as \"HHMM\".");
        }
    }

    /**
     * Returns false as Add is not a terminating Command.
     *
     * @return false.
     */
    public boolean isExit() {
        return this.IS_EXIT;
    }

    private LocalTime validateTimeString(String timeString) {
        //desired date format "1800"
        String validatedTimeString = timeString.substring(0, 2) + ":" + timeString.substring(2, 4) + ":" + "00";
        LocalTime time = LocalTime.parse(validatedTimeString);
        return time;
    }

    private LocalDate validateDateString(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }
}
