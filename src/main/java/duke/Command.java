package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An abstraction for a command to Duke.
 */
public enum Command {
    DEADLINE("deadline"),
    DELETE("delete"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    TODO("todo"),
    UNMARK("unmark");

    private String INPUT_DATE_FORMAT = "dd-MM-yyyy";

    private final String name;
    Command(String name) {
        this.name = name;
    }

    /**
     * Runs the command with the given Duke bot.
     *
     * @param args Command arguments.
     * @param duke Duke bot executing the command.
     */
    public void run(String args, Duke duke) {
        UI ui = duke.getUI();
        TaskList taskList = duke.getTaskList();
        Storage storage = duke.getStorage();

        if (!isCorrectUse(args, duke)) {
            return;
        }
        switch(name) {
            case "delete":
                int index = Integer.parseInt(args) - 1;
                Task t = taskList.deleteTask(index);
                ui.printTaskDeleted(t, taskList.getCount());
                try {
                    storage.rewriteFile(taskList.getTasks());
                } catch (IOException e) {
                    ui.printError("Unable to write to file.");
                }
                break;

            case "list":
                ui.printTasks(duke.getTaskList().getTasks());
                break;

            case "deadline":
                String[] temp = args.split(" /by ", 2);
                t = new Deadline(temp[0], LocalDate.parse(temp[1],
                        DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));
                taskList.addTask(t);
                ui.printTaskAdded(t, taskList.getCount());
                try {
                    storage.appendTaskToFile(t);
                } catch (IOException e) {
                    ui.printError("Unable to write to file.");
                }
                break;

            case "event":
                temp = args.split(" /at ", 2);
                t = new Event(temp[0], LocalDate.parse(temp[1],
                        DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));
                taskList.addTask(t);
                ui.printTaskAdded(t, taskList.getCount());
                try {
                    storage.appendTaskToFile(t);
                } catch (IOException e) {
                    ui.printError("Unable to write to file.");
                }
                break;

            case "todo":
                t = new ToDo(args);
                taskList.addTask(t);
                ui.printTaskAdded(t, taskList.getCount());
                try {
                    storage.appendTaskToFile(t);
                } catch (IOException e) {
                    ui.printError("Unable to write to file.");
                }
                break;

            case "mark":

            case "unmark":
                index = Integer.parseInt(args) - 1;
                t = taskList.getTasks().get(index);
                boolean b = name.equals("mark");
                taskList.markTask(t, b);
                ui.printTaskMarked(t, b);
                try {
                    storage.rewriteFile(taskList.getTasks());
                } catch (IOException e) {
                    ui.printError("Unable to write to file.");
                }
                break;
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isCorrectUse(String args, Duke duke) {
        UI ui = duke.getUI();
        TaskList taskList = duke.getTaskList();
        String usage = correctUsage();
        switch(name) {
            case "delete":
                if (args.isEmpty()) {
                    ui.printError("Please specify a task number.\n\n"
                            + usage);
                    return false;
                }
                return true;

            case "list":
                if (!args.isEmpty()) {
                    ui.printError("'list' expects no arguments.");
                    return false;
                }
                return true;

            case "deadline":
                String[] temp = args.split(" /by ", 2);
                if (temp.length < 2) {
                    ui.printError("Please specify a task and deadline.\n\n"
                            + usage);
                    return false;
                } else if (!isValidDate(temp[1])) {
                    ui.printError("Please specify the due date in the right format.\n\n"
                            + usage);
                    return false;
                }
                return true;

            case "event":
                temp = args.split(" /at ", 2);
                if (temp.length < 2) {
                    ui.printError("Please specify an event and date.\n\n"
                            + usage);
                    return false;
                } else if (!isValidDate(temp[1])) {
                    ui.printError("Please specify the event date in the right format.\n\n"
                            + usage);
                    return false;
                }
                return true;

            case "todo":
                if (args.isEmpty()) {
                    ui.printError("Please specify a task.\n\n" + usage);
                    return false;
                }
                return true;

            case "mark":

            case "unmark":
                if (args.isEmpty()) {
                    ui.printError("Please specify a task number\n\n" + usage);
                } else {
                    try {
                        int index = Integer.parseInt(args) - 1;
                        if (index < 0 || index >= taskList.getCount()) {
                            ui.printError("Please specify a valid task number.\n"
                                    + "There are " + taskList.getCount()
                                    + " task(s) in the list.\n\n" + usage);
                            return false;
                        }
                        return true;
                    } catch (NumberFormatException e) {
                        ui.printError("Please specify a task number.\n\n"
                                + "\"" + args + "\"" + " is not an item number\n" + usage);
                        return false;
                    }
                }
        }
        return false;
    }

    private String correctUsage() {
        String ret = "Correct usage: ";
        switch(name) {
            case "deadline":
                return ret + "deadline [task-description] /by [DD-MM-YYYY]";

            case "delete":
                return ret + "delete [task-number]";

            case "event":
                return ret + "event [task-description] /at [DD-MM-YYYY]";

            case "mark":
                return ret + "mark [task-number]";

            case "todo":
                return ret + "todo [task-description]";

            case "unmark":
                return ret + "unmark [task-number]";
        }
        return "";
    }
}
