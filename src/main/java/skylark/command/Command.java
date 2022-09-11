package skylark.command;

import skylark.skylark.SkylarkException;
import skylark.task.Deadline;
import skylark.task.Event;
import skylark.task.Task;
import skylark.task.TaskList;
import skylark.task.Todo;

/**
 * Represents a command based on user input. <br><br>
 * A <code>Command</code> object corresponds to
 * a particular command selected by the user e.g., user selects <code>list</code>.
 */
public abstract class Command {
    private static final int MIN_LENGTH_DONE = 4;
    private static final int MIN_LENGTH_UNDONE = 6;
    private static final int MIN_LENGTH_TODO = 4;
    private static final int MIN_LENGTH_DEADLINE = 8;
    private static final int MIN_LENGTH_EVENT = 5;
    private static final int MIN_LENGTH_DELETE = 6;
    private static final int MIN_LENGTH_FIND = 5;
    private static final int MIN_LENGTH_TAG = 5;

    /** String representing the input keyed by the user. */
    private final String input;

    /** Returns the exact input keyed by the user. */
    private Command(String input) {
        this.input = input;
    }

    /**
     * Interprets the command based on keywords.
     * If there are no matching commands, a UnknownCommand is returned.
     *
     * @param command Input string from the user.
     * @return A Command object based on interpreted input.
     */
    public static Command createCommand(String command) {
        if (command.equals(CommandList.COMMAND_LIST.toString())) {
            return new ListCommand(command);
        } else if (command.length() >= Command.MIN_LENGTH_DONE
                && command.startsWith(CommandList.COMMAND_DONE.toString())) {
            return new DoneCommand(command);
        } else if (command.length() >= Command.MIN_LENGTH_UNDONE
                && command.startsWith(CommandList.COMMAND_UNDONE.toString())) {
            return new UndoneCommand(command);
        } else if (command.length() >= Command.MIN_LENGTH_TODO
                && command.startsWith(CommandList.COMMAND_TODO.toString())) {
            return new TodoCommand(command);
        } else if (command.length() >= Command.MIN_LENGTH_DEADLINE
                && command.startsWith(CommandList.COMMAND_DEADLINE.toString())) {
            return new DeadlineCommand(command);
        } else if (command.length() >= Command.MIN_LENGTH_EVENT
                && command.startsWith(CommandList.COMMAND_EVENT.toString())) {
            return new EventCommand(command);
        } else if (command.length() >= Command.MIN_LENGTH_DELETE
                && command.startsWith(CommandList.COMMAND_DELETE.toString())) {
            return new DeleteCommand(command);
        } else if (command.length() >= Command.MIN_LENGTH_FIND
                && command.startsWith(CommandList.COMMAND_FIND.toString())) {
            return new FindCommand(command);
        } else if (command.length() >= Command.MIN_LENGTH_TAG
                && command.startsWith(CommandList.COMMAND_TAG.toString())) {
            return new TagCommand(command);
        } else {
            return new UnknownCommand(command);
        }
    }

    /**
     * Executes the command.
     * Throws a SkylarkException if there is an error.
     *
     * @param taskList List of Tasks that already exists.
     * @return String representation of response from executing the command
     * @throws SkylarkException When there is an unexpected exception
     */
    public abstract String run(TaskList taskList) throws SkylarkException;
    public String getInput() {
        return this.input;
    }

    private static class ListCommand extends Command {
        /**
         * Returns a ListCommand object.
         *
         * @param input String that is input by the user.
         */
        public ListCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the tasks in your list:");
            stringBuilder.append(System.lineSeparator());
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                stringBuilder.append(i + 1).append(". ").append(currentTask.toString());
                stringBuilder.append(System.lineSeparator());
            }

