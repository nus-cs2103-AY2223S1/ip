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
     */
    public AddCommand(int taskType, String taskDetails) {
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    /**
     * Creates the corresponding task based on the taskType in AddCommand and adds task
     * to the TaskList. The task list is then stored on a file under data/tasks.txt
     *
     * @param tasks List to add and keep track of the tasks
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing and reading of tasks from a txt file
     * @throws IOException if the writeToFile method fails while storing the TaskList on a local txt file
     */
    @Override
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
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
        storage.writeToFile(tasks);
    }

    /**
     * Takes in dates in the format YYYY-MM-DD and transform them into MMM d yyyy.
     * (2019-05-05 to May 5 2019)
     *
     * @param dateInput String representation of the date in YYYY-MM-DD format.
     * @return String representation of the same date in MMM d yyyy format.
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
     * Checks if it is the exit command.
     *
     * @return false since an AddCommand does not terminate the ChatBot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * String representation of successfully executing the AddCommand.
     *
     * @return String to notify user that task has been successfully added
     */
    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Got it. I have added this task:";
    }

    /**
     * Overridden equals method to check if 2 instances of AddCommand are the same
     *
     * @param o Object to be tested against an instance of AddCommand
     * @return true if the Object is an instance of AddCommand, contains the same
     *     taskType and same taskDetails
     */
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
