/**
 * Driver class of Thomas.
 */
public class Thomas {
    private UI ui;
    private TaskList taskList;

    /**
     * Constructor for Thomas.
     */
    public Thomas() {
        this.ui = new UI();
        this.taskList = new TaskList();
    }

    /**
     * Runs Thomas until it is stopped.
     */
    public void run() {
        this.start();
        this.runEngine();
        this.stop();
    }

    /**
     * Reads the user input and executes it, until the user issues the exit command.
     */
    public void runEngine() {
        String userInput = ui.getUserInput();
        while (true) {
            Command command = (new Parser()).parseInput(userInput);

            if (command instanceof ExitCommand) return;

            ui.echo(command.execute(this.taskList));
            userInput = ui.getUserInput();
        }
    }

    /**
     * Starts Thomas by displaying a welcome message.
     */
    public void start() {
        ui.showWelcomeMessage();
    }

    /**
     * Stops Thomas after displaying a goodbye message.
     */
    public void stop() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        Thomas thomas = new Thomas();
        thomas.run();
    }
}
