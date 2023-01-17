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
    static final String GREETING = "Hello! I'm Dukity Duke! What can I do for you?";
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
    public String greet() {
        return GREETING;
    }

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
        outputString += "Now you have " + String.valueOf(taskArrayList.size() - 1) + " tasks in the list." + "\n";
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

    /**
     * Denotes the list of commands available to manipulate tasks
     * @return a string listing all the commands to manipulate a task
     */
    public String help1() {
        String outputString = "";
        outputString += "list: list down all your available tasks\n";
        outputString += "mark: marks a task a done E.g mark 2 (marks 2nd task as done)\n";
        outputString += "unmark: marks a task as not done E.g unmark 2 (marks 2nd task as not done)\n";
        outputString += "delete: deletes a task E.g delete 1 (deletes the first task)\n";

        return outputString;
    }

    /**
     * Denotes the list of commands available to create tasks
     * @return a string listing all the commands to create a task
     */
    public String help2() {
        String outputString = "";
        outputString += "todo: adds a todo task to the list E.g todo return book\n";
        outputString += "event: adds an event task to the list E.g event project meeting /at Mon 2-4pm\n";
        outputString += "deadline: adds a deadline task to the list E.g deadline return book /by 2/12/2019 1800\n";

        return outputString;
    }
}
