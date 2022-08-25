package BetaGo;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser commander;

    public Duke() {
        this.tasks = new TaskList();
        this.commander = new Parser(this.tasks);
        this.storage = new Storage(this.tasks);
    }

    public void run() {
        Ui.greet();
        this.storage.loadFile();
        this.commander.readCommands();
        Ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
