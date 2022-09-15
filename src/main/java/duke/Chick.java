package duke;

/**
 * Main class representing Chick Bot which is also a Duke Bot.
 */
public class Chick {
    protected Ui ui;
    protected TaskList tasks;
    protected Parser parser;

    private boolean isChickRunning;

    /**
     * Class constructor for Chick Bot.
     */
    public Chick() {
        ui = new Ui();
        ui.printLogo();
        tasks = new TaskList(ui);
        parser = new Parser(tasks, this, ui);
        tasks.loadTasks(parser);
        isChickRunning = true;
    }

    /**
     * Returns the next line input by user.
     *
     * @return String input by user.
     */
    public String getNextLine() {
        return ui.getNextLine();
    }

    /**
     * Parses and executes a command string.
     *
     * @param command Command string to be parsed and executed.
     * @return Response string from Duke Bot.
     */
    public String parseCommand(String command) {
        try {
            return parser.parse(command, true);
        } catch (ChickException e) {
            return ui.printMessage(e.getMessage());
        }
    }

    /**
     * Terminates running of Duke Bot.
     * This method sets the runningDuke flag to false.
     */
    public void terminate() {
        isChickRunning = false;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parseCommand(input);
    }

    /**
     * Main method to run Duke Bot.
     * @param args Unused input arguments.
     */
    public static void main(String[] args) {
        Chick chick = new Chick();
        while (chick.isChickRunning) {
            String userInput = chick.getNextLine();
            chick.parseCommand(userInput);
        }
    }
}
