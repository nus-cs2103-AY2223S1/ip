import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String BYE = "bye";
    private static final String LIST = "list";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t-------------------------------");
        System.out.println("\tHello, I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-------------------------------");
        ArrayList<String> tasks = new ArrayList<>();
        while (true) {
            Scanner s = new Scanner(System.in);
            String command = s.nextLine().trim();
            System.out.println("\t-------------------------------");
            if (command.equals(BYE)) {

                System.out.println("\tBye! Hope to see you again");
                break;
            } else if (command.equals(LIST)) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
                }
            } else {
                System.out.println("\tadded: " + command);
                tasks.add(command);
            }
            System.out.println("\t-------------------------------");
        }
        System.out.println("\t-------------------------------");
    }
}
