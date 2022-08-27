public class Jamie {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Jamie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.load();
    }

    public void run() {
        ui.welcome();
    }

    public static void main(String[] args) {
        new Jamie("Data/Jamie.txt").run();
    }
}
