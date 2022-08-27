public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.printStartUpUi();
        while(true) {
            try {
                String [] parsedCommand = parser.parseCommand(tasks).split(" ");
                //parsedCommand={command}{task index, can be multiple for list date or search}
                if (parsedCommand[0].equals("mark") || parsedCommand[0].equals("unmark") ||
                        parsedCommand[0].equals("todo") || parsedCommand[0].equals("event") ||
                        parsedCommand[0].equals("deadline") || parsedCommand[0].equals("delete")) {
                    ui.printAddCommandUi(parsedCommand[0], parsedCommand[1], tasks);
                } else if (parsedCommand[0].equals("list")) {
                    ui.printListCommandUi(parsedCommand[0], parsedCommand[1], tasks);
                } else if (parsedCommand[0].equals("help")) {
                    ui.printDukeInfo();
                } else {//bye
                    ui.printEndingUi();
                    // may throw duke exception
                    break;
                }
            } catch (Exception e) {
                ui.printException(e);
            }
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }
}