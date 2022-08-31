import java.util.Scanner;

public class Ui {

    private Scanner s;
    private static final String INDENT = "        ";
    private static final String DOUBLE_INDENT = "                ";
    private static final String DIVIDER = " ________________________________________________________________";
    private static final String NAME = "\n"
            + DOUBLE_INDENT + " _____  _____  __     __   __ \n"
            + DOUBLE_INDENT + "|__   ||  ___||  |   |  |/  / \n"
            + DOUBLE_INDENT + "  /  / |  |__ |  |   |  |  /  \n"
            + DOUBLE_INDENT + " /  /_ |  ___||  |__ |  |\\  \\ \n"
            + DOUBLE_INDENT + "|_____||_____||_____||__| \\__\\  ";


    public Ui() {
        s = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("\n" + DOUBLE_INDENT + INDENT + " Hello there!\n" + DOUBLE_INDENT + INDENT + "  My name is"
                + NAME);
        System.out.println("\n" + DOUBLE_INDENT + "   it's nice to meet you :)" + "\n"
                + DOUBLE_INDENT + "what can I do for you today?" + "\n" + DIVIDER);
    }

    public void showFileCreated() {
        System.out.println(INDENT + " A new Task file is created! What can I do for you?\n" + DIVIDER);
    }

    public void showFileExists() {
        System.out.println(INDENT + " Your Task file already exists! Welcome back :D\n"
                + INDENT + " What can I do for you today?\n" + DIVIDER);
    }

    public String readCommand() {
        return this.s.nextLine();
    }

    public void showAddedTask(TaskList tasks) {
        System.out.println(INDENT + "new task added: " + tasks.getRecentTask()
                + "\n" + INDENT + "you now have " + tasks.getNoOfTasks() + " tasks in your list\n");
    }

    public void showWrittenTask() {
        System.out.println(INDENT + "Task is saved in memory :)\n" + DIVIDER);
    }

    public void showMarkedTask(String task) {
        System.out.println(INDENT + "alright! I've marked this task as done :)\n"
                + DOUBLE_INDENT + task + "\n" + DIVIDER);
    }

    public void showUnmarkedTask(String task) {
        System.out.println(INDENT + "Okay, I'll mark this task as undone:\n"
                + DOUBLE_INDENT + task + "\n" + DIVIDER);
    }

    public void listTasks(TaskList tasks) {
        System.out.println(INDENT + "These are the tasks in your list so far!\n"
                + INDENT + "You currently have " + tasks.getNoOfTasks() + " tasks in your list:");
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            System.out.println(INDENT + (i + 1) + ". " +  tasks.getTask(i));
        }
        System.out.println(DIVIDER);
    }

    public void showDeletingTask(String task) {
        System.out.println(INDENT + "got it, removing this task from your list...\n"
                + DOUBLE_INDENT + task + DIVIDER);
    }

    public void showError(String message) {
        System.out.println(INDENT + message + "\n" + DIVIDER);
    }

    public void showExit() {
        System.out.println(INDENT + "Bye! Hope to see you again soon! Thank You for using Zelk :D\n" + DIVIDER);
        s.close();
    }

}
