import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Task> taskList = new ArrayList<>();

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
        if (taskList.isEmpty()) {
            reply("You have no tasks in your list.");
            return;
        }
        String[] toReply = new String[taskList.size() + 1];
        toReply[0] = "Here are the tasks in your list.";
        for (int i = 0; i < taskList.size(); ++i) {
            toReply[i + 1] = String.format("%d. %s", i + 1, taskList.get(i));
        }
        reply(toReply);
    }

    public static void justAddedComment() {
        reply(new String[] {"Successfully added the following task",
                        taskList.get(taskList.size() - 1).toString(),
                        String.format("You now have %d tasks in the list.", taskList.size())});
    }

    /**
     * Append a Todo to the todoList.
     *
     * @param arguments The command arguments.
     */
    public static void todo(String[] arguments) throws IOException {
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
        taskList.add(new Todo(todoName.toString()));
        SaveFile.addData(taskList.get(taskList.size() - 1).toData());
        justAddedComment();
    }

    /**
     * Append a Deadline to the todoList.
     *
     * @param arguments The command arguments.
     */
    public static void deadline(String[] arguments) throws IOException {
        StringBuilder deadlineName = new StringBuilder();
        StringBuilder deadlineDeadlineString = new StringBuilder();
        boolean byFlagRead = false;
        for (int i = 1; i < arguments.length; ++i) {
            if (arguments[i].equals("/by") && !byFlagRead) {
                byFlagRead = true;
                continue;
            }
            if (byFlagRead) {
                if (deadlineDeadlineString.length() != 0) {
                    deadlineDeadlineString.append(' ');
                }
                deadlineDeadlineString.append(arguments[i]);
            } else {
                if (deadlineName.length() != 0) {
                    deadlineName.append(' ');
                }
                deadlineName.append(arguments[i]);
            }
        }
        if (deadlineName.length() == 0 || deadlineDeadlineString.length() == 0) {
            reply(new String[]{"Format the command as follows:",
                    "deadline <deadline name> /by <deadline>"});
            return;
        }
        LocalDateTime deadlineDeadline;
        try {
            deadlineDeadline = LocalDateTime
                .parse(deadlineDeadlineString.toString(), DateTimeFormatter.ofPattern("yyyy/M/d H:m:s"));
        } catch (DateTimeParseException e) {
            reply("Please format your date as \"year/month/date hour/minute/second\" (24 hour format).");
            return;
        }
        todoList.add(new Deadline(deadlineName.toString(), deadlineDeadline));
        SaveFile.addData(taskList.get(taskList.size() - 1).toData());
        justAddedComment();
    }

    /**
     * Append a Event to the todoList.
     *
     * @param arguments The command arguments.
     */
    public static void event(String[] arguments) throws IOException {
        StringBuilder eventName = new StringBuilder();
        StringBuilder eventStartTimeString = new StringBuilder();
        StringBuilder eventEndTimeString = new StringBuilder();
        int readMode = 0;
        for (int i = 1; i < arguments.length; ++i) {
            if (arguments[i].equals("/from")) {
                readMode = 1;
                continue;
            }
            if (arguments[i].equals("/to")) {
                readMode = 2;
                continue;
            }
            if (readMode == 0) {
                if (eventName.length() != 0) {
                    eventName.append(' ');
                }
                eventName.append(arguments[i]);
            } else if (readMode == 1) {
                if (eventStartTimeString.length() != 0) {
                    eventStartTimeString.append(' ');
                }
                eventStartTimeString.append(arguments[i]);
            } else if (readMode == 2) {
                if (eventEndTimeString.length() != 0) {
                    eventEndTimeString.append(' ');
                }
                eventEndTimeString.append(arguments[i]);
            }
        }
        if (eventName.length() == 0 || eventStartTimeString.length() == 0 || eventEndTimeString.length() == 0) {
            reply(new String[]{"Format the command as follows:",
                    "event <event name> /from <event start time> /to <event end time>"});
            return;
        }
        LocalDateTime eventStartTime, eventEndTime;
        try {
            eventStartTime = LocalDateTime
                .parse(eventStartTimeString.toString(), DateTimeFormatter.ofPattern("yyyy/M/d H:m:s"));
            eventEndTime = LocalDateTime
                .parse(eventEndTimeString.toString(), DateTimeFormatter.ofPattern("yyyy/M/d H:m:s"));
        } catch (DateTimeParseException e) {
            reply("Please format your date as \"year/month/date hour/minute/second\" (24 hour format).");
            return;
        }
        todoList.add(new Event(eventName.toString(), eventStartTime, eventEndTime));
        SaveFile.addData(taskList.get(taskList.size() - 1).toData());
        justAddedComment();
    }

    /**
     * Marks tasks as done. In the case of invalid arguments, it will reply
     * with the appropriate message.
     *
     * @param arguments The command arguments.
     */
    public static void mark(String[] arguments) throws IOException {
        if (taskList.isEmpty()) {
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
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", taskList.size()));
            return;
        }
        try {
            taskList.get(i).doTask();
            reply(new String[]{"Ok, I'm marking this as done",
                    taskList.get(i).toString()});
            SaveFile.getFileData().get(i).setNameData("done", "1");
            SaveFile.saveFile();
        } catch (IndexOutOfBoundsException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", taskList.size()));
        }
    }

    /**
     * Marks tasks as not done. In the case of invalid arguments, it will
     * reply with the appropriate message.
     *
     * @param arguments The command arguments.
     */
    public static void unmark(String[] arguments) throws IOException {
        if (taskList.isEmpty()) {
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
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", taskList.size()));
            return;
        }
        try {
            taskList.get(i).undo();
            reply(new String[]{"Ok, I'm marking this as not done",
                    taskList.get(i).toString()});
            SaveFile.getFileData().get(i).setNameData("done", "0");
            SaveFile.saveFile();
        } catch (IndexOutOfBoundsException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", taskList.size()));
        }
    }

    /**
     * Deletes a task.
     *
     * @param arguments The command arguments.
     */
    public static void delete(String[] arguments) {
        if (taskList.isEmpty()) {
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
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", taskList.size()));
            return;
        }
        try {
            reply(new String[]{"Ok, I'm deleting this",
                    taskList.get(i).toString()});
            taskList.remove(i);
        } catch (IndexOutOfBoundsException e) {
            reply(String.format("Invalid argument! (Please enter an integer between 1 and %d)", taskList.size()));
        }
    }

    /**
     * Lists the list of commands.
     */
    public static void mismatch() {
        reply("list of commands: list, mark, unmark, todo, deadline, event, delete");
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        SaveFile.loadSaveFile();
        for (SaveLine i : SaveFile.getFileData()) {
            if (i.getInfoType().equals("todo")) {
                taskList.add(new Todo(i));
                continue;
            }
            if (i.getInfoType().equals("deadline")) {
                taskList.add(new Deadline(i));
                continue;
            }
            if (i.getInfoType().equals("event")) {
                taskList.add(new Event(i));
                continue;
            }
        }
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
