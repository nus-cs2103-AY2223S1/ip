package duke.chatbot;

import java.util.Scanner;

import duke.chatbot.commands.ByeCommandHandler;
import duke.chatbot.commands.ListTaskCommandHandler;
import duke.chatbot.commands.exceptions.EmptyCommandException;
import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commands.exceptions.InvalidCommandException;
import duke.chatbot.commands.exceptions.InvalidIndexException;
import duke.taskmanager.TaskManager;
import duke.taskmanager.exceptions.LoadDataException;
import duke.taskmanager.task.DeadlineTask;
import duke.taskmanager.task.EventTask;
import duke.taskmanager.task.ToDoTask;

/**
 * Chatbot that processes commands.
 */
public class ChatBot {
    private static final String HORIZONTAL_BAR = "------------------------------------------------------------";

    private final String name;
    private final TaskManager taskManager;
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
        this.isRunning = false;
        this.latestResponse = "";
    }

    /**
     * Returns the runningState of the chatbot
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
                + "Enter anything to exit!\n";
    }

    /**
     * Initializes the chatbot by setting its runningState to true and responds
     * with a greeting message. The task manager is also initialized by loading
     * any pre-existing data.
     */
    public void initialize() {
        this.isRunning = true;
        this.latestResponse = getGreetingResponse();
        try {
            taskManager.loadData();
        } catch (LoadDataException exception) {
            this.latestResponse = exception.getMessage();
        }
    }

    /**
     * Terminates the chatbot by setting its runningState to false and responds
     * with a goodbye message.
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
        Scanner inputScanner = new Scanner(input);
        String response = "";
        try {
            // Guard Clause for empty commands
            if (input.length() <= 0) {
                throw new EmptyCommandException();
            }

            // Get commands and arguments
            String command = inputScanner.next();
            boolean hasArguments = inputScanner.hasNext();
            String arguments = "";
            if (hasArguments) {
                arguments = inputScanner.nextLine().substring(1);
            }

            switch (command) {
            case "bye":
                response = new ByeCommandHandler(this).execute(arguments);
                break;
            case "list":
                response = new ListTaskCommandHandler(this.taskManager).execute(arguments);
                break;
            case "todo":
                String todoTaskName = "";
                if (hasArguments) {
                    todoTaskName = arguments;
                }
                response = taskManager.addTask(new ToDoTask(todoTaskName));
                break;
            case "deadline":
                String deadlineTaskName = "";
                String deadline = "";
                if (hasArguments) {
                    String[] argumentList = arguments.split(DeadlineTask.TASK_DELIMITER);
                    if (argumentList.length < 2) {
                        throw new InvalidArgumentsException();
                    }
                    deadlineTaskName = argumentList[0];
                    deadline = argumentList[1];
                }
                response = taskManager.addTask(new DeadlineTask(deadlineTaskName, deadline,
                        taskManager.getDateFormat()));
                break;
            case "event":
                String eventTaskName = "";
                String eventTime = "";
                if (hasArguments) {
                    String[] argumentList = arguments.split(EventTask.TASK_DELIMITER);
                    if (argumentList.length < 2) {
                        throw new InvalidArgumentsException();
                    }
                    eventTaskName = argumentList[0];
                    eventTime = argumentList[1];
                }
                response = taskManager.addTask(new EventTask(eventTaskName, eventTime,
                        taskManager.getDateFormat()));
                break;
            case "mark":
                response = taskManager.markTask(parseNumber(arguments));
                break;
            case "unmark":
                response = taskManager.unmarkTask(parseNumber(arguments));
                break;
            case "delete":
                response = taskManager.deleteTask(parseNumber(arguments));
                break;
            case "find":
                if (!hasArguments) {
                    throw new InvalidArgumentsException();
                }
                response = taskManager.findTask(arguments);
                break;
            case "update":
                if (!hasArguments) {
                    throw new InvalidArgumentsException();
                }

                String[] argumentList = arguments.split(TaskManager.UPDATE_DELIMITER);
                if (argumentList.length < 2) {
                    throw new InvalidArgumentsException();
                }
                response = taskManager.updateTask(parseNumber(argumentList[0]), argumentList[1]);
                break;
            default:
                throw new InvalidCommandException();
            }
            System.out.println(wrapMessage(response));
            taskManager.saveData();
        } catch (Exception exception) {
            response = exception.getMessage();
        } finally {
            inputScanner.close();
            this.latestResponse = response;
        }
    }

    /**
     * Parses a string argument into a number.
     *
     * @throws InvalidIndexException when the string argument is not a number
     */
    private int parseNumber(String argument) throws InvalidIndexException {
        try {
            int itemNumber = Integer.parseInt(argument);
            return itemNumber;
        } catch (NumberFormatException exception) {
            throw new InvalidIndexException();
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
