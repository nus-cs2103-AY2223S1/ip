import java.util.ArrayList;
public abstract class Command {
    protected final String[] args;
    protected final int argCount;
    private

    Command(String[] args, int argCount) {
        this.args = args;
        this.argCount = argCount;
    }

    public abstract void execute(TaskList taskList) throws DukeException;
    public abstract boolean isExit();
    protected void verifyCorrectNumberOfArguments() throws DukeException {
        if (!(this.argCount == args.length)) {
            throw new DukeException("Wrong number of arguments provided for the command!");
        }
    }

    public static class ByeCommand extends Command {
        private static final int argCount = 0;

        ByeCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            ui.printMessages(new String[]{"Bye. Hope to see you again soon!"});
        }

        @Override
        public boolean isExit() {
            return true;
        }
    }

    public static class ListCommand extends Command {
        private static final int argCount = 0;

        ListCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            verifyCorrectNumberOfArguments();
            String[] tasks = taskList.getAllTasks().toArray(new String[0]);
            if (tasks.length == 0) {
                ui.printMessages(new String[]{"No tasks"});
            } else {
                ui.printMessages(tasks);
            }
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class MarkCommand extends Command {
        private static final int argCount = 1;

        MarkCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            verifyCorrectNumberOfArguments();
            int taskIndex = Integer.parseInt(args[0]) - 1;
            Task markedTask = taskList.markTask(taskIndex);
            ui.printMessages(new String[]{"Nice! I've marked this task as done:", markedTask.toString()});
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class UnmarkCommand extends Command {
        private static final int argCount = 1;

        UnmarkCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            verifyCorrectNumberOfArguments();
            int taskIndex = Integer.parseInt(args[0]) - 1;
            Task unmarkedTask = taskList.unmarkTask(taskIndex);
            ui.printMessages(new String[]{"Ok, I've marked this task as not done yet:", unmarkedTask.toString()});
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class ToDoCommand extends Command {
        private static final int argCount = 1;

        ToDoCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            verifyCorrectNumberOfArguments();
            Task taskAdded = taskList.addTask(new ToDo(args[0]));
            ui.printAddedTask(taskAdded, taskList);
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class EventCommand extends Command {
        private static final int argCount = 2;

        EventCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            verifyCorrectNumberOfArguments();
            Task taskAdded = taskList.addTask((new Event(args[0], args[1])));
            ui.printAddedTask(taskAdded, taskList);
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class DeadlineCommand extends Command {
        private static final int argCount = 2;

        DeadlineCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            verifyCorrectNumberOfArguments();
            Task taskAdded = taskList.addTask((new Deadline(args[0], args[1])));
            ui.printAddedTask(taskAdded, taskList);
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class EmptyCommand extends Command {
        private static final int argCount = 0;

        EmptyCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            ArrayList<String> toPrint = new ArrayList<>();
            toPrint.add("No command entered!");
            String[] helpGuide = HelpCommand.getHelpGuide();
            for (String line: helpGuide) {
                toPrint.add(line);
            }
            ui.printMessages(toPrint.toArray(new String[0]));
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class UnknownCommand extends Command {
        private static final int argCount = 0;

        UnknownCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            ArrayList<String> toPrint = new ArrayList<>();
            toPrint.add("Unknown command provided!");
            String[] helpGuide = HelpCommand.getHelpGuide();
            for (String line: helpGuide) {
                toPrint.add(line);
            }
            ui.printMessages(toPrint.toArray(new String[0]));
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class HelpCommand extends Command {
        private static final int argCount = 0;

        HelpCommand(String[] args) {
            super(args, argCount);
        }

        public static String[] getHelpGuide() {
            ArrayList<String> helpGuide = new ArrayList<>();
            helpGuide.add("Here are the list of commands:");
            helpGuide.add("0. help");
            helpGuide.add("1. bye");
            helpGuide.add("2. list");
            helpGuide.add("3. mark <task index>");
            helpGuide.add("4. unmark <task index>");
            helpGuide.add("5. todo <task description>");
            helpGuide.add("6. event <event description> /at <time>");
            helpGuide.add("7. deadline <task description> /by <time>");
            return helpGuide.toArray(new String[0]);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            verifyCorrectNumberOfArguments();
            ui.printMessages(HelpCommand.getHelpGuide());
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }
}
