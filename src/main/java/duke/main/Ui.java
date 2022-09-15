package duke.main;

import duke.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 * Print the respective message.
 */
public class Ui {
    /**
     * Print intro message.
     *
     * @return A String containing the message.
     */
    public String printIntro() {
        String msg = "Hello from Duke\n" + "What can I do for you?";
        printLine();
        System.out.println(msg);
        printLine();
        return msg;
    }

    /**
     * Print exit message.
     *
     * @return A String containing the message.
     */
    public String printExit() {
        String msg = "Bye. Hope to see you again soon!";
        printLine();
        System.out.println(msg);
        printLine();
        return msg;
    }

    /**
     * Print list of tasks.
     *
     * @param tasks Existing tasks.
     * @return A String containing the message.
     */
    public String printListOfTasks(TaskList tasks) {
        printLine();
        int count = 1;
        String msg = "Here are the tasks in your list:\n";
        for (Task t : tasks.getTasks()) {
            msg += count++ + "." + t.toString() + "\n";
        }
        msg += "Now you have " + (count - 1) + " tasks in the list.";
        System.out.println(msg);
        printLine();
        return msg;
    }

    /**
     * Print task marked as done message.
     *
     * @param taskToMark duke.task.Task to be marked as done.
     * @return A String containing the message.
     */
    public String printMarkDone(Task taskToMark) {
        String msg = "Nice! I've marked this task as done:\n" +
                taskToMark.toString();
        printLine();
        System.out.println(msg);
        printLine();
        return msg;
    }

    /**
     * Print task marked as not done message.
     *
     * @param taskToUnmark duke.task.Task to be marked as not done.
     * @return A String containing the message.
     */
    public String printMarkNotDone(Task taskToUnmark) {
        String msg = "Nice! I've marked this task as not done yet:\n"
                + taskToUnmark.toString();
        printLine();
        System.out.println(msg);
        printLine();
        return msg;
    }

    /**
     * Print the Duke Exception.
     *
     * @param de Duke Exception.
     * @return A String containing the message.
     */
    public String printDukeException(DukeException de) {
        printLine();
        System.out.println(de.getMessage());
        printLine();
        return de.getMessage();
    }

    /**
     * Print the IO Exception.
     *
     * @param ie IO Exception.
     * @return A String containing the message.
     */
    public String printIoException(IOException ie) {
        printLine();
        System.out.println("OOPS!!!" + ie.getMessage());
        printLine();
        return "OOPS!!!" + ie.getMessage();
    }

    /**
     * Print the number of tasks message.
     *
     * @param taskList duke.main.TaskList containing the list of tasks.
     * @return A String containing the message.
     */
    public String printNumTasks(TaskList taskList) {
        String msg = "Now you have " + taskList.getCount() + " tasks in the list.";
        System.out.println(msg);
        return msg;
    }

    /**
     * Print the task added message.
     *
     * @param taskToAdd duke.task.Task to be added.
     * @param taskList duke.main.TaskList which the task will be added to.
     * @return A String containing the message.
     */
    public String printAdd(Task taskToAdd, TaskList taskList) {
        String msg = "Got it. I've added this task:\n"
                + taskToAdd.toString() + "\n"
                + printNumTasks(taskList);
        printLine();
        System.out.println(msg);
        printLine();
        return msg;
    }

    /**
     * Print the task deleted message.
     *
     * @param index The index of the duke.task.Task to be removed.
     * @param taskList duke.main.TaskList which the task will be removed from.
     * @return A String containing the message.
     */
    public String printDelete(int index, TaskList taskList) {
        int count = taskList.getCount() - 1;
        printLine();
        String msg = "Noted. I've removed this task:\n"
                + taskList.getTasks().get(index - 1).toString() + "\n"
                + "Now you have " + count + " tasks in the list.";
        System.out.println(msg);
        printLine();
        return msg;
    }

    /**
     * Print the list of tasks according to the input.
     *
     * @param tasks The list of tasks to print.
     * @return A String containing the message.
     */
    public String printSomeTasks(ArrayList<Task> tasks) {
        printLine();
        int count = 0;
        String msg = "Here are the tasks in your list:\n";
        for (Task t : tasks) {
            msg += ++count + "." + t.toString() + "\n";
        }
        msg += "Now you have " + (count) + " tasks in the list.";
        printLine();
        return msg;
    }

    /**
     * Print the archived tasks message.
     *
     * @return A String containing the message.
     */
    public String printArchive() {
        printLine();
        String msg = "Existing data stored as archive, tasks have been purged.";
        printLine();
        return msg;
    }

    /**
     * Print a line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }
}
