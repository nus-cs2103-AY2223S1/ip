package duke;

public class AddCommand extends Command {
    Task task;

    private static String getFirstWord(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    private static boolean isTodo(String input) {
        return getFirstWord(input).equals("todo");
    }

    private static boolean isEvent(String input) {

        return getFirstWord(input).equals("event");
    }

    private static boolean isDeadline(String input) {
        return getFirstWord(input).equals("deadline");
    }

    AddCommand(String fullCommand) {
        this.task = Parser.commandToTask(fullCommand);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (task == null) {
            System.out.println("task in addcomand is null");
            return;
        }
        System.out.println("duke.Task added: " + task);
        tasks.add(task);
        task = null;
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof AddCommand;
    }
}
