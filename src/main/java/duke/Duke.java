package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;


/**
 * Main driver class.
 */
public class Duke {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_MARK_AS_DONE = "mark";
    private static final String COMMAND_MARK_AS_UNDONE = "unmark";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static final char TIME_DELIMITER = '/';
    private static final String DATA_FILE_PATH = "./data.ser";


    /** Ui object to handle user interaction */
    private Ui ui;

    /** Storage object to handle storing the file on the hard disk */
    private Storage storage;

    /** TaskList object to store the user's tasks */
    private TaskList storedTasks;




    /**
     * Creates a new Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_FILE_PATH);
    }


    /**
     * Reads the data file and returns the status message.
     *
     * @return Status message.
     */
    public String initialize() {

        try {
            // Attempt to load the task list from the hard disk, if it exists
            this.storedTasks = this.storage.readFromFile();

        } catch (FileNotFoundException e) {
            this.storedTasks = new TaskList();
            return ui.getDataFileNotFoundErrorMessage() + ui.getCreateNewTaskListMessage();

        } catch (IOException e) {
            this.storedTasks = new TaskList();
            return ui.getDataFileReadErrorMessage() + ui.getCreateNewTaskListMessage();

        } catch (ClassNotFoundException e) {
            this.storedTasks = new TaskList();
            return ui.getDataFileDeserializeErrorMessage() + ui.getCreateNewTaskListMessage();
        }

        return ui.getDataFileSuccessMessage();
    }


    private String findTasks(String[] commands) {

        // Keyword to search for is the second token
        TaskList searchResults = this.storedTasks.searchTasks(commands[1]);

        return ui.getListTasksMessage(searchResults, true);
    }


    private String markTaskAsDoneOrUndone(String[] commands) {

        String result;

        // First token is the action
        String action = commands[0];

        // Task number is the second token
        // Task number is 1 index, so subtract 1 to make it 0 index
        int indexNumber = Integer.parseInt(commands[1]) - 1;

        // Get the task from the TaskList object
        Task t = this.storedTasks.getTask(indexNumber);


        // Mark the task as done or undone depending on the command
        if (action.equals(COMMAND_MARK_AS_DONE)) {
            t = t.markTask();
            result = ui.getMarkTaskMessage(t);

        } else {
            t = t.unmarkTask();
            result = ui.getUnmarkTaskMessage(t);
        }

        // Store the task back in the TaskList
        this.storedTasks.setTask(indexNumber, t);

        return result;
    }


    private String addTask(String[] commands) {

        // Create the correct type of task based on the first token
        Task t = null;

        try {
            t = createTask(commands);
            assert (t != null && t instanceof Task) : "t must be a Task object";

        } catch (IllegalArgumentException e) {
            // Cannot create task due to invalid commands
            return ui.getAddTaskInvalidSyntaxErrorMessage(e);
        }

        this.storedTasks.addTask(t);

        return ui.getAddTaskMessage(t, storedTasks.getSize());
    }


    private Task createTodoTask(String[] commands) throws IllegalArgumentException {

        // Check if the given commands are valid
        if (!isValidToDoCommand(commands)) {
            throw new IllegalArgumentException("todo");
        }

        // Second token onwards is the description
        String description = commands[1];
        for (int i = 2; i < commands.length; i++) {
            description = description.concat(String.format(" %s", commands[i]));
        }

        return new ToDo(description);
    }


    // Same method used for a deadline and event task.
    // Differentiate between the two types of tasks using a boolean flag.
    private Task createDeadlineOrEventTask(String[] commands, boolean isDeadlineTask) {

        // Check if the given commands are valid
        if (!isValidEventCommand(commands)) {
            String taskType = isDeadlineTask ? "deadline" : "event";
            throw new IllegalArgumentException(taskType);
        }

        // Second token until /at is the description
        String description = commands[1];

        int i = 0;
        for (i = 2; i < commands.length; i++) {
            if (commands[i].charAt(0) == TIME_DELIMITER) {
                // Stop adding tokens to the description
                break;
            }
            description = description.concat(String.format(" %s", commands[i]));
        }

        // Skip over the delimiter token
        // The tokens after the delimiter are the date and time
        i++;
        String dateAndTime = commands[i];
        for (i++; i < commands.length; i++) {
            dateAndTime = dateAndTime.concat(String.format(" %s", commands[i]));
        }

        return isDeadlineTask ? new Deadline(description, dateAndTime) : new Event(description, dateAndTime);
    }


