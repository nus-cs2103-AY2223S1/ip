package duke;
import java.io.File;
import java.util.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Duke extends Application{
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /*
     * Constructor for Class Duke.
     * @param storage the storage object to read and write from Duke
     * @param taskList the taskList object
     * @param ui to print to the console
     * @param parser to parse commands from the user. 
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.ui = new UI();
        this.parser = new Parser();
    }

    /*
     * Run method for Duke, which coordinates the storage, taskList, ui and parser.
     */
    public void run() {
        this.ui.greet();

        // Read file with tasks if it exists, else create a new one.
        File taskFile = new File("./data/duke.txt");
        if (taskFile.exists()) {
            this.taskList = this.storage.taskListReader();
        } else {
            try {
                File directory = new File("./data/");
                if (!directory.exists()) {
                    directory.mkdir();
                } 
                taskFile.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Takes in inputs and passes them to the Parser if they are neither list or bye.
        // Scan for commands.
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        
        while (!command.toLowerCase().equals("bye")) {
            if (command.equals("list")){
                this.ui.printList(this.taskList);
            } else {
                try {
                    this.parser.commandParser(command, taskList, ui);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            command = sc.nextLine();
        }

        this.storage.taskListWriter(taskList);
        sc.close();
        this.ui.exit();
    
    }

    public static void main(String[] args) {
        Duke chatNUS = new Duke();
        chatNUS.run();
    }

    @Override
    public void start(Stage stage) {
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(this.dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        this.scene = new Scene(mainLayout);

        stage.setScene(this.scene);
        stage.show();
        
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMaxHeight(600.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(685, 535);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525.0);
        sendButton.setPrefWidth(55.0);

        scrollPane.setPrefSize(585, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        this.userInput.setOnAction((event) -> {
            handleUserInput();
        });



    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        ImageView userImage = new ImageView(user);
        ImageView dukeImage = new ImageView(duke);
        
        userImage.setFitHeight(100.0);
        userImage.setFitWidth(100.0);
        
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImage),
            DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        
        userInput.clear();
    }

    private String getResponse(String text) {
        return "Duke heard: " + text;
    }

}
