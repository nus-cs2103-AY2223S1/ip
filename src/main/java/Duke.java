import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private boolean end;

    public Duke() {
        this.tasks = new TaskList(Storage.read());
        this.parser = new Parser(tasks);
        this.end = false;
    }

    private void run() {
        String command;
        Scanner sc = new Scanner(System.in);

        Ui.greet();
        while(!this.end) {
            command = sc.nextLine();
            try {
                end = parser.handler(command);
            } catch (DukeException e) {
                Ui.printException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
