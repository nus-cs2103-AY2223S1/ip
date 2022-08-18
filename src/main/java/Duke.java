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
            // retrieve first word
            int indexOfFirstSpace = userInput.indexOf(" ");
            int indexOfFirstSlash = userInput.indexOf("/");
            String firstWord = userInput;
            String taskDescription = "";
            String time = "";
            if (indexOfFirstSpace != - 1) {
                firstWord = userInput.substring(0, indexOfFirstSpace);
                taskDescription = userInput.substring(indexOfFirstSpace + 1);
            }
            if (indexOfFirstSlash != -1) {
                taskDescription = userInput.substring(indexOfFirstSpace + 1, indexOfFirstSlash - 1);
                time = userInput.substring(indexOfFirstSlash + 1);
            }
            // retrieve int for numbered operations
            char charOfInt = userInput.charAt(userInput.length() - 1);
            // convert ASCII character of integer to int
            int taskNumber =  charOfInt - '0';
            switch (firstWord) {
                case "bye":
                    Duke.exit();
                    break;
                case "unmark":
                    Duke.unmarkTask(taskNumber);
                    break;
                case "mark":
                    Duke.markTask(taskNumber);
                    break;
                case "list":
                    Duke.displayList();
                    break;
                case "deadline":
                    Duke.addTask(new Deadline(taskDescription, time));
                    break;
                case "todo":
                    Duke.addTask(new ToDo(taskDescription));
                    break;
                case "event":
                    Duke.addTask(new Event(taskDescription, time));
                    break;
                default:
                    System.out.println(userInput);
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

    public static void addTask(Task task) {
        taskList.add(task);
        Duke.lineFormat();
        System.out.println("     Got it. I've added this task: \n" +
                "       " + task.toString() + "\n" +
                "     Now you have " + taskList.size() + " tasks in the list.");
        Duke.lineFormat();
    }

    public static void displayList() {
        Duke.lineFormat();
        System.out.println("     Here are the tasks in your list: ");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            String taskDescription = currentTask.toString();
            System.out.println("     " +
                    String.valueOf(i) + "." +
                    taskDescription);
        }
        Duke.lineFormat();
    }

    public static void markTask(int i) {
        Task currentTask = taskList.get(i - 1);
        currentTask.setTaskStatus(true);
        String taskDescription = currentTask.toString();
        Duke.lineFormat();
        System.out.println("     Nice! I've marked this task as done: \n" +
                "       " + taskDescription);
        Duke.lineFormat();
    }

    public static void unmarkTask(int i) {
        Task currentTask = taskList.get(i - 1);
        currentTask.setTaskStatus(false);
        String taskDescription = currentTask.toString();
        Duke.lineFormat();
        System.out.println("     OK, I've marked this task as not done yet: \n" +
                "       " + taskDescription);
        Duke.lineFormat();
    }

    public static void exit() {
        Duke.terminate = true;
        Duke.lineFormat();
        System.out.println("     Bye. Hope to see you again soon!\n");
        Duke.lineFormat();
    }
}