import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();

    private static void list() {
        System.out.println("____________________________________________________________ \n"
                + "Here are the tasks in your list:");

        int index = 1;
        Iterator<Task> iter = storage.iterator();
        while (iter.hasNext()) {
            System.out.printf("%d. ", index);
            System.out.println(iter.next().printTask());
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
            storage.add(new Task(command));
        }

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                list();
            } else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
                int index = Character.getNumericValue(command.charAt(command.length() - 1));
                storage.get(index - 1).toggleDone(command);
            } else {
                System.out.println(
                        "____________________________________________________________ \n"
                                + "added: " + command + "\n"
                                + "____________________________________________________________");
            }

            command = scan.nextLine();
            if (!command.equals("list") && !(command.startsWith("mark ") || command.startsWith("unmark "))) {
                storage.add(new Task(command));
            }
        }

        System.out.println(
                "____________________________________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "____________________________________________________________");
    }
}
