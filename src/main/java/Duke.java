import java.util.Scanner;

public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile(), storage);
        parser = new Parser(tasks);
    }

    public void run() {
        ui.greeting();
        parser.parseFunc();
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public static void echo(String userInput) {
        System.out.println(userInput);
    }
}
