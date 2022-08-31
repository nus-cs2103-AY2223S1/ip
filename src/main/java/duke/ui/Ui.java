package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.tasks.Task;

/**
 * This class encapsulates the user interface
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs a new Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Greets the user whenever the application starts
     */
    public String printWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you? ^_^";
    }

    /**
     * Exits when the user chooses to stop the application
     */
    public String printExit() {
        this.scanner.close();
        return "Bye. Hope to see you again soon :D";
    }

    /**
     * Prints the task that is just added
     * @param addedTask The task that is just added
     * @param numOfTasks The number of tasks in the list
     */
    public String printAddTask(Task addedTask, int numOfTasks) {
        String header = "Got it! I have added this task:\n\n" + addedTask;
        String tasks = String.format("\n\nNow you have %d %s in the list!", numOfTasks,
                numOfTasks < 2 ? "task" : "tasks");
        return header + tasks;
    }

    /**
     * Prints the task that is just deleted
     * @param deletedTask The deleted task
     * @param numOfTasks The number of tasks remaining
     */
    public String printDeleteTask(Task deletedTask, int numOfTasks) {
        String msg = String.format("Noted, I have removed this task:\n\n%s", deletedTask);
        String tasks = String.format("\n\nNow you have %d %s in the list!", numOfTasks,
                numOfTasks < 2 ? "task" : "tasks");
        return msg + tasks;
    }

    /**
     * Prints the task that is marked as completed
     * @param task The task that is marked as completed
     */
    public String printMarkTask(Task task) {
        String msg = String.format("Nice! I have marked this task as done:\n\n%s",
                task);
        return msg;
    }

    /**
     * Prints the task that is marked as not completed
     * @param task The task that is marked as not completed
     */
    public String printUnmarkTask(Task task) {
        String msg = String.format("Okay! I have marked this task as not done:\n\n%s",
                task);
        return msg;
    }

    /**
     * Prints the exception's message
     * @param exception The exception thrown and caught
     */
    public String printException(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Gets the input provided by the user
     * @return The input provided
     */
    public String input() {
        return this.scanner.nextLine();
    }

    /**
     * Notifies users about invalid commands
     * @throws DukeException If the command is invalid
     */
    public String printInvalid() throws DukeException {
        throw new DukeException("I don't know what this means :(");
    }

    /**
     * Prints the list of tasks
     * @param list The list of tasks
     */
    public String printList(ArrayList<Task> list) {
        int len = list.size();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String task = String.format("\n%d. %s", i + 1, list.get(i));
            stringBuilder.append(task);
        }

        if (len == 0) {
            return "There are no tasks added!";
        }

        String header = String.format("Here %s the %s in your list :D\n", len == 1 ? "is" : "are",
                len == 1 ? "task" : "tasks");
        return header + stringBuilder;
    }

    /**
     * Prints the tasks after executing the tasks command
     * @param list The list of tasks to be printed
     * @param date Date of the tasks
     * @return A string consisting of the tasks
     */
    public String printTasks(ArrayList<Task> list, String date) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Task t = list.get(i);
            if (t.getTaskType().equals("D") || t.getTaskType().equals("E")) {
                String formatted = String.format("\n%d. %s", i + 1, t);
                stringBuilder.append(formatted);
            }
        }

        if (len == 0) {
            return String.format("No tasks on %s!", parsedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        } else {
            String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
            String header = String.format("Your %s for %s include:\n", len == 1 ? "task" : "tasks", formattedDate);
            return header + stringBuilder;
        }
    }

    /**
     * Prints the tasks returned after executing the find command
     * @param list List of tasks to print
     * @return A string of the tasks
     */
    public String printFind(ArrayList<Task> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 1;
        for (Task task: list) {
            String item = String.format("\n%d. %s", count, task);
            stringBuilder.append(item);
            count++;
        }

        if (count == 1) {
            return "No matching tasks!";
        }

        String header = String.format("Here %s the matching %s in your list:\n", count == 2 ? "is" : "are",
                count == 2 ? "task" : "tasks");

        return header + stringBuilder;
    }

    /**
     * Prints the input
     * @param input A string to be printed
     * @return
     */
    public String print(String input) {
        return input;
    }
}
