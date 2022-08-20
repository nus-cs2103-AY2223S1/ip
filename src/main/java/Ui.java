import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private boolean isExit = false;

    public String readCommand() {
        return sc.nextLine();
    }

    public static void separationLine() {
        System.out.println("____________________________________________________________");
    }
    public void welcome() {
        separationLine();
        System.out.println("Hello, I'm Adam\n"
                + "what can I do for you?");
        separationLine();
    }

    public void bye() {
        separationLine();
        System.out.println("Sorry to see you go, goodbye :(");
        separationLine();
    }

    public static void addTaskLog(Task task) {
        separationLine();
        System.out.println("Ok, new task for you: \n"
                + " " + task);
        System.out.print("You now have " + TaskList.length() + " tasks. \n");
        separationLine();
    }

    public static void removeTaskLog(Task task) {
        separationLine();
        System.out.println("Ok, I've removed this task for you: \n"
                + " " + task);
        System.out.print("You now have " + (TaskList.length() - 1) + " tasks. \n");
        separationLine();
    }

    public static void markLog(Task task, boolean isDone) {
        separationLine();
        if (isDone) {
            System.out.println("This task is done, goodjob! :)");
        } else {
            System.out.println("This task hasn't been done yet? I've updated it for you");
        }
        System.out.println(task);
        separationLine();
    }

    public void exit() {
        this.isExit = true;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
