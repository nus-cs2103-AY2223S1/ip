package duke;

public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    private String workingDir = System.getProperty("user.dir");
    private String pathToDuke = workingDir + "/src/main" + "/KiwiList.txt";

    public Duke() {

        ui = new Ui();
        
        storage = new Storage(pathToDuke);

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
