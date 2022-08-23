public class DukeCommandHandler {
    private String command;
    private DukeMessager dukeMessager;
    private boolean isRunning = false;

    public DukeCommandHandler() {
        dukeMessager = new DukeMessager();
    }

    private void receiveCommand(String command) {
        this.command = command;
    }

    private void echoCommand() {
        dukeMessager.message(command);
    }

    private void byeCommand() {
        dukeMessager.bye();
        isRunning = false;
    }

    private void executeCommand(String command) {
        receiveCommand(command);
        switch (command){
        case "bye":
            byeCommand();
        default:
            echoCommand();
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
