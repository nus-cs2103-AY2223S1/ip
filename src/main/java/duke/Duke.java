package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;


public class Duke extends Application {
    private Scanner sc;
    private Storage storage;
    private TaskList tasklist;
    private Ui ui = new Ui();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Sets up required objects, loads the data from the storage file.
     * @param filePath Filepath to the text file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            storage.readData();
        } catch (DukeException e) {
            this.tasklist = new TaskList();
            ui.errorMsg(e.getMessage());
        }
    }

    public Duke() {};

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        stage.show();
    }

    /**
     * Sends welcome greeting and processes inputs.
     */
    public void run() {
        TaskHandler taskHandler = new TaskHandler(tasklist, ui);
        ui.welcomeMsg();
        boolean isDone = false;
        sc = new Scanner(System.in);
        while (!isDone) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                isDone = true;
                ui.farewellMsg();
            } else if (input.equals("list")) {
                tasklist.showList();
            } else if (input.startsWith("mark ")) {
                taskHandler.markChild(input);
            } else if (input.startsWith("unmark ")) {
                taskHandler.unmarkChild(input);
            } else if (input.startsWith("delete ")) {
                taskHandler.deleteTask(input);
            } else if (input.startsWith("find ")) {
                taskHandler.findTask(input);
            } else {
                taskHandler.addTask(input);
            }
        }
        storage.writeData();
    }

    public static void main(String[] args) {
        new Duke("src/filestorage/dummylist.txt").run();
    }
}
