import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        boolean inProgress = true;

        while (inProgress) {
            String userInput = bufferedReader.readLine();
            String[] userInputArr = userInput.split(" ", 2);
            String[] taskDetailsArr;
            try {
            switch (userInputArr[0]) {
                case "list":
                    listArrItems();
                    break;
                case "bye":
                    System.out.println("\tBye. Hope to see you again soon!");
                    inProgress = false;
                    break;
                case "mark":
                    if (userInputArr.length < 2) {
                        throw new DukeException("\tOOPS!!! Please specify a task to mark.");
                    }
                    try {
                        markItem(Integer.parseInt(userInputArr[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tOOPS!!! Please specify a valid task number.");
                    }
                    break;
                case "unmark":
                    if (userInputArr.length < 2) {
                        throw new DukeException("\tOOPS!!! Please specify a task to unmark.");
                    }
                    try {
                        unmarkItem(Integer.parseInt(userInputArr[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tOOPS!!! Please specify a valid task number.");
                    }
                    break;
                case "delete":
                    if (userInputArr.length < 2) {
                        throw new DukeException("\tOOPS!!! Please specify a task to delete.");
                    }
                    try {
                        deleteTask(Integer.parseInt(userInputArr[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tOOPS!!! Please specify a valid task number.");
                    }
                    break;
                case "todo":
                    if (userInputArr.length < 2) {
                        throw new DukeException("\tOOPS!!! The description of a todo cannot be empty.");
                    }
                    addToTasks(new ToDo(userInputArr[1]));
                    break;
                case "deadline":
                    if (userInputArr.length < 2) {
                        throw new DukeException("\tOOPS!!! The date/time of a deadline cannot be empty.");
                    }
                    taskDetailsArr = userInputArr[1].split(" /by ");
                    if (taskDetailsArr.length < 2) {
                        throw new DukeException("\tOOPS!!! The date/time of a deadline cannot be empty.");
                    }
                    addToTasks(new Deadline(taskDetailsArr[0], taskDetailsArr[1]));
                    break;
                case "event":
                    if (userInputArr.length < 2) {
                        throw new DukeException("\tOOPS!!! The date/time of a event cannot be empty.");
                    }
                    taskDetailsArr = userInputArr[1].split(" /at ");
                    if (taskDetailsArr.length < 2) {
                        throw new DukeException("\tOOPS!!! The date/time of a event cannot be empty.");
                    }
                    addToTasks(new Event(taskDetailsArr[0], taskDetailsArr[1]));
                    break;
                default:
                    throw new DukeException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        bufferedReader.close();
        inputStreamReader.close();
    }

    private static void listArrItems() {
        // Corner case with empty list
        if (tasks.size() == 0) {
            System.out.println("\t" + "list is empty");
            return;
        }

        // Usual Path
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    private static void addToTasks(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:\n" +
                "\t\t"+ task + "\n" +
                "\tNow you have " + tasks.size() + " tasks in your list.");
    }

    private static void deleteTask(int index) throws DukeException {
        if (index > tasks.size() || index < 1) {
            throw new DukeException("\tOOPS!!! Please specify a valid task.");
        }
        System.out.println("\tNoted. I've removed this task:\n" +
                "\t\t"+ tasks.remove(index - 1) + "\n" +
                "\tNow you have " + tasks.size() + " tasks in your list.");
    }

    private static void markItem(int index) throws DukeException {
        if (index > tasks.size() || index < 1) {
            throw new DukeException("\tOOPS!!! Please specify a valid task.");
        }
        tasks.get(index - 1).isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + tasks.get(index - 1));
    }

    private static void unmarkItem(int index) throws DukeException {
        if (index > tasks.size() || index < 1) {
            throw new DukeException("\tOOPS!!! Please specify a valid task.");
        }
        tasks.get(index - 1).isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:\n\t\t" + tasks.get(index - 1));
    }
}
