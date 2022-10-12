package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.concurrent.CompletableFuture;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import javafx.application.Platform;

/**
 * Parser class to manage user inputs
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor Method for Parser class
     * @param s
     * @param tasks
     */
    public Parser(Storage s, TaskList tasks) {
        this.storage = s;
        this.tasks = tasks;
    }

    /**
     * List of commands that the user can enter
     */
    enum Command {
        list,
        bye,
        mark,
        unmark,
        delete,
        deadline,
        todo,
        event,
        find,
        remind
    }

    /**
     * Parses the command entered by the user and prints out the output required
     * @param command
     * @return true if the command is by, else returns false
     */
    public String parse(String[] command) {
        try {
            Command cmd = Command.valueOf(command[0]);
            switch (cmd) {
            case bye:
                return parseBye();
            case list:
                return parseList();
            case mark:
                return parseMark(command);
            case event:
                return parseEvent(command);
            case todo:
                return parseTodo(command);
            case delete:
                return parseDelete(command);
            case unmark:
                return parseUnmark(command);
            case deadline:
                return parseDeadline(command);
            case find:
                return parseFind(command);
            case remind:
                return parseRemind();
            default:
                return "Invalid Command";
            }
        } catch (IllegalArgumentException e) {
            return "Invalid Command";
        }
    }

    private String parseList() {
        return tasks.list();
    }

    private String parseBye() {
        String result = "Bye! Shutting Down...";
        storage.write(tasks);
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Platform.exit();
        });
        return result;
    }

    private String parseMark(String[] command) {
        int index = Integer.parseInt(command[1]) - 1;
        return tasks.mark(index);
    }

    private String parseUnmark(String[] command) {
        int index = Integer.parseInt(command[1]) - 1;
        return tasks.unMark(index);
    }

    private String parseDelete(String[] command) {
        if (command.length < 2) {
            throw new DukeException("please specify which item to delete");
        }
        int index = Integer.parseInt(command[1]) - 1;
        return tasks.delete(index);
    }

    private String parseDeadline(String[] command) {
        try {
            String[] dl = command[1].split("/by ", 2);
            Deadline d = new Deadline(dl[0], LocalDate.parse(dl[1]));
            return tasks.add(d);
        } catch (ArrayIndexOutOfBoundsException arrException) {
            return "Invalid Format for deadline";
        } catch (DateTimeParseException dte) {
            return "Invalid Date Entered";
        }
    }

    private String parseTodo(String[] command) {
        try {
            Todo t = new Todo(command[1]);
            return tasks.add(t);
        } catch (ArrayIndexOutOfBoundsException err) {
            return "oops the description of a todo cannot be empty!";
        }
    }

    private String parseEvent(String[] command) {
        try {
            String[] desc = command[1].split("/at ", 2);
            assert desc[1] != null;
            String[] dl = command[1].split("/by ", 2);
            Event e = new Event(dl[0], LocalDate.parse(dl[1]));
            return tasks.add(e);
        } catch (ArrayIndexOutOfBoundsException aie) {
            return "Invalid time specified. You can specify a time with /at";
        }
    }

    private String parseFind(String[] command) {
        String item = command[1];
        return tasks.find(item);
    }

    private String parseRemind() {
        return tasks.dueSoon();
    }
}
