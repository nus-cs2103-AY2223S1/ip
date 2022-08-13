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

    private void startInteraction() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        boolean hasEnded = false;
        while (!hasEnded) {
            String command = getUserCommand(sc);
            switch (command.toLowerCase()) {
            case "list":
                StringBuilder response = new StringBuilder();
                response.append("List of tasks:\n");
                for (int i = 0; i < tasks.size(); ++i) {
                    response.append(String.format("\t %d. %s", i + 1, tasks.get(i)));
                    if (i + 1 < tasks.size()) {
                        response.append("\n");
                    }
                }
                printResponse(response.toString());
                break;
            case "bye":
                printResponse(BYE_MESSAGE);
                hasEnded = true;
                break;
            default:
                tasks.add(command);
                printResponse("Added task: " + command);
            }
        }
    }

    public void run() {
        greet();
        startInteraction();
    }
}
