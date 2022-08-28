public class Duke {
    

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

   
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            } 
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/SavedTask.txt").run();
    }
}
