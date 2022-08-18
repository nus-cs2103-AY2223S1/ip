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
                if (taskList.size() == 0) {
                    System.out.println(indent + "[No tasks available]");
                }
                for (int index = 0; index < taskList.size(); ++index) {
                    System.out.print(indent);
                    System.out.print(index + 1);
                    System.out.println(". " + taskList.get(index));
                }
                System.out.println(divider);
            } else if (input.contains("mark")) {
                try {
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
                } catch (StringIndexOutOfBoundsException exception) {
                    System.out.println(divider + indent + "Wait, which task are you referring to?\n"
                            + divider);
                } catch (Exception exception) {
                    System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                }
            } else if (input.contains("delete")) {
                try {
                    int indexToDelete = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                    Task removedTask = taskList.remove(indexToDelete);
                    int numberOfTasks = taskList.size();
                    System.out.println(divider + indent + "Noted, I've removed this task:");
                    System.out.println(indent + indent + removedTask.toString());
                    System.out.println(indent + "Now you have " + numberOfTasks + " tasks in your list.\n" + divider);
                } catch (StringIndexOutOfBoundsException exception) {
                    System.out.println(divider + indent + "Wait, which task do you want to delete?\n" + divider);
                } catch (Exception exception) {
                    System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                }
            } else {
                boolean response = false;
                if (input.contains("event")) {
                    try {
                        int indexOfDateTime = input.indexOf("/at");
                        String dateTime = input.substring(indexOfDateTime + 4);
                        String eventDescription = input.substring(6, indexOfDateTime - 1);
                        Event newTask = new Event(dateTime, eventDescription);
                        response = taskList.add(newTask);
                    } catch (StringIndexOutOfBoundsException exception) {
                        System.out.println(divider + indent + "Warning: The description and time " +
                                "cannot be empty.\n" + divider);
                    } catch (Exception exception) {
                        System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                    }
                } else if (input.contains("todo")) {
                    try {
                        String toDoDescription = input.substring(5);
                        ToDo newToDo = new ToDo(toDoDescription);
                        response = taskList.add(newToDo);
                    } catch (StringIndexOutOfBoundsException exception) {
                        System.out.println(divider + indent + "Warning: The description cannot be empty.\n"
                            + divider);
                    } catch (Exception exception) {
                        System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                    }
                } else if (input.contains("deadline")) {
                    try {
                        int indexOfDateTime = input.indexOf("/by");
                        String dateTime = input.substring(indexOfDateTime + 4);
                        String deadlineDescription = input.substring(9, indexOfDateTime - 1);
                        Deadline newDeadline = new Deadline(dateTime, deadlineDescription);
                        response = taskList.add(newDeadline);
                    } catch (StringIndexOutOfBoundsException exception) {
                        System.out.println(divider + indent + "Warning: The description and time" +
                                " cannot be empty.\n" + divider);
                    } catch (Exception exception) {
                        System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                    }
                } else {
                    System.out.println(divider + indent + "Oops, sorry! I don't know what that means :(\n" + divider);
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
