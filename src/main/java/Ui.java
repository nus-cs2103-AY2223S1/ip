import java.io.IOException;

/**
 * Deals with interactions with the user.
 * Print the respective message.
 */
public class Ui {
    /**
     * Print intro message.
     */
    public void printIntro() {
        printLine();
        System.out.println("Hello from Duke\n" + "What can I do for you?");
        printLine();
    }

    /**
     * Print exit message.
     */
    public void printExit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Print list of tasks.
     * @param tasks Existing tasks.
     */
    public void printListOfTasks(TaskList tasks) {
        printLine();
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for(Task t : tasks.getTasks()) {
            System.out.println(count++ + "." + t.toString());
        }
        System.out.println("Now you have " + (count - 1) + " tasks in the list.");
        printLine();
    }

    /**
     * Print task marked as done message.
     * @param taskToMark Task to be marked as done.
     */
    public void printMarkDone(Task taskToMark) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToMark.toString());
        printLine();
    }

    /**
     * Print task marked as not done message.
     * @param taskToUnmark Task to be marked as not done.
     */
    public void printMarkNotDone(Task taskToUnmark) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskToUnmark.toString());
        printLine();
    }

    /**
     * Print the Duke Exception.
     * @param de Duke Exception.
     */
    public void printDukeException(DukeException de) {
        printLine();
        System.out.println(de.getMessage());
        printLine();
    }

    /**
     * Print the IO Exception.
     * @param ie IO Exception.
     */
    public void printIoException(IOException ie) {
        printLine();
        System.out.println("OOPS!!!" + ie.getMessage());
        printLine();
    }

    /**
     * Print the number of tasks message.
     * @param taskList TaskList containing the list of tasks.
     */
    public void printNumTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getCount() + " tasks in the list.");
    }

    /**
     * Print the task added message.
     * @param taskToAdd Task to be added.
     * @param taskList TaskList which the task will be added to.
     */
    public void printAdd(Task taskToAdd, TaskList taskList) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskToAdd.toString());
        printNumTasks(taskList);
        printLine();
    }

    /**
     * Print the task deleted message.
     * @param index The index of the Task to be removed.
     * @param taskList TaskList which the task will be removed from.
     */
    public void printDelete(int index, TaskList taskList) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.getTasks().get(index - 1).toString());
        // minus the task about to be deleted from the count
        System.out.println("Now you have " + (taskList.getCount() - 1) + " tasks in the list.");
        printLine();
    }

    /**
     * Print a line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }


}
