import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();
    private final static String WELCOME = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";

    private static String addTopBottomLines(String string) {
        return "____________________________________________________________\n" +
                string + "\n" +
                "____________________________________________________________";
    }

    private static void printTasks() {
        String out = "";
        for (int i = 0; i < tasks.size(); i++) {
            out += i + ". " + tasks.get(i).description + "\n";
        }
        System.out.println(addTopBottomLines(out.trim()));
    }

    private static void addTask(String desc) {
        tasks.add(new Task(desc));
        System.out.println(addTopBottomLines("added: " + desc));
    }

    public static void main(String[] args) {
        System.out.println(WELCOME);
        while (true) {
            String userInput = sc.nextLine();
            switch (userInput) {
                case "bye":
                    System.out.println("bye");
                    return;
                case "list":
                    printTasks();
                    break;
                default:
                    addTask(userInput);

            }
        }
    }
}
