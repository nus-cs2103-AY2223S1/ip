public abstract class Command {

    public static Command of(String message, TaskList tasks) throws DukeException {
        String[] splitMessage = message.split("\\s+", 2);
        CommandWord commandWord = CommandWord.get(splitMessage[0]);
        switch (commandWord) {
            case BYE:
                return new EndCommand();
            case LIST:
                return new ListCommand(tasks);
            case MARK:
                if (splitMessage.length < 2) {
                    throw new DukeException("You forgot to tell me the task number!");
                }
                return new MarkCommand(splitMessage[1], tasks);
            case UNMARK:
                if (splitMessage.length < 2) {
                    throw new DukeException("You forgot to tell me the task number!");
                }
                return new UnmarkCommand(splitMessage[1], tasks);
            case TODO:
                if (splitMessage.length < 2) {
                    throw new DukeException("You forgot to add the description!");
                }
                return new ToDoCommand(splitMessage[1], tasks);
            case DEADLINE:
                if (splitMessage.length < 2) {
                    throw new DukeException("You forgot to add the description!");
                }
                return new DeadlineCommand(splitMessage[1], tasks);
            case EVENT:
                if (splitMessage.length < 2) {
                    throw new DukeException("You forgot to add the description!");
                }
                return new EventCommand(splitMessage[1], tasks);
            case DELETE:
                if (splitMessage.length < 2) {
                    throw new DukeException("You forgot to tell me the task number!");
                }
                return new DeleteCommand(splitMessage[1], tasks);
            default:
                throw new DukeException("Sorry, there is no such command!");
        }
    }
    public abstract boolean run() throws DukeException;


    private static class EndCommand extends Command {

        public static final String END = "Bye! Hope you had fun!";

        public EndCommand() {
        }

        @Override
        public boolean run() throws DukeException {
            Reply.printMessage(END);
            return true;
        }
    }

    private static class ListCommand extends Command {

        protected TaskList tasks;
        public static final String LIST = "Here are the tasks in your list:\n";


        public ListCommand(TaskList tasks) {
            this.tasks = tasks;
        }

        @Override
        public boolean run() throws DukeException {
            Reply.printMessage(LIST + this.tasks.toString());
            return false;
        }

    }

    private static class MarkCommand extends Command {
        protected String content;
        protected TaskList tasks;

        public MarkCommand(String content, TaskList tasks) {
            this.content = content;
            this.tasks = tasks;
        }

        @Override
        public boolean run() throws DukeException {
            try {
                Reply.printMessage(this.tasks.markTask(Integer.parseInt(this.content) - 1));
            } catch (NumberFormatException e) {
                throw new DukeException("Task number need to be an integer!");
            }
            return false;
        }
    }

    private static class UnmarkCommand extends Command {
        protected String content;
        protected TaskList tasks;


        public UnmarkCommand(String content, TaskList tasks) {
            this.content = content;
            this.tasks = tasks;
        }

        @Override
        public boolean run() throws DukeException {
            try {
                Reply.printMessage(this.tasks.unmarkTask(Integer.parseInt(content) - 1));
            } catch (NumberFormatException e) {
                throw new DukeException("Task number need to be an integer!");
            }
            return false;
        }
    }

    private static class ToDoCommand extends Command {
        protected String content;
        protected TaskList tasks;

        public ToDoCommand(String content, TaskList tasks) {
            this.content = content;
            this.tasks = tasks;
        }

        @Override
        public boolean run() throws DukeException {
            Reply.printMessage(this.tasks.addTask(new ToDo(this.content)));
            return false;
        }
    }

    private static class DeadlineCommand extends Command {
        protected String content;
        protected TaskList tasks;

        public DeadlineCommand(String content, TaskList tasks) {
            this.content = content;
            this.tasks = tasks;
        }

        @Override
        public boolean run() throws DukeException {
            String[] splitMessage = this.content.split(" /by ", 2);
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to add the deadline!");
            }
            Reply.printMessage(this.tasks.addTask(new Deadline(splitMessage[0], splitMessage[1])));
            return false;
        }
    }

    private static class EventCommand extends Command {
        protected String content;
        protected TaskList tasks;

        public EventCommand(String content, TaskList tasks) {
            this.content = content;
            this.tasks = tasks;
        }

        @Override
        public boolean run() throws DukeException {
            String[] splitMessage = this.content.split(" /at ", 2);
            if (splitMessage.length < 2) {
                throw new DukeException("You forgot to add the time!");
            }
            Reply.printMessage(this.tasks.addTask(new Event(splitMessage[0], splitMessage[1])));
            return false;
        }
    }

    private static class DeleteCommand extends Command {
        protected String content;
        protected TaskList tasks;

        public DeleteCommand(String content, TaskList tasks) {
            this.content = content;
            this.tasks = tasks;
        }

        @Override
        public boolean run() throws DukeException {
            try {
                Reply.printMessage(this.tasks.deleteTask(Integer.parseInt(this.content) - 1));
            } catch (NumberFormatException e) {
                throw new DukeException("Task number need to be an integer!");
            }
            return false;
        }
    }
}
