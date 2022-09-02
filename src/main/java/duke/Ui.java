package duke;

import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for showing output to the user
 */
public class Ui {

    // Use final variables to denote standard greetings we will say to the user
    static final String GREETING = "Hello! I'm Duke! What can I do for you?";
    static final String BYE = "Bye. Hope to see you again soon ^^!";
    static final String LINEBREAK = "-------------------------";

    private Scanner enterInput;

    /**
     * Constructor for the duke.Ui
     */
    public Ui() {
        this.enterInput = new Scanner(System.in);
    }

    /**
     * Greets the user
     */
    public void greet() {
        System.out.println(GREETING);
    }

    /**
     * Say bye to the user
     */
    public void bye() {
        System.out.println(BYE);
    }

    public String getCommand() {
        return enterInput.nextLine();
    }

    /**
     * Show all the tasks the user currently has
     */
    public void showTaskList() {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        System.out.println(LINEBREAK);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task currentTask = taskArrayList.get(i);
            System.out.println(String.valueOf(i + 1) + "." + currentTask.toString());
        }
    }

    /**
     * Informs user that a task has been added
     * @param task the task that is added
     */
    public void printAddedTask(Task task) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        System.out.println(LINEBREAK);
        System.out.println("Got it. I 've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.");
        System.out.println(LINEBREAK);
    }

    /**
     * Informs user a task has been deleted
     * @param index the position of the task to delete
     */
    public void markTaskDeletedAndPrintOutput(int index) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        Task currentTask = taskArrayList.get(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currentTask.toString());
        taskArrayList.remove(index - 1);
        System.out.println("Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list.");
    }

    /**
     * Marks a task as done and informs the user
     * @param index the position of the task to be marked done
     */
    public void markTaskDoneAndPrintOutput(int index) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        Task currentTask = taskArrayList.get(index - 1);
        System.out.println("Nice! I've marked this task as done:");
        currentTask.markAsDone();
        System.out.println(currentTask.toString());
    }

    /**
     * Marks a task a not done and informs the user
     * @param index the position to mark a task as not done
     */
    public void markTaskNotDoneAndPrintOutput(int index) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        Task currentTask = taskArrayList.get(index - 1);
        System.out.println("0K, I've marked this task as not done yet:");
        currentTask.markNotDone();
        System.out.println(currentTask.toString());
    }
}
