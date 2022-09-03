package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import javafx.scene.layout.VBox;
import parser.DateTimeParser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>Command class</h1>
 * Filters out the commandType and runs the executes the appropriate
 * command based on the input commandType.
 */
public abstract class Command {
    protected TaskList tasks;
    protected String input;
    protected Ui ui;

    public Command(TaskList tasks, String input, Ui ui) {
        this.tasks = tasks;
        this.input = input;
        this.ui = ui;
    }

    public abstract void execute(VBox dialogContainer, DialogBox userDialog, Storage storage) throws DukeException;
}
//    private static final String EMPTY_KEYWORD_ERROR_MESSAGE = "Eh your keyword cannot be empty lah!";
//    private static final String INCORRECT_TASK_NUMBER_ERROR_MESSAGE = "Eh, you enter your task number correctly anot?";
//    private static final String INVALID_TASK_NUMBER_ERROR_MESSAGE = "Eh, you got that task number meh?";
//    private static final String NO_MATCHING_TASKS_MESSAGE = "Boss, no matching tasks ah";
//    private static final String TASK_ALREADY_UNMARKED_ERROR_MESSAGE = "Eh, your task alr not done lah";
//    private static final String TASK_ALREADY_COMPLETED_ERROR_MESSAGE = "Eh, you done that task alr lah";
//    private static final String LIST_TASKS_MESSAGE = "Boss ah, this one your tasks:";
//    private static final String NO_TASKS_MESSAGE = "Boss, you got no task yet ah";
//    private static final String EMPTY_TASK_NAME_ERROR_MESSAGE = "Eh you never added your task name";

//    private static final String FIND_TASKS_PREFIX_MESSAGE = "Boss ah, this one all your tasks matching '";
//    private static final String FIND_TASKS_SUFFIX_MESSAGE = "' : ";
//    private static final String EVENT_COMMAND_REGEX = " /at ";
//    private static final String DEADLINE_COMMAND_REGEX = " /by ";
//    private CommandType commandType;
//    private TaskList tasks;
//    private String input;
//    private Ui ui;
//
//    /**
//     * Creates a Command object to be executed.
//     *
//     * @param commandType type of command to be executed.
//     * @param tasks list of Tasks the user currently has.
//     * @param input the String to be parsed.
//     * @param ui the User Interface that prints out the output.
//     */
//    public Command(CommandType commandType, TaskList tasks, String input, Ui ui) {
//        this.commandType = commandType;
//        this.tasks = tasks;
//        this.input = input;
//        this.ui = ui;
//    }
//
//    private TaskList getMatchingTasks(String keyword) {
//        TaskList newTaskList = new TaskList();
//        for (int i = 0; i < tasks.getSize(); i++) {
//            if (tasks.containsKeyword(i, keyword)) {
//                newTaskList.add(tasks.get(i));
//            }
//        }
//        return newTaskList;
//    }
//
//    private void addTask(Task input) {
//        tasks.add(input);
//    }
//
//    private LocalDateTime stringToLocalDateTime(String deadline) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
//        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
//        return deadlineDateTime;
//    }
//
//    private boolean hasTaskIndex(int taskIndex) {
//        return taskIndex - 1 < this.tasks.getSize() && taskIndex - 1 >= 0;
//    }
//
//    /**
//     * Returns a boolean describing whether the CommandType is a "Bye" Command.
//     *
//     * @return boolean describing whether the CommandType is a "Bye" Command.
//     */
//    public boolean isExit() {
//        return commandType == CommandType.BYE;
//    }

