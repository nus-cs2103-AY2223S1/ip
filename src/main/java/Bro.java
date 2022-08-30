public class Bro {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    public Bro(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BroException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
           try {
               String fullCommand = ui.readCommand();
               Command c = new Parser().parse(fullCommand);
               c.execute(tasks, ui, storage);
               isExit = c.isExit();
           } catch(BroException e){
               ui.errorMessage(e.getMessage());
           }
        }
    }

    public static void main(String[] args) {
        Bro bro = new Bro("./Bro.txt");
        bro.run();
    }
}
