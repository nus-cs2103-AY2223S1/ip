import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    private static ArrayList<Task> data = new ArrayList<Task>();
    private static List<String> UNKNOWN_COMMANDS = Arrays.asList("todo", "deadline", "event");

    private static void addItem(String item) {
        System.out.println("added: " + item);
        data.add(new Task(item));
    }

    private static void addTodo(String[] inputs) {
        String task = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        data.add(new Todo(task));
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                data.get(data.size() - 1), data.size());
    }
    private static void addDeadLine(String[] inputs) {
        int limit = findElem(inputs, "/by");
        String task = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        String by = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
        data.add(new Deadline(task, by));
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                data.get(data.size() - 1), data.size());
    }

    private static void addEvent(String[] inputs) {
        int limit = findElem(inputs, "/at");
        String task = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        String at = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
        data.add(new Event(task, at));
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                data.get(data.size() - 1), data.size());
    }

    private static void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < data.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + data.get(i));
        }
    }

    private static void markItem(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        data.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + data.get(index - 1));
    }

    private static void unMarkItem(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        data.get(index - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + data.get(index - 1));

    }

    private static void deleteItem(String[] inputs) {
        int index = Integer.parseInt(inputs[1]);
        Task taskRemoved = data.remove(index - 1);
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n",
                taskRemoved, data.size());
    }

    private static <T> int findElem(T[] arr, T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String command = sc.nextLine();
                String[] inputs = command.split(" ");
                if (inputs.length == 1) {
                    if (command.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (command.equals("list")) {
                        listItems();
                    } else if (UNKNOWN_COMMANDS.contains(command)) {
                        throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else if (inputs.length == 2) {
                    if (inputs[0].equals("mark")) {
                        markItem(inputs);
                    } else if (inputs[0].equals("unmark")) {
                        unMarkItem(inputs);
                    } else if (inputs[0].equals("delete")) {
                        deleteItem(inputs);
                    }
                } else {
                    switch (inputs[0]) {
                        case "todo": {
                            addTodo(inputs);
                            break;
                        }
                        case "deadline": {
                            addDeadLine(inputs);
                            break;
                        }
                        case "event": {
                            addEvent(inputs);
                            break;
                        }
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
