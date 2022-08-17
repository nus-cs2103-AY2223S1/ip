import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> storage = new ArrayList<>();

    private static void list() {
        System.out.println("____________________________________________________________");

        int index = 1;
        Iterator<String> iter = storage.iterator();
        while (iter.hasNext()) {
            System.out.print(String.format("%d. ", index));
            System.out.println(iter.next());
            index++;
        }

        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
        String command = scan.nextLine();
        if (!command.equals("list")) {
            storage.add(command);
        }

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                list();
                command = scan.nextLine();
            } else {
                System.out.println(
                        "____________________________________________________________ \n"
                                + "added: " + command + "\n"
                                + "____________________________________________________________");
                command = scan.nextLine();
            }

            if (!command.equals("list")) {
                storage.add(command);
            }
        }

        System.out.println(
                "____________________________________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "____________________________________________________________");
    }
}
