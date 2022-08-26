public class Wanya {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Wanya(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ui.showLoading();
        try {
            tasks = new TaskList(storage.load());
        } catch (WanyaException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        while(ui.isActive() && ui.hasNextLine()) {
            String command = ui.getUserCommand();
            Parser.parseCommand(command, tasks, ui);
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        Wanya wanya = new Wanya("tasks.txt");
        wanya.run();
    }
}


