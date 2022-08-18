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
        } else if (keyword.equals("unknown")) {
            System.out.printf("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } else if (keyword.equals("empty")) {
            System.out.printf("\t☹ OOPS!!! The description of a %s cannot be empty.\n", input);
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
        } else if (keyword.equals("delete")) {
            tasksList.remove(index);
            System.out.printf("\tNoted. I've removed this task:\n");
            System.out.printf("\t    %s\n", currentTask);
            System.out.printf("\tNow you have %d tasks in the list.\n", tasksList.size());
        }
        System.out.println(DIVIDER);
    }

    public static void mainProcess() {

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

            try {
                String input = sc.next();

                if (input.equals("list")) {
                    print(input, "list");
                } else if (input.equals("bye")) {
                    print(input, "bye");
                    break;
                } else if (input.equals("mark")) {
                    // get the index
                    if (sc.hasNextInt()) {
                        int index = sc.nextInt();
                        if (index <= tasksList.size() && index > 0) {
                            print("mark", index - 1);
                        } else {
                            // list out of bound
                            print(input, "unknown");
                        }
                    } else {
                        // invalid
                        // ☹ OOPS!!! The description of a mark cannot be empty.
                        throw new DukeException("mark empty");
                    }

                } else if (input.equals("unmark")) {
                    if (sc.hasNextInt()) {
                        int index = sc.nextInt();
                        if (index <= tasksList.size() && index > 0) {
                            print("unmark", index - 1);
                        } else {
                            // list out of bound
                            print(input, "unknown");
                        }
                    } else {
                        // ☹ OOPS!!! The description of a unmark cannot be empty.
                        throw new DukeException("unmark empty");
                    }
                } else if (input.equals("delete")) {
                    // get the index
                    if (sc.hasNextInt()) {
                        int index = sc.nextInt();
                        print("delete", index - 1);
                    } else {
                        // invalid
                        // ☹ OOPS!!! The description of a mark cannot be empty.
                        throw new DukeException("delete empty");
                    }

                } else if (input.equals("todo")) {
                    if (sc.hasNextLine()) {
                        input = sc.nextLine();
                        if (input.length() <= 0) {
                            throw new DukeException("todo empty");
                        }
                        input = input.substring(1);

                        Task newTask = new Todo(input);
                        tasksList.add(newTask);
                        print(input, "add");
                    }

                } else if (input.equals("deadline")) {
                    if (sc.hasNextLine()) {
                        input = sc.nextLine();
                        if (input.length() <= 0) {
                            throw new DukeException("deadline empty");
                        }
                        int index = input.lastIndexOf("/by");

                        if (index > -1) {
                            String by = input.substring(index + 4, input.length());
                            input = input.substring(1, index - 1);

                            Task newTask = new Deadline(input, by);
                            tasksList.add(newTask);
                            print(input, "add");
                        } else {
                            throw new DukeException("unknown");
                        }
                    }

                } else if (input.equals("event")) {
                    if (sc.hasNextLine()) {
                        input = sc.nextLine();
                        if (input.length() <= 0) {
                            throw new DukeException("event empty");
                        }
                        int index = input.lastIndexOf("/at");

                        if (index > -1) {
                            String at = input.substring(index + 4, input.length());
                            input = input.substring(1, index - 1);

                            Task newTask = new Event(input, at);
                            tasksList.add(newTask);
                            print(input, "add");
                        } else {
                            throw new DukeException("unknown");
                        }
                    }
                } else {
                    throw new DukeException("unknown");
                }
            } catch (DukeException ex) {
                if (ex.getMessage().equals("unknown")) {
                    print("", "unknown");
                } else if (ex.getMessage().equals("event empty")) {
                    print("event", "empty");
                } else if (ex.getMessage().equals("deadline empty")) {
                    print("deadline", "empty");
                } else if (ex.getMessage().equals("todo empty")) {
                    print("todo", "empty");
                } else if (ex.getMessage().equals("mark empty")) {
                    print("mark", "empty");
                } else if (ex.getMessage().equals("unmark empty")) {
                    print("unmark", "empty");
                } else if (ex.getMessage().equals("delete empty")) {
                    print("delete", "empty");
                }
            }
        }
        sc.close();

    }
}
