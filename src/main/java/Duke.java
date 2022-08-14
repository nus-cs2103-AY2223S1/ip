import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        System.out.println("Hello! I'm Pip :)\nWhat can I do for you?"); // greet the user

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine().trim();
            if (input.equals("bye")) { // exit
                in.close();
                System.out.println("Goodbye and see you again soon!");
                break;
            } else if (input.equals("list")) { // display tasks stored
                taskList.displayTasks();
            } else if (input.startsWith("mark")) { // mark task as done
                String taskNumAsString = input.substring(5);
                try {
                    int taskNum = Integer.parseInt(taskNumAsString);
                    Task task = taskList.getTask(taskNum);
                    if (task != null) {
                        task.markAsDone();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please specify a task number!");
                }
            } else if (input.startsWith("unmark")) { // mark task as not done
                String taskNumAsString = input.substring(7);
                try {
                    int taskNum = Integer.parseInt(taskNumAsString);
                    Task task = taskList.getTask(taskNum);
                    if (task != null) {
                        task.markAsNotDone();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please specify a task number!");
                }
            } else { // store tasks entered by the user
                taskList.addTask(input);
            }
        }
    }
}