            return stringBuilder.toString();
        }
    }

    private static class DoneCommand extends Command {
        private static final int POSITION = 5;

        /**
         * Returns a DoneCommand object.
         *
         * @param input String that is input by the user.
         */
        public DoneCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            String response;
            String command = super.getInput();
            try {
                int index = Integer.parseInt(command.substring(DoneCommand.POSITION)) - 1;
                if (taskList.doesIndexExist(index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.markAsDone();
                    response = "Nice! I've marked this task as done:"
                            + System.lineSeparator()
                            + currentTask;
                    taskList.saveToFile();
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
                }
            } catch (NumberFormatException numberFormatException) {
                throw new SkylarkException("Input is not a number!");
            }
            return response;
        }
    }

    private static class UndoneCommand extends Command {
        private static final int POSITION = 7;

        /**
         * Returns a UndoneCommand object.
         *
         * @param input String that is input by the user.
         */
        public UndoneCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            String command = super.getInput();
            String response;
            try {
                int index = Integer.parseInt(command.substring(UndoneCommand.POSITION)) - 1;
                if (taskList.doesIndexExist(index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.markAsUndone();
                    response = "OK, I've marked this task as not done yet:"
                            + System.lineSeparator()
                            + currentTask;
                    taskList.saveToFile();
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
                }
            } catch (NumberFormatException numberFormatException) {
                throw new SkylarkException("Input is not a number!");
            }
            return response;
        }
    }

    private static class TodoCommand extends Command {
        private static final int POSITION = 5;

        /**
         * Returns a Todo object.
         *
         * @param input String that is input by the user.
         */
        public TodoCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            String command = super.getInput();
            if (command.equals(CommandList.COMMAND_TODO.toString())) {
                throw new SkylarkException("The description of a todo cannot be empty.");
            }
            String description = command.substring(TodoCommand.POSITION);
            if (description.isEmpty()) {
                throw new SkylarkException("The description of a todo cannot be empty.");
            }
            Todo toDoTask = new Todo(description);
            taskList.add(toDoTask);
            String response = "Got it. I've added this task:"
                    + System.lineSeparator()
                    + toDoTask
                    + System.lineSeparator()
                    + "Now you have "
                    + taskList.size()
                    + " tasks in the list.";
            taskList.saveToFile();

            return response;
        }
    }

    private static class DeadlineCommand extends Command {
        private static final int POSITION = 9;
        private static final int POSITION_DATE = 4;

        /**
         * Returns a DeadlineCommand object.
         *
         * @param input String that is input by the user.
         */
        public DeadlineCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            String command = super.getInput();
            int slashIndex = command.lastIndexOf("/");
            String desc = command.substring(DeadlineCommand.POSITION, slashIndex - 1);
            String endDate = command.substring(slashIndex + DeadlineCommand.POSITION_DATE);
            if (desc.isEmpty() || endDate.isEmpty()) {
                throw new SkylarkException("The description or end date of a deadline cannot be empty.");
            }
            Deadline deadlineTask = new Deadline(desc, endDate);
            taskList.add(deadlineTask);
            String response = "Got it. I've added this task:"
                    + System.lineSeparator()
                    + deadlineTask
                    + "Now you have "
                    + taskList.size()
                    + " tasks in the list.";
            taskList.saveToFile();

            return response;
        }
    }

    private static class EventCommand extends Command {
        private static final int POSITION = 6;
        private static final int POSITION_DATE = 4;

        /**
         * Returns a EventCommand object.
         *
         * @param input String that is input by the user.
         */
        public EventCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            String command = super.getInput();
            int slashIndex = command.lastIndexOf("/");
            String desc = command.substring(EventCommand.POSITION, slashIndex - 1);
            String timing = command.substring(slashIndex + EventCommand.POSITION_DATE);
            if (desc.isEmpty() || timing.isEmpty()) {
                throw new SkylarkException("The description or timing of an event cannot be empty.");
            }
            Event eventTask = new Event(desc, timing);
            taskList.add(eventTask);
            String response = "Got it. I've added this task:"
                    + System.lineSeparator()
                    + eventTask
                    + System.lineSeparator()
                    + "Now you have " + taskList.size() + " tasks in the list.";
            taskList.saveToFile();

            return response;
        }
    }

    private static class DeleteCommand extends Command {
        private static final int POSITION = 7;

        /**
         * Returns a DeleteCommand object.
         *
         * @param input String that is input by the user.
         */
        public DeleteCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            String response;
            try {
                String command = super.getInput();
                int index = Integer.parseInt(command.substring(DeleteCommand.POSITION)) - 1;
                if (taskList.doesIndexExist(index)) {
                    Task currentTask = taskList.get(index);
                    taskList.remove(index);
                    response = "Noted. I've removed this task:"
                            + System.lineSeparator()
                            + currentTask
                            + System.lineSeparator()
                            + "Now you have "
                            + taskList.size()
                            + " tasks in the list.";
                    taskList.saveToFile();
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
                }
            } catch (NumberFormatException numberFormatException) {
                throw new SkylarkException("Input is not a number!");
            }
            return response;
        }
    }

    private static class FindCommand extends Command {
        private static final int POSITION = 5;

        public FindCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            String command = super.getInput();
            String query = command.substring(FindCommand.POSITION);
            if (query.isEmpty()) {
                throw new SkylarkException("The find query cannot be empty.");
            }
            StringBuilder response = new StringBuilder();
            response.append("Here are the matching tasks in your list:");
            response.append(System.lineSeparator());
            int count = 1;
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                if (currentTask.toString().contains(query)) {
                    response.append(count);
                    response.append(". ");
                    response.append(currentTask);
                    response.append(System.lineSeparator());
                    count += 1;
                }
            }

            return response.toString();
        }
    }

    private static class TagCommand extends Command {
        private static final int POSITION = 4;
        private static final int POSITION_END = 5;

        /**
         * Returns a TagCommand object.
         *
         * @param input String that is input by the user.
         */
        public TagCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            String response;
            try {
                String command = super.getInput();
                int index = Integer.parseInt(command.substring(TagCommand.POSITION, TagCommand.POSITION_END)) - 1;
                String tag = command.substring(TagCommand.POSITION_END).strip();
                if (tag.isEmpty()) {
                    throw new SkylarkException("Sorry, the tag is empty!");
                }
                if (taskList.doesIndexExist(index)) {
                    Task currentTask = taskList.get(index);
                    currentTask.setTag(tag);
                    response = "Noted. I've tagged this task:"
                            + System.lineSeparator()
                            + currentTask;
                    taskList.saveToFile();
                } else {
                    throw new SkylarkException("Sorry, index does not exist!");
                }
            } catch (NumberFormatException numberFormatException) {
                throw new SkylarkException("Input is not a number!");
            }
            return response;
        }
    }

    private static class UnknownCommand extends Command {
        /** Text that is displayed when the user issues an unknown command. */
        private static final String TEXT = "I'm sorry, but I don't know what that means.";

        /**
         * Returns a UnknownCommand object.
         *
         * @param input String that is input by the user.
         */
        public UnknownCommand(String input) {
            super(input);
        }

        @Override
        public String run(TaskList taskList) throws SkylarkException {
            throw new SkylarkException(TEXT);
        }
    }
}

