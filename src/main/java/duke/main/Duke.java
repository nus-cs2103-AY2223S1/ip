package duke.main;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Main class of Duke.
 */
public class Duke {

    /* Storage object handling saving and reading from save file */
    private Storage storage;
    /* List of tasks */
    private TaskList tasks;
    /* Ui object handling output to user */
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.readTaskListFromFile());
        } catch (DukeException e) {
            ui.printErrorMessage(e);
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Returns text response corresponding to user input string.
     *
     * @param input User input string.
     * @return Corresponding string text response.
     */
    public String getResponse(String input) {
        String response = "";

        // Parse Input and handles invalid input if any
        try {
            Parser.parse(input);
        } catch (DukeException de) {
            return ui.printErrorMessage(de);
        }

        // Retrieve command and arguments parsed from input to perform respective actions
        Keyword command = Parser.getCommand();
        String argument = Parser.getArgument();

        try {
            switch (command) {
            case TODO: {
                response = getTodoResponse(argument);
                break;
            }
            case DEADLINE: {
                response = getDeadlineResponse(argument);
                break;
            }
            case EVENT: {
                response = getEventResponse(argument);
                break;
            }
            case LIST: {
                response = tasks.display();
                break;
            }
            case FIND: {
                response = tasks.search(argument);
                break;
            }
            case DELETE: {
                response = getDeleteResponse(argument);
                break;
            }
            case MARK: // Fallthrough
            case UNMARK: {
                response = getMarkUnmarkResponse(argument, command);
                break;
            }
            case TAG: {
                response = getTagResponse(argument);
                break;
            }
            case BYE: {
                response = ui.sayGoodbye();
                break;
            }
            default: {
                System.out.print("Unexpected Error in Get Response");
                break;
            }
            }
        } catch (DukeException de) {
            response = ui.printErrorMessage(de);
        }
        return response;
    }

    private String getTodoResponse(String argument) throws DukeException {
        Task task = new ToDo(argument);
        tasks.addTask(task);
        storage.saveTaskListToFile(tasks);
        return ui.displayTaskAddedMessage(task, tasks.size());
    }

    private String getDeadlineResponse(String argument) throws DukeException {
        String[] taskTokens = argument.split(" /by ");
        String taskName = taskTokens[0];
        String deadline = taskTokens[1];
        LocalDateTime deadlineDate = DateTimeFormatUtils.parseDate(deadline);
        Task task = new Deadline(taskName, deadlineDate);
        tasks.addTask(task);
        storage.saveTaskListToFile(tasks);
        return ui.displayTaskAddedMessage(task, tasks.size());
    }

    private String getEventResponse(String argument) throws DukeException {
        String[] taskTokens = argument.split(" /at ");
        String taskName = taskTokens[0];
        String duration = taskTokens[1];
        LocalDateTime[] eventDuration = DateTimeFormatUtils.parseDuration(duration);
        Task task = new Event(taskName, eventDuration[0], eventDuration[1]);
        tasks.addTask(task);
        storage.saveTaskListToFile(tasks);
        return ui.displayTaskAddedMessage(task, tasks.size());
    }

    private String getDeleteResponse(String argument) throws DukeException {
        Task deletedTask = tasks.getTask(argument);
        tasks.deleteTask(argument);
        storage.saveTaskListToFile(tasks);
        return ui.displayTaskDeletedMessage(deletedTask, tasks.size());
    }

    private String getMarkUnmarkResponse(String argument, Keyword command) throws DukeException {
        tasks.markUnmarkTask(argument, command);
        Task task = tasks.getTask(argument);
        storage.saveTaskListToFile(tasks);
        return ui.displayTaskMarkUnmarkMessage(task, command);
    }

    private String getTagResponse(String argument) throws DukeException {
        String[] taskTokens = argument.split("\\|");
        String taskNumber = taskTokens[0];
        String tagName = taskTokens[1];
        Task task = tasks.getTask(taskNumber);
        tasks.tagTask(taskNumber, tagName);
        storage.saveTaskListToFile(tasks);
        return ui.displayTagTaskMessage(task);
    }
}


