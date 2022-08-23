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

    private void addCommand(String command) {
        taskList.addTask(command);
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
        default:
            addCommand(commandFilter.getRemainderCommand());
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
