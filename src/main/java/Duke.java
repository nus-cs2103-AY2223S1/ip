import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasksToDo = new ArrayList<>();
        int i = 0;
        System.out.println("Hi I'm Duke, What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] strs = input.split(" ");
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.println((j + 1) + ". " + tasksToDo.get(j).toString());
                }
            } else if (strs.length == 2 && (strs[0].equals("mark") || strs[0].equals("unmark"))) {
                int index = Integer.parseInt(strs[1]) - 1;
                Task task = tasksToDo.get(index);
                if (strs[0].equals("mark")) {
                    task.markTaskAsDone();
                    System.out.println("Nice! I have mark this task as done:\n" + task.toString());
                } else if (strs[0].equals("unmark")) {
                    task.unMarkTaskAsDone();
                    System.out.println("Ok, I have mark this task as not done yet:\n" + task.toString());
                }
            } else if (strs.length == 2 && (strs[0].equals("delete"))) {
                int index = Integer.parseInt(strs[1]) - 1;
                Task task = tasksToDo.get(index);
                tasksToDo.remove(index);
                i--;
                System.out.println("Noted. I've removed this task:\n" + task.toString() +
                        "\nNow you have " + i + " tasks in the list.");
            } else {
                try {
                    String[] details;
                    switch (strs[0]) {
                        case "deadline":
                            details = input.split(" ", 2)[1].split(" /by ");
                            tasksToDo.add(new Deadline(details[0], details[1]));
                            i++;
                            System.out.println("Got it! I've added this task:\n" + tasksToDo.get(i - 1).toString()
                                    + "\nNow you've got " + i + " tasks in the list!");
                            break;
                        case "event":
                            details = input.split(" ", 2)[1].split(" /at ");
                            tasksToDo.add(new Event(details[0], details[1]));
                            i++;
                            System.out.println("Got it! I've added this task:\n" + tasksToDo.get(i - 1).toString()
                                    + "\nNow you've got " + i + " tasks in the list!");
                            break;
                        case "todo":
                            tasksToDo.add(new Todo(input.split(" ", 2)[1]));
                            i++;
                            System.out.println("Got it! I've added this task:\n" + tasksToDo.get(i - 1).toString()
                                    + "\nNow you've got " + i + " tasks in the list!");
                            break;
                        default:
                            System.out.println("OOPS!!! I don't understand what that means!");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of a " + strs[0] + " cannot be empty");
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye! Hope to see you again!");

    }
}
