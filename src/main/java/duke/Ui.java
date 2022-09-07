package duke;

import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for showing output to the user
 *
 * @author Kang Zong Xian
 */
public class Ui {

    // Use final variables to denote standard greetings we will say to the user
    static final String GREETING = "Hello! I'm Duke! What can I do for you?";
    static final String BYE = "Bye. Hope to see you again soon ^^!";
    static final String LINEBREAK = "-------------------------";

    private Scanner enterInput;

    /**
     * Constructor for the Ui
     */
    public Ui() {
        this.enterInput = new Scanner(System.in);
    }

    /**
     * Greets the user
     */
    public String greet() { return GREETING;}

    /**
     * Say bye to the user
     */
    public String bye() {
        return BYE;
    }

    /**
     * Get the command entered by the user
     * @return a string indicating what the user has entered
     */
    public String getCommand() {
        return enterInput.nextLine();
    }

    /**
     * Show all the tasks the user currently has
     */
    public String showTaskList() {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        System.out.println(LINEBREAK);
        String outputString = "";
        outputString += "Here are the tasks in your list:\n";
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task currentTask = taskArrayList.get(i);
            outputString += String.valueOf(i + 1) + "." + currentTask.toString() + "\n";
        }
        return outputString;
    }

    /**
     * Informs user that a task has been added
     * @param task the task that is added
     */
    public String printAddedTask(Task task) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        String outputString = "";
        outputString += "Got it. I 've added this task:\n";
        outputString += task.toString() + "\n";
        outputString += "Now you have " + taskArrayList.size() + " tasks in the list." + "\n";
        return outputString;
    }

    /**
     * Informs user a task has been deleted
     * @param index the position of the task to delete
     */
    public String markTaskDeletedAndPrintOutput(int index) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        Task currentTask = taskArrayList.get(index - 1);
        String outputString = "";
        outputString += "Noted. I've removed this task:\n";
        outputString += currentTask.toString() + "\n";
        outputString += "Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list." + "\n";
        taskArrayList.remove(index - 1);
        return outputString;
    }

    /**
     * Marks a task as done and informs the user
     * @param index the position of the task to be marked done
     */
    public String markTaskDoneAndPrintOutput(int index) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        Task currentTask = taskArrayList.get(index - 1);
        String outputString = "";
        outputString += "Nice! I've marked this task as done:\n";
        currentTask.markAsDone();
        outputString += currentTask.toString();
        return outputString;
    }

    /**
     * Marks a task a not done and informs the user
     * @param index the position to mark a task as not done
     */
    public String markTaskNotDoneAndPrintOutput(int index) {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        Task currentTask = taskArrayList.get(index - 1);
        String outputString = "";
        outputString += "0K, I've marked this task as not done yet:\n";
        currentTask.markNotDone();
        outputString += currentTask.toString();
        return outputString;
    }
}
