public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private String folderPath;
    private String filePath;

    public Duke(String folderPath, String filePath) {
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    public void run() {
        new LoadDataCommand(folderPath, filePath).execute(tasks, ui, storage);
        new WelcomeCommand().execute(tasks, ui, storage);
        while (ui.hasNext()) {
            try {
                String inputs = ui.readCommand();
                Command command = parser.parse(inputs);
                command.execute(tasks, ui, storage);
                new SaveDataCommand(filePath).execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke("./data", "./data/duke.txt");
        duke.run();
    }
}
