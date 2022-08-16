import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static String getTaskDetails(int pos) {
        return "[" +
                tasks.get(pos).getType() +
                "][" +
                tasks.get(pos).getStatus() +
                "] " +
                tasks.get(pos).getDescription();
    }
    public static void addTask(String command) throws DukeException {
        String[] cmdArr = command.split(" ", 2);
        if (cmdArr.length != 2) {
            throw new DukeException(
                    "The description of " +
                            cmdArr[0] +
                            " cannot be empty!"
            );
        }
        String type = cmdArr[0];
        String details = cmdArr[1];
        switch (type) {
            case "todo":
                tasks.add(new ToDo(details));
                System.out.println(
                        "Got it. I've added this task:\n" +
                                getTaskDetails(tasks.size() - 1) +
                                "\nNow you have " +
                                tasks.size() +
                                (tasks.size() == 1 ? " task" : " tasks") +
                                " in the list."
                );
                break;
            case "deadline":
            case "event":
                String[] detailsArr = details.split("/");
                if (detailsArr.length != 2) {
                    throw new DukeException("Timing required for " + type + "!");
                }
                String description = details.split("/")[0];
                String timing = details.split("/")[1].split(" ", 2)[1];
                if (type.equals("deadline")) {
                    tasks.add(new Deadline(description, timing));
                } else {
                    tasks.add(new Event(description, timing));
                }
                System.out.println(
                        "Got it. I've added this task:\n" +
                                getTaskDetails(tasks.size() - 1) +
                                "\nNow you have " +
                                tasks.size() +
                                (tasks.size() == 1 ? " task" : " tasks") +
                                " in the list."
                );
        }
    }
    public static void unknownCommand(String command) throws DukeException {
        throw new DukeException(
                "I'm unable to do '" +
                         command +
                        "' at the moment. :("
        );
    }
    public static void listTasks() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("No tasks found.");
        }
        for (int pos = 0; pos < tasks.size(); pos++) {
            System.out.println(pos + 1 + ". " + getTaskDetails(pos));
        }
    }
    public static int handleTaskPos(String command) throws DukeException {
        String[] cmdArr = command.split(" ");
        int pos;
        if (cmdArr.length == 1) {
            throw new DukeException("Provide a task number to mark");
        } else if (cmdArr.length > 2) {
            throw new DukeException("Provide a valid task number!");
        }
        try {
            pos = Integer.parseInt(command.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Provide a numeric task number!");
        }
        if (pos < 0) {
            throw new DukeException("Provide a valid number!");
        }
        if (tasks.size() <= pos) {
            throw new DukeException("No such task.");
        }
        return pos;
    }
    public static void markTask(String command) throws DukeException {
        int pos = handleTaskPos(command);
        switch (command.split(" ")[0]) {
            case "mark":
                tasks.get(pos).markAsDone();
                System.out.println(
                        "Nice! I've marked this task as done" +
                                "\n" +
                                getTaskDetails(pos)
                );
                break;
            case "unmark":
                tasks.get(pos).markAsUndone();
                System.out.println(
                        "Nice! I've marked this task as undone" +
                                "\n" +
                                getTaskDetails(pos)
                );
        }
    }
    public static void deleteTask(String command) throws DukeException {
        int pos = handleTaskPos(command);
        System.out.println(
                "OK. I've removed this task:\n" +
                        getTaskDetails(pos) +
                        "\nNow you have " +
                        (tasks.size() - 1) +
                        (tasks.size() == 2 ? " task" : " tasks") +
                        " in the list."
        );
        tasks.remove(pos);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Hello! I'm Duke.\nWhat can I do for you?\n>> ");
        String command = input.nextLine();
        while (!command.equals("bye")) {
            String cmdMain = command.split(" ", 2)[0];
            try {
                switch (cmdMain) {
                    case "list":
                        listTasks();
                        break;
                    case "mark":
                    case "unmark":
                        markTask(command);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        addTask(command);
                        break;
                    case "delete":
                        deleteTask(command);
                        break;
                    default:
                        unknownCommand(command);
                    }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.print("\n>> ");
                command = input.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}
