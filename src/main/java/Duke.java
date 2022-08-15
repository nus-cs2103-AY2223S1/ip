import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINES = "_________________________\n";
    public static final String START = "Hey there! I'm Duke.\nWhat do you want to do today?\n";
    public static final String END = "Bye! Hope you had fun!";
    private ArrayList<String> tasks;
    private boolean ended;


    public Duke() {
        this.tasks = new ArrayList<>();
        this.ended = false;
    }

    public void printMessage(String message) {
        System.out.print(LINES + message + "\n" + LINES);
    }

    public void printList() {
        System.out.print(LINES);

        System.out.print(LINES);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        printMessage(START);
        while(!ended) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    printMessage(END);
                    this.ended = true;
                    break;
                case "list":
                    StringBuilder allTasks = new StringBuilder();
                    for (int i = 1; i <= tasks.size(); i++) {
                        allTasks.append(i + ". " + tasks.get(i - 1));
                        if (i < tasks.size()) {
                            allTasks.append("\n");
                        }
                    }
                    printMessage(allTasks.toString());
                    break;
                default:
                    printMessage("added: " + command);
                    this.tasks.add(command);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
