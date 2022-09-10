package duke;

public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public Duke() {

        ui = new Ui();
        
        storage = new Storage("./src/main/KiwiList.txt");

        taskList = storage.load();

    }

    public static String getTasks() {
        return taskList.printList();
    }

    public String getResponse(String input) {

        Parser p = new Parser(taskList, ui);

        String userInput = input;

        if (!userInput.equalsIgnoreCase("Bye")) {
            return p.parse(input);
        } else {
            storage.save(taskList);
            return ui.printGoodbyeMessage();
        }
    }
}
