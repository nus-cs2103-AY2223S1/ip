public class Byu {

    private FileReader storage;
    private ToDoList tasks;
    private Ui ui;

    public Byu(String filePath) {
        ui = new Ui();
        storage = new FileReader(filePath);
        tasks = storage.load();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Byu("./data/tasks.txt").run();
    }

}
