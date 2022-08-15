import java.util.Scanner;
import java.util.ArrayList;

public class Anya {
    static final String breakLine = "\n---------------------------------------------------------------------";
    public static void main(String[] args) {
        // Initialising variables
        Scanner sc = new Scanner(System.in);
        String command;
        ArrayList<String> tasks = new ArrayList<>();

        // Greet
        System.out.println("Hello! Anya is happy to meet you.\nHow can Anya help?" + breakLine);

        // Get command
        command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                list(tasks);
            } else {
                add(tasks, command);
            }
            command = sc.nextLine();
        }

        // Exit
        System.out.println("Anya is sad to see you leave. Please be back soon." + breakLine);
    }

    public static void add(ArrayList<String> tasks, String task) {
        tasks.add(task);
        System.out.println("Anya added: " + task + breakLine);
    }

    public static void list(ArrayList<String> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i).toString());
        }
        System.out.println(breakLine);
    }
}
