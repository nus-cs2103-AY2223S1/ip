import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        System.out.println("Hello! I'm " + name + "\nHow can I help you?");

        ArrayList<Task> tasksList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! See you again :)");
                return;
            } else if (command.equals("list")) {
                for (int i = 0; i < tasksList.size(); i++) {
                    System.out.println(i + 1 + ". " + tasksList.get(i));
                }
                continue;
            } else if (command.contains("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasksList.get(index).unmark();
                System.out.println("Okay, this task is now unchecked:\n" + tasksList.get(index));
            } else if (command.contains("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasksList.get(index).mark();
                System.out.println("Great! This task is completed:\n" + tasksList.get(index));
            } else if (command.contains("delete")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                Task temp = tasksList.get(index);
                tasksList.remove(index);
                System.out.println("Noted. I've removed this task:\n" + temp);
            } else {
                try {
                    if (command.contains("todo")) {
                        String taskName = command.length() > 5
                                ? command.split("todo ")[1]
                                : "";
                        if (taskName.isBlank()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasksList.add(new Todo(taskName));
                        System.out.println("Got it. I've added this task:\n" + tasksList.get(tasksList.size() - 1));
                    }
                    else if (command.contains("deadline")) {
                        String[] res = command.split("deadline ")[1].split("\\\\by ");
                        tasksList.add(new Deadline(res[0], res[1]));
                        System.out.println("Got it. I've added this task:\n" + tasksList.get(tasksList.size() - 1));
                    }
                    else if (command.contains("event")) {
                        String[] res = command.split("event ")[1].split("\\\\at ");
                        tasksList.add(new Event(res[0], res[1]));
                        System.out.println("Got it. I've added this task:\n" + tasksList.get(tasksList.size() - 1));
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
