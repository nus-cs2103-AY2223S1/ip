import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private static final String line = "---------------------------------------------------";
    // deals with interactions with the user

    public String readCommand() {
        return scanner.nextLine();
    }

    private static String showTaskTense(TaskList taskList) {
        return taskList.size() == 1 ? " task" : " tasks";
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showGreeting() {
        System.out.println(line);
        System.out.println("Hi there! I'm Duke\n" +
                "     What's up?");
        System.out.println(line);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showAddTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + "  " + task.toString());
        System.out.println("Now you have " + taskList.size() + showTaskTense(taskList) + " in the list.");
    }

    public void showDeleteTask(Task task, TaskList taskList) {
        System.out.println("Done! " + task.toString() + " has been deleted :(");
        System.out.println("Now you have " + taskList.size() + showTaskTense(taskList) + " left.");
    }

    public void showMarkedTask(Task task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void showTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("Nothing to do right now...");
        } else {
            System.out.println("Tasks: ");
            System.out.println(taskList);
        }
        System.out.println("You have " + taskList.size() + showTaskTense(taskList) + "!");
    }

    public void showTasksOnDate(TaskList taskList, String dateStr) {
        if (taskList.size() != 0) {
            System.out.println("These are the tasks on " + dateStr + ":");
            System.out.println(taskList);
            System.out.println("You have " + taskList.size() + showTaskTense(taskList)
                    + " on " + dateStr + ".");
        } else {
            System.out.println("There are no tasks on this date!");
        }
    }
}
