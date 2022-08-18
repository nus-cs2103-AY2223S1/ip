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
     * @param message An array of Strings containing the messages for each line.
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

    public static void justAddedComment() {
        reply(new String[] {"Successfully added the following task",
                        todoList.get(todoList.size() - 1).toString(),
                        String.format("You now have %d tasks in the list.", todoList.size())});
    }

    /**
     * Append a Todo to the todoList.
     *
     * @param arguments The command arguments.
     */
    public static void todo(String[] arguments) {
        StringBuilder todoName = new StringBuilder();
        for (int i = 1; i < arguments.length; ++i) {
            if (todoName.length() != 0) {
                todoName.append(' ');
            }
            todoName.append(arguments[i]);
        }
        if (todoName.length() == 0) {
            reply("Please include a name");
            return;
        }
        todoList.add(new Todo(todoName.toString()));
        justAddedComment();
    }

    /**
     * Append a Deadline to the todoList.
     *
     * @param arguments The command arguments.
     */
    public static void deadline(String[] arguments) {
        StringBuilder deadlineName = new StringBuilder();
        StringBuilder deadlineDeadline = new StringBuilder();
        boolean byFlagRead = false;
        for (int i = 1; i < arguments.length; ++i) {
            if (arguments[i].equals("/by") && !byFlagRead) {
                byFlagRead = true;
                continue;
            }
            if (byFlagRead) {
                if (deadlineDeadline.length() != 0) {
                    deadlineDeadline.append(' ');
                }
                deadlineDeadline.append(arguments[i]);
            } else {
                if (deadlineName.length() != 0) {
                    deadlineName.append(' ');
                }
                deadlineName.append(arguments[i]);
            }
        }
        if (deadlineName.length() == 0 || deadlineDeadline.length() == 0) {
            reply(new String[]{"Format the command as follows:",
                    "deadline <deadline name> /by <deadline>"});
            return;
        }
        todoList.add(new Deadline(deadlineName.toString(), deadlineDeadline.toString()));
        justAddedComment();
    }

    /**
     * Append a Event to the todoList.
     *
     * @param arguments The command arguments.
     */
    public static void event(String[] arguments) {
        StringBuilder eventName = new StringBuilder();
        StringBuilder eventTime = new StringBuilder();
        boolean atFlagRead = false;
        for (int i = 1; i < arguments.length; ++i) {
            if (arguments[i].equals("/at") && !atFlagRead) {
                atFlagRead = true;
                continue;
            }
            if (atFlagRead) {
                if (eventTime.length() != 0) {
                    eventTime.append(' ');
                }
                eventTime.append(arguments[i]);
            } else {
                if (eventName.length() != 0) {
                    eventName.append(' ');
                }
                eventName.append(arguments[i]);
            }
        }
        if (eventName.length() == 0 || eventTime.length() == 0) {
            reply(new String[]{"Format the command as follows:",
                    "event <event name> /at <event time>"});
            return;
        }
        todoList.add(new Event(eventName.toString(), eventTime.toString()));
        justAddedComment();
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

    /**
     * Deletes a task.
     *
     * @param arguments The command arguments.
     */
    public static void delete(String[] arguments) {
        if (todoList.isEmpty()) {
            reply("You don't have any tasks to delete!");
            return;
        }
        int i;
        try {
            i = Integer.parseInt(arguments[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            reply("Please enter the item ID you wish to delete");
            return;
        } catch (NumberFormatException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", todoList.size()));
            return;
        }
        try {
            reply(new String[]{"Ok, I'm deleting this",
                    todoList.get(i).toString()});
            todoList.remove(i);
        } catch (IndexOutOfBoundsException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", todoList.size()));
        }
    }

    /**
     * Lists the list of commands.
     */
    public static void mismatch() {
        reply("list of commands: list, mark, unmark, todo, deadline, event, delete");
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
            if (arguments[0].equals("todo")) {
                todo(arguments);
                continue;
            }
            if (arguments[0].equals("deadline")) {
                deadline(arguments);
                continue;
            }
            if (arguments[0].equals("event")) {
                event(arguments);
                continue;
            }
            if (arguments[0].equals("delete")) {
                delete(arguments);
                continue;
            }
            mismatch();
        }
    }
}
