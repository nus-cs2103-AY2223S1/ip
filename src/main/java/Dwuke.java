/**
 * Driver class of Dwuke.
 */
public class Dwuke {
    private UI ui;
    private TaskList taskList;

    /**
     * Constructor for Dwuke.
     */
    public Dwuke() {
        this.ui = new UI();
        this.taskList = new TaskList();
    }

    /**
     * Runs Dwuke until it is stopped.
     */
    public void run() {
        this.start();
        this.wun();
        this.stop();
    }

    /**
     * Reads the user input and executes it, until the user issues the exit command.
     */
    public void wun() {
        String userInput = ui.getUserInput();
        while (true) {
            Command command = Parser.parseInput(userInput, this.taskList);

            if (command instanceof ExitCommand) return;

            ui.echo(command.execute());
            userInput = ui.getUserInput();
        }
    }

    /**
     * Starts Dwuke by displaying a welcome message.
     */
    public void start() {
        ui.showWelcomeMessage();
    }

    /**
     * Stops Dwuke after displaying a goodbye message.
     */
    public void stop() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        Dwuke dwuke = new Dwuke();
        dwuke.run();
    }
}
