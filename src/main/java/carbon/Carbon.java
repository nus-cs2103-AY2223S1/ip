package carbon;

import carbon.error.CarbonException;

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
    private void run() {
        this.isRunning = true;
        while (this.isRunning) {
            try {
                String input = this.ui.printIn();
                String log = this.parser.process(input);
                if (log != "") {
                    this.ui.printOut(log);
                } else {
                    this.ui.exit();
                    this.isRunning = false;
                }
            } catch (CarbonException error) {
                this.ui.printError(error);
            }
        }
    }

    public static void main(String[] args) {
        Carbon shell = new Carbon();
        shell.run();
    }
}
