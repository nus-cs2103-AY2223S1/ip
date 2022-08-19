import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class ChatBot {
    private final String name;
    private boolean runningState;
    private final TaskManager taskManager;
    ChatBot(String name) {
        this.name =  name;
        this.runningState = false;
        this.taskManager = new TaskManager();
    }

    public void initialize() {
        this.runningState = true;
        System.out.println(wrapMessage("Greetings, " + this.name + " at your service.\n" +
                "How may I help you today?\n"));
    }

    public void terminate() {
        this.runningState = false;
        System.out.println(wrapMessage("Goodbye! It was nice seeing you.\n"));
    }

    public boolean isRunning() {
        return this.runningState;
    }

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
                        printError(input);
                        break;
                }
            } else {
                String arguments = inputScanner.nextLine();
                Scanner argumentScanner = new Scanner(arguments);
                switch (command) {
                    case "todo":
                        System.out.println(wrapMessage(taskManager.addTask(
                                new ToDoTask(argumentScanner.nextLine()))));
                        break;
                    case "deadline":
                        argumentScanner.useDelimiter("/by");
                        System.out.println(wrapMessage(taskManager.addTask(
                                new DeadlineTask(argumentScanner.next(), argumentScanner.next()))));
                        break;
                    case "event":
                        argumentScanner.useDelimiter("/at");
                        System.out.println(wrapMessage(taskManager.addTask(
                                new EventTask(argumentScanner.next(), argumentScanner.next()))));
                        break;
                    case "mark":
                        System.out.println(wrapMessage(taskManager.mark(Integer.parseInt(arguments.substring(1)))));
                        break;
                    case "unmark":
                        System.out.println(wrapMessage(taskManager.unmark(Integer.parseInt(arguments.substring(1)))));
                        break;
                    case "delete":
                        System.out.println(wrapMessage(taskManager.delete(Integer.parseInt(arguments.substring(1)))));
                        break;
                    default:
                        printError(input);
                        break;
                }
                argumentScanner.close();
            }
        } catch (InputMismatchException exception) {
            System.out.println(wrapMessage("You need to put a number after your command!\n"));
        } catch (NoSuchElementException exception) {
            System.out.println(wrapMessage("You placed invalid arguments!\n"));
        } catch (EmptyTaskException exception) {
            System.out.println(wrapMessage(exception.toString()));
        } catch (InvalidDeadlineException exception) {
            System.out.println(wrapMessage(exception.toString()));
        } catch (InvalidEventException exception) {
            System.out.println(wrapMessage(exception.toString()));
        } finally {
            inputScanner.close();
        }
    }

    private String wrapMessage(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--------------------------------------------------\n");
        stringBuilder.append(str);
        stringBuilder.append("--------------------------------------------------");
        return stringBuilder.toString();
    }

    private void printError(String input) {
        System.out.println(wrapMessage("Sorry, I don't understand what you mean by \"" + input + "\"\n"));
    }
}