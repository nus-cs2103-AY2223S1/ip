import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Piggy");
        System.out.println("What can I oink for you?");

        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%d.%s\n", i + 1, list.get(i));
                }
            } else if (input.matches("^mark \\d+$")) {
                Task item = list.get(Integer.parseInt(input.substring(5)) - 1);
                item.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + item);
            } else if (input.matches("^unmark \\d+$")) {
                Task item = list.get(Integer.parseInt(input.substring(7)) - 1);
                item.markNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + item);
            } else if (input.matches("^delete \\d+$")) {
                Task item = list.remove(Integer.parseInt(input.substring(7)) - 1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println("  " + item);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (input.equals("bye")) {
                break;
            } else {
                Task task;
                try {
                    if (input.startsWith("todo")) {
                        if (input.length() < 6) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        task = new Todo(input.substring(5));
                    } else if (input.startsWith("deadline")) {
                        if (input.length() < 10) {
                            throw new DukeException(
                                    "The description of a deadline cannot be empty.");
                        } else if (!input.contains("/by")) {
                            throw new DukeException("A deadline must contain a /by");
                        }
                        String[] split = input.substring(9).split(" /by ");
                        task = new Deadline(split[0], split[1]);
                    } else if (input.startsWith("event")) {
                        if (input.length() < 7) {
                            throw new DukeException("The description of an event cannot be empty.");
                        } else if (!input.contains("/at")) {
                            throw new DukeException("An event must contain an /at");
                        }
                        String[] split = input.substring(6).split(" /at ");
                        task = new Event(split[0], split[1]);
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } catch (DukeException err) {
                    System.out.println(err);
                }

            }
        }

        System.out.println("Bye. Hope to oink you again soon!");
        sc.close();
    }
}
