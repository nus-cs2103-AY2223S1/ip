import java.util.Scanner;
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
            System.out.printf("\tGot it. I've added this task:\n");
            Task currTask = tasksList.get(tasksList.size() - 1);
            System.out.printf("\t    %s\n", currTask);
            System.out.printf("\tNow you have %d tasks in the list.\n", tasksList.size());
        } else if (keyword.equals("contained")) {
            System.out.printf("\t%s is already added\n", input);
        } else if (keyword.equals("list")) {
            System.out.printf("\tHere are the tasks in your list:\n");
            for (int i = 0; i < tasksList.size(); i++) {
                Task currTask = tasksList.get(i);
                System.out.printf("\t%d.%s\n", i + 1, currTask);
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
            System.out.printf("\tNice! I've marked this task as done:\n");
            System.out.printf("\t    %s\n", currentTask);

        } else if (keyword.equals("unmark")) {
            currentTask.unMark();
            System.out.printf("\tOK, I've marked this task as not done yet:\n");
            System.out.printf("\t    %s\n", currentTask);
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
            } else if (input.equals("todo")) {
                if (sc.hasNextLine()) {
                    input = sc.nextLine();
                }
                input = input.substring(1);
                Task newTask = new Todo(input);
                tasksList.add(newTask);
                print(input, "add");
            } else if (input.equals("deadline")) {
                if (sc.hasNextLine()) {
                    input = sc.nextLine();
                }

                int index = input.lastIndexOf("/by");

                if (index > -1) {
                    String by = input.substring(index + 4, input.length());
                    input = input.substring(1, index - 1);

                    Task newTask = new Deadline(input, by);
                    tasksList.add(newTask);
                    print(input, "add");
                } else {
                    print(input, "invalid");
                }
            } else if (input.equals("event")) {
                if (sc.hasNextLine()) {
                    input = sc.nextLine();
                }

                int index = input.lastIndexOf("/at");

                if (index > -1) {
                    String at = input.substring(index + 4, input.length());
                    input = input.substring(1, index - 1);

                    Task newTask = new Event(input, at);
                    tasksList.add(newTask);
                    print(input, "add");
                } else {
                    print(input, "invalid");
                }
            } else {
                print(input, "invalid");
            }
        }
        sc.close();
    }
}
