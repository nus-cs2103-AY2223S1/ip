import java.util.List;

public class Controller {

    public static Enum parseToEnum(String input) {
        Enum command = null;
        try {
            command = Model.getCommand(input);
        } catch (InvalidCommandException e) {
            View.printInvalidCommandException(e);
        }
        return command;
    }

    public static String parseToDescription(String input) {
        String description = null;
        try {
            description = Model.getDescription(input);
        } catch (EmptyDescriptionException e) {
            description = null;
        }
        return description;
    }

    public static void execute(Enum commands, String description) {
        if (commands == dukeCommands.BYE) {
            View.printTerminateStatement();
            Model.terminate();

        } else if (commands == dukeCommands.LIST) {
            List<Task> memo = Model.list();
            View.printMemo(memo);

        } else if (commands == dukeCommands.MARK) {
            Integer index = null;
            try {
                index = Model.getIndex(description);
                Task task = Model.markTask(index);
                View.printTask(task);
            } catch (InvalidIndexException e) {
                View.printInvalidIndexException(e);
            }

        } else if (commands == dukeCommands.UNMARK) {
            Integer index = null;
            try {
                index = Model.getIndex(description);
                Task task = Model.unmarkTask(index);
                View.printTask(task);
            } catch (InvalidIndexException e) {
                View.printInvalidIndexException(e);
            }

        } else if (commands == dukeCommands.DELETE) {
            Integer index = null;
            try {
                index = Model.getIndex(description);
                Task task = Model.deleteTask(index);
                View.printDeletedTask(task, Model.getNumOfRemainingTasks());
            } catch (InvalidIndexException e) {
                View.printInvalidIndexException(e);
            }

        } else if (commands == dukeCommands.TODO) {
            Task todoTask = null;
            try {
                todoTask = Model.addTodoTask(description);
                View.printAddTask(todoTask, Model.getNumOfRemainingTasks());
            } catch (EmptyDescriptionException e) {
                View.printEmptyDescriptionException(e);
            }

        } else if (commands == dukeCommands.DEADLINE) {
            Task deadlineTask = null;
            try {
                deadlineTask = Model.addDeadlineTask(description);
                View.printAddTask(deadlineTask, Model.getNumOfRemainingTasks());
            } catch (EmptyDescriptionException e) {
                View.printEmptyDescriptionException(e);
            }

        } else if (commands == dukeCommands.EVENT) {
            Task eventTask = null;
            try {
                eventTask = Model.addEventTask(description);
                View.printAddTask(eventTask, Model.getNumOfRemainingTasks());
            } catch (EmptyDescriptionException e) {
                View.printEmptyDescriptionException(e);
            }
        }
    }
}
