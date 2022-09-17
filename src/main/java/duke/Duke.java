package duke;

public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public Duke() {

        ui = new Ui();

        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);
        String pathToDuke = workingDir + "/out/KiwiList.txt";
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
