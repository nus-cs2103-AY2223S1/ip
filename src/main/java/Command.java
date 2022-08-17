public abstract class Command {

    public static Command of(String message, TaskList tasks) {
        String[] splitMessage = message.split("\\s+", 2);
        switch (splitMessage[0]) {
            case "bye":
                return new EndCommand();
            case "list":
                return new ListCommand(tasks);
            case "mark":
                return new MarkCommand(splitMessage[1], tasks);
            case "unmark":
                return new UnmarkCommand(splitMessage[1], tasks);
            case "todo":
                return new ToDoCommand(splitMessage[1], tasks);
            case "deadline":
                return new DeadlineCommand(splitMessage[1], tasks);
            case "event":
                return new EventCommand(splitMessage[1], tasks);
            default:
                return new UnknownCommand();
        }
    }
    public abstract boolean run();


    private static class EndCommand extends Command {

        public static final String END = "Bye! Hope you had fun!";

        public EndCommand() {
        }

        @Override
        public boolean run() {
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
        public boolean run() {
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
        public boolean run() {
            Reply.printMessage(this.tasks.markTask(Integer.parseInt(content) - 1));
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
        public boolean run() {
            Reply.printMessage(this.tasks.unmarkTask(Integer.parseInt(content) - 1));
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
        public boolean run() {
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
        public boolean run() {
            String[] splitMessage = this.content.split(" /by ", 2);
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
        public boolean run() {
            String[] splitMessage = this.content.split(" /at ", 2);
            Reply.printMessage(this.tasks.addTask(new Event(splitMessage[0], splitMessage[1])));
            return false;
        }
    }

    private static class UnknownCommand extends Command {

        public static final String UNKNOWN = "Sorry, this is an unknown command!";

        public UnknownCommand() {
        }

        @Override
        public boolean run() {
            Reply.printMessage(UNKNOWN);
            return false;
        }
    }
}
