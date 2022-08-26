package duke;

import java.util.Scanner;

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

    public void bye() {
        System.out.println(BYE);
    }

    public String getCommand() {
        return enterInput.nextLine();
    }

    public void showTaskList() {
        System.out.println(LINEBREAK);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.taskArrayList.size(); i++) {
            Task currentTask = TaskList.taskArrayList.get(i);
            System.out.println(String.valueOf(i + 1) + "." + currentTask.toString());
        }
    }

    public void printAddedTask(Task task) {
        System.out.println(LINEBREAK);
        System.out.println("Got it. I 've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + TaskList.taskArrayList.size() + " tasks in the list.");
        System.out.println(LINEBREAK);
    }

    public void markTaskDeletedAndPrintOutput(int index) {
        Task currentTask = TaskList.taskArrayList.get(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(currentTask.toString());
        TaskList.taskArrayList.remove(index - 1);
        System.out.println("Now you have " + String.valueOf(TaskList.taskArrayList.size()) +" tasks in the list.");
    }

    public void markTaskDoneAndPrintOutput(int index) {
        Task currentTask = TaskList.taskArrayList.get(index - 1);
        System.out.println("Nice! I've marked this task as done:");
        currentTask.markAsDone();
        System.out.println(currentTask.toString());
    }

    public void markTaskNotDoneAndPrintOutput(int index) {
        Task currentTask = TaskList.taskArrayList.get(index - 1);
        System.out.println("0K, I've marked this task as not done yet:");
        currentTask.markNotDone();
        System.out.println(currentTask.toString());
    }
}
