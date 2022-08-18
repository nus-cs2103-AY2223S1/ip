import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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
                    System.out.println(wrapMessage(taskManager.addTask(new Task(input))));
                    break;
            }
        } else {
            if (inputScanner.hasNextInt()) {
                int itemNumber = inputScanner.nextInt();
                if (!(inputScanner.hasNext())) {
                    switch (command) {
                        case "mark":
                            System.out.println(wrapMessage(taskManager.mark(itemNumber)));
                            break;
                        case "unmark":
                            System.out.println(wrapMessage(taskManager.unmark(itemNumber)));
                            break;
                        default:
                            System.out.println(wrapMessage(taskManager.addTask(new Task(input))));
                            break;
                    }
                } else {
                    System.out.println(wrapMessage(taskManager.addTask(new Task(input))));
                }
            } else {
                System.out.println(wrapMessage(taskManager.addTask(new Task(input))));
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
}