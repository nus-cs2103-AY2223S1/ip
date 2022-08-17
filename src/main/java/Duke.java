import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<String> tasks = new ArrayList<>();
    static String exitWord = "bye";
    static String listWord = "list";
    public static void main(String[] args) {
        String inputText = "";

        printGreeting();
        while (true) {
            Scanner input = new Scanner(System.in);
            inputText = input.nextLine();

            if (inputText.equals(exitWord)) {
                printBye();
                return;
            }

            if (inputText.equals(listWord)) {
                printTasks();
            } else {
                tasks.add(inputText);

                System.out.println("\n  _______________");
                System.out.println("  Added: " + inputText);
                System.out.println("  _______________\n");
            }
        }
    }

    private static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printBye() {
        System.out.println("Goodbye. Hope to see you again soon 😈 \n");
    }

    private static void printTasks() {
        System.out.println("Your tasks:");
        for (String task: tasks) {
            System.out.println(tasks.indexOf(task)+1 + ": " + task);
        }
    }
}
