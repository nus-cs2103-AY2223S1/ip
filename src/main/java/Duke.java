public class Duke {
    private Storage storage; 
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage, ui);
        } catch (DukeException e) {
            ui.showError(e);
        }
    }
    
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                if (c instanceof ExitCommand) break;
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("././././data/duke.txt").run();
    }
}