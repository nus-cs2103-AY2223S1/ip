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
 * of task to be created, namely todo, deadline and event.
 */
public class AddCommand extends Command {
    private int taskType;
    private String taskDetails;

    /**
     * Constructor to create an instance of AddCommand.
     *
     * @param taskType Integer value representing the type of task, 0-TODO Task,
     *                 1-DEADLINE Task, 2-EVENT Task
     * @param taskDetails String representation of the task description
     * @return instance of AddCommand
     */
    public AddCommand(int taskType, String taskDetails) {
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    /**
     * Create the corresponding task based on the taskType in AddCommand and adds task
     * to the TaskList. The task list is then stored on a file under data/tasks.txt
     *
     * @param tasks a list to add and keep track of the tasks
     * @param ui an instance of ui that handles the interaction with user inputs
     * @param storage a storage that handles the writing and reading of tasks from a txt file
     * @throws IOException if the writeToFile method fails while storing the TaskList on a local txt file
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

    /**
     * Takes in dates in the format YYYY-MM-DD and transform them into MMM d yyyy.
     * (2019-05-05 to May 5 2019)
     *
     * @param dateInput a String representation of the date in YYYY-MM-DD format.
     * @return a string representation of the same date in MMM d yyyy format.
     */
    public String formatDate(String dateInput) {
        try {
            LocalDate d1 = LocalDate.parse(dateInput);
            return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) { //
            return dateInput;
        }
    }

    /**
     * Check if it is the exit command.
     *
     * @return false since an AddCommand does not terminate the ChatBot.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * A string
     * @return
     */
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
