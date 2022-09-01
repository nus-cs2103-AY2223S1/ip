package duke;

import duke.messages.ExceptionMessages;
import duke.messages.ResponseMessages;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.IOException;

/**
 * duke.Main driver class for the chatbot
 */
public class Dukebot {
    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private TextUi ui;
    private boolean isExit;


    /**
     * Initializes necessary components of the chatbot
     */
    public Dukebot() {
        this.isExit = false;
        this.ui = new TextUi();
        this.handleStartup();
    }

    /**
     * The running loop of a bot instance.
     */
    public void run() {
        while(!isExit) {
            String input = ui.getInput();
            handleInput(input);
        }
    }

    public boolean isExit() {
        return isExit;
    }

    public String getResponse(String input) {
        return handleInput(input);
    }

    public String handleStartup() {
        this.parser = new Parser();
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.STARTUP);
        try {
            this.storage = new Storage();
            storage.createStorage();
            this.taskList = storage.loadFromStorage();
            sb.append(handleList());
            return sb.toString();
        } catch (IOException e) {
            sb.append(ExceptionMessages.LOAD_ERROR);
            this.taskList = new TaskList();
            return sb.toString();
        }
    }

    private String handleBye() {
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.ENDING);
        isExit = true;
        return sb.toString();
        // Not necessary to save to storage here as saving is done for every TaskList modification.
    }

    private String handleList() {
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.LIST_TASKS);
        for (int i = 0; i < this.taskList.getSize(); i++) {
            sb.append(i + 1);
            sb.append(".");
            sb.append(taskList.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    private String handleRemove(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        taskList.remove(Integer.parseInt(inputArgs[1]) - 1);
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.TASK_REMOVED);
        sb.append(taskList.get(index).toString());
        sb.append(String.format(ResponseMessages.TASK_COUNT, taskList.getSize()));
        sb.append("\n");
        return sb.toString();
    }

    private String handleMark(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        taskList.mark(index);
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.TASK_MARKED);
        sb.append(taskList.get(index).toString());
        sb.append("\n");
        return sb.toString();
    }

    private String handleUnmark(String[] inputArgs) throws DukeException {
        int index = parser.parseIndex(inputArgs);
        taskList.unmark(index);
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.TASK_UNMARKED);
        sb.append(taskList.get(index).toString());
        sb.append("\n");
        return sb.toString();
    }

    private String handleTodo(String[] inputArgs) throws DukeException {
        Todo t = parser.parseTodo(inputArgs);
        taskList.add(t);
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.TASK_ADDED);
        sb.append(taskList.get(taskList.getSize() - 1).toString());
        sb.append(String.format(ResponseMessages.TASK_COUNT, taskList.getSize()));
        sb.append("\n");
        return sb.toString();
    }

    private String handleDeadline(String[] inputArgs) throws DukeException {
        Deadline d = parser.parseDeadline(inputArgs);
        taskList.add(d);
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.TASK_ADDED);
        sb.append(taskList.get(taskList.getSize() - 1).toString());
        sb.append(String.format(ResponseMessages.TASK_COUNT, taskList.getSize()));
        sb.append("\n");
        return sb.toString();
    }

    private String handleEvent(String[] inputArgs) throws DukeException {
        Event e = parser.parseEvent(inputArgs);
        taskList.add(e);
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessages.TASK_ADDED);
        sb.append(taskList.get(taskList.getSize() - 1).toString());
        sb.append(String.format(ResponseMessages.TASK_COUNT, taskList.getSize()));
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Handles listing all tasks matching a given string
     * @param inputArgs the full command entered by the user
     * @throws DukeException if there command is incorrectly formatted
     */
    private String handleFind(String[] inputArgs) throws DukeException {
        String key = parser.parseFind(inputArgs);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ResponseMessages.FIND_TASKS, key));
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.get(i).getDescription().contains(key)) {
                sb.append(taskList.get(i).toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Overarching handler for commands entered by the user
     * @param input a full command entered by the user
     */
    private String handleInput(String input) {
        String response;
        String[] inputArgs = input.split("\\s+", 2);
        String keyWord = inputArgs[0];
        try {
            switch (keyWord) {
            case "bye":
                response = handleBye();
                return response;
            case "list":
                response = handleList();
                return response;
            case "remove":
                response = handleRemove(inputArgs);
                storage.writeToStorage(taskList);
                return response;
            case "mark":
                response = handleMark(inputArgs);
                storage.writeToStorage(taskList);
                return response;
            case "unmark":
                response = handleUnmark(inputArgs);
                storage.writeToStorage(taskList);
                return response;
            case "todo":
                response = handleTodo(inputArgs);
                storage.writeToStorage(taskList);
                return response;
            case "deadline":
                response = handleDeadline(inputArgs);
                storage.writeToStorage(taskList);
                return response;
            case "event":
                response = handleEvent(inputArgs);
                storage.writeToStorage(taskList);
                return response;
            case "find":
                response = handleFind(inputArgs);
                return response;
            default:
                throw new DukeException(ExceptionMessages.UNSUPPORTED_ACTION);
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }

    }

}
