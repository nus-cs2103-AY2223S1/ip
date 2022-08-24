import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage();
        storage.load();
    }

    public void run() {
        this.ui.greet();
        boolean terminate = false;

        do {
            try {
                String userInput = this.ui.receiveInput();
                Command c = Parser.parseCommand(userInput);
                c.execute(taskList, ui, storage);
                terminate = c.isEnd();
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        } while (!terminate);
    }


}
