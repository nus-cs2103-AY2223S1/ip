import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static TaskList data;
    private enum Commands {list, mark, unmark, todo, event, deadline, delete};

    // marks task as complete or not complete
    public static String setTaskCompletion(String input, boolean isComplete) throws DukeException {
        String res = input.substring(isComplete ? 4 : 6).trim();
        Task task = data.get(data.getIndex(res));
        if (isComplete) task.markDone();
        else task.markNotDone();
        data.saveData();
        return (isComplete ? "Nice! I've marked this task as done:\n" : "OK, I've marked this task as not done yet:\n")
                + task;
    }

    // create a todo task
    public static String createTodo(String input) throws DukeException {
        String description = input.substring(4).trim();
        if (description.length() == 0) throw new InvalidInput("The description of a todo cannot be empty.");
        ToDo task = new ToDo(description);
        data.add(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + data.size() + " tasks.";
    }

    // create event task
    public static String createEvent(String input) throws DukeException {
        String[] info = input.substring(5).split("/at");
        if (info.length != 2) throw new InvalidInput("Ensure input format is correct.");
        String description = info[0].strip();
        String at = info[1].strip();
        if (description.length() == 0 || at.length() == 0)
            throw new InvalidInput("The description of event cannot be empty.");
        Event task = new Event(description, at);
        data.add(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + data.size() + " tasks.";
    }

    // create deadline task
    public static String createDeadline(String input) throws DukeException {
        String[] info = input.substring(8).split("/by");
        if (info.length != 2) throw new InvalidInput("Ensure input format is correct.");
        String description = info[0].strip();
        String by = info[1].strip();
        if (description.length() == 0 || by.length() == 0)
            throw new InvalidInput("The description of deadline cannot be empty.");
        Deadline task = new Deadline(description, by);
        data.add(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + data.size() + " tasks.";
    }

    // delete a specified task
    public static String deleteTask(String input) throws DukeException {
        String res = input.substring(6).trim();
        int target_index = data.getIndex(res);
        Task task = data.get(target_index);
        data.remove(target_index);
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + data.size() + " tasks.";
    }

    public static String reply(String input) throws DukeException {
        Commands command;
        try {
            command = Commands.valueOf(input.split(" ")[0]);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommand();
        }
        switch (command) {
            case list:
                return data.listData();
            case mark:
                return setTaskCompletion(input, true);
            case unmark:
                return setTaskCompletion(input, false);
            case todo:
                return createTodo(input);
            case event:
                return createEvent(input);
            case deadline:
                return createDeadline(input);
            case delete:
                return deleteTask(input);
            default:
                throw new UnknownCommand();
        }
    }

    public static void main(String[] args) {
        try {
            data = new TaskList();
            data.loadData();
        } catch (DukeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("Quack! I'm Duck\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.println("____________________________________________________________");
            System.out.println("Duck:");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                sc.close();
                System.out.println("Quack! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                return;
            } else {
                try {
                    String reply = reply(input);
                    System.out.println(reply);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
            System.out.println("____________________________________________________________");
        }
    }
}
