package duke.chatbot;

import duke.taskmanager.TaskManager;
import duke.taskmanager.task.*;
import duke.taskmanager.exceptions.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class ChatBot {
    private final String name;
    private boolean isRunning;
    private final TaskManager taskManager;
    public ChatBot(String name) {
        this.name =  name;
        this.isRunning = false;
        this.taskManager = new TaskManager();
    }

    public void initialize() {
        this.isRunning = true;
        System.out.println(wrapMessage("Greetings, " + this.name + " at your service.\n"
                + "How may I help you today?\n"));
        taskManager.load();
    }

    public void terminate() {
        this.isRunning = false;
        System.out.println(wrapMessage("Goodbye! It was nice seeing you.\n"));
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void processCommand(String input) {
        Scanner inputScanner = new Scanner(input);
        try {
            String command = inputScanner.next();
            if (!(inputScanner.hasNext())) {
                switch (command) {
                case "bye":
                    this.isRunning = false;
                    break;
                case "list":
                    System.out.println(wrapMessage(taskManager.list()));
                    break;
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event":
                    throw new EmptyTaskException();
                default:
                    throw new InvalidCommandException();
                }
            } else {
                String arguments = inputScanner.nextLine().substring(1);
                Scanner argumentScanner = new Scanner(arguments);
                String response;
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
                    response = taskManager.mark(Integer.parseInt(arguments));
                    break;
                case "unmark":
                    response = taskManager.unmark(Integer.parseInt(arguments));
                    break;
                case "delete":
                    response = taskManager.delete(Integer.parseInt(arguments));
                    break;
                default:
                    throw new InvalidCommandException();
                }
                System.out.println(wrapMessage(response));
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

    private String wrapMessage(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------------------------------------------------------\n");
        stringBuilder.append(str);
        stringBuilder.append("------------------------------------------------------------");
        return stringBuilder.toString();
    }
}