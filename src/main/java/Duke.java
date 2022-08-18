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
     * @param commandGenerator given by user in console
     */
    public void handleCommand(CommandGenerator commandGenerator) {
        String command = commandGenerator.getCommand();
        switch (command) {
            case "list":
                taskList.printAllTasks();
                break;
            case "mark":
            case "unmark":
                if (!CommandGenerator.isInteger(commandGenerator.getCommandAction())) {
                    break;
                }
                taskList.updateTask(Integer.parseInt(commandGenerator.getCommandAction()) - 1, command);
                break;
            case "bye":
                run = false;
                break;
            default:
                taskList.addTask(commandGenerator.getText());
        }
    }

    public void run() {
        printGreetMessage();
        while (isRunning()) {
            io.scan();
            commandGenerator = new CommandGenerator(io.getText());
            handleCommand(commandGenerator);
        }
        printExitMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}



























