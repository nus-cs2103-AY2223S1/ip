public class NewDuke { // Code to be moved to Duke.java after completion. Used for testing only
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public NewDuke(String filepath) throws Exception {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } finally {
            System.out.println("");
        }
        //} catch (DukeException e) {
        //    tasks = new TaskList();
        //}
    }

    public void run() throws Exception {
        ui.greet();
        ui.getInput();
    }

    public static void main(String[] args) throws Exception {
        new NewDuke("./data/tasks.txt").run();
    }
}
