import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String DIVIDER = "\t___________________________\n";
    private static List<Task> tasksList = new ArrayList<Task>(100);

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
                Task currTask = tasksList.get(i);
                String statusIcon = currTask.getStatusIcon();
                String description = currTask.getDescription();
                System.out.printf("\t%d.[%s] %s\n", i + 1, statusIcon, description);
            }
        } else if (keyword.equals("invalid")) {
            System.out.printf("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        System.out.println(DIVIDER);
    }

    // for printing mark and unmark
    public static void print(String keyword, int index) {
        System.out.println(DIVIDER);
        Task currentTask = tasksList.get(index);

        if (keyword.equals("mark")) {
            currentTask.mark();
            String statusIcon = currentTask.getStatusIcon();
            String description = currentTask.getDescription();
            System.out.printf("\tNice! I've marked this task as done:\n");
            System.out.printf("\t    [%s] %s\n", statusIcon, description);

        } else if (keyword.equals("unmark")) {
            currentTask.unMark();
            String statusIcon = currentTask.getStatusIcon();
            String description = currentTask.getDescription();
            System.out.printf("\tOK, I've marked this task as not done yet:\n");
            System.out.printf("\t    [%s] %s\n", statusIcon, description);
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
            } else if (input.equals("mark")) {
                // get the index
                if (sc.hasNext()) {
                    int index = sc.nextInt();
                    if (index <= tasksList.size() && index > 0) {
                        print("mark", index - 1);
                    } else {
                        print(input, "invalid");
                    }
                } else {
                    print(input, "invalid");
                }

            } else if (input.equals("unmark")) {
                if (sc.hasNext()) {
                    int index = sc.nextInt();
                    if (index <= tasksList.size() && index > 0) {
                        print("unmark", index - 1);
                    } else {
                        print(input, "invalid");
                    }
                } else {
                    print(input, "invalid");
                }
            } else {
                if (sc.hasNextLine()) {
                    input += sc.nextLine();
                }

                Task newTask = new Task(input);
                tasksList.add(newTask);
                print(input, "add");
            }
        }
        sc.close();
    }
}
