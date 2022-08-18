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
                boolean response = false;
                if (input.contains("event")) {
                    int indexOfDateTime = input.indexOf("/at");
                    String dateTime = input.substring(indexOfDateTime + 4);
                    String eventDescription = input.substring(6, indexOfDateTime - 1);
                    Event newTask = new Event(dateTime, eventDescription);
                    response = taskList.add(newTask);

                } else if (input.contains("todo")) {
                    String toDoDescription = input.substring(5);
                    ToDo newToDo = new ToDo(toDoDescription);
                    response = taskList.add(newToDo);
                } else { // input contains "deadline"
                    int indexOfDateTime = input.indexOf("/by");
                    String dateTime = input.substring(indexOfDateTime + 4);
                    String deadlineDescription = input.substring(9, indexOfDateTime - 1);
                    Deadline newDeadline = new Deadline(dateTime, deadlineDescription);
                    response = taskList.add(newDeadline);
                }
                if (response) {
                    int numberOfTasks = taskList.size();
                    Task newTask = taskList.get(numberOfTasks - 1);
                    System.out.println(divider + indent + "Got it. I've added this task:");
                    System.out.println(indent + indent + newTask.toString());
                    System.out.println(indent + "Now you have " + numberOfTasks + " tasks in your list.\n" + divider);
                }
            }
        }

        String exitStatement = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + indent + exitStatement + "\n" + divider);
        scanner.close();
    }
}
