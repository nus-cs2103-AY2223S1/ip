package duke;

import java.io.IOException;

/**
 * Main driver class for the chatbot
 */
public class Dukebot {
    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private Ui ui;


    /**
     * Initializes necessary components of the chatbot
     */
    public Dukebot(Ui ui) {
        this.ui = ui;
        this.handleStartup();
    }

    private void handleStartup() {
        this.parser = new Parser();
        ui.display(Messages.STARTUP);
        try {
            this.storage = new Storage();
            storage.createStorage();
            this.taskList = storage.loadFromStorage();
        } catch (IOException e) {
            ui.display(ExceptionMessages.LOAD_ERROR);
            this.taskList = new TaskList();
        }
    }

    private void handleBye() {
        ui.display(Messages.ENDING);
        System.exit(0);
    }

    private void handleList() {
        ui.display(Messages.LIST_TASKS);
        for (int i = 0; i < this.taskList.getSize(); i++) {
            ui.display((i + 1) + "." + taskList.get(i).toString());
        }
    }

    private void handleRemove(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        ui.display(Messages.TASK_REMOVED);
        ui.display(taskList.get(index).toString());
        taskList.remove(Integer.parseInt(inputArgs[1]) - 1);
        ui.display(String.format(Messages.TASK_COUNT, taskList.getSize()));
    }

    private void handleMark(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        taskList.mark(index);
        ui.display(Messages.TASK_MARKED);
        ui.display(this.taskList.get(index).toString());
    }

    private void handleUnmark(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        taskList.get(index).markUndone();
        ui.display(Messages.TASK_UNMARKED);
        ui.display(taskList.get(index).toString());
    }

    private void handleTodo(String[] inputArgs) throws DukeException {
        Todo t = parser.parseTodo(inputArgs);
        taskList.add(t);
        ui.display(Messages.TASK_ADDED);
        ui.display(taskList.get(taskList.getSize() - 1).toString());
        ui.display(String.format(Messages.TASK_COUNT, taskList.getSize()));
    }

    private void handleDeadline(String[] inputArgs) throws DukeException {
        Deadline d = parser.parseDeadline(inputArgs);
        taskList.add(d);
        ui.display(Messages.TASK_ADDED);
        ui.display(taskList.get(taskList.getSize() - 1).toString());
        ui.display(String.format(Messages.TASK_COUNT, taskList.getSize()));
    }

    private void handleEvent(String[] inputArgs) throws DukeException {
        Event e = parser.parseEvent(inputArgs);
        taskList.add(e);
        ui.display(Messages.TASK_ADDED);
        ui.display(taskList.get(taskList.getSize() - 1).toString());
        ui.display(String.format(Messages.TASK_COUNT, taskList.getSize()));
    }

    /**
     * Handles listing all tasks matching a given string
     * @param inputArgs the full command entered by the user
     * @throws DukeException if there command is incorrectly formatted
     */
    private void handleFind(String[] inputArgs) throws DukeException {
        String key = parser.parseFind(inputArgs);
        ui.display(String.format(Messages.FIND_TASKS, key));
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.get(i).getDescription().contains(key)) {
                ui.display(taskList.get(i).toString());
            }
        }
    }
    /**
     * Overarching handler for commands entered by the user
     * @param input a full command entered by the user
     */
    protected void handleInput(String input) {
        String[] inputArgs = input.split("\\s+", 2);
        String keyWord = inputArgs[0];
        try {
            switch (keyWord) {
            case "bye":
                handleBye();
            case "list":
                handleList();
                break;
            case "remove":
                handleRemove(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "mark":
                handleMark(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "unmark":
                handleUnmark(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "todo":
                handleTodo(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "deadline":
                handleDeadline(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "event":
                handleEvent(inputArgs);
                storage.writeToStorage(taskList);
                break;
            case "find":
                handleFind(inputArgs);
            default:
                throw new DukeException(ExceptionMessages.UNSUPPORTED_ACTION);
            }
        } catch (DukeException e) {
            ui.display(e.getMessage());
        } catch (IOException e) {
            ui.display("Error writing to storage");
        }

    }

}
