/**
 * Driver class of Thomas
 */
public class Thomas {
    UI ui;
    CommandList commandList;

    /**
     * Constructor for Thomas
     */
    public Thomas() {
        this.ui = new UI();
        this.commandList = new CommandList();
    }

    /**
     * Runs Thomas until termination
     */
    public void run() {
        this.start();
        this.runEngine();
        this.stop();
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command
     */
    public void runEngine() {
        String command = ui.getUserCommand();
        while (!isExitCommand(command)) {
            if (!isListCommand(command)) {
                commandList.add(command);
                ui.echo("added: " + command);
            } else {
                ui.echo(commandList.getAllCommands());
            }
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
     * Stops Thomas after displaying a goodbye message
     */
    public void stop() {
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

    /**
     * Checks if the input command is the list command
     * @param command The input command
     * @return True if the input command is the list command, false otherwise
     */
    public boolean isListCommand(String command) {
        return command.equals("list");
    }

    public static void main(String[] args) {
        Thomas thomas = new Thomas();
        thomas.run();
    }
}
