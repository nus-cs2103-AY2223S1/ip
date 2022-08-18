import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static boolean terminate = false;
    protected static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();

        Scanner in = new Scanner(System.in);
        while (!terminate) {
            String userInput = in.nextLine();
            char charOfInt = userInput.charAt(userInput.length() - 1);
            // convert ASCII character of integer to int
            int taskNumber =  charOfInt - '0';
            if (userInput.equals("bye")) {
                Duke.exit();
            } else if (userInput.contains("unmark")) {
                Duke.unmarkTask(taskNumber);
            } else if (userInput.contains("mark")) {
                Duke.markTask(taskNumber);
            } else if (userInput.equals("list")) {
                Duke.displayList();
            } else {
                Duke.addTask(userInput);
            }
        }
    }

    public static void lineFormat() {
        System.out.println("    ____________________________________________________________");
    }

    public static void greet() {
        Duke.lineFormat();
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?\n");
        Duke.lineFormat();
    }

    public static void addTask(String userInput) {
        Duke.lineFormat();
        System.out.println("     added: " + userInput);
        Duke.lineFormat();
        Task addedTask = new Task(userInput);
        taskList.add(addedTask);
    }

    public static void displayList() {
        Duke.lineFormat();
        System.out.println("     Here are the tasks in your list: ");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            String indicator = currentTask.getStatusIcon();
            String taskDescription = currentTask.getTaskDescription();
            System.out.println("     " +
                    String.valueOf(i) + ".[" +
                    indicator + "] " +
                    taskDescription);
        }
        Duke.lineFormat();
    }

    public static void markTask(int i) {
        Task currentTask = taskList.get(i - 1);
        String taskDescription = currentTask.getTaskDescription();
        Duke.lineFormat();
        System.out.println("     Nice! I've marked this task as done: \n" +
                "       [X] " + taskDescription);
        Duke.lineFormat();
        currentTask.setTaskStatus(true);
    }

    public static void unmarkTask(int i) {
        Task currentTask = taskList.get(i - 1);
        String taskDescription = currentTask.getTaskDescription();
        Duke.lineFormat();
        System.out.println("     OK, I've marked this task as not done yet: \n" +
                "       [ ] " + taskDescription);
        Duke.lineFormat();
        currentTask.setTaskStatus(false);
    }

    public static void exit() {
        Duke.terminate = true;
        Duke.lineFormat();
        System.out.println("     Bye. Hope to see you again soon!\n");
        Duke.lineFormat();
    }
}