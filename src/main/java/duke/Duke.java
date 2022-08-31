package duke;

public class Duke {
    protected Storage storage = new Storage("data/tasks.txt");
    protected TaskList tasks = new TaskList(storage.load(), storage);
    protected Ui ui = new Ui();
    protected Parser parser = new Parser(ui, tasks);

    /**
     * Generates appropriate response from Duke.
     *
     * @param input Input from the user.
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        return parser.processCommand(input);
    }
}
