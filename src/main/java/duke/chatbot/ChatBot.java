package duke.chatbot;

import duke.taskmanager.TaskManager;
import duke.taskmanager.task.ToDoTask;
import duke.taskmanager.task.DeadlineTask;
import duke.taskmanager.task.EventTask;
import duke.taskmanager.exceptions.InvalidCommandException;
import duke.taskmanager.exceptions.EmptyTaskException;
import duke.taskmanager.exceptions.InvalidDeadlineException;
import duke.taskmanager.exceptions.InvalidEventException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class ChatBot {
    private final String name;
    private boolean isRunning;
    private final TaskManager taskManager;

    /**
     * Constructor for a chatbot that can be intialized or terminated.
     * Primarily used for processing commands to update its own task manager.
     * Default state of the chat bot is runningState = false
     *
     * @param name string of the name of the chatbot
     */
    public ChatBot(String name) {
        this.name = name;
        this.isRunning = false;
        this.taskManager = new TaskManager();
    }

    /**
     * Initializes the chatbot by setting its runningState to true and responds
     * with a greeting message. The task manager is also initialized by loading
     * any pre-existing data.
     */
    public void initialize() {
        this.isRunning = true;
        System.out.println(wrapMessage("Greetings, " + this.name + " at your service.\n"
                + "How may I help you today?\n"));
        taskManager.load();
    }

    /**
     * Terminates the chatbot by setting its runningState to false and responds
     * with a goodbye message.
     */
    public void terminate() {
        this.isRunning = false;
        System.out.println(wrapMessage("Goodbye! It was nice seeing you.\n"));
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
     * Processes commands from an input by the user and calls the appropriate command
     * for the task manager. Handles exceptions for invalid commands. Calls the
     * save command of the task manager after every successful command processed.
     *
     * @param input string of the input provided by the user
     * @return the response to the user input
     */
    public String processCommand(String input) {
        Scanner inputScanner = new Scanner(input);
        String response = "";
        try {
            String command = inputScanner.next();
            if (!(inputScanner.hasNext())) {
                switch (command) {
                case "bye":
                    this.isRunning = false;
                    break;
                case "list":
                    response = taskManager.listTask();
                    break;
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event":
                    throw new EmptyTaskException();
                case "find":
                    // Fallthrough
                default:
                    throw new InvalidCommandException();
                }
            } else {
                String arguments = inputScanner.nextLine().substring(1);
                Scanner argumentScanner = new Scanner(arguments);
                switch (command) {
                case "todo":
                    response = taskManager.addTask(new ToDoTask(argumentScanner.nextLine()));
                    break;
                case "deadline":
                    argumentScanner.useDelimiter(" /by ");
                    response = taskManager.addTask(new DeadlineTask(argumentScanner.next(),
                            argumentScanner.next(), taskManager.getDateFormat()));
                    break;
                case "event":
                    argumentScanner.useDelimiter(" /at ");
                    response = taskManager.addTask(new EventTask(argumentScanner.next(),
                            argumentScanner.next(), taskManager.getDateFormat()));
                    break;
                case "mark":
                    response = taskManager.markTask(Integer.parseInt(arguments));
                    break;
                case "unmark":
                    response = taskManager.unmarkTask(Integer.parseInt(arguments));
                    break;
                case "delete":
                    response = taskManager.deleteTask(Integer.parseInt(arguments));
                    break;
                case "find":
                    System.out.println(wrapMessage(taskManager.findTask(arguments)));
                    break;
                default:
                    throw new InvalidCommandException();
                }
                System.out.println(wrapMessage(response));
                argumentScanner.close();
                taskManager.save();
            }
        } catch (InputMismatchException exception) {
            response = "You need to put a number after your command!\n";
        } catch (NoSuchElementException exception) {
            response = "You placed invalid arguments!\n";
        } catch (EmptyTaskException | InvalidDeadlineException | InvalidEventException exception) {
            response = exception.toString();
        } catch (InvalidCommandException exception) {
            response = "Sorry, I don't understand what you mean by \"" + input + "\"\n";
        } finally {
            inputScanner.close();
            return response;
        }
    }

    /**
     * Wraps a given string in a neat format for responses provided by the chatbot.
     *
     * @param str string of the text to be wrapped in a neat format
     */
    private String wrapMessage(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------------------------------------------------------\n");
        stringBuilder.append(str);
        stringBuilder.append("------------------------------------------------------------");
        return stringBuilder.toString();
    }
}