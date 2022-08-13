import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String minorIndentation = "  ";
    private static final String indentation = "    ";
    private static final String horizontalLine = indentation + "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        String logo = indentation + "____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(indentation + "Hello I'm\n" + logo);
        System.out.println(indentation + "What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNext()) {
            try {
                String input = scanner.nextLine();

                System.out.println(horizontalLine);

                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf(indentation + "%d: %s\n", i + 1, tasks.get(i));
                    }
                } else if (input.startsWith("mark")) {
                    String indexInput = input.substring(5);
                    int i = Integer.parseInt(indexInput);
                    if (i <= 0 || i > tasks.size()) {
                        throw new DukeException("Invalid index!");
                    }
                    // subtract 1 to account for 0-index data structure
                    Task task = tasks.get(i - 1);
                    task.markAsDone();

                    System.out.printf(indentation + "Marked task %d as done!\n", i);
                    System.out.println(indentation + minorIndentation + task);
                } else if (input.startsWith("unmark")) {
                    String indexInput = input.substring(7);
                    int i = Integer.parseInt(indexInput);
                    if (i <= 0 || i > tasks.size()) {
                        throw new DukeException("Invalid index!");
                    }
                    // subtract 1 to account for 0-index data structure
                    Task task = tasks.get(i - 1);
                    task.markAsUndone();

                    System.out.printf(indentation + "Marked task %d as not done!\n", i);
                    System.out.println(indentation + minorIndentation + task);
                } else if (input.startsWith("delete")) {
                    String indexInput = input.substring(7);
                    int i = Integer.parseInt(indexInput);
                    if (i <= 0 || i > tasks.size()) {
                        throw new DukeException("Invalid index!");
                    }
                    // subtract 1 to account for 0-index data structure
                    Task task = tasks.get(i - 1);
                    tasks.remove(i - 1);
                    int numberOfTasks = tasks.size();

                    System.out.println(indentation + "Removing this todo!");
                    System.out.println(indentation + minorIndentation + task);
                    System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
                } else if (input.startsWith("todo")) {
                    Task task = Todo.create(input);
                    tasks.add(task);
                    int numberOfTasks = tasks.size();

                    System.out.println(indentation + "Added this todo!");
                    System.out.println(indentation + minorIndentation + task);
                    System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
                } else if (input.startsWith("deadline")) {
                    Task task = Deadline.create(input);
                    tasks.add(task);
                    int numberOfTasks = tasks.size();

                    System.out.println(indentation + "Added this deadline!");
                    System.out.println(indentation + minorIndentation + task);
                    System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
                } else if (input.startsWith("event")) {
                    Task task = Event.create(input);
                    tasks.add(task);
                    int numberOfTasks = tasks.size();

                    System.out.println(indentation + "Added this event!");
                    System.out.println(indentation + minorIndentation + task);
                    System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
                } else {
                    throw new DukeException("Unknown command!");
                }

                System.out.println(horizontalLine);
            } catch (DukeException e) {
                System.out.println(indentation + e.getMessage());
                System.out.println(horizontalLine);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
                System.out.println(horizontalLine);
            }
        }

        System.out.println(indentation + "Bye, hope to see you soon!");
        System.out.println(horizontalLine);
    }
}
