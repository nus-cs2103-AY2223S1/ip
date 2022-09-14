package duke.chatbot;

import java.util.Scanner;

import duke.chatbot.commandmanager.CommandManager;
import duke.chatbot.commandmanager.commands.exceptions.EmptyCommandException;
import duke.chatbot.personality.Personality;
import duke.chatbot.personality.exceptions.LoadPersonalityException;
import duke.chatbot.taskmanager.TaskManager;
import duke.chatbot.taskmanager.exceptions.LoadDataException;

/**
 * Chatbot that processes commands.
 */
public class ChatBot {
    private static final String HORIZONTAL_BAR = "------------------------------------------------------------";

    private final String name;
    private final Logger logger;
    private final TaskManager taskManager;
    private final CommandManager commandManager;
    private final Personality personality;
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
        this.logger = new Logger();
        this.taskManager = new TaskManager();
        this.commandManager = new CommandManager();
        this.personality = new Personality(name);
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
     * Returns the next log stored by the logger.
     *
     * @return the string of the next log
     */
    public String getNextLog() {
        return this.logger.getNext();
    }

    /**
     * Returns the previous log stored by the logger.
     *
     * @return the string of the previous log
     */
    public String getPreviousLog() {
        return this.logger.getPrevious();
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
     * Initializes the chatbot by setting its running state to true and responds
     * with a greeting message. The task manager is also initialized by loading
     * any pre-existing data.
     */
    public void initialize() {
        this.isRunning = true;
        this.commandManager.initialize(this, this.personality, this.taskManager);

        try {
            this.personality.loadPersonality();
            this.latestResponse = personality.formulateResponse("greet", this.name);
        } catch (LoadPersonalityException exception) {
            this.latestResponse = exception.getMessage();
            terminate();
        }

        try {
            this.taskManager.loadData(this.personality);
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
        this.logger.updateLog(input);
        assert isRunning : "chatbot should be running";
        input = input.strip();
        assert isRunning == true : "chatbot should be running";
        String response = "";
        try {
            // Guard Clause for empty commands
            if (input.length() == 0) {
                throw new EmptyCommandException(this.personality);
            }

            // Get command and arguments
            Scanner inputScanner = new Scanner(input);
            String command = inputScanner.next();
            String arguments = "";
            if (inputScanner.hasNext()) {
                arguments = inputScanner.nextLine().strip();
            }
            inputScanner.close();

            response = this.commandManager.getCommand(this.personality, command).execute(arguments);
            this.taskManager.saveData(personality);
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
