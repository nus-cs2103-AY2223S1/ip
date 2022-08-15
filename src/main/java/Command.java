import java.util.ArrayList;

abstract class Command {
    private static final String WRONG_ARGS_COUNT = "Wrong number of arguments provided!";

    protected final CommandType command;
    protected final String[] args;

    private Command(CommandType command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public static Command of(String input) throws DukeException {
        String[] splits = input.strip().split(" ", 2);
        String command = splits[0].toLowerCase();
        String[] args;

        if (splits.length > 1) {
            args = splits[1].split(" / ");
            for (int i = 0; i < args.length; ++i) {
                args[i] = args[i].strip();
            }
        } else {
            args = new String[0];
        }

        switch (command) {
        case "list":
            return new ListCommand(args);
        case "check":
            return new UpdateStatusCommand(args, true);
        case "uncheck":
            return new UpdateStatusCommand(args, false);
        case "todo":
            return new AddTaskCommand(CommandType.TODO, args);
        case "deadline":
            return new AddTaskCommand(CommandType.DEADLINE, args);
        case "event":
            return new AddTaskCommand(CommandType.EVENT, args);
        case "bye":
            return new ByeCommand(args);
        case "":
            return new EmptyCommand(args);
        default:
            return new BadCommand(args);
        }
    }

    public void execute(ArrayList<Task> tasks) throws DukeException {
        if (!this.command.isCompatible(args)) {
            throw new DukeException(WRONG_ARGS_COUNT);
        }
        this.runSpecialTask(tasks);
    }

    public abstract void runSpecialTask(ArrayList<Task> tasks) throws DukeException;

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

    private static class UpdateStatusCommand extends Command {
        private final String WRONG_ARGUMENT = "This command expects a number argument!";
        private final String INDEX_OUT_OF_BOUND = "This command expects an index between 1 and number of tasks.";

        private final boolean isDone;

        public UpdateStatusCommand(String[] args, boolean isDone) {
            super(CommandType.UPDATE_STATUS, args);
            this.isDone = isDone;
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) throws DukeException {
            int index;
            try {
                index = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(WRONG_ARGUMENT);
            }
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException(INDEX_OUT_OF_BOUND);
            }
            Task task = tasks.get(index);
            task.isDone = this.isDone;
            IOHelper.print(String.format("I've %s this task\n\t", this.isDone ? "checked" : "unchecked") + task);
        }
    }

    private static class AddTaskCommand extends Command {
        public AddTaskCommand(CommandType command, String[] args) {
            super(command, args);
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) {
            Task task = Task.of(this.command, this.args);
            tasks.add(task);
            IOHelper.print("I've added the following task:\n\t" + task);
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

    private static class BadCommand extends Command {
        private static final String BAD_COMMAND = "The command was not understood.";

        public BadCommand(String[] args) {
            super(CommandType.BAD, args);
        }

        @Override
        public void execute(ArrayList<Task> tasks) throws DukeException {
            // bypass compatibility check
            this.runSpecialTask(tasks);
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) throws DukeException {
            throw new DukeException(BAD_COMMAND);
        }
    }

    private static class EmptyCommand extends Command {
        public EmptyCommand(String[] args) {
            super(CommandType.EMPTY, args);
        }

        @Override
        public void runSpecialTask(ArrayList<Task> tasks) {
            // does nothing
        }
    }
}
