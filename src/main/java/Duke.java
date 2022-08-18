import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static int index = 0;
    private static ArrayList<Task> arr = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
        printGreetings();
        String str = sc.nextLine().trim();
        while(!str.equals("bye")) {
            try {
                processInput(str);
            } catch (DukeException e) {
                printMessage(e.toString());
            }
            str = sc.nextLine().trim();
        }
        printGoodbye();
    }

    private static void processInput(String str) throws DukeException {
        String[] splitStr = str.split(" ", 2);
        switch (splitStr[0]) {
            case "list":
                printTaskList();
                break;
            case "mark":
                mark(splitStr);
                break;
            case "unmark":
                unmark(splitStr);
                break;
            case "todo":
                addTask(splitStr, TaskType.TODO);
                break;
            case "deadline":
                addTask(splitStr, TaskType.DEADLINE);
                break;
            case "event":
                addTask(splitStr, TaskType.EVENT);
                break;
            case "delete":
                deleteTask(splitStr);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means!");
        }
    }

    private static void mark(String[] splitStr) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to mark.");
        }
        int markNo;
        try {
            markNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to mark.");
        }
        if (markNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (markNo > index) {
            throw new DukeException("There are not that many tasks!");
        }
        arr.get(markNo - 1).markAsDone();
        printMessage("Nice! I've marked this task as done:\n       " + arr.get(markNo - 1));
    }

    private static void unmark(String[] splitStr) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to unmark.");
        }
        int unmarkNo;
        try {
            unmarkNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to unmark.");
        }
        if (unmarkNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (unmarkNo > index) {
            throw new DukeException("There are not that many tasks!");
        }
        arr.get(unmarkNo - 1).markAsUnDone();
        printMessage("Nice! I've marked this task as not done yet:\n       " + arr.get(unmarkNo - 1));
    }

    private static void addTask(String[] splitStr, TaskType type) throws DukeException {
        if (splitStr.length < 2) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        switch (type) {
            case TODO:
                arr.add(new Todo(splitStr[1]));
                index++;
                printMessage("Got it. I've added this task:\n       " + arr.get(index - 1)
                        + "\n     Now you have " + index + " tasks in the list");
                break;
            case DEADLINE:
                String[] strDeadline = splitStr[1].split("/by", 2);
                if (strDeadline.length < 2 || strDeadline[1].equals("")) {
                    throw new DukeException("Please also specify the date and time.");
                }
                arr.add(new Deadline(strDeadline[0].trim(), strDeadline[1].trim()));
                index++;
                printMessage("Got it. I've added this task:\n       " + arr.get(index - 1)
                        + "\n     Now you have " + index + " tasks in the list.");
                break;
            case EVENT:
                String[] strEvent = splitStr[1].split("/at", 2);
                if (strEvent.length < 2 || strEvent[1].equals("")) {
                    throw new DukeException("Please also specify the date and time.");
                }
                arr.add(new Event(strEvent[0].trim(), strEvent[1].trim()));
                index++;
                printMessage("Got it. I've added this task:\n       " + arr.get(index - 1)
                        + "\n     Now you have " + index + " tasks in the list.");
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means!");
        }
    }

    private static void deleteTask(String[] splitStr) throws DukeException{
        if (splitStr.length < 2) {
            throw new DukeException("Please specify task number to delete.");
        }
        int deleteNo;
        try {
            deleteNo = Integer.parseInt(splitStr[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify task number to delete.");
        }
        if (deleteNo <= 0) {
            throw new DukeException("Invalid task number.");
        }
        if (deleteNo > index) {
            throw new DukeException("There are not that many tasks!");
        }
        index--;
        printMessage("Noted. I've removed this task:\n       " + arr.get(deleteNo - 1)
                + "\n     Now you have " + index + " tasks in the list.");
        arr.remove(deleteNo - 1);
    }

    private static void printMessage(String message) {
        String line = "    ____________________________________________________________";
        String wrappedMessage = line + "\n     " + message + "\n" + line;
        System.out.println(wrappedMessage);
    }

    private static void printGreetings() {
        printMessage("Hello! I'm Duke.\n     What can I do for you?");
    }

    private static void printGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private static void printTaskList() {
        int i = 1;
        String line = "    ____________________________________________________________";
        System.out.println(line);
        System.out.println("     Here are the tasks in your list");
        for (Task t : arr) {
            System.out.println("     " + i + "." + t);
            i++;
        }
        System.out.println(line);
    }
}
