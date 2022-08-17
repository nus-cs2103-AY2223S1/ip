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
            default:
                return new AddCommand(message, tasks);
        }
    }
    public abstract boolean run();


    private static class EndCommand extends Command {

        public static final String END = "Bye! Hope you had fun!\n";

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

    private static class AddCommand extends Command {
        protected String content;
        protected TaskList tasks;

        public AddCommand(String content, TaskList tasks) {
            this.content = content;
            this.tasks = tasks;
        }

        @Override
        public boolean run() {
            this.tasks.addTask(this.content);
            Reply.printMessage("added: " + this.content);
            return false;
        }
    }
}
