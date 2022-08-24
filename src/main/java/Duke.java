import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    protected static boolean terminate = false;
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    // protected static HashMap<LocalDate, ArrayList<Task>> dateToTasks = new HashMap<>();

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
            try {
                String userInput = in.nextLine();
                // retrieve first word
                int indexOfFirstSpace = userInput.indexOf(" ");
                int indexOfFirstSlash = userInput.indexOf("/");
                String firstWord = userInput;
                String taskDescription = "";
                String time = "";

                // int for numbered operations
                char charOfInt = '\0';
                if (indexOfFirstSpace != - 1) {
                    firstWord = userInput.substring(0, indexOfFirstSpace);
                    taskDescription = userInput.substring(indexOfFirstSpace + 1);

                    // retrieve int for numbered operations
                    charOfInt = userInput.charAt(userInput.length() - 1);
                }

                // retrieve task details
                if (indexOfFirstSlash != -1) {
                    taskDescription = userInput.substring(indexOfFirstSpace + 1, indexOfFirstSlash - 1);
                    time = userInput.substring(indexOfFirstSlash + 1);
                }

                // convert ASCII character of integer to int
                int taskNumber =  charOfInt - '0';
                switch (firstWord) {
                case "bye":
                    Duke.exit();
                    break;
                case "unmark":
                    Duke.unmarkTask(taskNumber, taskDescription);
                    break;
                case "mark":
                    Duke.markTask(taskNumber, taskDescription);
                    break;
                case "list":
                    Duke.displayList();
                    break;
                case "deadline":
                    Duke.addDeadline(taskDescription, time);
                    break;
                case "todo":
                    Duke.addTodo(taskDescription);
                    break;
                case "event":
                    Duke.addEvent(taskDescription, time);
                    break;
                case "delete":
                    Duke.removeTask(taskNumber, taskDescription);
                    break;
                case "":
                    throw new DukeException("OOPS!!! Please enter an instruction");
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                Duke.lineFormat();
                System.out.println("     " + e.toString());
                Duke.lineFormat();
            } catch (IndexOutOfBoundsException e) {
                Duke.lineFormat();
                System.out.println("     OOPS!!! Please enter a valid task number.");
                Duke.lineFormat();
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

//    public static void addTask(Task task, LocalDate date) {
//        taskList.add(task);
//        if (!dateToTasks.containsKey(date)) {
//            dateToTasks.put(date, new ArrayList<>());
//        }
//        dateToTasks.get(date).add(task);
//        Duke.lineFormat();
//        System.out.println("     Got it. I've added this task:\n" +
//                "       " + task.toString() + "\n" +
//                "     Now you have " + taskList.size() + " tasks in the list.");
//        Duke.lineFormat();
//    }

    public static void addTask(Task task) {
        taskList.add(task);
        Duke.lineFormat();
        System.out.println("     Got it. I've added this task:\n" +
                "       " + task.toString() + "\n" +
                "     Now you have " + taskList.size() + " tasks in the list.");
        Duke.lineFormat();
    }

    public static void removeTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for delete cannot be empty.");
        }
        Task removedTask = taskList.remove(taskNumber - 1);
        System.out.println("     Noted. I've removed this task:\n" +
                "       " + removedTask.toString() + "\n" +
                "     Now you have " + taskList.size() + " tasks in the list.");
        Duke.lineFormat();
    }

    public static void addTodo(String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new ToDo(description);
        Duke.addTask(task);
    }

    public static void addEvent(String description, String at) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        try {
            int indexOfDate = at.indexOf(" ") + 1;
            String date = at.substring(indexOfDate);
            LocalDate d1 = LocalDate.parse(date);
            Task task = new Event(description, d1);
            Duke.addTask(task);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            System.out.println("     OOPS!!! Please enter a valid date format (/at yyyy-mm-dd)");
        }
    }

    public static void addDeadline(String description, String by) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            int indexOfDate = by.indexOf(" ") + 1;
            String date = by.substring(indexOfDate);
            LocalDate d1 = LocalDate.parse(date);
            Task task = new Deadline(description, d1);
            Duke.addTask(task);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            System.out.println("     OOPS!!! Please enter a valid date format (/by yyyy-mm-dd)");
        }
    }

    public static void displayList() {
        Duke.lineFormat();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            String taskDescription = currentTask.toString();
            System.out.println("     " +
                    String.valueOf(i) + "." +
                    taskDescription);
        }
        Duke.lineFormat();
    }

    public static void markTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for mark cannot be empty.");
        }
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.setTaskStatus(true);
        String taskDescription = currentTask.toString();
        Duke.lineFormat();
        System.out.println("     Nice! I've marked this task as done:\n" +
                "       " + taskDescription);
        Duke.lineFormat();
    }

    public static void unmarkTask(int taskNumber, String description) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The task number for unmark cannot be empty.");
        }
        Task currentTask = taskList.get(taskNumber - 1);
        currentTask.setTaskStatus(false);
        String taskDescription = currentTask.toString();
        Duke.lineFormat();
        System.out.println("     OK, I've marked this task as not done yet:\n" +
                "       " + taskDescription);
        Duke.lineFormat();
    }

//    public static void displayTasksOnDate(String time) {
//        try {
//            int indexOfDate = time.indexOf(" ") + 1;
//            String date = time.substring(indexOfDate);
//            LocalDate d1 = LocalDate.parse(date);
//
//        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
//            System.out.println("     OOPS!!! Please enter a valid date format (/by yyyy-mm-dd)");
//        }
//    }

    public static void exit() {
        Duke.terminate = true;
        Duke.lineFormat();
        System.out.println("     Bye. Hope to see you again soon!");
        Duke.lineFormat();
    }
}