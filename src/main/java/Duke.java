import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String indent = "     ";
        String divider = "  ____________________________________________________________\n";
        String logo = indent + "____        _        \n"
                    + indent + "|  _ \\ _   _| | _____ \n"
                    + indent + "| | | | | | | |/ / _ \\\n"
                    + indent + "| |_| | |_| |   <  __/\n"
                    + indent + "|____/ \\__,_|_|\\_\\___|\n\n";
        String openingStatement = indent + "Hello! I'm Duke.\n"
                                + indent + "What can I do for you?\n";
        System.out.println(divider + logo + openingStatement + divider);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.print(divider);
                System.out.print(indent + "Here are the tasks in your list:\n");
                for (int index = 0; index < taskList.size(); ++index) {
                    System.out.print(indent);
                    System.out.print(index + 1);
                    System.out.println(". " + taskList.get(index));
                }
                System.out.println(divider);
            } else if (input.contains("mark")) {
                int taskIndex = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                if (input.contains("unmark")) {
                    taskList.get(taskIndex).unmarkAsDone();
                    System.out.println(divider + indent + "Okay, I've marked this task as not done yet:");
                    System.out.println(indent + indent + taskList.get(taskIndex) + "\n" + divider);
                } else {
                    taskList.get(taskIndex).markAsDone();
                    System.out.println(divider + indent + "Nice! I've marked this as done:");
                    System.out.println(indent + indent + taskList.get(taskIndex) + "\n" + divider);
                }
            } else {
                Task newTask = new Task(input);
                boolean response = taskList.add(newTask);
                if (response) {
                    System.out.println(divider + indent + "new task added! "
                                        + newTask.getDescription() + "\n" + divider);
                }
            }
        }

        String exitStatement = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + indent + exitStatement + "\n" + divider);
        scanner.close();
    }
}
