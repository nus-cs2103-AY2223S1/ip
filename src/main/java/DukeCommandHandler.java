public class DukeCommandHandler {
    private String command;
    private DukeMessager dukeMessager;
    private boolean isRunning = false;
    private int size = 100;
    private String[] list = new String[size];
    private int index = 0;

    public DukeCommandHandler() {
        dukeMessager = new DukeMessager();
    }

    private void receiveCommand(String command) {
        this.command = command;
    }

    private void addToList() {
        list[index] = command;
        index++;
        dukeMessager.message("Added: " + command);
    }

    private void listCommand() {
        for (int i = 0; i < size; i++) {
            if (list[i] == null) {
                return;
            }
            dukeMessager.message(String.format("%d. %s", i+1, list[i]));
        }
    }

    private void byeCommand() {
        dukeMessager.bye();
        isRunning = false;
    }

    private void executeCommand(String command) {
        receiveCommand(command);
        switch (command){
        case "list":
            listCommand();
            break;
        case "bye":
            byeCommand();
            break;
        default:
            addToList();
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