//    /**
//     * Executes the Command based on the CommandType and updates the Ui.
//     *
//     * @param dialogContainer the VBox to be updated with the corresponding DialogBox.
//     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
//     * @throws DukeException If an exception is encountered or if
//     *         the input is invalid.
//     */
//    public void execute(VBox dialogContainer, DialogBox userDialog) throws DukeException {
//        switch (commandType) {
//        case BYE:
//            break;
//        case TODO:
//            parseAndExecuteTodo(dialogContainer, userDialog);
//            break;
//        case EVENT:
//            parseAndExecuteEvent(dialogContainer, userDialog);
//            break;
//        case DEADLINE:
//            parseAndExecuteDeadline(dialogContainer, userDialog);
//            break;
//        case EMPTY:
//            throw new DukeException(EMPTY_USER_INPUT_ERROR_MESSAGE);
//        case MARK:
//            parseAndExecuteMarkCommand(dialogContainer, userDialog);
//            break;
//        case UNMARK:
//            parseAndExecuteUnmarkCommand(dialogContainer, userDialog);
//            break;
//        case DELETE:
//            parseAndExecuteDeleteCommand(dialogContainer, userDialog);
//            break;
//        case FIND:
//            parseAndExecuteFindCommand(dialogContainer, userDialog);
//            break;
//        default:
//            throw new DukeException(DEFAULT_ERROR_MESSAGE);
//        }
//    }
//
//    private void parseAndExecuteListCommand(TaskList tasks, String listTasksMessage,
//                                            String noTasksMessage, VBox dialogContainer, DialogBox userDialog) {
//        ui.printTasks(tasks, listTasksMessage, noTasksMessage, dialogContainer, userDialog);
//    }
//
//    private void parseAndExecuteEvent(VBox dialogContainer, DialogBox userDialog) throws DukeException {
//        boolean isEmptyEvent = isEmptyTask(5, EVENT_COMMAND_REGEX);
//        boolean isIncorrectEventRange = isInvalidTaskInput(5, EVENT_COMMAND_REGEX);
//        if (isEmptyEvent) {
//            throw new DukeException(EMPTY_TASK_NAME_ERROR_MESSAGE);
//        }
//        if (isIncorrectEventRange) {
//            throw new DukeException(EMPTY_EVENT_RANGE_ERROR_MESSAGE);
//        }
//        Event event = getEventFromInput(5, EVENT_COMMAND_REGEX);
//        this.addTask(event);
//        ui.printAddedTaskMessage(event, dialogContainer, userDialog);
//        ui.printTaskCountMessage(tasks, dialogContainer);
//    }
//
//    private Event getEventFromInput(int beginIndex, String commandRegex) {
//        String[] splitEvent = input.substring(beginIndex).split(commandRegex, 3);
//        LocalDateTime eventDateTime = DateTimeParser.changeStringToParsingDateTime(splitEvent[1].trim());
//        return new Event(splitEvent[0].trim(), false, eventDateTime);
//    }
//
//    private Deadline getDeadlineFromInput(int beginIndex, String commandRegex) {
//        String[] splitDeadline = input.substring(beginIndex).split(commandRegex, 3);
//        LocalDateTime deadlineDateTime = DateTimeParser.changeStringToParsingDateTime(splitDeadline[1].trim());
//        return new Deadline(splitDeadline[0].trim(), false, deadlineDateTime);
//    }
//
//    private void parseAndExecuteDeadline(VBox dialogContainer, DialogBox userDialog) throws DukeException {
//        boolean isEmptyDeadline = isEmptyTask(8, DEADLINE_COMMAND_REGEX);
//        boolean isIncorrectDeadlineDate = isInvalidTaskInput(8, DEADLINE_COMMAND_REGEX);
//        if (isEmptyDeadline) {
//            throw new DukeException(EMPTY_TASK_NAME_ERROR_MESSAGE);
//        } else if (isIncorrectDeadlineDate) {
//            throw new DukeException(EMPTY_DEADLINE_ERROR_MESSAGE);
//        }
//        try {
//            Deadline deadline = getDeadlineFromInput(8, DEADLINE_COMMAND_REGEX);
//            this.addTask(deadline);
//            ui.printAddedTaskMessage(deadline, dialogContainer, userDialog);
//            ui.printTaskCountMessage(tasks, dialogContainer);
//        } catch (DateTimeParseException e) {
//            throw new DukeException(INVALID_INPUT_DEADLINE_ERROR_MESSAGE);
//        }
//    }
//
//    private void parseAndExecuteMarkCommand(VBox dialogContainer, DialogBox userDialog) throws DukeException {
//        String markIndexString = input.substring(4).trim();
//        try {
//            int taskIndex = Integer.parseInt(markIndexString);
//            if (!hasTaskIndex(taskIndex)) {
//                throw new DukeException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
//            }
//            if (!this.tasks.get(taskIndex - 1).canChangeIsDone(true)) {
//                throw new DukeException(TASK_ALREADY_COMPLETED_ERROR_MESSAGE);
//            }
//            this.tasks.get(taskIndex - 1).changeIsDone(true);
//            ui.printMarkedMessage(this.tasks.get(taskIndex - 1), dialogContainer, userDialog);
//        } catch (NumberFormatException e) {
//            throw new DukeException(INCORRECT_TASK_NUMBER_ERROR_MESSAGE);
//        }
//    }
//
//    private void parseAndExecuteUnmarkCommand(VBox dialogContainer, DialogBox userDialog) throws DukeException {
//        String unmarkIndexString = input.substring(6).trim();
//        try {
//            int taskIndex = Integer.parseInt(unmarkIndexString);
//            if (!hasTaskIndex(taskIndex)) {
//                throw new DukeException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
//            }
//            if (!this.tasks.get(taskIndex - 1).canChangeIsDone(false)) {
//                throw new DukeException(TASK_ALREADY_UNMARKED_ERROR_MESSAGE);
//            }
//            this.tasks.get(taskIndex - 1).changeIsDone(false);
//            ui.printUnmarkedMessage(this.tasks.get(taskIndex - 1), dialogContainer, userDialog);
//        } catch (NumberFormatException e) {
//            throw new DukeException(INCORRECT_TASK_NUMBER_ERROR_MESSAGE);
//        }
//    }
//
//    private void parseAndExecuteDeleteCommand(VBox dialogContainer, DialogBox userDialog) throws DukeException {
//        String taskIndexToDelete = input.substring(6).trim();
//        try {
//            int taskIndex = Integer.parseInt(taskIndexToDelete);
//            if (!hasTaskIndex(taskIndex)) {
//                throw new DukeException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
//            }
//            ui.printDeletedTaskMessage(tasks.get(taskIndex - 1), dialogContainer, userDialog);
//            tasks.remove(taskIndex - 1);
//        } catch (NumberFormatException e) {
//            throw new DukeException(INCORRECT_TASK_NUMBER_ERROR_MESSAGE);
//        }
//    }
//
//    private void parseAndExecuteFindCommand(VBox dialogContainer, DialogBox userDialog) throws DukeException {
//        String keyword = input.substring(4).trim();
//        if (keyword.equals("")) {
//            throw new DukeException(EMPTY_KEYWORD_ERROR_MESSAGE);
//        }
//        TaskList newTaskList = getMatchingTasks(keyword);
//        ui.printTasks(newTaskList, FIND_TASKS_PREFIX_MESSAGE + keyword
//                + FIND_TASKS_SUFFIX_MESSAGE, NO_MATCHING_TASKS_MESSAGE, dialogContainer, userDialog);
//    }
//
//    private void parseAndExecuteTodo(VBox dialogContainer, DialogBox userDialog) throws DukeException {
//        String todoDescription = input.substring(4).trim();
//        if (todoDescription.equals("")) {
//            throw new DukeException(EMPTY_TASK_NAME_ERROR_MESSAGE);
//        }
//        Todo todo = new Todo(todoDescription, false);
//        this.addTask(todo);
//        ui.printAddedTaskMessage(todo, dialogContainer, userDialog);
//        ui.printTaskCountMessage(tasks, dialogContainer);
//    }
//
//    private boolean isEmptyTask(int beginIndex, String commandRegex) {
//        String[] splitTask = input.substring(beginIndex).split(commandRegex, 3);
//        return splitTask.length == 2 && splitTask[0].trim().equals("");
//    }
//
//    private boolean isInvalidTaskInput(int beginIndex, String commandRegex) {
//        String[] splitInput = input.substring(beginIndex).split(commandRegex, 3);
//        return splitInput.length != 2 || splitInput[0].trim().equals("")
//                || splitInput[1].trim().equals("");
//
//    }
