package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke (String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError;
            tasks  = new TaskList();
        }
        this.parser = new Parser(this.tasks, this.storage);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        this.ui.start();
        Scanner sc = new Scanner(System.in);

        String message = sc.nextLine();

        while (!message.equals("bye")) {
            try {
                System.out.println(message);
                this.parser.parse(message);
            } catch (DukeException e){
                System.out.println(e);
            }
            message = sc.nextLine();
        }
        this.ui.end();
        sc.close();
    }
}
