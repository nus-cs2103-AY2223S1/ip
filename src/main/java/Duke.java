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

        Task[] tasks = new Task[100];
        int index = 0;

        while (true) {
            String input = scanner.nextLine();

            System.out.println(horizontalLine);

            if (input.equals("bye")) {
                System.out.println(indentation + "Bye, hope to see you soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.printf(indentation + "%d: %s\n", i + 1, tasks[i]);
                }
            } else if (input.startsWith("mark")) {
                String indexInput = input.substring(5);
                int i = Integer.parseInt(indexInput);

                // subtract 1 to account for 0-index data structure
                Task task = tasks[i - 1];
                task.markAsDone();
                System.out.printf(indentation + "Marked task %d as done!\n", i);
                System.out.println(indentation + minorIndentation + task);
            } else if (input.startsWith("unmark")) {
                String indexInput = input.substring(7);
                int i = Integer.parseInt(indexInput);

                // subtract 1 to account for 0-index data structure
                Task task = tasks[i - 1];
                task.markAsUndone();
                System.out.printf(indentation + "Marked task %d as not done!\n", i);
                System.out.println(indentation + minorIndentation + task);
            } else if (input.startsWith("todo")) {
                Task task = Todo.create(input);
                tasks[index++] = task;
                System.out.println(indentation + "Added this todo!");
                System.out.println(indentation + minorIndentation + task);
                System.out.printf(indentation + "Now you have %d tasks.\n", index);
            } else if (input.startsWith("deadline")) {
                Task task = Deadline.create(input);
                tasks[index++] = task;
                System.out.println(indentation + "Added this deadline!");
                System.out.println(indentation + minorIndentation + task);
                System.out.printf(indentation + "Now you have %d tasks.\n", index);
            } else if (input.startsWith("event")) {
                Task task = Event.create(input);
                tasks[index++] = task;
                System.out.println(indentation + "Added this event!");
                System.out.println(indentation + minorIndentation + task);
                System.out.printf(indentation + "Now you have %d tasks.\n", index);
            }

            System.out.println(horizontalLine);
        }
    }
}
