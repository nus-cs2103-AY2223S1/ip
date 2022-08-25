package duke;

import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

public class Duke {
    private static boolean isAlive;
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;
    private static TaskList tasks;

    private static int checkTask(String id) {
        try {
            int task = Integer.parseInt(id);
            if (task > tasks.size() || task <= 0) {
                return -1;
            }
            return task;
        } catch (NumberFormatException e) {
            return -2;
        }
    }

    private static String handle(String command, String params) {
        switch (command) {
        case "list":
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < tasks.size(); ++i) {
                if (i != 0) {
                    out.append("\n");
                }
                out.append(i + 1).append(". ").append(tasks.getTask(i));
            }
            if (out.toString().equals("")) {
                return "No tasks left!";
            }
            return out.toString();
        case "mark":
            int markedTask = checkTask(params);
            if (markedTask < 0) {
                return "Invalid task number!";
            }
            tasks.getTask(markedTask - 1).markDone();
            storage.save(tasks);
            return "OK, this task is done:\n" + tasks.getTask(markedTask - 1);
        case "unmark":
            int unmarkedTask = checkTask(params);
            if (unmarkedTask < 0) {
                return "Invalid task number!";
            }
            tasks.getTask(unmarkedTask - 1).markUndone();
            storage.save(tasks);
            return "OK, this task is undone:\n" + tasks.getTask(unmarkedTask - 1);
        case "delete":
            int deleteTask = checkTask(params);
            if (deleteTask < 0) {
                return "Invalid task number!";
            }
            Task removedTask = tasks.deleteTask(deleteTask - 1);
            storage.save(tasks);
            return "OK, this task has been deleted:\n" + removedTask;
        case "bye":
            isAlive = false;
            return "Goodbye!";
        case "todo":
            if (params.equals("")) {
                return "Todo description can't be empty.";
            }
            tasks.add(new Todo(params));
            storage.save(tasks);
            return "Added new todo: " + tasks.getTask(tasks.size() - 1);
        case "deadline":
            if (params.equals("")) {
                return "Deadline description can't be empty.";
            }
            String[] splitDeadline = parser.splitOnFirst(params, " /by ");
            try {
                tasks.add(new Deadline(splitDeadline[0], splitDeadline[1]));
            } catch (DateTimeParseException e) {
                return "Invalid date! (yyyy-mm-dd)";
            }
            storage.save(tasks);
            return "Added new deadline: " + tasks.getTask(tasks.size() - 1);
        case "event":
            if (params.equals("")) {
                return "Event description can't be empty.";
            }
            String[] splitEvent = parser.splitOnFirst(params, " /at ");
            try {
                tasks.add(new Event(splitEvent[0], splitEvent[1]));
            } catch (DateTimeParseException e) {
                return "Invalid date! (yyyy-mm-dd)";
            }
            storage.save(tasks);
            return "Added new event: " + tasks.getTask(tasks.size() - 1);
        default:
            return "I don't know what '" + command + "' is!";
        }
    }

    public static void main(String[] args) {

        ui = new Ui();
        storage = new Storage("tasks.txt");
        parser = new Parser();
        tasks = storage.load();

        ui.respond("Hello! You have " + tasks.size() + " tasks. What can I do for you today?");

        isAlive = true;
        while (isAlive) {
            try {
                String in;
                in = ui.getLine();
                String[] splits = parser.splitOnFirst(in, " ");
                ui.respond(handle(splits[0], splits[1]));
            } catch (NoSuchElementException e) {
                isAlive = false;
                ui.respond(handle("bye",""));
            }
        }
    }
}
