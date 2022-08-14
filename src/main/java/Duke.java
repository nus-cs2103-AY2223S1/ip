import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner myScanner = new Scanner(System.in);
        String command = myScanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < history.size(); i++) {
                    System.out.println(i + 1 + ". " + history.get(i));
                }
            } else {
                history.add(command);
                System.out.println("added: " + command);
            }
            System.out.println();
            command = myScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
