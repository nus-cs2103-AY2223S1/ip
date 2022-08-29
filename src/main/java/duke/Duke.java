package duke;

/**
 * Main class representing Duke Bot.
 */
public class Duke {
    protected Ui ui;
    protected TaskList tasks;
    protected Parser parser;

    private boolean runningDuke;
    
    /**
     * Class constructor for Duke Bot.
     */
    public Duke() {
        ui = new Ui();
        ui.printLogo();
        tasks = new TaskList(ui);
        parser = new Parser(tasks, this, ui);
        tasks.loadTasks(parser);
        runningDuke = true;
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
     */
    public void parseCommand(String command) {
        try {
            parser.parse(command, true);
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * Terminates running of Duke Bot.
     * This method sets the runningDuke flag to false.
     */
    public void terminate() {
        runningDuke = false;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        while (duke.runningDuke) {
            String m = duke.getNextLine();
            duke.parseCommand(m);
        }
    }
}
