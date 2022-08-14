import java.util.ArrayList;

abstract class Command {
    private static final String WRONG_ARGS_COUNT = "Wrong number of arguments provided!";

    protected final CommandType type;
    protected final String[] args;

    public Command(CommandType type, String[] args) {
        this.type = type;
        this.args = args;
    }

    public static Command of(String input) {
        String[] splits = input.strip().split(" ", 2);
        String command = splits[0].toLowerCase();
        String[] args = new String[0];

        if (splits.length > 1) {
            args = splits[1].split("/");
        }

        switch (command) {
        case "todo":
            return new TodoCommand(args);
        case "bye":
            return new ByeCommand(args);
        case "check":
            return new UpdateStatusCommand(args, true);
        case "uncheck":
            return new UpdateStatusCommand(args, false);
        case "list":
            return new ListCommand(args);
        default:
            return new BadCommand(args);
        }
    }

    public void execute(ArrayList<Task> tasks) {
        if (!this.type.isCompatible(args)) {
            IOHelper.print(WRONG_ARGS_COUNT);
            return;
        }
        this.runSpecialTask(tasks);
    }

    public abstract void runSpecialTask(ArrayList<Task> tasks);

    private static class TodoCommand extends Command {
        public TodoCommand(String[] args) {
            super(CommandType.TODO, args);
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) {
            Task task = new Task(args[0]);
            tasks.add(task);
            IOHelper.print("I've added the following task:\n\t" + task);
        }
    }

    private static class UpdateStatusCommand extends Command {
        private final String WRONG_ARGUMENT = "This command expects a number argument!";
        private final String INDEX_OUT_OF_BOUND = "This command expects an index between 1 and number of tasks.";

        private final boolean isDone;

        public UpdateStatusCommand(String[] args, boolean isDone) {
            super(CommandType.UPDATE_STATUS, args);
            this.isDone = isDone;
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) {
            int index;
            try {
                index = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                IOHelper.print(WRONG_ARGUMENT);
                return;
            }
            if (index < 0 || index > tasks.size()) {
                IOHelper.print(INDEX_OUT_OF_BOUND);
                return;
            }
            Task task = tasks.get(index);
            task.isDone = this.isDone;
            IOHelper.print(String.format("I've %s this task\n\t", this.isDone ? "checked" : "unchecked") + task);
        }
    }

    private static class ListCommand extends Command {
        public ListCommand(String[] args) {
            super(CommandType.LIST, args);
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) {
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
    }

    private static class BadCommand extends Command {
        private static final String BAD_COMMAND = "The command was not understood.";

        public BadCommand(String[] args) {
            super(CommandType.BAD, args);
        }

        @Override
        public void execute(ArrayList<Task> tasks) {
            // bypass compatibility check
            this.runSpecialTask(tasks);
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) {
            IOHelper.print(BAD_COMMAND);
        }
    }

    private static class ByeCommand extends Command {
        private final String BYE_MESSAGE = "Bye! Hope to see you soon!";

        public ByeCommand(String[] args) {
            super(CommandType.BYE, args);
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) {
            IOHelper.print(BYE_MESSAGE);
        }
    }
}
