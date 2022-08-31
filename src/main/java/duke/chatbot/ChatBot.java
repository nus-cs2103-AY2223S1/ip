package duke.chatbot;

import duke.taskmanager.TaskManager;
import duke.taskmanager.task.*;
import duke.taskmanager.exceptions.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class ChatBot {
    private final String name;
    private boolean runningState;
    private final TaskManager taskManager;

    /**
     * Constructor for a chatbot that can be intialized or terminated.
     * Primarily used for processing commands to update its own task manager.
     * Default state of the chat bot is runningState = false
     *
     * @param name string of the name of the chatbot
     */
    public ChatBot(String name) {
        this.name =  name;
        this.runningState = false;
        this.taskManager = new TaskManager();
    }

    /**
     * Initializes the chatbot by setting its runningState to true and responds
     * with a greeting message. The task manager is also initialized by loading
     * any pre-existing data.
     */
    public void initialize() {
        this.runningState = true;
        System.out.println(wrapMessage("Greetings, " + this.name + " at your service.\n" +
                "How may I help you today?\n"));
        taskManager.load();
    }

    /**
     * Terminates the chatbot by setting its runningState to false and responds
     * with a goodbye message.
     */
    public void terminate() {
        this.runningState = false;
        System.out.println(wrapMessage("Goodbye! It was nice seeing you.\n"));
    }

    /**
     * Returns the runningState of the chatbot
     *
     * @return running state of the chatbot
     */
    public boolean isRunning() {
        return this.runningState;
    }

    /**
     * Processes commands from an input by the user and calls the appropriate command
     * for the task manager. Handles exceptions for invalid commands. Calls the
     * save command of the task manager after every successful command processed.
     *
     * @param input string of the input provided by the user
     */
    public void processCommand(String input) {
        Scanner inputScanner = new Scanner(input);
        try {
            String command = inputScanner.next();
            if (!(inputScanner.hasNext())) {
                switch (command) {
                case "bye":
                    this.runningState = false;
                    break;
                case "list":
                    System.out.println(wrapMessage(taskManager.list()));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    throw new EmptyTaskException();
                default:
                    throw new InvalidCommandException();
                }
            } else {
                String arguments = inputScanner.nextLine().substring(1);
                Scanner argumentScanner = new Scanner(arguments);
                switch (command) {
                case "todo":
                    System.out.println(wrapMessage(taskManager.addTask(
                            new ToDoTask(argumentScanner.nextLine()))));
                    break;
                case "deadline":
                    argumentScanner.useDelimiter(" /by ");
                    System.out.println(wrapMessage(taskManager.addTask(
                            new DeadlineTask(argumentScanner.next(), argumentScanner.next(), taskManager.getDateFormat()))));
                    break;
                case "event":
                    argumentScanner.useDelimiter(" /at ");
                    System.out.println(wrapMessage(taskManager.addTask(
                            new EventTask(argumentScanner.next(), argumentScanner.next(), taskManager.getDateFormat()))));
                    break;
                case "mark":
                    System.out.println(wrapMessage(taskManager.mark(Integer.parseInt(arguments))));
                    break;
                case "unmark":
                    System.out.println(wrapMessage(taskManager.unmark(Integer.parseInt(arguments))));
                    break;
                case "delete":
                    System.out.println(wrapMessage(taskManager.delete(Integer.parseInt(arguments))));
                    break;
                default:
                    throw new InvalidCommandException();
                }
                argumentScanner.close();
                taskManager.save();
            }
        } catch (InputMismatchException exception) {
            System.out.println(wrapMessage("You need to put a number after your command!\n"));
        } catch (NoSuchElementException exception) {
            System.out.println(wrapMessage("You placed invalid arguments!\n"));
        } catch (EmptyTaskException | InvalidDeadlineException | InvalidEventException exception) {
            System.out.println(wrapMessage(exception.toString()));
        } catch (InvalidCommandException exception) {
            System.out.println(wrapMessage("Sorry, I don't understand what you mean by \"" + input + "\"\n"));
        } finally {
            inputScanner.close();
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