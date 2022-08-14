/**
 * Driver class of Thomas
 */
public class Thomas {
    UI ui;

    /**
     * Constructor for Thomas
     */
    public Thomas() {
        this.ui = new UI();
    }

    /**
     * Runs Thomas until termination
     */
    public void run() {
        this.start();
        this.runProcess();
        this.exit();
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command
     */
    public void runProcess() {
        String command = ui.getUserCommand();
        while (!isExitCommand(command)) {
            ui.echo(command);
            command = ui.getUserCommand();
        }
    }

    /**
     * Starts Thomas by displaying a welcome message
     */
    public void start() {
        ui.showWelcomeMessage();
    }

    /**
     * Exits Thomas after displaying a goodbye message
     */
    public void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Checks if the input command is the exit command
     * @param command The input command
     * @return True if the input command is the exit command, false otherwise
     */
    public boolean isExitCommand(String command) {
        return command.equals("bye");
    }

    public static void main(String[] args) {
        Thomas thomas = new Thomas();
        thomas.run();
    }
}
