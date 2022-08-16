import java.util.Scanner;
public class Duke {
    private static Task[] tasks = new Task[100];
    public static String getTaskDetails(int pos) {
        return "[" +
                tasks[pos].getType() +
                "][" +
                tasks[pos].getStatus() +
                "] " +
                tasks[pos].getDescription();
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
                for (int pos = 0; pos < 100; pos++) {
                    if (tasks[pos] == null) {
                        tasks[pos] = new ToDo(details);
                        System.out.println(
                                "Got it. I've added this task:\n" +
                                        getTaskDetails(pos) +
                                        "\nNow you have " +
                                        (pos + 1) +
                                        " tasks in the list."
                                );
                        break;
                    }
                }
                break;
            case "deadline":
            case "event":
                String[] detailsArr = details.split("/");
                if (detailsArr.length != 2) {
                    throw new DukeException("Timing required for " + type + "!");
                }
                String description = details.split("/")[0];
                String timing = details.split("/")[1].split(" ", 2)[1];
                for (int pos = 0; pos < 100; pos++) {
                    if (tasks[pos] == null) {
                        if (type.equals("deadline")) {
                            tasks[pos] = new Deadline(description, timing);
                        } else {
                            tasks[pos] = new Event(description, timing);
                        }
                        System.out.println(
                                "Got it. I've added this task:\n" +
                                        getTaskDetails(pos) +
                                        "\nNow you have " +
                                        (pos + 1) +
                                        " tasks in the list."
                        );
                        break;
                    }
                }
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
        for (int pos = 0; pos < 100; pos++) {
            if (tasks[pos] == null) {
                if (pos == 0) throw new DukeException("No tasks found.");
                break;
            }
            System.out.println(pos + 1 + ". " + getTaskDetails(pos));
        }
    }
    public static void markTask(String command) throws DukeException {
        String[] cmdArr = command.split(" ");
        if (cmdArr.length == 1) {
            throw new DukeException("Provide a task number to mark");
        } else if (cmdArr.length > 2) {
            throw new DukeException("Provide a valid task number!");
        }
        int pos;
        try {
            pos = Integer.parseInt(command.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Provide a numeric task number!");
        }
        if (pos < 0) {
            throw new DukeException("Provide a valid number!");
        }
        if (tasks[pos] == null) {
            throw new DukeException("No such task.");
        }
        switch (cmdArr[0]) {
            case "mark":
                tasks[pos].markAsDone();
                System.out.println(
                        "Nice! I've marked this task as done" +
                                "\n" +
                                getTaskDetails(pos)
                );
                break;
            case "unmark":
                tasks[pos].markAsUndone();
                System.out.println(
                        "Nice! I've marked this task as undone" +
                                "\n" +
                                getTaskDetails(pos)
                );
        }
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