    private Task createTask(String[] commands) throws IllegalArgumentException {

        // The first token is used to identify which type of task to create
        switch (commands[0]) {

        case COMMAND_ADD_TODO:
            try {
                return createTodoTask(commands);
            } catch (IllegalArgumentException e) {
                // Unable to create ToDo task
                throw e;
            }


        case COMMAND_ADD_DEADLINE:
            try {
                return createDeadlineOrEventTask(commands, true);
            } catch (IllegalArgumentException e) {
                // Unable to create Deadline task
                throw e;
            }


        case COMMAND_ADD_EVENT:
            try {
                return createDeadlineOrEventTask(commands, false);
            } catch (IllegalArgumentException e) {
                // Unable to create Event task
                throw e;
            }


        default:
            return null;
        }
    }


    private boolean isValidToDoCommand(String[] commands) {
        // Format of ToDo task: todo description

        // Therefore, second token onwards is the description
        // So check if there is a second token
        return commands.length >= 2;
    }


    private boolean isValidEventCommand(String[] commands) {
        /*  Format of Deadline task: deadline description /by dateAndTime
            Format of Event task: event description /at dateAndTime

            Need to check for 2 things:

            1. There is at least 1 token before /at, i.e. /at is the 3rd token or further
            2. There is at least 1 token after /at which represents the dateAndTime,
               i.e. delimiter must not be the last item
        */

        int delimiterIndex = -1;


        try {
            delimiterIndex = findDelimiter(commands);

        } catch (IllegalArgumentException e) {
            // Cannot find delimiter, i.e. delimiter doesn't exist
            return false;
        }

        // Check 1: /at is the 3rd token or further
        if (delimiterIndex < 3) {
            return false;
        }

        // Check 2: at least 1 token after /at, i.e. delimiter must not be the last item
        return delimiterIndex < commands.length - 1;
    }


    // Return the index of the first delimiter in the commands array
    private int findDelimiter(String[] commands) throws IllegalArgumentException {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].charAt(0) == TIME_DELIMITER) {
                return i;
            }
        }

        throw new IllegalArgumentException();
    }


    private String deleteTask(String[] commands) {

        // Task number is the second token
        // Task number is 1 index, so subtract 1 to make it 0 index
        int indexNumber = Integer.parseInt(commands[1]) - 1;

        // Remove the task from the TaskList object
        Task t = storedTasks.removeTask(indexNumber);

        assert (t != null && t instanceof Task) : "t must be a Task object";

        return ui.getDeleteTaskMessage(t, storedTasks.getSize());
    }


    private String exitDuke() {
        return ui.getExitMessage();
    }


    private String handleInvalidCommand() {
        return ui.getInvalidCommandErrorMessage();
    }


    // Calls the relevant function based on the given command
    // Returns the response of the command
    private String executeCommand(String[] commands) {

        String result;

        // The first token is used to identify which action to take
        switch (commands[0]) {

        case COMMAND_LIST:
            result = ui.getListTasksMessage(this.storedTasks, false);
            break;


        case COMMAND_FIND:
            result = findTasks(commands);
            break;


        // Handle marking a task as done and undone in the same function
        case COMMAND_MARK_AS_DONE:
            // Fall through
        case COMMAND_MARK_AS_UNDONE:
            result = markTaskAsDoneOrUndone(commands);
            storage.writeToFile(this.storedTasks);
            break;


        // Handle adding all types of tasks in the same function
        case COMMAND_ADD_TODO:
            // Fall through
        case COMMAND_ADD_DEADLINE:
            // Fall through
        case COMMAND_ADD_EVENT:
            result = addTask(commands);
            storage.writeToFile(this.storedTasks);
            break;


        case COMMAND_DELETE:
            result = deleteTask(commands);
            storage.writeToFile(this.storedTasks);
            break;


        case COMMAND_EXIT:
            result = exitDuke();
            break;


        // Command is invalid
        default:
            result = handleInvalidCommand();
        }

        return result;
    }


    /**
     * Returns the response to the specified input.
     *
     * @param input User input string.
     * @return Response string.
     */
    public String getResponse(String input) {
        assert input != null : "input cannot be null";

        String[] commands = Parser.parseCommand(input);
        return executeCommand(commands);
    }


    public static void main(String[] args) {

    }
}
