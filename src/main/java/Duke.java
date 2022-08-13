import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        // Create arraylist to store given tasks
        ArrayList<String> tasks = new ArrayList<String>();

        // Read string input from user
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {
            if (Objects.equals(command, "list")) {
                int count = 1;
                for (String task : tasks) {
                    String s = Integer.toString(count);
                    System.out.println(s + ". " + task);
                    count++;
                }
                command = sc.nextLine();
                continue;
            }
            tasks.add(command);
            System.out.println("added: " + command);
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
