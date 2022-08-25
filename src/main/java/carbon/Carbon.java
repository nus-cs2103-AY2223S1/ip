package carbon;

public class Carbon {
    // own fields
    private boolean isRunning;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    // actual constructor and init method
    private Carbon() {
        // init fields
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser(this.ui, this.storage);
    }

    // main shell method
    private void runShell() {
        this.isRunning = true;
        while (this.isRunning) {
            String input = this.ui.printIn();
            this.isRunning = this.parser.process(input);
        }
    }

    public static void main(String[] args) {
        Carbon shell = new Carbon();
        shell.runShell();
    }
}
