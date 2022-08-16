import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void printList(ArrayList<Task> items) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            int j = i + 1;
            Task item = items.get(i);
            System.out.println(j + "." + item.toString());
        }
    }

    public static void markDone(ArrayList<Task> items, int index) throws DukeException {
        if (index < 0 || index > items.size() - 1) {
            throw new OutOfRangeException();
        } else {
            items.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(items.get(index).toString());
        }
    }

    public static void deleteTask(ArrayList<Task> items, int index) throws DukeException {
        if (index < 0 || index > items.size() - 1) {
            throw new OutOfRangeException();
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(items.get(index).toString());
            items.remove(index);
            System.out.printf("Now you have %d tasks in the list.\n", items.size());
        }
    }

    public static void addToDo(ArrayList<Task> items, String command) throws DukeException {
        if (command.substring(4).trim().isEmpty()) {
            throw new EmptyCommandException("todo");
        }
        ToDo newTask = new ToDo(command.substring(5));
        items.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.\n", items.size());
    }

    public static void addDeadline(ArrayList<Task> items, String command) throws DukeException {
        if (command.substring(8).trim().isEmpty()) {
            throw new EmptyCommandException("deadline");
        }
        if (!command.substring(9).contains("/by")) {
            throw new NoTimeException("deadline");
        }
        String desc = command.substring(9).split("/by")[0];
        String by = command.substring(9).split("/by")[1];
        Deadline newTask = new Deadline(desc, by);
        items.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.\n", items.size());
    }

    public static void addEvent(ArrayList<Task> items, String command) throws DukeException {
        if (command.substring(5).trim().isEmpty()) {
            throw new EmptyCommandException("event");
        }
        if (!command.substring(6).contains("/at")) {
            throw new NoTimeException("event");
        }
        String desc = command.substring(6).split("/at")[0];
        String at = command.substring(6).split("/at")[1];
        Event newTask = new Event(desc, at);
        items.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.printf("Now you have %d tasks in the list.\n", items.size());
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        ArrayList<Task> items = new ArrayList<>();

        while (!command.toLowerCase().equals("bye")) {
            try {
                if (command.toLowerCase().equals("list")) {
                    printList(items);
                } else if (command.split(" ")[0].toLowerCase().equals("done")) {
                    if (command.split(" ").length == 1) {
                        throw new NoIndexException();
                    }
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    markDone(items, index);
                } else if (command.split(" ")[0].toLowerCase().equals("delete")) {
                    if (command.split(" ").length == 1) {
                        throw new NoIndexException();
                    }
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    deleteTask(items, index);
                } else {
                    String taskType = command.split(" ")[0].toLowerCase();
                    if (taskType.equals("deadline")) {
                        addDeadline(items, command);
                    } else if (taskType.equals("event")) {
                        addEvent(items, command);
                    } else if (taskType.equals("todo")) {
                        addToDo(items, command);
                    } else {
                        throw new InvalidCommandException();
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            } finally {
                command = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
