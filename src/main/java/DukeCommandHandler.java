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

    private void addCommand() {
        Task task = new Task(commandFilter.getRemainderCommand());
        taskList.addTask(task);
    }

    private void listCommand() {
        taskList.listTask();
    }

    private void byeCommand() {
        dukeMessager.bye();
        isRunning = false;
    }

    private void markCommand() {
        taskList.markTask(commandFilter.getRemainderCommandAsInt() - 1);
    }

    private void unmarkCommand() {
        taskList.unmarkTask(commandFilter.getRemainderCommandAsInt() - 1);
    }

    private void todoCommand() {
        Todo todo = new Todo(commandFilter.getRemainderCommand());
        taskList.addTask(todo);
    }

    private void deadlineCommand() {
        String[] descriptions = commandFilter.getRemainderCommand().split(" /by ");
        Deadline deadline = new Deadline(descriptions[0], descriptions[1]);
        taskList.addTask(deadline);
    }

    private void eventCommand() {
        String[] descriptions = commandFilter.getRemainderCommand().split(" /at ");
        Event event = new Event(descriptions[0], descriptions[1]);
        taskList.addTask(event);
    }

    private void executeCommand(String command) {
        commandFilter.filterCommand(command);
        switch (commandFilter.getCommand()){
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
        default:
            addCommand();
        }
    }

    public void run() {
        isRunning = true;
        dukeMessager.introduction();
        while (isRunning) {
            String command = dukeMessager.getMessage();
            executeCommand(command);
        }
    }
}
