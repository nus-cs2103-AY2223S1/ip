import components.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Duke extends Application {

  private ScrollPane scrollPane;
  private VBox dialogContainer;
  private TextField userInput;
  private Button sendButton;
  private Scene scene;
  private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
  private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
  private Storage storage;
  private TaskList taskList;
  private Ui ui;
  private ArrayList<String> undo;

  public Duke(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      taskList = new TaskList(storage.load());
    } catch (DukeException e) {
      ui.showLoadingError();
      taskList = new TaskList();
    }
  }

  public Duke() {
    ui = new Ui();
    storage = new Storage("duke.txt");
    try {
      taskList = new TaskList(storage.load());
    } catch (DukeException e) {
      ui.showLoadingError();
      taskList = new TaskList();
    }
    taskList.setStorage(storage);
    Parser.setUi(ui);
    Parser.setTaskList(taskList);
  }

  public void run() {
    taskList.setStorage(storage);
    Parser.setUi(ui);
    Parser.setTaskList(taskList);
    Scanner sc = new Scanner(System.in);
    ui.getPrompt(sc);
  }

  public static void main(String[] args) {
    new Duke("duke.txt").run();
  }

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

    // more code to be added here later
    //Step 2. Formatting the window to look as expected
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

    AnchorPane.setLeftAnchor(userInput, 1.0);
    AnchorPane.setBottomAnchor(userInput, 1.0);

    //Part 3. Add functionality to handle user input.
    sendButton.setOnMouseClicked((event) -> {
      handleUserInput();
    });

    userInput.setOnAction((event) -> {
      handleUserInput();
    });

    //Scroll down to the end every time dialogContainer's height changes.
    dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    taskList.setStorage(storage);
    Parser.setUi(ui);
    Parser.setTaskList(taskList);
  }

  /**
   * Iteration 1:
   * Creates a label with the specified text and adds it to the dialog container.
   *
   * @param text String containing text to add
   * @return a label with the specified text that has word wrap enabled.
   */
  private Label getDialogLabel(String text) {
    // You will need to import `javafx.scene.control.Label`.
    Label textToAdd = new Label(text);
    textToAdd.setWrapText(true);

    return textToAdd;
  }

  /**
   * Iteration 2:
   * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
   * the dialog container. Clears the user input after processing.
   */
  private void handleUserInput() {
      String userText = userInput.getText();
      String dukeText = getResponse(userInput.getText());
      dialogContainer.getChildren().addAll(
              DialogBox.getUserDialog(userText, user),
              DialogBox.getDukeDialog(dukeText, duke)
      );
      userInput.clear();
  }

  /**
   * You should have your own function to generate a response to user input.
   * Replace this stub with your completed method.
   */
  public String getResponse(String input) {
    try {
      if (input.equals("bye")) {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
      } else if (input.equals("list")) {
        return taskList.showTasks();
      } else if (input.contains("unmark")) {
        if (input.equals("unmark")) {
          return new DukeException("☹ OOPS!!! The description of a mark cannot be empty.").getMessage();
        } else {
          int num = Integer.parseInt(input.substring(7));
          return taskList.setTaskStatus(num - 1, false);
        }
      } else if (input.contains("mark")) {
        if (input.equals("mark")) {
          return new DukeException("☹ OOPS!!! The description of a mark cannot be empty.").getMessage();
        } else {
          int num = Integer.parseInt(input.substring(5));
          return taskList.setTaskStatus(num - 1, true);
        }
      } else if (input.contains("find")) {
        return taskList.findLine(input.substring(5));
      } else if (input.contains("todo")) {
        if (input.equals("todo")) {
          return new DukeException("☹ OOPS!!! The description of a todo cannot be empty.").getMessage();
        } else {
          String d1 = input.substring(5);
          Todo test = new Todo(d1);
          return taskList.add(test);
        }
      } else if (input.contains("deadline")) {
        if (input.equals("deadline")) {
          return new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.").getMessage();
        } else {
          try {
            String description = input.substring(9, input.indexOf("/") - 1);
            String var = input.substring(input.indexOf("/") + 4, input.length());
            LocalDate d1 = LocalDate.parse(var);
            Deadline test = new Deadline(description, d1);
            return taskList.add(test);
          } catch (DateTimeParseException e) {
            String description = input.substring(9, input.indexOf("/") - 1);
            String by = input.substring(input.indexOf("/") + 4, input.length());
            String time = input.substring(input.length() - 4, input.length());
            Deadline test = new Deadline(description, by);
            return taskList.add(test);
          }

        }
      } else if (input.contains("event")) {
        if (input.equals("event")) {
          return new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.").getMessage();

        } else {
          try {
            String description = input.substring(6, input.indexOf("/") - 1);
            LocalDate d1 = LocalDate.parse(input.substring(input.indexOf("/") + 4));
            Event test = new Event(description, d1);
            return taskList.add(test);

          } catch (DateTimeParseException e) {
            String description = input.substring(6, input.indexOf("/") - 1);
            String at = input.substring(input.indexOf("/") + 4);
            Event test = new Event(description, at);
            return taskList.add(test);
          }
        }
      } else if (input.contains("delete")) {
        if (input.equals("delete")) {
          return new DukeException("☹ OOPS!!! The description of a delete cannot be empty.").getMessage();

        } else {
          int removal = Integer.parseInt(input.substring(7));
          return taskList.remove(removal - 1);
        }
      } else {
        return new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()").getMessage();
      }
    } catch (DukeException e) {
      e.getMessage();
    }
    return new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-()").getMessage();
  }
}
