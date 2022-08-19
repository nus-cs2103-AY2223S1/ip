import java.util.ArrayList;

public abstract class Command {
    protected final String[] args;
    protected final int argCount;

    Command(String[] args, int argCount) {
        this.args = args;
        this.argCount = argCount;
    }

    public abstract void execute(TaskList taskList) throws DukeException;
    public abstract boolean isExit();
    protected boolean isNumberOfArgumentsCorrect() {
        return this.argCount == args.length;
    }

    public static class ByeCommand extends Command {
        private static final int argCount = 0;
        public static final String usage = "bye";

        ByeCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) {
            Ui.printMessages(new String[]{"Bye. Hope to see you again soon!"});
        }

        @Override
        public boolean isExit() {
            return true;
        }
    }

    public static class ListCommand extends Command {
        private static final int argCount = 0;
        public static final String usage = "list";

        ListCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            String[] tasks = taskList.getAllTasks().toArray(new String[0]);
            if (tasks.length == 0) {
                Ui.printMessages(new String[]{"No tasks"});
            } else {
                Ui.printMessages(tasks);
            }
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class MarkCommand extends Command {
        private static final int argCount = 1;
        public static final String usage = "mark <task index>";

        MarkCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            if (!isNumberOfArgumentsCorrect()) {
                throw new DukeException("Wrong number of arguments provided.\nUsage: " + MarkCommand.usage);
            }
            try {
                int taskIndex = Integer.parseInt(args[0]) - 1;
                Task markedTask = taskList.markTask(taskIndex);
                Ui.printMessages(new String[]{"Nice! I've marked this task as done:", markedTask.toString()});
            } catch (NumberFormatException e) {
                throw new DukeException("Argument provided is not a number");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task index out of range");
            }
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class UnmarkCommand extends Command {
        private static final int argCount = 1;
        public static final String usage = "unmark <task index>";

        UnmarkCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            if (!isNumberOfArgumentsCorrect()) {
                throw new DukeException("Wrong number of arguments provided.\nUsage: " + UnmarkCommand.usage);
            }
            try {
                int taskIndex = Integer.parseInt(args[0]) - 1;
                Task unmarkedTask = taskList.unmarkTask(taskIndex);
                Ui.printMessages(new String[]{"Ok, I've marked this task as not done yet:", unmarkedTask.toString()});
            } catch (NumberFormatException e) {
                throw new DukeException("Argument provided is not a number");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task index out of range");
            }
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class ToDoCommand extends Command {
        private static final int argCount = 1;
        public static final String usage = "todo <task description>";

        ToDoCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            if (!isNumberOfArgumentsCorrect()) {
                throw new DukeException("Wrong number of arguments provided.\nUsage: " + ToDoCommand.usage);
            }
            Task taskAdded = taskList.addTask(new ToDo(args[0]));
            Ui.printTaskChange("Got it. I've added this task:", taskAdded, taskList);
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class EventCommand extends Command {
        private static final int argCount = 2;
        public static final String usage = "event <event description> /at <time>";

        EventCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            if (!isNumberOfArgumentsCorrect()) {
                throw new DukeException("Wrong number of arguments provided.\nUsage: " + EventCommand.usage);
            }
            Task taskAdded = taskList.addTask((new Event(args[0], args[1])));
            Ui.printTaskChange("Got it. I've added this event:", taskAdded, taskList);
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class DeadlineCommand extends Command {
        private static final int argCount = 2;
        public static final String usage = "deadline <task description> /by <time>";

        DeadlineCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            if (!isNumberOfArgumentsCorrect()) {
                throw new DukeException("Wrong number of arguments provided.\nUsage: " + DeadlineCommand.usage);
            }
            Task taskAdded = taskList.addTask((new Deadline(args[0], args[1])));
            Ui.printTaskChange("Got it. I've added this task:", taskAdded, taskList);
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class DeleteCommand extends Command {
        private static final int argCount = 1;
        public static final String usage = "delete <task index>";

        DeleteCommand(String[] args) {
            super(args, argCount);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            if (!isNumberOfArgumentsCorrect()) {
                throw new DukeException("Wrong number of arguments provided.\nUsage: " + DeleteCommand.usage);
            }
            try {
                int taskIndex = Integer.parseInt(args[0]) - 1;
                Task deletedTask = taskList.deleteTask(taskIndex);
                Ui.printTaskChange("Noted. I've removed this task:", deletedTask, taskList);
            } catch (NumberFormatException e) {
                throw new DukeException("Argument provided is not a number");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task index out of range");
            }
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
        public void execute(TaskList taskList) {
            ArrayList<String> toPrint = new ArrayList<>();
            toPrint.add("No command entered!");
            String[] helpGuide = HelpCommand.getHelpGuide();
            for (String line: helpGuide) {
                toPrint.add(line);
            }
            Ui.printMessages(toPrint.toArray(new String[0]));
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
        public void execute(TaskList taskList) {
            ArrayList<String> toPrint = new ArrayList<>();
            toPrint.add("Unknown command provided!");
            String[] helpGuide = HelpCommand.getHelpGuide();
            for (String line: helpGuide) {
                toPrint.add(line);
            }
            Ui.printMessages(toPrint.toArray(new String[0]));
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class HelpCommand extends Command {
        private static final int argCount = 0;
        public static final String usage = "help";

        HelpCommand(String[] args) {
            super(args, argCount);
        }

        public static String[] getHelpGuide() {
            ArrayList<String> helpGuide = new ArrayList<>();
            helpGuide.add("Here are the list of commands:");
            helpGuide.add("0. " + HelpCommand.usage);
            helpGuide.add("1. " + ByeCommand.usage);
            helpGuide.add("2. " + ListCommand.usage);
            helpGuide.add("3. " + MarkCommand.usage);
            helpGuide.add("4. " + UnmarkCommand.usage);
            helpGuide.add("5. " + ToDoCommand.usage);
            helpGuide.add("6. " + EventCommand.usage);
            helpGuide.add("7. " + DeadlineCommand.usage);
            helpGuide.add("8. " + DeleteCommand.usage);
            return helpGuide.toArray(new String[0]);
        }

        @Override
        public void execute(TaskList taskList) throws DukeException {
            Ui.printMessages(HelpCommand.getHelpGuide());
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }
}
