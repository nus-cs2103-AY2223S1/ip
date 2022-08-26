package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command for adding tasks to the task lists. It comprises of 3 types
 * of task, namely todo, deadline and event.
 */
public class AddCommand extends Command {
    private int taskType;
    private String taskDetails;

    /**
     * Insert Javadocs
     */
    public AddCommand(int taskType, String taskDetails) {
        // 0: TODO Task
        // 1: DEADLINE Task
        // 2: EVENT Task
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    /**
     * Insert Javadocs
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (this.taskType == 0) {
            tasks.add(new Todo(this.taskDetails, false));
        } else if (this.taskType == 1) {
            String[] infoArray = this.taskDetails.split(" /by ", 2);
            tasks.add(new Deadline(infoArray[0], formatDate(infoArray[1]), false));
        } else {
            String[] infoArray = this.taskDetails.split(" /at ", 2);
            tasks.add(new Event(infoArray[0], formatDate(infoArray[1]), false));
        }
        String taskDescription = "  " + tasks.getTask(tasks.size() - 1).toString();
        System.out.println(this);
        System.out.println(taskDescription);
        storage.writeToFile(tasks);
    }

    // Format the dates from YYYY-MM_DD to MMM d yyyy
    /**
     * Insert Javadocs
     */
    public String formatDate(String dateInput) {
        try {
            LocalDate d1 = LocalDate.parse(dateInput);
            return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) { //
            return dateInput;
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Got it. I have added this task:";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof AddCommand) {
            AddCommand t = (AddCommand) o;
            if (t.taskType == this.taskType) {
                return Objects.equals(t.taskDetails, this.taskDetails);
            }
        }

        return false;
    }
}
