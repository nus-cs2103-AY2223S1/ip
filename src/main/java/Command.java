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
        case "delete":
            return new DeleteTaskCommand(args);
        case "bye":
            return new ByeCommand(args);
        case "":
            return new EmptyCommand(args);
        default:
            return new BadCommand(args);
        }
    }

    public void execute(TaskList tasks) throws DukeException {
        if (!this.command.isCompatible(args)) {
            throw new DukeException(WRONG_ARGS_COUNT);
        }
        this.runSpecialTask(tasks);
    }

    public abstract void runSpecialTask(TaskList tasks) throws DukeException;

    private static class ListCommand extends Command {
        public ListCommand(String[] args) {
            super(CommandType.LIST, args);
        }

        @Override
        public void runSpecialTask(TaskList tasks) {
            tasks.print();
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
        public void runSpecialTask(TaskList tasks) throws DukeException {
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
            task.setDone(isDone);
            tasks.save();
            IOHelper.print(String.format("I've %s this task\n\t", this.isDone ? "checked" : "unchecked") + task);
        }
    }

    private static class AddTaskCommand extends Command {
        public AddTaskCommand(CommandType command, String[] args) {
            super(command, args);
        }

        @Override
        public void runSpecialTask(TaskList tasks) {
            Task task = Task.of(this.command, this.args);
            tasks.add(task);
            tasks.save();
            IOHelper.print("I've added the following task:\n\t" + task);
        }
    }

    // TODO: Merge this with UpdateStatusCommand to form CommandWithIndex
    private static class DeleteTaskCommand extends Command {
        private final String WRONG_ARGUMENT = "This command expects a number argument!";
        private final String INDEX_OUT_OF_BOUND = "This command expects an index between 1 and number of tasks.";

        public DeleteTaskCommand(String[] args) {
            super(CommandType.DELETE, args);
        }

        @Override
        public void runSpecialTask(TaskList tasks) throws DukeException {
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
            tasks.remove(index);
            tasks.save();
            IOHelper.print("I've removed this task\n\t" + task);
        }
    }

    private static class ByeCommand extends Command {
        private final String BYE_MESSAGE = "Bye! Hope to see you soon!";

        public ByeCommand(String[] args) {
            super(CommandType.BYE, args);
        }

        @Override
        public void runSpecialTask(TaskList tasks) {
            IOHelper.print(BYE_MESSAGE);
        }
    }

    private static class BadCommand extends Command {
        private static final String BAD_COMMAND = "The command was not understood.";

        public BadCommand(String[] args) {
            super(CommandType.BAD, args);
        }

        @Override
        public void execute(TaskList tasks) throws DukeException {
            // bypass compatibility check
            this.runSpecialTask(tasks);
        }

        @Override
        public void runSpecialTask(TaskList tasks) throws DukeException {
            throw new DukeException(BAD_COMMAND);
        }
    }

    private static class EmptyCommand extends Command {
        public EmptyCommand(String[] args) {
            super(CommandType.EMPTY, args);
        }

        @Override
        public void runSpecialTask(TaskList tasks) {
            // does nothing
        }
    }
}
