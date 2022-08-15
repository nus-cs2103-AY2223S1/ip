import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        List<String> store = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! This is \n" + logo + "\nat your service!!");
        System.out.println("________________________________________________________________");
        System.out.println("How may I serve you today, Master?");
        System.out.println("\t============================================================\n");

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("\t____________________________________________________________");
            if (command.equals("list")) {
                for (int i = 0; i < store.size(); i++) {
                    int index = i + 1;
                    System.out.println("\t" + index + ". " + store.get(i));
                }
            } else {
                System.out.println("\tadded: " + command);
                store.add(command);
            }
            System.out.println("\t____________________________________________________________\n");
            command = sc.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye Master! It was a pleasure serving you, see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
