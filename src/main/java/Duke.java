import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET_MESSAGE = "Hello! I am Duke. How can I help you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you soon!";
    private static final String WRONG_COMMAND = "Sorry! The command was not understood.";
    private static final String INDEX_OUT_OF_BOUND = "Sorry! The task index is out of bound.";

    private final ArrayList<Task> tasks;
    private final Scanner scanner;

    public Duke() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    private String[] getUserCommands() {
        String[] args = IOHelper.read(scanner).strip().split(" ");
        StringJoiner description = new StringJoiner(" ");
        StringJoiner remaining = new StringJoiner(" ");
        boolean hasFoundCut = false;
        for (int i = 1; i < args.length; ++i) {
            if (args[i].equals("/")) {
                hasFoundCut = true;
                continue;
            }
            if (!hasFoundCut) {
                description.add(args[i]);
            } else {
                remaining.add(args[i]);
            }
        }
        if (remaining.length() == 0) {
            return new String[]{args[0], description.toString()};
        } else {
            return new String[]{args[0], description.toString(), remaining.toString()};
        }
    }

    private void greet() {
        System.out.println(LOGO);
        IOHelper.print(GREET_MESSAGE);
    }

    private void endInteraction() {
        IOHelper.print(BYE_MESSAGE);
    }

    private void addTask(String[] commands) {
        if (commands.length != 2) {
            IOHelper.print(WRONG_COMMAND);
            return;
        }
        Task task = new Task(commands[1]);
        tasks.add(task);
        IOHelper.print("Added task:\n\t" + task);
    }

    private void setTaskState(String[] commands, boolean isDone) {
        if (commands.length != 2) {
            IOHelper.print(WRONG_COMMAND);
            return;
        }
        int index;
        try {

            index = Integer.parseInt(commands[1]) - 1;
        } catch (NumberFormatException e) {
            IOHelper.print(WRONG_COMMAND);
            return;
        }
        if (index < 0 || index >= tasks.size()) {
            IOHelper.print(INDEX_OUT_OF_BOUND);
            return;
        }
        tasks.get(index).setDone(isDone);
        IOHelper.print(String.format("I have %s this task:\n\t%s",
                isDone ? "checked" : "unchecked", tasks.get(index)));
    }

    private void listTasks(String[] commands) {
        if (commands.length != 1) {
            IOHelper.print(WRONG_COMMAND);
            return;
        }
        StringBuilder response = new StringBuilder();
        response.append("List of tasks:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            response.append(String.format("\t%d. %s", i + 1, tasks.get(i)));
            if (i + 1 < tasks.size()) {
                response.append("\n");
            }
        }
        IOHelper.print(response.toString());
    }

    private void startInteraction() {
        IOHelper.printWithoutPrompt(LOGO);
        while (true) {
            String[] commands = getUserCommands();
            boolean hasEnded = false;

            switch (commands[0].toLowerCase()) {
            case "list":
                listTasks(commands);
                break;
            case "bye":
                endInteraction();
                hasEnded = true;
                break;
            case "todo":
                addTask(commands);
                break;
            case "check":
                setTaskState(commands, true);
                break;
            case "uncheck":
                setTaskState(commands, false);
                break;
            case "":
                break;
            default:
                IOHelper.print(WRONG_COMMAND);
            }
            if (hasEnded) {
                break;
            }
        }
    }

    public void run() {
        greet();
        startInteraction();
    }
}
