package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;
import java.io.IOException;

/**
 * Abstraction of all commands possibly received on duke,
 * and executes all commands.
 * @author Shaune Ang
 */
public class Command {
    static final String EXITWORD = "bye";
    private TaskList taskList;
    private Ui ui;
    private FileLoaderSaver storage;
    private String fullCommand;

    private CommandTypes commandType;

    // enum of all command types
    public enum CommandTypes {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        EXIT
    }

    /**
     * Constructor for command objects
     * @param commandType type of command this command with execute
     * @param fullCommand full user input command in String
     */
    public Command (CommandTypes commandType, String fullCommand) {
        this.commandType = commandType;
        this.fullCommand = fullCommand;
    }

    /**
     * Checks if command entered is EXITWORD
     * @return true if command is EXITWORD else false
     */
    public boolean isExit() {
        return fullCommand.equals(EXITWORD);
    }

    /**
     * Executes tasks based on the command type of the command
     * @param taskList list containing all the current tasks
     * @param ui user interaction object for output
     * @param storage duke.FileLoaderSaver object for saving tasks after creating, deletion or manipulation
     * @throws IOException
     * @throws Exception
     */
    public void execute(TaskList taskList, Ui ui, FileLoaderSaver storage) throws IOException, Exception {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;

        switch (commandType) {
            case LIST:
                taskList.listTasks();
                break;
            case TODO:
                addToDo(fullCommand);
                storage.writeToFile(taskList.createTxtFile());
                taskList.displayListSize();
                break;
            case DEADLINE:
                addDeadline(fullCommand);
                storage.writeToFile(taskList.createTxtFile());
                taskList.displayListSize();
                break;
            case EVENT:
                addEvent(fullCommand);
                storage.writeToFile(taskList.createTxtFile());
                taskList.displayListSize();
                break;
            case MARK:
            case UNMARK:
                changeMark(fullCommand);
                storage.writeToFile(taskList.createTxtFile());
                break;
            case DELETE:
                deleteTask(fullCommand);
                storage.writeToFile(taskList.createTxtFile());
                taskList.displayListSize();
                break;
            case FIND:
                findTask(fullCommand);
                break;
            case EXIT:
                Ui.goodBye();
                break;
        }
    }

    /**
     * Changes isCompleted of the task according to index given
     *
     * @param command change mark command from user
     */
    private void changeMark(String command) {
        String[] splitComm = command.split(" ");
        String action = splitComm[0];
        int index = Integer.parseInt(splitComm[1]) - 1;

        if (action.equals("mark")) {
            taskList.complete(index);
        } else if (action.equals("unmark")){
            taskList.incomplete(index);
        }
    }

    /**
     * Deletes task according to index given
     *
     * @param command delete task command from user
     */
    private void deleteTask(String command) {
        String[] splitComm = command.split(" ");
        int index = Integer.parseInt(splitComm[1]) - 1;

        taskList.delete(index);
    }

    /**
     * Creates a duke.task.ToDos instance and adds it to duke.ToDoList
     *
     * @param command todo command from user
     * @throws Exception
     */
    private void addToDo(String command) throws Exception {
        if (!command.matches("todo \\S.*")) {
            throw new Exception("The description of a todo cannot be empty.");
        }
        String name = command.substring(command.indexOf(" ") + 1);

        taskList.addTask(new ToDos(name));
    }

    /**
     * Creates a duke.task.Deadline instance and adds it to duke.ToDoList
     *
     * @param command deadline command from user
     * @throws Exception
     */
    private void addDeadline(String command) throws Exception {
        if (!command.matches("deadline \\S.*")) {
            throw new Exception("The description of a deadline cannot be empty.");
        } else if (!command.contains("/by")) {
            throw new Exception("The description is missing a deadline.");
        }

        String details = command.substring(command.indexOf(" ") + 1);
        String name = details.split(" /by ")[0];
        String deadline = details.split(" /by ")[1];

        taskList.addTask(new Deadline(name, deadline));
    }

    /**
     * Creates a duke.task.Event instance and adds it to duke.ToDoList
     *
     * @param command event command from user
     * @throws Exception
     */
    private void addEvent(String command) throws Exception {
        if (!command.matches("event \\S.*")) {
            throw new Exception("The description of an event cannot be empty.");
        } else if (!command.contains("/at")) {
            throw new Exception("The description is missing a time.");
        }

        String details = command.substring(command.indexOf(" ") + 1);
        String name = details.split(" /at ")[0];
        String time = details.split(" /at ")[1];

        taskList.addTask(new Event(name, time));
    }

    /**
     * Finds tasks whose name matches search
     * @param command
     * @throws Exception
     */
    private void findTask(String command) throws Exception {
        String searchString = command.substring(command.indexOf(" ") + 1);
        taskList.findTasks(searchString);
    }
}
