import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String DIVIDER = "\t___________________________";
    private static Set<String> uniqueTasks = new HashSet<String>();
    private static List<String> tasksList = new ArrayList<String>(100);

    public static void print(String input, String keyword) {
        System.out.println(DIVIDER);

        if (keyword.equals("bye")) {
            System.out.printf("\tBye. Hope to see you again soon!\n");
        } else if (keyword.equals("add")) {
            System.out.printf("\tadded: %s\n", input);
        } else if (keyword.equals("contained")) {
            System.out.printf("\t%s is already added\n", input);
        } else if (keyword.equals("list")) {
            for (int i = 0; i < tasksList.size(); i++) {
                System.out.printf("\t%d. %s\n", i + 1, tasksList.get(i));
            }
        }
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DIVIDER);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();

            if (input.equals("list")) {
                print(input, "list");
            } else if (input.equals("bye")) {
                print(input, "bye");
                break;
            } else {
                if (!uniqueTasks.contains(input)) {
                    uniqueTasks.add(input);
                    tasksList.add(input);
                    print(input, "add");
                } else {
                    print(input, "contained");
                }
            }
        }
        sc.close();
    }
}
