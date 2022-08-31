package puke;

import java.util.Scanner;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Main class
 */
public class Duke extends Application {


    private ScrollPane scrollPane;

    private VBox dialogContainer;

    private TextField userInput;

    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Puke");
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


        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        // more code to be added here later
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        Label dukeText = new Label(getResponse(input));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            return "Puke says: " + puke(receiver, Duke.d, input);
        } catch (DukeException e) {
            System.out.println("error");
            return e.toString();
        }
    }


    /**
     * storage field that handles logic to deal with hard disk
     */
    protected Storage storage;
    /**
     * taskList that stores details of the list status
     */
    protected TaskList tasklist;
    /**
     * Handles the UI interaction with user
     */
    protected Ui ui;
    /**
     * Handles user input
     */
    private Parser p;

    private Scanner receiver = new Scanner(System.in);
    protected static Duke d = new Duke();

    /**
     * Creates the chatbot
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasklist = new TaskList(storage.load());
        this.p = new Parser();
    }

    
    public static String puke(Scanner bc, Duke d,String input) throws DukeException {
        Scanner sc = new Scanner(input);
        String a = sc.next();
        if (a.equals("bye")) {
            ended = true;
            return Duke.d.ui.systemMessage(1,d, new ToDo(""));
        }
        if (a.equals("list")) {
            return Duke.d.tasklist.listTasks();
            //puke(sc,d,input);
        }
        String s = sc.nextLine();

        if (a.equals("mark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            return Duke.d.ui.taskManager("do", pos, d);
            //puke(sc,d, "");
        } else if (a.equals("unmark")) {
            int pos = Character.getNumericValue(s.charAt(1));
            return Duke.d.ui.taskManager("undo", pos, d);
            //puke(sc,d,s);
        } else if (a.equals("todo")) {
            String desc = Duke.d.p.getMessage(s, "ToDo");
            Task newTask = new ToDo(desc);
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(2, d, newTask);
            //puke(sc,d, "");
        } else if (a.equals("deadline")) {
            String desc = Duke.d.p.getMessage(s, "Deadline");
            String date = Duke.d.p.getDate(s);
            System.out.println(date);
            Task newTask = new Deadline(desc, LocalDate.parse(date));
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(2, d, newTask);
            //puke(sc,d, "");
        } else if (a.equals("event")) {
            String desc = Duke.d.p.getMessage(s, "Event");
            String date = Duke.d.p.getDate(s);
            Task newTask = new Event(desc, LocalDate.parse(date));
            Duke.d.tasklist.addIncrement(newTask);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(2, d, newTask);
            //puke(sc,d, "");
        } else if (a.equals("delete")) {
            int pos = Character.getNumericValue(s.charAt(1));
            Task temp = Duke.d.tasklist.tasks.get(pos - 1);
            Duke.d.tasklist.delete(pos - 1);
            Duke.d.storage.saveTasks(Duke.d.tasklist.tasks);
            return Duke.d.ui.systemMessage(3, d, temp);
        } else if (a.equals("find")) {
            String temp = d.p.getFindTask(s);
            return Duke.d.tasklist.find(temp);

        } else {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "OOPS!!! I'm sorry, but I dont't know what that means\n" +
                    "    ____________________________________________________________");
        }
    }

    private static boolean ended = false;
    public static void startBot() {
        try {
            puke(Duke.d.receiver, d, "");
        } catch (DukeException e) {
            System.out.println(e);
        } catch (StackOverflowError e) {
            System.out.println("goodbye");
        } finally {
            if (ended) {
                return;
            } else {
                startBot();
            }
        }
    }

    /**
     * Entry point to application
     * @param args
     */

    /*public static void main(String[] args) {
        Duke.d.ui.intro();
        Duke.startBot();
        Duke.d.receiver.close();
    } */
}
