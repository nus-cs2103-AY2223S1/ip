import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" +
                           "What can I do for you?\n");

        List<String> items = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        while (true) {
            String input = s.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(i + 1 + ". " + items.get(i));
                }
            } else {
                items.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
