package duke;

import java.util.Scanner;

public class Duke {
    protected Storage storage = new Storage("data/tasks.txt");
    protected TaskList tasks = new TaskList(storage.load(), storage);
    protected Ui ui = new Ui();
    protected Parser parser = new Parser(ui, tasks);

    public void run() {
        ui.showWelcome();
        Scanner input = new Scanner(System.in);
        String next = input.nextLine();

        while (!next.equals("bye")) {
            parser.processCommand(next);
            next = input.nextLine();
        }

        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}