package mia;

/**
 * A {@code Command} is an actionable item that can be run by {@code Mia}.
 *
 * @author Richard Dominick
 */
abstract class Command {
    private final Mia context;
    private final boolean isExit;

    private Command(Mia context) {
        this(context, false);
    }

    private Command(Mia context, boolean isExit) {
        this.context = context;
        this.isExit = isExit;
    }

    /**
     * Parses a user input string into a command that is appropriate for the calling context.
     *
     * @param context The context the command is intended to be executed from.
     * @param input The user input string to parse.
     * @return A {@code Command} for the context to call.
     * @throws IllegalArgumentException When a parsing error occurs.
     */
    public static Command from(Mia context, String input) throws IllegalArgumentException {
        if (input.equals("bye")) {
            return new ExitCommand(context);
        } else if (input.equals("list")) {
            return new ListCommand(context);
        } else if (input.startsWith("todo ")) {
            final String[] args = Command.getArguments(input);
            return new CreateTodoTaskCommand(context, args);
        } else if (input.startsWith("deadline ")) {
            final String[] args = Command.getArguments(input, "/by");
            return new CreateDeadlineTaskCommand(context, args);
        } else if (input.startsWith("event ")) {
            final String[] args = Command.getArguments(input, "/at");
            return new CreateEventTaskCommand(context, args);
        } else if (input.startsWith("delete ")) {
            final String[] args = Command.getArguments(input);
            return new DeleteTaskCommand(context, args);
        } else if (input.startsWith("mark ")) {
            final String[] args = Command.getArguments(input);
            return new UpdateTaskCommand(context, true, args);
        } else if (input.startsWith("unmark ")) {
            final String[] args = Command.getArguments(input);
            return new UpdateTaskCommand(context, false, args);
        } else if (input.startsWith("find ")) {
            final String[] args = Command.getArguments(input);
            return new FindTaskCommand(context, args);
        }
        throw new IllegalArgumentException("Sorry boss, I don't know what you are trying to say 😟");
    }

    private static String[] getArguments(String command, String... delimiters) throws ArrayIndexOutOfBoundsException {
        final ArrayIndexOutOfBoundsException e = new ArrayIndexOutOfBoundsException("Invalid number of arguments");
        final String[] args = command.split(" ", 2);
        if (args.length < 2) {
            throw e;
        }
        final String[] parsed = new String[delimiters.length + 1];
        String remaining = args[1].strip();
        for (int i = 0; i < delimiters.length; i++) {
            final String[] splits = remaining.split(delimiters[i], 2);
            if (splits.length < 2) {
                throw e;
            }
            parsed[i] = splits[0].strip();
            remaining = splits[1].strip();
        }
        parsed[delimiters.length] = remaining;
        return parsed;
    }

    /**
     * Determines whether the {@code Command} intends for the calling context to be exited subsequently.
     *
     * @return {@code true} if the {@code Command} intends for an exit, {@code false} otherwise.
     */
    public boolean shouldExitContext() {
        return isExit;
    }

    /**
     * Executes the actionable item that the {@code Command} is associated with.
     */
    public abstract void run();

    private static class ExitCommand extends Command {
        private ExitCommand(Mia context) {
            super(context, true);
        }

        @Override
        public void run() {
            super.context.respond("See you!");
        }
    }

    private static class ListCommand extends Command {
        private ListCommand(Mia context) {
            super(context);
        }

        @Override
        public void run() {
            super.context.respond(super.context.getTasks().toString());
        }
    }

    private static class CreateTodoTaskCommand extends Command {

        private final String[] args;

        private CreateTodoTaskCommand(Mia context, String[] args) {
            super(context);
            assert args.length == 1;
            this.args = args;
        }

        @Override
        public void run() {
            final Task todo = new Todo(args[0]);
            super.context.getTasks().addTask(todo);
            super.context.respond(String.format("Added todo \"%s\" to tasks list!", todo.getTitle()));
        }
    }

    private static class CreateDeadlineTaskCommand extends Command {

        private final String[] args;

        private CreateDeadlineTaskCommand(Mia context, String[] args) {
            super(context);
            assert args.length == 2;
            this.args = args;
        }

        @Override
        public void run() {
            final Task deadline = new Deadline(args[0], args[1]);
            super.context.getTasks().addTask(deadline);
            super.context.respond(String.format(
                "Added \"%s\" (task with deadline) to tasks list!", deadline.getTitle()));
        }
    }

    private static class CreateEventTaskCommand extends Command {

        private final String[] args;

        private CreateEventTaskCommand(Mia context, String[] args) {
            super(context);
            assert args.length == 2;
            this.args = args;
        }

        @Override
        public void run() {
            final Task event = new Event(args[0], args[1]);
            super.context.getTasks().addTask(event);
            super.context.respond(String.format("Added new event \"%s\" to tasks list!", event.getTitle()));
        }
    }

    private static class UpdateTaskCommand extends Command {

        private final boolean isCompleted;
        private final String[] args;

        private UpdateTaskCommand(Mia context, boolean isCompleted, String[] args) {
            super(context);
            assert args.length == 1;
            this.isCompleted = isCompleted;
            this.args = args;
        }

        @Override
        public void run() {
            final int number;
            try {
                number = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                super.context.respond(String.format("You specified an invalid task number %s!", args[0]));
                return;
            }
            if (isCompleted) {
                if (super.context.getTasks().checkTask(number)) {
                    super.context.respond("Task has been marked as done!");
                } else {
                    super.context.respond(String.format(
                            "Task not modified! Either the task is already done,"
                                    + " or you specified an invalid task number %d.",
                            number));
                }
            } else {
                if (super.context.getTasks().uncheckTask(number)) {
                    super.context.respond("Task has been marked as not done!");
                } else {
                    super.context.respond(String.format(
                            "Task not modified! Either the task is still not done,"
                                    + " or you specified an invalid task number %d.",
                            number));
                }
            }
        }
    }

    private static class DeleteTaskCommand extends Command {

        private final String[] args;

        private DeleteTaskCommand(Mia context, String[] args) {
            super(context);
            assert args.length == 1;
            this.args = args;
        }

        @Override
        public void run() {
            final int number;
            try {
                number = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                super.context.respond(String.format("You specified an invalid task number %s!", args[0]));
                return;
            }
            if (super.context.getTasks().deleteTask(number)) {
                super.context.respond("Task has been deleted successfully!");
            } else {
                super.context.respond(String.format(
                        "Something went wrong when deleting task %d!"
                                + " Likely, you specified a task number that is out of range.",
                        number));
            }
        }
    }

    private static class FindTaskCommand extends Command {
        private final String[] args;

        private FindTaskCommand(Mia context, String[] args) {
            super(context);
            assert args.length == 1;
            this.args = args;
        }

        @Override
        public void run() {
            super.context.respond(String.format("Finding tasks that match \"%s\"...", args[0]));
            super.context.respond(super.context.getTasks().search(args[0]));
        }
    }
}
