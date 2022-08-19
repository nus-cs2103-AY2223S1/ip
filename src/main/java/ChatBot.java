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
        String command = inputScanner.next();
        if (!(inputScanner.hasNext())) {
            switch (command) {
                case "bye":
                    this.runningState = false;
                    break;
                case "list":
                    System.out.println(wrapMessage(taskManager.list()));
                    break;
                default:
                    printError(input);
                    break;
            }
        } else {
            try {
                switch (command) {
                    case "todo":
                        System.out.println(wrapMessage(taskManager.addTask(
                                new ToDoTask(inputScanner.nextLine()))));
                        break;
                    case "deadline":
                        inputScanner.useDelimiter("/by");
                        System.out.println(wrapMessage(taskManager.addTask(
                                new DeadlineTask(inputScanner.next(), inputScanner.next()))));
                        break;
                    case "event":
                        inputScanner.useDelimiter("/at");
                        System.out.println(wrapMessage(taskManager.addTask(
                                new EventTask(inputScanner.next(), inputScanner.next()))));
                        break;
                    case "mark":
                        if (inputScanner.hasNextInt()) {
                            int itemNumber = inputScanner.nextInt();
                            if (!(inputScanner.hasNext())) {
                                System.out.println(wrapMessage(taskManager.mark(itemNumber)));
                            } else {
                                printError(input);
                            }
                        } else {
                            printError(input);
                        }
                        break;
                    case "unmark":
                        if (inputScanner.hasNextInt()) {
                            int itemNumber = inputScanner.nextInt();
                            if (!(inputScanner.hasNext())) {
                                System.out.println(wrapMessage(taskManager.unmark(itemNumber)));
                            } else {
                                printError(input);
                            }
                        } else {
                            printError(input);
                        }
                        break;
                    default:
                        printError(input);
                        break;
                }
            } catch(Exception exception) {
                printError(input);
            }
        }
        inputScanner.close();
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