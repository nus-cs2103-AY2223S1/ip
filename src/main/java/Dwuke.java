/**
 * Driver class of Dwuke.
 */
public class Dwuke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Dwuke.
     */
    public Dwuke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = storage.load();
    }

    /**
     * Runs Dwuke until it is stopped.
     */
    public void run() {
        this.start();
        this.loop();
        this.stop();
    }

    /**
     * Reads the user input and executes it, until the user issues the exit command.
     */
    public void loop() {
        String userInput = this.ui.getUserInput();
        while (true) {
            Command command = Parser.parseInput(userInput, this.taskList);

            if (command instanceof ExitCommand) {
                return;
            }

            this.ui.echo(command.execute());
            this.storage.save(this.taskList);

            userInput = this.ui.getUserInput();
        }
    }

    /**
     * Starts Dwuke by displaying a welcome message.
     */
    public void start() {
        this.ui.showWelcomeMessage();
    }

    /**
     * Stops Dwuke after displaying a goodbye message.
     */
    public void stop() {
        this.ui.showGoodbyeMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        Dwuke dwuke = new Dwuke();
        dwuke.run();
    }
}
