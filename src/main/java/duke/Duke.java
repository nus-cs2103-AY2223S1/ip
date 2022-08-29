package duke;

import java.util.Scanner;

public class Duke {
    protected Storage storage = new Storage("data/tasks.txt");
    protected TaskList tasks = new TaskList(storage.load(), storage);
    protected Ui ui = new Ui();
    protected Parser parser = new Parser(ui, tasks);

    /**
     * Runs the main program of Duke.
     */
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

    /**
     * Generates appropriate response from Duke.
     *
     * @param input Input from the user.
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        return parser.processCommand(input);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}