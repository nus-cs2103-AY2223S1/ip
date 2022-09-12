package duke.chatbot;

import java.util.Scanner;

import duke.chatbot.commandmanager.CommandManager;
import duke.chatbot.commandmanager.commands.exceptions.EmptyCommandException;
import duke.chatbot.taskmanager.TaskManager;
import duke.chatbot.taskmanager.exceptions.LoadDataException;

/**
 * Chatbot that processes commands.
 */
public class ChatBot {
    private static final String HORIZONTAL_BAR = "------------------------------------------------------------";

    private final String name;
    private final TaskManager taskManager;
    private final CommandManager commandManager;
    private boolean isRunning;
    private String latestResponse;
    /**
     * Constructor for a chatbot that can be intialized or terminated.
     * Primarily used for processing commands to update its own task manager.
     *
     * @param name string of the name of the chatbot
     */
    public ChatBot(String name) {
        this.name = name;
        this.taskManager = new TaskManager();
        this.commandManager = new CommandManager();
        this.isRunning = false;
        this.latestResponse = "";
    }

    /**
     * Returns the running state of the chatbot
     *
     * @return running state of the chatbot
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Returns the latest stored response.
     *
     * @return the string of the latest stored response
     */
    public String getLatestResponse() {
        return this.latestResponse;
    }
    /**
     * Returns the greeting message of the chatbot
     *
     * @return the greeting message
     */
    public String getGreetingResponse() {
        return "Greetings, " + this.name + " at your service.\n"
                + "How may I help you today?\n";
    }

    /**
     * Returns the goodbye message of the chatbot
     *
     * @return the goodbye message
     */
    public String getGoodbyeResponse() {
        return "Goodbye! It was nice seeing you.\n"
                + "Press enter to exit!\n";
    }

    /**
     * Initializes the chatbot by setting its running state to true and responds
     * with a greeting message. The task manager is also initialized by loading
     * any pre-existing data.
     */
    public void initialize() {
        this.isRunning = true;
        this.latestResponse = getGreetingResponse();
        this.commandManager.initialize(this, this.taskManager);
        try {
            taskManager.loadData();
        } catch (LoadDataException exception) {
            this.latestResponse = exception.getMessage();
        }
    }

    /**
     * Terminates the chatbot by setting its running state to false
     */
    public void terminate() {
        this.isRunning = false;
    }

    /**
     * Processes commands from an input by the user and calls the appropriate command
     * for the task manager and updates the latest response.
     * Handles exceptions for invalid commands.
     * Calls the save command of the task manager after every successful command.
     *
     * @param input string of the input provided by the user
     */
    public void processCommand(String input) {
        assert isRunning : "chatbot should be running";
        input = input.strip();
        String response = "";
        try {
            // Guard Clause for empty commands
            if (input.length() == 0) {
                throw new EmptyCommandException();
            }

            // Get command and arguments
            Scanner inputScanner = new Scanner(input);
            String command = inputScanner.next();
            String arguments = "";
            if (inputScanner.hasNext()) {
                arguments = inputScanner.nextLine().strip();
            }
            inputScanner.close();

            response = this.commandManager.getCommand(command).execute(arguments);
            this.taskManager.saveData();
            System.out.println(wrapMessage(response));
        } catch (Exception exception) {
            response = exception.getMessage();
        } finally {
            this.latestResponse = response;
        }
    }

    /**
     * Wraps a given string in a neat format for responses provided by the chatbot.
     *
     * @param str string of the text to be wrapped in a neat format
     */
    private String wrapMessage(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HORIZONTAL_BAR);
        stringBuilder.append("\n");
        stringBuilder.append(str);
        stringBuilder.append(HORIZONTAL_BAR);
        return stringBuilder.toString();
    }
}
