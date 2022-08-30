package Duke;

/**
 * The Duke.Duke class stores Storage and TaskList as parameters
 * @author LimWeiJun
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

//    @Override
//    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }

    public Duke(String filePath) {
        String[] seperates = filePath.split("/");
        storage = new Storage(seperates[0], seperates[1]);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.printErrorMessage(e.toString());
        }
    }

    public void run() {
        Parser.readLine(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
