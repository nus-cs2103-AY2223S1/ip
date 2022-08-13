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

        scanLoop:
        while (scanner.hasNext()) {
            try {
                String input = scanner.nextLine();

                String[] split = input.split(" ", 2);
                String command = split[0];

                System.out.println(horizontalLine);

                // Handle the various commands.
                switch (command) {
                    case "bye":
                        // Stops the application, by breaking out of the scan loop.
                        break scanLoop;
                    case "list":
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf(indentation + "%d: %s\n", i + 1, tasks.get(i));
                        }
                        break;
                    case "mark": {
                        if (split.length != 2) {
                            throw DukeException.noIndex;
                        }
                        String indexInput = split[1];
                        int i = Integer.parseInt(indexInput);
                        if (i <= 0 || i > tasks.size()) {
                            throw DukeException.invalidIndex;
                        }
                        // Subtract 1 to account for 0-index data structure.
                        Task task = tasks.get(i - 1);
                        task.markAsDone();

                        System.out.printf(indentation + "Marked task %d as done!\n", i);
                        System.out.println(indentation + minorIndentation + task);
                        break;
                    }
                    case "unmark": {
                        if (split.length != 2) {
                            throw DukeException.noIndex;
                        }
                        String indexInput = split[1];
                        int i = Integer.parseInt(indexInput);
                        if (i <= 0 || i > tasks.size()) {
                            throw DukeException.invalidIndex;
                        }
                        // Subtract 1 to account for 0-index data structure.
                        Task task = tasks.get(i - 1);
                        task.markAsUndone();

                        System.out.printf(indentation + "Marked task %d as not done!\n", i);
                        System.out.println(indentation + minorIndentation + task);
                        break;
                    }
                    case "delete": {
                        if (split.length != 2) {
                            throw DukeException.noIndex;
                        }
                        String indexInput = split[1];
                        int i = Integer.parseInt(indexInput);
                        if (i <= 0 || i > tasks.size()) {
                            throw DukeException.invalidIndex;
                        }
                        // Subtract 1 to account for 0-index data structure.
                        Task task = tasks.get(i - 1);
                        tasks.remove(i - 1);
                        int numberOfTasks = tasks.size();

                        System.out.println(indentation + "Removing this todo!");
                        System.out.println(indentation + minorIndentation + task);
                        System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
                        break;
                    }
                    case "todo": {
                        if (split.length != 2) {
                            throw Todo.emptyDescription;
                        }
                        String description = split[1];
                        Task task = Todo.create(description);
                        tasks.add(task);
                        int numberOfTasks = tasks.size();

                        System.out.println(indentation + "Added this todo!");
                        System.out.println(indentation + minorIndentation + task);
                        System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
                        break;
                    }
                    case "deadline": {
                        if (split.length != 2) {
                            throw Deadline.emptyDescription;
                        }
                        String descAndDate = split[1];
                        Task task = Deadline.create(descAndDate);
                        tasks.add(task);
                        int numberOfTasks = tasks.size();

                        System.out.println(indentation + "Added this deadline!");
                        System.out.println(indentation + minorIndentation + task);
                        System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
                        break;
                    }
                    case "event": {
                        if (split.length != 2) {
                            throw Event.emptyDescription;
                        }
                        String descAndDate = split[1];
                        Task task = Event.create(descAndDate);
                        tasks.add(task);
                        int numberOfTasks = tasks.size();

                        System.out.println(indentation + "Added this event!");
                        System.out.println(indentation + minorIndentation + task);
                        System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
                        break;
                    }
                    default:
                        throw DukeException.unknownCommand;
                }

                System.out.println(horizontalLine);
            } catch (DukeException e) {
                System.out.println(indentation + e.getMessage());
                System.out.println(horizontalLine);
            } catch (NumberFormatException e) {
                // Handles case where user inputs an invalid number.
                System.out.println(indentation + "Invalid number!");
                System.out.println(horizontalLine);
            }
        }

        System.out.println(indentation + "Bye, hope to see you soon!");
        System.out.println(horizontalLine);
    }
}
