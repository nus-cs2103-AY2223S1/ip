import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        System.out.println("Hello! I'm " + name + "\nHow can I help you?");

        Task[] tasks = new Task[100];
        int currIndex = 0;

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! See you again :)");
                return;
            }
            if (command.equals("list")) {
                for (int i = 0; i < currIndex; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
                continue;
            }
            if (command.contains("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[index].unmark();
                System.out.println("Okay, this task is now unchecked:\n" + tasks[index]);
            }
            else if (command.contains("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[index].mark();
                System.out.println("Great! This task is completed:\n" + tasks[index]);
            }
            else {
                try {
                    if (command.contains("todo")) {
                        String taskName = command.length() > 5
                                ? command.split("todo ")[1]
                                : "";
                        if (taskName.isBlank()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks[currIndex++] = new Todo(taskName);
                        System.out.println("Got it. I've added this task:\n" + tasks[currIndex - 1]);
                    }
                    else if (command.contains("deadline")) {
                        String[] res = command.split("deadline ")[1].split("\\\\by ");
                        tasks[currIndex++] = new Deadline(res[0], res[1]);
                        System.out.println("Got it. I've added this task:\n" + tasks[currIndex - 1]);
                    }
                    else if (command.contains("event")) {
                        String[] res = command.split("event ")[1].split("\\\\at ");
                        tasks[currIndex++] = new Event(res[0], res[1]);
                        System.out.println("Got it. I've added this task:\n" + tasks[currIndex - 1]);
                    }
                    else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    continue;
                }
            }
        }
    }
}
