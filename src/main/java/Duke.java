import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");


        ArrayList<Task> tasks = new ArrayList<Task>();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            String input = sc.nextLine();
            String command = input.contains(" ") ? input.split(" ", 2)[0] : input;
            String parameters = input.contains(" ") ? input.split(" ", 2)[1] : "";
            switch (command) {

                case "list":
                    if (tasks.size() == 0) {
                        System.out.println("You have nothing to do!");
                        break;
                    }
                    System.out.println("Here are the tasks in your list:");
                    tasks.forEach((task) -> {
                        int itemNumber = tasks.indexOf(task) + 1;
                        String result = String.format("%d: %s", itemNumber, task);
                        System.out.println(result);
                    });
                    break;

                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    exit = true;
                    break;

                case "mark":

                    try {

                        String itemString = input.split(" ")[1];
                        int itemNumber = Integer.parseInt(itemString) - 1;
                        Task task = tasks.get(itemNumber);

                        if (task.isDone()) {
                            System.out.println("This task is already marked as done!");
                        } else {
                            task.mark();

                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(task);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid item to mark!");
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task not found!");
                    }
                    break;

                case "unmark":

                    try {

                        String itemString = input.split(" ")[1];
                        int itemNumber = Integer.parseInt(itemString) - 1;
                        Task task = tasks.get(itemNumber);

                        if (!task.isDone()) {
                            System.out.println("This task is already marked as not done yet!");
                        } else {
                            task.unmark();

                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(task);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid item to mark!");
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task not found!");
                    }
                    break;

                case "deadline":

                    try {

                        String taskName = parameters.split(" /by ")[0];
                        String by = parameters.split(" /by ")[1];

                        Task task = new Deadline(taskName, by);
                        tasks.add(task);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid date/time!");
                    }
                    break;

                case "event":

                    try {

                        String taskName = parameters.split(" /at ")[0];
                        String by = parameters.split(" /at ")[1];

                        Task task = new Event(taskName, by);
                        tasks.add(task);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid date/time!");
                    }
                    break;

                case "todo":
                    Task task = new Todo(parameters);
                    tasks.add(task);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
                    break;

                case "delete":

                    try {

                        String itemString = input.split(" ")[1];
                        int itemNumber = Integer.parseInt(itemString) - 1;
                        Task taskToRemove = tasks.get(itemNumber);

                        tasks.remove(taskToRemove);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println(taskToRemove);
                        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
                        

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid item to mark!");
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task not found!");
                    }
                    break;

                default:
                    System.out.println("Invalid command!");
                    break;

            }
        }
    }
}
