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
     * @param ui       UI for displaying output.
     * @return Response message.
     * @throws DukeException If the input contains errors.
     */
    public static String parse(String input, TaskList taskList, Ui ui) throws DukeException {
        String[] command = input.split(" ", 2);
        switch (command[0].toLowerCase()) {
        case "bye":
            return byeCommand();
        case "list":
            return listCommand(taskList, ui);
        case "mark":
            return markCommand(taskList, ui, command);
        case "unmark":
            return unmarkCommand(taskList, ui, command);
        case "todo":
            return todoCommand(taskList, ui, command);
        case "deadline":
            return deadlineCommand(taskList, ui, command);
        case "event":
            return eventCommand(taskList, ui, command);
        case "delete":
            return deleteCommand(taskList, ui, command);
        case "find":
            return findCommand(taskList, ui, command);
        default:
            return unknownCommand();
        }
    }

    private static String byeCommand() {
        System.exit(0);
        assert false;
        return null;
    }

    private static String listCommand(TaskList taskList, Ui ui) {
        return ui.showTasks(taskList);
    }

    private static String markCommand(TaskList taskList, Ui ui, String[] command) throws DukeException {
        assert (command.length > 1);
        int index = Integer.parseInt(command[1]) - 1;
        assert (index >= 0 && index < taskList.getSize());
        taskList.mark(index);
        return ui.showMarked(taskList.getTask(index));
    }

    private static String unmarkCommand(TaskList taskList, Ui ui, String[] command) throws DukeException {
        assert (command.length > 1);
        int index = Integer.parseInt(command[1]) - 1;
        assert (index >= 0 && index < taskList.getSize());
        taskList.unmark(index);
        return ui.showUnmarked(taskList.getTask(index));
    }

    private static String todoCommand(TaskList taskList, Ui ui, String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(command[1]);
        taskList.add(newTask);
        return ui.showAdded(newTask, taskList.getSize());
    }

    private static String deadlineCommand(TaskList taskList, Ui ui, String[] command) throws DukeException {
        assert (command.length > 1);
        String[] arguments = command[1].split(" /by ", 2);
        assert (arguments.length > 1);
        Task newTask = new Deadline(arguments[0], arguments[1]);
        taskList.add(newTask);
        return ui.showAdded(newTask, taskList.getSize());
    }

    private static String eventCommand(TaskList taskList, Ui ui, String[] command) throws DukeException {
        assert (command.length > 1);
        String[] arguments = command[1].split(" /at ", 2);
        assert (arguments.length > 1);
        Task newTask = new Event(arguments[0], arguments[1]);
        taskList.add(newTask);
        return ui.showAdded(newTask, taskList.getSize());
    }

    private static String deleteCommand(TaskList taskList, Ui ui, String[] command) throws DukeException {
        assert (command.length > 1);
        int index = Integer.parseInt(command[1]) - 1;
        assert (index >= 0 && index < taskList.getSize());
        Task task = taskList.getTask(index);
        taskList.remove(index);
        return ui.showRemoved(task, taskList.getSize());
    }

    private static String findCommand(TaskList taskList, Ui ui, String[] command) {
        assert (command.length > 1);
        return ui.showResults(taskList.search(command[1]));
    }

    private static String unknownCommand() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
}
