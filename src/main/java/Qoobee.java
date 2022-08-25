import java.io.FileNotFoundException;
import java.util.Scanner;

public class Qoobee {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Qoobee() {
        this.ui = new Ui();
        ui.greet();
        this.storage = new Storage("TaskList.txt");
        try {
            tasks = new TaskList(storage);
            storage.loadFile();
            parser = new Parser(ui, tasks);
        } catch (QoobeeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(storage);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            parser.parse(input);
            if (!ui.isOn()) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Qoobee qoobee = new Qoobee();
        qoobee.run();
    }

}
