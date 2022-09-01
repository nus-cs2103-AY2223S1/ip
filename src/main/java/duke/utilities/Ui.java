package duke.utilities;

import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that provides a simple user interface for the Duke system.
 */
public class Ui {

    private static String LINE = "--------------------";

    private Scanner s;

    /**
     * Creates a new UI for user.
     */
    public Ui() {
        this.s = new Scanner(System.in);
    }

    /**
     * Greets the user
     * @return Greets the user
     */
    public String printDukeOpening() {
        String name = "Turtle";
        String output = "Hello I am " + name + "!\n" + "What can I do for you?";
        System.out.println(output);
        System.out.println(LINE);
        return output;
    }

    /**
     * Ends the user interaction.
     * @return Ends the user interaction.
     */
    public String printDukeClosing() {
        String output = "Goodbye!";
        System.out.println(output);
        System.out.println(LINE);
        return output;
    }

    /**
     * Tells user that there is error.
     * @return Tells user that there is error.
     */
    public String printDukeException(DukeException e) {
        String output = "OOPS!!!" + e.getMessage() + "\n";
        System.out.println(output);
        System.out.println(LINE);
        return output;
    }

    /**
     * Tells user that there is IO error.
     * @return Tells user that there is IO error.
     */
    public String printIoException(IOException e) {
        String output = "OOPS!!! " + e.getMessage();
        System.out.println(output);
        System.out.println(LINE);
        return output;
    }

    /**
     * Tells user that date format is wrong.
     * @return Tells user that date format is wrong.
     */
    public String printDateTimeParseException() {
        String output = "All dates must be in the format (yyyy-MM-dd HH:mm)!";
        System.out.println(output);
        System.out.println(LINE);
        return output;
    }

    /**
     * Displays all the tasks.
     * @return Displays all the tasks.
     */
    public String printTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        System.out.println(output.toString());
        System.out.println(LINE);
        return output.toString();
    }

    /**
     * Tells user task is added.
     * @return Tells user task is added.
     */
    public String printAddTask(Task task, TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n");
        output.append(task.toString() + "\n");
        output.append("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.\n");
        System.out.println(output.toString());
        System.out.println(LINE);
        return output.toString();
    }

    /**
     * Tells user task status have changed.
     * @return Tells user task status have changed.
     */
    public String printChangeTaskStatus(Task task, boolean isDone) {
        StringBuilder output = new StringBuilder();
        if (isDone) {
            output.append("Nice! I've marked this task as done:\n");
            output.append("  " + task.toString() );
        } else {
            output.append("OK, I've marked this task as not done yet:\n");
            output.append("  " + task.toString() );
        }
        System.out.println(output.toString());
        System.out.println(LINE);
        return output.toString();
    }

    /**
     * Tells user that tasks is deleted
     * @return Tells user that tasks is deleted
     */
    public String printDeleteTask(Task task, TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Noted. I've removed this task:\n");
        output.append(task.toString() + "\n");
        output.append("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.\n");
        System.out.println(output.toString());
        System.out.println(LINE);
        return output.toString();
    }

    /**
     * Displays the tasks with the user input.
     * @return Displays the tasks with the user input.
     */
    public String printMatchTask(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            output.append((i + 1) + "." + t.toString() + "\n");
        }
        System.out.println(output.toString());
        System.out.println(LINE);
        return (output.toString());
    }

    /**
     * Tells user app is loading.
     */
    public void showLoadingError() { System.out.println("Loading ...");}

    /**
     * Closes scanner.
     * */
    public void closeScanner() {
        this.s.close();
    }

    /**
     * Goes to next line.
     * @return next line
     */
    public String nextLine() {
        return this.s.nextLine();
    }
}
