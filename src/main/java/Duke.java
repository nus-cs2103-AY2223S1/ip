import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET_MESSAGE = "Hello! I am Duke. How can I help you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you soon!";

    private final ArrayList<String> tasks;

    public Duke() {
        tasks = new ArrayList<>();
    }

    private void printResponse(String response) {
        System.out.println(">> " + response);
    }

    private String getUserCommand(Scanner sc) {
        System.out.print("<< ");
        return sc.nextLine();
    }

    private void greet() {
        System.out.println(LOGO);
        printResponse(GREET_MESSAGE);
    }

    private void endInteraction() {
        printResponse(BYE_MESSAGE);
    }

    private void addTask(String task) {
        tasks.add(task);
        printResponse("Added task: " + task);
    }

    private void listTasks() {
        StringBuilder response = new StringBuilder();
        response.append("List of tasks:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            response.append(String.format("\t %d. %s", i + 1, tasks.get(i)));
            if (i + 1 < tasks.size()) {
                response.append("\n");
            }
        }
        printResponse(response.toString());
    }

    private void startInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean hasEnded = false;
        while (!hasEnded) {
            String command = getUserCommand(sc);
            switch (command.toLowerCase()) {
            case "list":
                listTasks();
                break;
            case "bye":
                endInteraction();
                hasEnded = true;
                break;
            default:
                addTask(command);
            }
        }
    }

    public void run() {
        greet();
        startInteraction();
    }
}
