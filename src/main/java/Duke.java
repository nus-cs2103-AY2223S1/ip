import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Task> todoList = new ArrayList<>();

    /**
     * Reads a line.
     *
     * @return A String containing the line.
     */
    public static String readLine() {
        return scanner.nextLine();
    }

    /**
     * Splits a command line into arguments.
     *
     * @param line The command line String.
     * @return A String array of arguments.
     */
    public static String[] parse(String line) {
        return line.split(" ");
    }

    /**
     * Prints a one line reply with the appropriate style.
     *
     * @param message A string of the one line reply message.
     */
    public static void reply(String message) {
        System.out.print("> ");
        System.out.println(message);
    }

    /**
     * Prints a multiline reply with the appropriate style.
     *
     * @param message An array of Strings containing the
     */
    public static void reply(String[] message) {
        for (int i = 0; i < message.length; ++i) {
            System.out.print(i == 0 ? "> " : "  ");
            System.out.println(message[i]);
        }
    }

    /**
     * Sends a goodbye message before closing dialogue.
     */
    public static void bye() {
        reply("Bye. Hope to see you again soon!");
    }

    /**
     * Lists the todo list.
     */
    public static void list() {
        if (todoList.isEmpty()) {
            reply("You have no tasks in your list.");
            return;
        }
        String[] toReply = new String[todoList.size() + 1];
        toReply[0] = "Here are the tasks in your list.";
        for (int i = 0; i < todoList.size(); ++i) {
            toReply[i + 1] = String.format("%d. %s", i + 1, todoList.get(i));
        }
        reply(toReply);
    }

    /**
     * Marks tasks as done. In the case of invalid arguments, it will reply
     * with the appropriate message.
     *
     * @param arguments The command arguments.
     */
    public static void mark(String[] arguments) {
        if (todoList.isEmpty()) {
            reply("You don't have any tasks to mark!");
            return;
        }
        int i;
        try {
            i = Integer.parseInt(arguments[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            reply("Please enter the item ID you wish to mark");
            return;
        } catch (NumberFormatException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", todoList.size()));
            return;
        }
        try {
            todoList.get(i).doTask();
            reply(new String[]{"Ok, I'm marking this as done",
                    todoList.get(i).toString()});
        } catch (IndexOutOfBoundsException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", todoList.size()));
        }
    }

    /**
     * Marks tasks as not done. In the case of invalid arguments, it will
     * reply with the appropriate message.
     *
     * @param arguments The command arguments.
     */
    public static void unmark(String[] arguments) {
        if (todoList.isEmpty()) {
            reply("You don't have any tasks to unmark!");
            return;
        }
        int i;
        try {
            i = Integer.parseInt(arguments[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            reply("Please enter the item ID you wish to unmark");
            return;
        } catch (NumberFormatException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", todoList.size()));
            return;
        }
        try {
            todoList.get(i).undo();
            reply(new String[]{"Ok, I'm marking this as not done",
                    todoList.get(i).toString()});
        } catch (IndexOutOfBoundsException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", todoList.size()));
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        reply("What can I do for you?");
        while (true) {
            String line = readLine();
            String[] arguments = parse(line);
            if (arguments[0].equals("bye")) {
                bye();
                break;
            }
            if (arguments[0].equals("list")){
                list();
                continue;
            }
            if (arguments[0].equals("mark")) {
                mark(arguments);
                continue;
            }
            if (arguments[0].equals("unmark")) {
                unmark(arguments);
                continue;
            }
            todoList.add(new Task(line));
            reply(String.format("added: %s", line));
        }
    }
}
