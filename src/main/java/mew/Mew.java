package mew;

import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.Timer;
import java.util.TimerTask;

import component.Command;
import component.Parser;
import component.Storage;
import component.Task;
import component.TaskList;
import component.Ui;
import javafx.application.Platform;
import mew.MewDateTimeParseException.InputOverFlowException;
import mew.MewDateTimeParseException.InvalidDateTimeFormatException;

/**
 * Main class Mew that runs the application.
 */
public class Mew {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new class Mew.
     * @param filePath Path of the storage.
     */
    public Mew(Path filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception exception) {
            exception.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Checks whether the input given is an end input.
     * @param input Input given by the user
     * @return Boolean result of whether input is an end input or not
     */
    public boolean isEndInput(String input) {
        return Parser.parse(input).equals(Command.EXIT);
    }

    /**
     * Lists the available tasks.
     * @param taskList Task list
     * @return Response of UI
     */
    public String listTasks(TaskList taskList) {
        return Ui.showTaskList(taskList);
    }

    /**
     * Marks a task at given index and saves the change to local storage.
     * @param input Input given by user
     * @return Response of UI
     */
    public String markTask(String input) {
        try {
            int taskIndex = Parser.getTaskIndex(input);
            Task markedTask = tasks.markTask(taskIndex - 1);
            storage.saveData(tasks);
            return Ui.showMarkDone(markedTask);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return Ui.askWhichTask();
        } catch (Exception e) {
            return Ui.printException(e);
        }
    }

    /**
     * Unmarks a task at given index and saves the change to local storage.
     * @param input Input given by user
     * @return Response of UI
     */
    public String unmarkTask(String input) {
        try {
            int taskIndex = Parser.getTaskIndex(input);
            Task unmarkedTask = tasks.unmarkTask(taskIndex - 1);
            storage.saveData(tasks);
            return Ui.showUnmarkAsDone(unmarkedTask);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return Ui.askWhichTask();
        } catch (Exception e) {
            return Ui.printException(e);
        }
    }

    /**
     * Deletes a task at given index and saves the change to local storage.
     * @param input Input given by user
     * @return Response of UI
     */
    public String deleteTask(String input) {
        try {
            int taskIndex = Parser.getTaskIndex(input);
            Task deletedTask = tasks.deleteTask(taskIndex - 1);
            storage.saveData(tasks);
            return Ui.showDeleteTaskDone(deletedTask, tasks.getNumberOfTasks());
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return Ui.askWhichTask();
        } catch (Exception e) {
            return Ui.printException(e);
        }
    }

    /**
     * Creates an Event and saves the change to local storage.
     * @param input Input given by user
     * @return Response of UI
     */
    public String createEvent(String input) {
        try {
            Task newTask = Parser.parseTask(input, "E");
            tasks.addTask(newTask);
            storage.saveData(tasks);
            return Ui.showAddTaskDone(newTask, tasks.getNumberOfTasks());
        } catch (InvalidDateTimeFormatException | InputOverFlowException e) {
            System.out.println("input datetime trouble");
            return Ui.showInvalidDatetimeInput();
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            System.out.println("empty description event");
            return Ui.showEmptyEventDescriptionWarning();
        } catch (Exception e) {
            System.out.println("other exceptions");
            return Ui.printException(e);
        }
    }

    /**
     * Creates a Deadline and saves the change to local storage.
     * @param input Input given by user
     * @return Response of UI
     */
    public String createDeadline(String input) {
        try {
            Task newTask = Parser.parseTask(input, "D");
            tasks.addTask(newTask);
            storage.saveData(tasks);
            return Ui.showAddTaskDone(newTask, tasks.getNumberOfTasks());
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.showEmptyDeadlineDescriptionWarning();
        } catch (InvalidDateTimeFormatException | InputOverFlowException | DateTimeParseException e) {
            return Ui.showInvalidDatetimeInput();
        } catch (Exception e) {
            return Ui.printException(e);
        }
    }

    /**
     * Creates a ToDo and saves the change to local storage.
     * @param input Input given by user
     * @return Response of UI
     */
    public String createToDo(String input) {
        try {
            Task newTask = Parser.parseTask(input, "T");
            tasks.addTask(newTask);
            storage.saveData(tasks);
            return Ui.showAddTaskDone(newTask, tasks.getNumberOfTasks());
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.showEmptyToDoDescriptionWarning();
        } catch (InvalidDateTimeFormatException | InputOverFlowException | DateTimeParseException e) {
            return Ui.showInvalidDatetimeInput();
        } catch (Exception e) {
            return Ui.printException(e);
        }
    }

    /**
     * Finds matching tasks according the keyword given that is contained in the input.
     * @param input Input given by user
     * @return Response of UI
     */
    public String findTask(String input) {
        String keyword = Parser.getKeyword(input);
        TaskList foundTasks = tasks.findTasks(keyword);
        return Ui.showFoundTasks(foundTasks);
    }

    /**
     * Edits description of a task according to the given input that contains the new description and the index of
     * the task to be edited.
     * @param input Input given by user
     * @return Response of UI
     */
    public String editTask(String input) {
        try {
            String newDescription = Parser.getNewDescription(input);
            int taskIndex = Parser.getTaskIndex(input);
            Task editedTask = tasks.editTaskDescription(taskIndex - 1, newDescription);
            return Ui.showEditTaskDone(editedTask);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return Ui.askWhichTask();
        } catch (Exception e) {
            return Ui.printException(e);
        }
    }

    /**
     * Processes unknown command by showing the UI response to unknown commands.
     * @param input Input given by the user
     * @return Response of UI
     */
    public String processUnknownCommand(String input) {
        return Ui.showUnknownCommand();
    }

    /**
     * Saves data to local storage and exits the application.
     * @return Exit response of UI
     */
    public String saveAndExit() {
        storage.saveData(tasks);
        return Ui.showExitStatement();
    }

    /**
     * Processes the response given from an input.
     * @param input Input given by the user
     * @return Response of Mew
     */
    public String processResponse(String input) {
        if (Parser.parse(input).equals(Command.LIST)) {
            return listTasks(tasks);
        } else if (Parser.parse(input).equals(Command.MARK)) {
            return markTask(input);
        } else if (Parser.parse(input).equals(Command.UNMARK)) {
            return unmarkTask(input);
        } else if (Parser.parse(input).equals(Command.DELETE)) {
            return deleteTask(input);
        } else if (Parser.parse(input).equals(Command.CREATE_EVENT)) {
            return createEvent(input);
        } else if (Parser.parse(input).equals(Command.CREATE_DEADLINE)) {
            return createDeadline(input);
        } else if (Parser.parse(input).equals(Command.CREATE_TODO)) {
            return createToDo(input);
        } else if (Parser.parse(input).equals(Command.FIND)) {
            return findTask(input);
        } else if (Parser.parse(input).equals(Command.EDIT)) {
            return editTask(input);
        } else if (Parser.parse(input).equals(Command.UNKNOWN)) {
            return processUnknownCommand(input);
        }
        return saveAndExit();
    }

    /**
     * Ends the application.
     */
    public void endApplication() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 700);
    }
}
