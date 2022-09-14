package duke;

import java.util.Scanner;
import java.util.Stack;

import duke.command.Action;
import duke.command.Command;
import duke.command.GreetCommand;
import duke.exception.CompileException;
import duke.exception.DukeException;
import duke.exception.DukeRuntimeException;
import duke.util.MessagePrinter;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * The Duke.
 */
public class Duke {
    private boolean isTerminated;
    private MessagePrinter messagePrinter;
    private TaskList tasks;
    private Storage storage;
    private Stack<TaskList> taskListHistory;

    /**
     * The constructor of the Duke with given storage path.
     *
     * @param storagePath The given storage path.
     */
    public Duke(String storagePath) {
        initialize(storagePath);
    }

    /**
     * The constructor of the Duke with given storage path.
     */
    public Duke() {
        String storagePathWin = "data\\duke.txt";
        String storagePathElse = "data/duke.txt";
        String storagePath = System.getProperty("os.name").startsWith("Windows") ? storagePathWin : storagePathElse;
        initialize(storagePath);
    }

    /**
     * The main method of Duke.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        String storagePathWin = "data\\duke.txt";
        String storagePathElse = "data/duke.txt";
        String storagePath = System.getProperty("os.name").startsWith("Windows") ? storagePathWin : storagePathElse;
        new Duke(storagePath).run();
    }

    private void initialize(String storagePath) {
        this.tasks = new TaskList();
        this.messagePrinter = new MessagePrinter(39, '*', 0);
        this.storage = new Storage(storagePath);
        this.taskListHistory = new Stack<>();
        this.execute(new GreetCommand());
    }

    /**
     * The method to launch Duke.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!this.isTerminated) {
            if (scanner.hasNext()) {
                getResponse(scanner.nextLine());
            }
        }
    }

    private Command parse(String entry) throws CompileException {
        return Parser.parseCommand(entry);
    }

    /**
     * The method to execute a givenCommand.
     *
     * @param command The given Command.
     */
    public String execute(Command command) throws DukeRuntimeException {
        TaskList oldTaskList = this.tasks.clone();
        String result = command.execute(this);
        TaskList newTaskList = this.tasks;
        boolean isUnchangedTaskList = oldTaskList.equals(newTaskList);
        boolean isUndoCommand = command.getAction().equals(Action.UNDO);
        if (!isUnchangedTaskList && !isUndoCommand) {
            taskListHistory.push(oldTaskList);
        }
        return result;
    }

    /**
     * The method to print given DukeException Details.
     *
     * @param dukeException The given DukeException.
     */
    public String handle(DukeException dukeException) {
        return this.messagePrinter.printMessage(dukeException.getMessage());
    }

    /**
     * Returns the response of Duke with given input.
     *
     * @param input The given input.
     * @return The response of Duke
     */
    public String getResponse(String input) {
        String responseIfTerminated = "...";
        try {
            if (isTerminated) {
                return responseIfTerminated;
            }
            Scanner scanner = new Scanner(input);
            if (scanner.hasNext()) {
                String entry = scanner.nextLine();
                Command command = parse(entry);
                this.isTerminated = command.isTerminated();
                return execute(command);
            }
        } catch (DukeException dukeException) {
            return handle(dukeException);
        }
        return null;
    }

    /**
     * Returns whether the Duke is running.
     * @return A boolean indicating whether the Duke is running.
     */
    public Boolean isRunning() {
        return !this.isTerminated;
    }

    public MessagePrinter getMessagePrinter() {
        return this.messagePrinter;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.tasks;
    }

    public Stack<TaskList> getTaskListHistory() {
        return this.taskListHistory;
    }

    public void setTaskList(TaskList taskList) {
        this.tasks = taskList;
    }
}
