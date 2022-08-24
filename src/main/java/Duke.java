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
        this.ui.showWelcome();
        String command = this.ui.nextCommand();
        try {
            Parser p = new Parser(this.tasks);
            while(!p.isBye(p.parseCommand(command))) {
                this.tasks = p.executeCommand(p.parseCommand(command), command);
                command = this.ui.nextCommand();
            }
            storage.writeFile(this.tasks.getList());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            this.ui.showBye();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}
