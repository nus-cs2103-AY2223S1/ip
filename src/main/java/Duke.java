import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * The main method of the chatbot, as well as its startup and teardown.
 */
public class Duke {
    /** List of commands */
    private static ArrayList<CommandMatcher> commands;
    /** List of strings to remember */
    private static ArrayList<Task> list;

    private static Optional<Task> getTask(String index) {
        try {
            int idx = Integer.parseInt(index);
            Task task = list.get(idx - 1);
            return Optional.of(task);
        } catch (NumberFormatException ex) {
            Ui.messagePrint("Sorry, I didn't understand " + index + ", please give me a number.");
            return Optional.empty();
        } catch (IndexOutOfBoundsException ex) {
            Ui.messagePrint("Sorry, the number " + index + ", wasn't in the range.");
            return Optional.empty();
        }
    }

    private static void initializeTaskList() {
        list = TasksFileState.getTasks();
    }

    private static void finalizeTaskList() {
        TasksFileState.saveTasks(list);
    }

    private static void initializeCommands() {
        commands = new ArrayList<>();

        commands.add(new CommandMatcher((str) -> str.equals("list"), (str) -> {
            String[] output = new String[list.size() + 1];
            output[0] = "Here, your tasks:";
            for (int i = 0; i < list.size(); i++) {
                output[i + 1] = (i + 1) + "." + list.get(i).toString();
            }
            Ui.messagePrint(output);
        }));

        commands.add(new PrefixCommandMatcher("mark", (str, map) -> {
            getTask(str).ifPresent((task) -> {
                task.markAsDone();
                String[] output = {
                    "Marked your task as done:",
                    task.toString()
                };
                Ui.messagePrint(output);
            });
        }));

        commands.add(new PrefixCommandMatcher("unmark", (str, map) -> {
            getTask(str).ifPresent((task) -> {
                task.markAsNotDone();
                Ui.messagePrint("Aw... it's not done yet:",
                        task.toString());
            });
        }));

        commands.add(new PrefixCommandMatcher("deadline", (str, map) -> {
            Task task = new Deadline(str, map.getOrDefault("by", "[unknown]"));
            list.add(task);
            Ui.messagePrint("Good luck with the deadline, here's the task:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("todo", (str, map) -> {
            Task task = new ToDo(str);
            list.add(task);
            Ui.messagePrint("I've recorded this thing you need to do:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("event", (str, map) -> {
            Task task = new Event(str, map.getOrDefault("at", "[unknown]"));
            list.add(task);
            Ui.messagePrint("That's going to happen at some time later:",
                    task.toString());
        }));

        commands.add(new PrefixCommandMatcher("delete", (str, map) -> {
            getTask(str).ifPresent((task) -> {
                list.remove(task);
                Ui.messagePrint("It seems you didn't need this task anymore, so I removed it:",
                        task.toString(),
                        String.format("You have %d tasks left.", list.size()));
            });
        }));

        // default command matcher - add to list
        commands.add(new CommandMatcher((str) -> true, (str) -> {
            Ui.messagePrint("(>.<') I'm sorry, I don't really know what that means.");
        }));
    }

    private static void handleCommand(String command) {
        for (CommandMatcher matcher : commands) {
            if (matcher.run(command)) {
                break;
            }
        }
    }

    /**
     * Runs the chatbot execution.
     * @param args Command line args which are not used.
     */
    public static void main(String[] args) {
        Ui.greet();
        initializeTaskList();
        initializeCommands();
        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                keepRunning = false;
            } else {
                handleCommand(command);
            }
        }
        finalizeTaskList();
        Ui.leave();
    }
}
