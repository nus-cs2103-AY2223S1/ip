public class DukeCommandHandler {
    private DukeMessager dukeMessager;
    private TaskList taskList;
    private CommandFilter commandFilter;
    private boolean isRunning = false;

    public DukeCommandHandler() {
        dukeMessager = new DukeMessager();
        taskList = new TaskList();
        commandFilter = new CommandFilter();
    }

    private void listCommand() throws ExcessDukeCommandException, EmptyListException {
        if (commandFilter.getRemainderCommand() != null) {
            throw new ExcessDukeCommandException();
        }
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        taskList.listTask();
    }

    private void byeCommand() throws ExcessDukeCommandException {
        if (commandFilter.getRemainderCommand() != null) {
            throw new ExcessDukeCommandException();
        }
        dukeMessager.bye();
        isRunning = false;
    }

    private int parseInt(String number) throws IntegerExpectedException {
        try {
            int index = Integer.parseInt(number) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new IntegerExpectedException();
        }
    }

    private void markCommand() throws EmptyException, IndexOutOfBoundsException {
        if (commandFilter.getRemainderCommand() == null) {
            throw new EmptyMarkException();
        }
        taskList.markTask(parseInt(commandFilter.getRemainderCommand()));
    }

    private void unmarkCommand() throws EmptyException, IndexOutOfBoundsException {
        if (commandFilter.getRemainderCommand() == null) {
            throw new EmptyUnmarkException();
        }
        taskList.unmarkTask(parseInt(commandFilter.getRemainderCommand()));
    }

    private void todoCommand() throws EmptyException {
        if (commandFilter.getRemainderCommand() == null) {
            throw new EmptyTodoException();
        }
        Todo todo = new Todo(commandFilter.getRemainderCommand());
        taskList.addTask(todo);
    }

    private void deadlineCommand() throws EmptyException {
        if (commandFilter.getRemainderCommand() == null) {
            throw new EmptyDeadlineException();
        }
        String description = commandFilter.getRemainderCommand();
        if (!description.contains(" /by ")) {
            throw new EmptyDateTimeException();
        }
        String[] descriptions = description.split(" /by ");
        Deadline deadline = new Deadline(descriptions[0], descriptions[1]);
        taskList.addTask(deadline);
    }

    private void eventCommand() throws EmptyException {
        if (commandFilter.getRemainderCommand() == null) {
            throw new EmptyEventException();
        }
        String description = commandFilter.getRemainderCommand();
        if (!description.contains(" /at ")) {
            throw new EmptyDateTimeException();
        }
        String[] descriptions = description.split(" /at ");
        Event event = new Event(descriptions[0], descriptions[1]);
        taskList.addTask(event);
    }

    private void deleteCommand() throws EmptyException, IndexOutOfBoundsException {
        if (commandFilter.getRemainderCommand() == null) {
            throw new EmptyDeleteException();
        }
        taskList.deleteTask(parseInt(commandFilter.getRemainderCommand()));
    }

    private void executeCommand(String command) throws DukeException, NumberFormatException {
        commandFilter.filterCommand(command);
        switch (commandFilter.getCommand()) {
        case "list":
            listCommand();
            break;
        case "bye":
            byeCommand();
            break;
        case "mark":
            markCommand();
            break;
        case "unmark":
            unmarkCommand();
            break;
        case "todo":
            todoCommand();
            break;
        case "deadline":
            deadlineCommand();
            break;
        case "event":
            eventCommand();
            break;
        case "delete":
            deleteCommand();
            break;
        default:
            throw new DukeCommandNotFoundException();
        }
    }

    public void run() {
        isRunning = true;
        dukeMessager.introduction();
        while (isRunning) {
            String command = dukeMessager.getMessage();
            try {
                executeCommand(command);
            } catch (Exception e) {
                dukeMessager.message(e);
            }
        }
    }
}
