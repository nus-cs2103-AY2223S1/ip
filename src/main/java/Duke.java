import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {

    private static List<Task> dukeInputs = new ArrayList<>();
    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";
    private static final String LIST_HEADER = "Here are the tasks in your list:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true) {
            // Read the input
            input = sc.nextLine();
            if (input.startsWith("mark")) {
                //split by space, then the second value
                int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
                dukeInputs.get(taskIndex).setDone();
                Task currentTask = dukeInputs.get(taskIndex);
                String completionStatus = "[" + currentTask.getStatusIcon() + "] ";

                System.out.println(DONE_MESSAGE);
                System.out.println("  " + completionStatus + currentTask.description);

            }

            else if (input.startsWith("unmark")) {
                int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
                dukeInputs.get(taskIndex).removeDone();
                Task currentTask = dukeInputs.get(taskIndex);
                String completionStatus = "[" + currentTask.getStatusIcon() + "] ";

                System.out.println(UNDONE_MESSAGE);
                System.out.println("  " + completionStatus + currentTask.description);

            }

            else if (input.equals("bye")) {
                System.out.println(ENDING_MESSAGE);
                break;
            }

            else if (input.equals("list")) {
                System.out.println(LIST_HEADER);
                for (int i = 0; i < dukeInputs.size(); i++) {
                    Task currentTask = dukeInputs.get(i);
                    String completionStatus = "[" + currentTask.getStatusIcon() + "] ";
                    System.out.println(i + 1 + ". " + completionStatus + currentTask.description);
                }
            }

            else {
                Task newTask = new Task(input);
                dukeInputs.add(newTask);
                System.out.println("added: " + input);
            }
        }
    }
}

