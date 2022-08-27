package duke;

public class Duke {

    private Storage storage;
    private Ui ui;
    private Parser parser;
    private boolean isProcess;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(this.ui,this.storage);
    }

    public void run() {
        this.ui.intro();
        this.isProcess = true;
        while(this.isProcess) {
            String cmd = this.ui.getCommand();
            this.isProcess = this.parser.process(cmd);
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
