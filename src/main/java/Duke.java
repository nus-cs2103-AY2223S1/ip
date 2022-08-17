import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String startUpMessage  = "Hello! I'm Duke\n"
                                                + "What can I do for you?";
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String exitCmd = "bye";
    private static final String noTaskMessage   = "It appears you have no tasks right now,\n"
                                                + "would you like to add some?";
    private static final ArrayList<String> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(startUpMessage);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals(exitCmd)) {
            if (userInput.equals("list")) {
                System.out.println(printTasks());
            } else {
                tasks.add(userInput);
                System.out.println("added: " + userInput);
            }
            userInput = sc.nextLine();
        }
        System.out.println(exitMessage);
    }

    static String printTasks() {
        if (tasks.isEmpty()) {
            return noTaskMessage;
        } else {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                if (i != tasks.size() - 1) {
                    message.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
                } else {
                    message.append(String.format("%d. %s", i + 1, tasks.get(i)));
                }
            }
            return message.toString();
        }
    }
}
