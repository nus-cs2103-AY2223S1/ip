package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Handles user input.
 *
 * @author Yuvaraj Kumaresan
 */
public class Parser {

    /**
     * Stores tasks in a text file.
     */
    protected static Storage storage;

    /**
     * Handles User interaction.
     */
    protected static Ui ui;

    /**
     * Constructor.
     *
     * @param list The storage list used.
     * @param ui   The Ui interface used.
     */
    public Parser(Storage list, Ui ui) {
        this.storage = list;
        this.ui = ui;
    }

    /**
     * Handles delete command.
     *
     * @param text The command instructions.
     * @return Ui response to deletion.
     */
    private static String handleDelete(String text) {

        int deletable = Integer.parseInt(text.replace("delete ", "")) - 1;
        if (deletable < storage.taskList.arrayList.size() && deletable >= 0) {
            Task deleted = storage.taskList.arrayList.get(deletable);
            storage.taskList.delete(deletable);
            return ui.delete(deleted);
        } else {
            return ui.deleteError();
        }

    }

    /**
     * Handles deadline command.
     *
     * @param description The command instructions.
     * @return Ui response to deadline task.
     */
    private static String handleDeadline(String[] description) {
        if (description[1].length() > 10) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            Deadline item = new Deadline(description[0], LocalDateTime.parse(description[1], formatter));
            storage.taskList.addDeadline(item);
            return ui.deadline(item);
        } else {
            description[1] = description[1] + " 00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            Deadline item = new Deadline(description[0], LocalDateTime.parse(description[1], formatter));
            storage.taskList.addDeadline(item);
            return ui.deadline(item);
        }
    }

    /**
     * Handles todo command.
     *
     * @param text The command instructions.
     * @return Ui response to todo task.
     */
    private static String handleTodo(String text) {
        ToDo item = new ToDo(text.replace("todo ", ""));
        storage.taskList.addTodo(item);
        return ui.todo(item);
    }

    /**
     * Handles event command.
     *
     * @param text The command instructions.
     * @return Ui response to event task.
     */
    private static String handleEvent(String text) {
        String[] description = text.replace("event ", "").split("/at ");
        Event item = new Event(description[0], description[1]);
        storage.taskList.addEvent(item);
        return ui.event(item);
    }

    /**
     * Handles mark command.
     *
     * @param text The command instructions.
     * @return Ui response to marking a task.
     */
    private static String handleMark(String text) {
        if (Integer.parseInt(text.replace("mark ", "")) - 1 < storage.taskList.arrayList.size()
                && Integer.parseInt(text.replace("mark ", "")) > 0) {
            Task toBeMarked = storage.taskList.arrayList.get(Integer.parseInt(text.replace("mark ", "")) - 1);
            if (!toBeMarked.getIsDone()) {
                storage.taskList.mark(toBeMarked);
                return ui.mark(text);
            } else {
                return ui.alreadyMarked();
            }
        } else {
            return ui.noItemToMark();
        }
    }

    /**
     * Handles unmark command.
     *
     * @param text The command instructions.
     * @return Ui response to unmarking a task.
     */
    private static String handleUnmark(String text) {
        if (Integer.parseInt(text.replace("unmark ", "")) - 1 < storage.taskList.arrayList.size()
                && Integer.parseInt(text.replace("unmark ", "")) > 0) {
            Task toBeUnmarked = storage.taskList.arrayList.get(Integer.parseInt(text.replace("unmark ", "")) - 1);
            if (toBeUnmarked.getIsDone()) {
                storage.taskList.unmark(toBeUnmarked);
                return ui.unmark(text);
            } else {
                return ui.alreadyUnmarked();
            }
        } else {
            return ui.noItemToUnmark();
        }
    }

    /**
     * Handles find command.
     *
     * @param text The command instructions.
     * @return Ui response to finding a task.
     */
    private static String handleFind(String text) {
        if (text.startsWith("find ")) {
            String search = text.replace("find ", "");
            return ui.find(search);
        } else if (text.equalsIgnoreCase("find")) {
            return ui.noSearchError();
        } else {
            return ui.findError();
        }
    }

    /**
     * Handles task command.
     *
     * @param text The command instructions.
     * @return Ui response to creating a duration task.
     */
    private static String handleDuration(String text) {
        String[] description = text.replace("task ", "").split("/takes ");
        Duration item = new Duration(description[0], description[1]);
        storage.taskList.addDuration(item);
        return ui.duration(item);
    }

    /**
     * Handles deletion error.
     *
     * @param text The command instructions.
     * @return Ui response to deletion error.
     */
    private static String handleDelerror(String text) {
        if (text.equalsIgnoreCase("delete") || text.equalsIgnoreCase("delete ")) {
            return ui.deleteNoNumber();
        } else {
            return handleDelete(text);
        }
    }

    /**
     * Handles mark error.
     *
     * @param text The command instructions.
     * @return Ui response to marking error.
     */
    private static String handleMarkerror(String text) {
        if (text.equalsIgnoreCase("mark") || text.equalsIgnoreCase("mark ")) {
            return ui.noNumberToMark();
        } else {
            return handleMark(text);
        }
    }

    /**
     * Handles unmark error.
     *
     * @param text The command instructions.
     * @return Ui response to unmarking error.
     */
    private static String handleUnmarkerror(String text) {
        if (text.equalsIgnoreCase("unmark") || text.equalsIgnoreCase("unmark ")) {
            return ui.noNumberToUnmark();
        } else {
            return handleUnmark(text);
        }
    }

    /**
     * Wraps todo handlers.
     *
     * @param text Initial instruction from user.
     * @return Output from todo handlers
     */
    private static String handleTodowrapper(String text) {
        if (text.equalsIgnoreCase("todo") || text.equalsIgnoreCase("todo ") || text.replace("todo ", "").trim().length() < 1) {
            try {
                throw new DukeException.DukeToDoException("Please provide a description for your todo task.");
            } catch (DukeException.DukeToDoException error) {
                return ui.todoError(error);
            }
        } else {
            return handleTodo(text);
        }
    }

    /**
     * Wraps delete handlers.
     *
     * @param text Initial instruction from user.
     * @return Output from delete handlers
     */
    private static String handleDelwrapper(String text) {
        try {
            return handleDelerror(text);

        } catch (NumberFormatException error) {
            return ui.deleteNumberFormatError();
        }
    }

    /**
     * Wraps deadline handlers.
     *
     * @param text Initial instruction from user.
     * @return Output from deadline handlers
     */
    private static String handleDeadlinewrapper(String text) {
        try {
            String[] description = text.replace("deadline ", "").split("/by ");
            try {
                return handleDeadline(description);
            } catch (DateTimeParseException e) {
                return ui.dateTimeParseError();
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            return ui.dateTimeArrayException();
        }
    }

    /**
     * Wraps event handlers.
     *
     * @param text Initial instruction from user.
     * @return Output from event handlers
     */
    private static String handleEventwrapper(String text) {
        try {
            return handleEvent(text);
        } catch (ArrayIndexOutOfBoundsException error) {
            return ui.eventError();
        }
    }

    /**
     * Wraps mark handlers.
     *
     * @param text Initial instruction from user.
     * @return Output from mark handlers
     */
    private static String handleMarkwrapper(String text) {
        try {
            return handleMarkerror(text);
        } catch (NumberFormatException error) {
            return ui.markFormatError();
        }
    }

    /**
     * Wraps unmark handlers.
     *
     * @param text Initial instruction from user.
     * @return Output from unmark handlers
     */
    private static String handleUnmarkwrapper(String text) {
        try {
            return handleUnmarkerror(text);
        } catch (NumberFormatException error) {
            return ui.unmarkFormatError();
        }
    }

    /**
     * Wraps duration task handlers.
     *
     * @param text Initial instruction from user.
     * @return Output from duration task handlers
     */
    private static String handleDurationwrapper(String text) {
        try {
            return handleDuration(text);
        } catch (ArrayIndexOutOfBoundsException error) {
            return ui.durationError();
        }
    }

    /**
     * Handles parsing of text input by the user.
     *
     * @return
     * @throws IOException
     */
    public static String parse(String text) throws IOException {

        if (text.equalsIgnoreCase("list")) {
            return ui.list();

        } else if (text.equalsIgnoreCase("bye")) {
            return ui.bye();

        } else if (text.startsWith("delete")) {
            return handleDelwrapper(text);

        } else if (text.startsWith("task")) {
            return handleDurationwrapper(text);

        } else if (text.startsWith("todo")) {
            return handleTodowrapper(text);

        } else if (text.startsWith("deadline")) {
            return handleDeadlinewrapper(text);

        } else if (text.startsWith("event")) {
            return handleEventwrapper(text);

        } else if (text.startsWith("mark")) {
            return handleMarkwrapper(text);

        } else if (text.startsWith("unmark")) {
            return handleUnmarkwrapper(text);

        } else if (text.startsWith("help")) {
            return ui.help();

        } else if (text.startsWith("find")) {
            return handleFind(text);

        } else {
            try {
                throw new DukeException.DukeCommandException("Invalid command");
            } catch (DukeException.DukeCommandException error) {
                return ui.generalError(error);
            }
        }
    }

}
