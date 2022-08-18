import java.util.ArrayList;

/**
 * Duke is a personal assistant chatbot.
 * It helps a person to keep track of various things.
 *
 * @author Rexong
 */
public class Duke {
    //Messages from Duke
    private static final String INTRODUCE_MESSAGE = "Hello! I'm Duke";
    private static final String ASK_MESSAGE = "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private TaskList taskList;
    private CommandGenerator commandGenerator;
    private IOHelper io;
    private boolean run;

    /**
     * Constructor with task initialised as ArrayList
     */
    public Duke() {
        taskList = new TaskList();
        io = new IOHelper();
        run = true;
    }

    public boolean isRunning() {
        return run;
    }

    /**
     * Print both introduce and ask messages.
     */
    public void printGreetMessage() {
        IOHelper.print(INTRODUCE_MESSAGE);
        IOHelper.print(ASK_MESSAGE);
    }

    /**
     * Print exit message.
     */
    public void printExitMessage() {
        IOHelper.print(EXIT_MESSAGE);
    }

    /**
     * Echos commands entered by user.
     *
     * @param message Message entered by the user to be echoed
     */
    public void echoMessage(String message) {
        IOHelper.print(message);
    }

    /**
     * Handles the different command and given each command,
     * evoke the respective TaskList methods
     * @param command given by user in console
     * @param commandAction given by user in console, maybe null
     */
    public void handleCommand(String command, String commandAction) {
        switch (command) {
            case "list":
                taskList.printAllTasks();
                break;
            case "mark":
            case "unmark":
                if (!CommandGenerator.isInteger(commandAction)) {
                    break;
                }
                taskList.updateTask(Integer.parseInt(commandAction) - 1, command);
                break;
            case "bye":
                run = false;
                break;
            default:
                taskList.addTaskProcess(command);
        }
    }

    public void run() {
        printGreetMessage();
        while (isRunning()) {
            io.scan();
            commandGenerator = new CommandGenerator(io.getText());
            handleCommand(commandGenerator.getCommand(), commandGenerator.getCommandAction());
        }
        printExitMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}



























