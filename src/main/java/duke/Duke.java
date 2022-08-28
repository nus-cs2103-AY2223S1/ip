package duke;

public class Duke {
    protected Ui ui;
    protected TaskList tasks;
    protected Parser parser;

    private boolean runningDuke;

    public Duke() {
        ui = new Ui();
        ui.printLogo();
        tasks = new TaskList(ui);
        parser = new Parser(tasks, this, ui);
        tasks.loadTasks(parser);
        runningDuke = true;
    }

    public String getNextLine() {
        return ui.getNextLine();
    }

    public void parseCommand(String command) {
        try {
            parser.parse(command, true);
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
    }

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
