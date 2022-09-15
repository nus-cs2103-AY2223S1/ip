package duke;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses a given input and performs various actions to the task list and the UI,
     *
     * @param input    Full command entered by the user.
     * @param taskList Task list to perform operations on.
     * @return Response message.
     * @throws DukeException If the input contains errors.
     */
    public static String parse(String input, TaskList taskList) throws DukeException {
        String[] command = input.split(" ", 2);
        switch (command[0].toLowerCase()) {
        case "bye":
            return byeCommand();
        case "list":
            return listCommand(taskList);
        case "mark":
            return markCommand(taskList, command);
        case "unmark":
            return unmarkCommand(taskList, command);
        case "todo":
            return todoCommand(taskList, command);
        case "deadline":
            return deadlineCommand(taskList, command);
        case "event":
            return eventCommand(taskList, command);
        case "recurring":
            return recurringCommand(taskList, command);
        case "delete":
            return deleteCommand(taskList, command);
        case "find":
            return findCommand(taskList, command);
        default:
            return unknownCommand();
        }
    }

    private static String byeCommand() {
        System.exit(0);
        assert false;
        return null;
    }

    private static String listCommand(TaskList taskList) {
        return Ui.showTasks(taskList);
    }

    private static String markCommand(TaskList taskList, String[] command) throws DukeException {
        assert (command.length > 1);
        int index = Integer.parseInt(command[1]) - 1;
        assert (index >= 0 && index < taskList.getSize());
        taskList.mark(index);
        return Ui.showMarked(taskList.getTask(index));
    }

    private static String unmarkCommand(TaskList taskList, String[] command) throws DukeException {
        assert (command.length > 1);
        int index = Integer.parseInt(command[1]) - 1;
        assert (index >= 0 && index < taskList.getSize());
        taskList.unmark(index);
        return Ui.showUnmarked(taskList.getTask(index));
    }

    private static String todoCommand(TaskList taskList, String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(command[1]);
        taskList.add(newTask);
        return Ui.showAdded(newTask, taskList.getSize());
    }

    private static String deadlineCommand(TaskList taskList, String[] command) throws DukeException {
        assert (command.length > 1);
        String[] arguments = command[1].split(" /by ", 2);
        assert (arguments.length > 1);
        Task newTask = new Deadline(arguments[0], arguments[1]);
        taskList.add(newTask);
        return Ui.showAdded(newTask, taskList.getSize());
    }

    private static String eventCommand(TaskList taskList, String[] command) throws DukeException {
        assert (command.length > 1);
        String[] arguments = command[1].split(" /at ", 2);
        assert (arguments.length > 1);
        Task newTask = new Event(arguments[0], arguments[1]);
        taskList.add(newTask);
        return Ui.showAdded(newTask, taskList.getSize());
    }

    private static String recurringCommand(TaskList taskList, String[] command) throws DukeException {
        assert (command.length > 1);
        String[] arguments = command[1].split(" /repeats ", 2);
        assert (arguments.length > 1);
        Task newTask = new Recurring(arguments[0], arguments[1]);
        taskList.add(newTask);
        return Ui.showAdded(newTask, taskList.getSize());
    }

    private static String deleteCommand(TaskList taskList, String[] command) throws DukeException {
        assert (command.length > 1);
        int index = Integer.parseInt(command[1]) - 1;
        assert (index >= 0 && index < taskList.getSize());
        Task task = taskList.getTask(index);
        taskList.remove(index);
        return Ui.showRemoved(task, taskList.getSize());
    }

    private static String findCommand(TaskList taskList, String[] command) {
        assert (command.length > 1);
        return Ui.showResults(taskList.search(command[1]));
    }

    private static String unknownCommand() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
}
