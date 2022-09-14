package Duke.Parser;

import Duke.Exception.DukeException;
import Duke.Task.Deadline;
import Duke.Task.Task;
import Duke.Task.ToDo;
import Duke.Task.Event;
import Duke.TaskList.TaskList;
import Duke.UI.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the object that converts user input into commands and tasks.
 */
public class Parser {
    private static final String END_COMMAND = "bye";
    private static final String PRINT_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    private static final String TODO_SEPARATOR = "/";
    private static final String DEADLINE_SEPARATOR = "/by";
    private static final String EVENT_SEPARATOR = "/at";

    private final Ui ui;
    private final TaskList taskList;

    private boolean isEnded = false;

    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public boolean hasNext() {
        return !isEnded;
    }

    /**
     * Recognises the command given by the user and calls the relevant handler to manage user input.
     */
    public void handleNext() {
        try {
            String inputCmd = ui.getNext().strip();
            String inputRem = ui.getNextLine().strip();
            switch (inputCmd) {
            case (END_COMMAND):
                closeParser();
                break;
            case (PRINT_COMMAND):
                printAllTasks();
                break;
            case (MARK_COMMAND):
                markTask(inputRem);
                break;
            case (UNMARK_COMMAND):
                unmarkTask(inputRem);
                break;
            case (TODO_COMMAND):
                createAndAddTodo(inputRem);
                break;
            case (DEADLINE_COMMAND):
                createAndAddDeadline(inputRem);
                break;
            case (EVENT_COMMAND):
                createAndAddEvent(inputRem);
                break;
            case (DELETE_COMMAND):
                deleteTask(inputRem);
                break;
            default:
                throw new DukeException("Unexpected task type!");
            }
        } catch (DukeException de) {
            ui.printException(de);
        }
    }

    private void closeParser() {
        this.isEnded = true;
    }

    private void printAllTasks() {
        this.ui.printAll(this.taskList);
    }

    private void markTask(String indexStr) throws DukeException {
        try {
            int index = Integer.parseInt(indexStr);
            Task task = taskList.markTask(--index);
            ui.printTaskMarked(task);
        } catch (NumberFormatException e) {
            throw DukeException.markTaskException("Please input a valid number!");
        } catch (DukeException de) {
            throw DukeException.markTaskException(de.toString());
        }
    }

    private void unmarkTask(String indexStr) throws DukeException {
        try {
            int index = Integer.parseInt(indexStr);
            Task task = taskList.unmarkTask(--index);
            ui.printTaskUnmarked(task);
        } catch (NumberFormatException e){
            throw DukeException.unmarkTaskException("Please input a valid number!");
        } catch (DukeException de) {
            throw DukeException.unmarkTaskException(de.toString());
        }
    }

    private void createAndAddTodo(String str) throws DukeException {
        try {
            String description = getValidDescription(str, TODO_SEPARATOR);
            Task newTodo = new ToDo(description);
            addTask(newTodo);
        } catch (DukeException de) {
            throw DukeException.createTaskException(de.toString());
        }
    }

    private void createAndAddDeadline(String str) throws DukeException {
        try {
            String description = getValidDescription(str, DEADLINE_SEPARATOR);

            LocalDateTime by = getValidDateTime(str, DEADLINE_SEPARATOR);

            Task newDeadline = new Deadline(description, by);
            addTask(newDeadline);
        } catch (DukeException de) {
            throw DukeException.createTaskException(de.toString());
        }
    }

    private void createAndAddEvent(String str) throws DukeException {
        try {
            String description = getValidDescription(str, EVENT_SEPARATOR);

            LocalDateTime at = getValidDateTime(str, EVENT_SEPARATOR);

            Task newEvent = new Event(description, at);
            addTask(newEvent);
        } catch (DukeException de) {
            throw DukeException.createTaskException(de.toString());
        }
    }

    private String getValidDescription(String str, String separator) throws DukeException {
        String description = getFirstToken(str, separator);
        if (description.equals("")) {
            throw DukeException.taskDescriptionException("No description provided");
        }
        return description;
    }

    private LocalDateTime getValidDateTime(String str, String separator) throws DukeException {
        String datetimeStr = getSecondToken(str, separator);
        if (datetimeStr.equals("")) {
            throw DukeException.taskDateTimeException("No date time provided");
        }
        try {
            return LocalDateTime.parse(
                    datetimeStr,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw DukeException.taskDateTimeException("Date time should be in the format yyyy-MM-dd HHmm!");
        }
    }

    private String getFirstToken(String str, String separator) throws DukeException {
        String[] tokens = tokenize(str, separator);
        return tokens[0].strip();
    }

    private String getSecondToken(String str, String separator) throws DukeException {
        String[] tokens = tokenize(str, separator);
        if (tokens.length < 2) {
            throw DukeException.dateTimeTokenException();
        }
        return tokens[1].strip();
    }

    private String[] tokenize(String str, String separator) throws DukeException {
        str = str.strip();
        if (str.equals("")) {
            throw DukeException.taskDescriptionException("No input");
        }
        String[] tokens = str.split(separator);
        if (tokens.length < 1 || tokens.length > 2) {
            throw DukeException.taskTokenException(tokens.length);
        }
        return tokens;
    }

    private void addTask(Task task) throws DukeException {
        taskList.addTask(task);
        ui.printTaskCreated(task);
    }
    private void deleteTask(String indexStr) throws DukeException {
        try {
            int index = Integer.parseInt(indexStr);
            Task task = taskList.deleteTask(--index);
            ui.printTaskDeleted(task);
        } catch (NumberFormatException e) {
            throw DukeException.deleteTaskException("Please input a valid number!");
        } catch (DukeException de) {
            throw DukeException.deleteTaskException(de.toString());
        }
    }
}
