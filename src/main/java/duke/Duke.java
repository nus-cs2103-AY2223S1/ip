package duke;

import duke.commands.BaseCommand;
import duke.commands.CommandResult;
import duke.commands.ErrorCommand;
import duke.commands.ExitCommand;
import duke.commands.tasks.BaseTaskCommand;
import duke.exceptions.NoCommandException;
import duke.exceptions.ParseException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The type duke.Duke.
 */
public class Duke {

    private static final String greetingMessage = "Welcome to Aladdin Services";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Duke Constructor
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * The runCommandLoopUntilExitCommand function runs a loop that will continue to
     * run until the user enters an exit command.
     * It first asks for a command from the user, and then tries to parse it using
     * the Parser class. If there is an error in parsing,
     * it will display that error message and ask for another command. Otherwise, if
     * there are no errors in parsing, it runs runCommand on
     * the parsed Command object (which returns a CommandResult). The function then
     * displays this result on screen using Ui's showResult method.
     */
    private void runCommandLoopUntilExitCommand() {
        BaseCommand command;
        do {
            String userCommandText = ui.getUserCommand();
            try {
                command = parser.parse(userCommandText);
            } catch (NoCommandException | ParseException e) {
                command = new ErrorCommand(e.getMessage());
            }
            CommandResult result = runCommand(command);
            ui.showResult(result);
        } while (!ExitCommand.equals(command));
    }

    /**
     * The runCommand function takes a BaseCommand object as an argument and
     * executes it.
     * If the command is a BaseTaskCommand, then the task list is set to this
     * TaskList object.
     * The function returns the result of executing that command.
     *
     * @param command
     *            Pass the command object to the run command method
     * @return A command result object
     */
    private CommandResult runCommand(BaseCommand command) {
        CommandResult result;
        if (command instanceof BaseTaskCommand) {
            BaseTaskCommand taskCommand = (BaseTaskCommand) command;
            taskCommand.setTaskList(this.tasks);
            result = taskCommand.execute();
            storage.writeDataToFile(tasks.exportTaskList());
            return result;
        }
        return command.execute();
    }

    /**
     * The run function is the main function of the program. It starts by showing
     * a greeting message to the user, then enters a loop that runs until an exit
     * command is entered. Inside this loop, it parses and executes commands from
     * standard input (the keyboard). The run function also handles exceptions and
     * prints error messages to standard output (the screen). When an exception is
     * thrown during execution of a command, it will be caught here so that we can
     * print out an appropriate error message before continuing with our program.
     */
    public void run() {
        ui.showGreeting();
        runCommandLoopUntilExitCommand();
        ui.showGoodbye();
    }

    /**
     * The entry point of application.
     *
     * @param args
     *            the input arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * The getResponse function takes a String as an argument and returns a String.
     * The function is used to return the response from Duke after he has heard
     * something from the user. It simply returns &quot;Duke heard: &quot; followed
     * by the
     * text that was passed in as an argument. This function is called by other
     * functions, such as run().
     *
     * @param text
     *            Pass the text from the main function to getresponse
     * @return A string
     */
    public String getResponse(String text) {
        BaseCommand command;
        try {
            command = parser.parse(text);
        } catch (NoCommandException | ParseException e) {
            command = new ErrorCommand(e.getMessage());
        }
        try {
            CommandResult result = runCommand(command);
            return result.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * The getWelcomeMessage function returns the greeting message.
     */
    public String getWelcomeMessage() {
        return greetingMessage;
    }
}
