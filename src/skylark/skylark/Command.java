package skylark;

public abstract class Command {

    private final String input;

    private Command(String input) {
        this.input = input;
    }

    public static Command createCommand(String command) {
        if (command.equals(CommandList.COMMAND_BYE.toString())) {
            return new ByeCommand(command);
        } else if (command.equals(skylark.CommandList.COMMAND_LIST.toString())) {
            return new ListCommand(command);
        } else if (command.length() >= 4 && command.startsWith(CommandList.COMMAND_DONE.toString())) {
            return new DoneCommand(command);
        } else if (command.length() >= 6 && command.startsWith(CommandList.COMMAND_UNDONE.toString())) {
            return new UndoneCommand(command);
        } else if (command.length() >= 4 && command.startsWith(CommandList.COMMAND_TODO.toString())) {
            return new TodoCommand(command);
        } else if (command.length() >= 8 && command.startsWith(CommandList.COMMAND_DEADLINE.toString())) {
            return new DeadlineCommand(command);
        } else if (command.length() >= 5 && command.startsWith(CommandList.COMMAND_EVENT.toString())) {
            return new EventCommand(command);
        } else if (command.length() >= 6 && command.startsWith(CommandList.COMMAND_DELETE.toString())) {
            return new DeleteCommand(command);
        } else {
            return new UnknownCommand(command);
        }
    }

    public abstract void run(TaskList taskList) throws SkylarkException;

    public String getInput() {
        return this.input;
    }

    private static class ByeCommand extends Command {
        private static final String TEXT = "Bye. Hope to see you again soon!";

        public ByeCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) {
            Printer.printText(ByeCommand.TEXT);
        }
    }

    private static class ListCommand extends Command {
        public ListCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) {
            Printer.printText("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                Printer.printText((i + 1) + ". " + currentTask.toString());
            }
        }
    }

    private static class DoneCommand extends Command {
        public DoneCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) throws SkylarkException {
            int index = Integer.parseInt(super.getInput().substring(5)) - 1;
            if (taskList.doesIndexExist(index)) {
                Task currentTask = taskList.get(index);
                currentTask.markAsDone();
                Printer.printText("Nice! I've marked this task as done:");
                Printer.printText(currentTask.toString());
                taskList.saveToFile();
            } else {
                throw new SkylarkException("Sorry, index does not exist!");
            }
        }
    }

    private static class UndoneCommand extends Command {
        public UndoneCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) throws SkylarkException {
            int index = Integer.parseInt(super.getInput().substring(7)) - 1;
            if (taskList.doesIndexExist(index)) {
                Task currentTask = taskList.get(index);
                currentTask.markAsUndone();
                Printer.printText("OK, I've marked this task as not done yet:");
                Printer.printText(currentTask.toString());
                taskList.saveToFile();
            } else {
                throw new SkylarkException("Sorry, index does not exist!");
            }
        }
    }

    private static class TodoCommand extends Command {
        public TodoCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) throws SkylarkException {
            if (super.getInput().equals(CommandList.COMMAND_TODO.toString())) {
                throw new ToDoException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            ToDo toDoTask = new ToDo(super.getInput().substring(5));
            taskList.add(toDoTask);
            Printer.printText("Got it. I've added this task:");
            Printer.printText(toDoTask.toString());
            Printer.printText("Now you have " + taskList.size() + " tasks in the list.");
            taskList.saveToFile();
        }
    }

    private static class DeadlineCommand extends Command {
        public DeadlineCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) throws SkylarkException {
            String command = super.getInput();
            int slashIndex = command.lastIndexOf("/");
            String desc = command.substring(9, slashIndex - 1);
            String endDate = command.substring(slashIndex + 4);
            if (desc.isEmpty() || endDate.isEmpty()) {
                throw new DeadlineException("☹ OOPS!!! " +
                        "The description or end date of a deadline cannot be empty.");
            }
            Deadline deadlineTask = new Deadline(desc, endDate);
            taskList.add(deadlineTask);
            Printer.printText("Got it. I've added this task:");
            Printer.printText(deadlineTask.toString());
            Printer.printText("Now you have " + taskList.size() + " tasks in the list.");
            taskList.saveToFile();
        }
    }

    private static class EventCommand extends Command {
        public EventCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) throws SkylarkException {
            String command = super.getInput();
            int slashIndex = command.lastIndexOf("/");
            String desc = command.substring(6, slashIndex - 1);
            String timing = command.substring(slashIndex + 4);
            if (desc.isEmpty() || timing.isEmpty()) {
                throw new EventException("☹ OOPS!!! " +
                        "The description or timing of an event cannot be empty.");
            }
            Event eventTask = new Event(desc, timing);
            taskList.add(eventTask);
            Printer.printText("Got it. I've added this task:");
            Printer.printText(eventTask.toString());
            Printer.printText("Now you have " + taskList.size() + " tasks in the list.");
            taskList.saveToFile();
        }
    }

    private static class DeleteCommand extends Command {
        public DeleteCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) throws SkylarkException {
            String command = super.getInput();
            int index = Integer.parseInt(command.substring(7)) - 1;
            if (taskList.doesIndexExist(index)) {
                Task currentTask = taskList.get(index);
                taskList.remove(index);
                Printer.printText("Noted. I've removed this task:");
                Printer.printText(currentTask.toString());
                Printer.printText("Now you have " + taskList.size() + " tasks in the list.");
                taskList.saveToFile();
            } else {
                throw new SkylarkException("Sorry, index does not exist!");
            }
        }
    }

    private static class UnknownCommand extends Command {
        private static final String TEXT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

        public UnknownCommand(String input) {
            super(input);
        }

        @Override
        public void run(TaskList taskList) throws SkylarkException {
            throw new SkylarkException(TEXT);
        }
    }
}

