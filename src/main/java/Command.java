import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

// Executes commands given by parser
public class Command {
    static final String EXITWORD = "bye";
    private ToDoList toDoList;
    private Ui ui;
    private FileLoaderSaver storage;
    private String fullCommand;

    private CommandTypes commandType;

    public enum CommandTypes {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        EXIT
    }

    /**
     * Creates command for non-tasks
     * @param commandType type of command
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

    public void execute(ToDoList toDoList, Ui ui, FileLoaderSaver storage) throws IOException, Exception {
        this.toDoList = toDoList;
        this.ui = ui;
        this.storage = storage;

        switch (commandType) {
            case LIST:
                toDoList.listTasks();
                break;
            case TODO:
                addToDo(fullCommand);
                storage.writeToFile(toDoList.createFile());
                toDoList.displayListSize();
                break;
            case DEADLINE:
                addDeadline(fullCommand);
                storage.writeToFile(toDoList.createFile());
                toDoList.displayListSize();
                break;
            case EVENT:
                addEvent(fullCommand);
                storage.writeToFile(toDoList.createFile());
                toDoList.displayListSize();
                break;
            case MARK:
            case UNMARK:
                changeMark(fullCommand);
                storage.writeToFile(toDoList.createFile());
                break;
            case DELETE:
                deleteTask(fullCommand);
                storage.writeToFile(toDoList.createFile());
                toDoList.displayListSize();
                break;
            case EXIT:
                Ui.goodBye();
                break;
        }
    }

    /**
     * Changes status of the task according to index given
     *
     * @param command
     */
    private void changeMark(String command) {
        String[] splitComm = command.split(" ");
        String action = splitComm[0];
        int index = Integer.parseInt(splitComm[1]) - 1;

        if (action.equals("mark")) {
            toDoList.complete(index);
        } else if (action.equals("unmark")){
            toDoList.incomplete(index);
        }
    }

    /**
     * Changes status of the task according to index given
     *
     * @param command
     */
    private void deleteTask(String command) {
        String[] splitComm = command.split(" ");
        int index = Integer.parseInt(splitComm[1]) - 1;

        toDoList.delete(index);
    }

    /**
     * Creates a ToDos instance and adds it to ToDoList
     *
     * @param command
     * @throws Exception
     */
    private void addToDo(String command) throws Exception {
        if (!command.matches("todo \\S.*")) {
            throw new Exception("The description of a todo cannot be empty.");
        }
        String name = command.substring(command.indexOf(" ") + 1);

        toDoList.addTask(new ToDos(name));
    }

    /**
     * Creates a Deadline instance and adds it to ToDoList
     *
     * @param command
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

        toDoList.addTask(new Deadline(name, deadline));
    }

    /**
     * Creates an Event instance and adds it to ToDoList
     *
     * @param command
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

        toDoList.addTask(new Event(name, time));
    }
}
